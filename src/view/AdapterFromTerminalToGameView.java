package view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import model.command.Command;
import model.core.*;
import model.entity.Player;
import model.inventory.Item;
import model.world.Zone;

import java.io.IOException;
import java.util.List;


/**
 * Adaptateur entre l'interface terminal Lanterna (Screen) et l'interface GameView.
 *
 * Suit le patron de conception Adaptateur : permet d'utiliser terminal
 */
public class AdapterFromTerminalToGameView implements GameView {

    private Screen screen;

    public AdapterFromTerminalToGameView(Screen screen) {
        this.screen = screen;
    }
    public Screen getScreen() {
        return screen;
    }

    @Override
    public void update() {
        GameEngine engine = GameEngine.getInstance();
        if (engine.getCurrentState() instanceof MenuState) {
            displayMainMenu();
        } else if (engine.getCurrentState() instanceof PlayingState) {
            displayGame();
        } else if (engine.getCurrentState() instanceof PauseState) {
            displayPauseMenu();
        } else if (engine.getCurrentState() instanceof InventoryState) {
            displayInventory();
        }
    }

    @Override
    public void displayGame() {
        try {
            screen.clear();
            TextGraphics tg = screen.newTextGraphics();

            int cols = screen.getTerminalSize().getColumns();
            int rows = screen.getTerminalSize().getRows();

            GameEngine engine = GameEngine.getInstance();
            Game game = engine.getCurrentGame();
            if (game == null) return;

            Player player = game.getPlayer();
            Zone zone = player.getPosition().getZone();
            List<Command> actions = game.getActions();
            int selected = game.getSelectedIndex();

            // Lieu actuel
            String zoneName = "Vous êtes dans : " + zone.getName();
            tg.setForegroundColor(TextColor.ANSI.GREEN);
            tg.putString(2, 1, zoneName);

            int line = 3;

            // Liste des actions
            tg.setForegroundColor(TextColor.ANSI.YELLOW);
            tg.putString(2, line++, "-- Actions --");

            if (actions.isEmpty()) {
                tg.setForegroundColor(TextColor.ANSI.WHITE);
                tg.putString(4, line++, "Aucune action disponible.");
            } else {
                for (int i = 0; i < actions.size(); i++) {
                    if (i == selected) {
                        tg.setForegroundColor(TextColor.ANSI.GREEN);
                        tg.putString(4, line++, "> " + actions.get(i).getLabel());
                    } else {
                        tg.setForegroundColor(TextColor.ANSI.WHITE);
                        tg.putString(4, line++, "  " + actions.get(i).getLabel());
                    }
                }
            }

            // Message de feedback
            String message = game.getMessage();
            if (message != null) {
                tg.setForegroundColor(TextColor.ANSI.RED);
                tg.putString(2, line + 1, message);
                game.setMessage(null);
            }

            // Instructions
            String hint = "Haut/Bas: naviguer | Entree: valider | Echap: pause | F1: inventaire";
            tg.setForegroundColor(TextColor.ANSI.CYAN);
            tg.putString((cols - hint.length()) / 2, rows - 2, hint);

            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException("Erreur d'affichage du jeu", e);
        }
    }

    @Override
    public void displayMainMenu() {
        try {
            screen.clear();
            TextGraphics tg = screen.newTextGraphics();

            int cols = screen.getTerminalSize().getColumns();
            int rows = screen.getTerminalSize().getRows();

            // Titre du jeu
            String title = "=== SMH - Le Jeu ===";
            tg.setForegroundColor(TextColor.ANSI.GREEN);
            tg.putString((cols - title.length()) / 2, 
            rows / 2 - 4, title);

            // Options du menu
            GameEngine engine = GameEngine.getInstance();
            MenuState menuState = (MenuState) engine.getCurrentState();
            MenuOption[] options = menuState.getOptions();
            int selected = menuState.getSelectedIndex();

            for (int i = 0; i < options.length; i++) {
                String label;
                if (i == selected) {
                    label = "> " + options[i].getLabel() + " <";
                    tg.setForegroundColor(TextColor.ANSI.GREEN);
                } else {
                    label = "  " + options[i].getLabel();
                    tg.setForegroundColor(TextColor.ANSI.WHITE);
                }
                tg.putString((cols - label.length()) / 2, rows / 2 - 1 + i * 2, label);
            }

            // Instructions
            String hint = "Flêches haut/bas pour naviguer, Entree pour valider";
            tg.setForegroundColor(TextColor.ANSI.CYAN);
            tg.putString((cols - hint.length()) / 2, rows / 2 + 5, hint);

            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException("Erreur d'affichage du menu", e);
        }
    }

    @Override
    public void displayPauseMenu() {
        try {
            screen.clear();
            TextGraphics tg = screen.newTextGraphics();

            int cols = screen.getTerminalSize().getColumns();
            int rows = screen.getTerminalSize().getRows();

            // Titre
            String title = "=== Pause ===";
            tg.setForegroundColor(TextColor.ANSI.YELLOW);
            tg.putString((cols - title.length()) / 2, rows / 2 - 4, title);

            // Options
            GameEngine engine = GameEngine.getInstance();
            PauseState pauseState = (PauseState) engine.getCurrentState();
            PauseOption[] options = pauseState.getOptions();
            int selected = pauseState.getSelectedIndex();

            for (int i = 0; i < options.length; i++) {
                String label;
                if (i == selected) {
                    label = "> " + options[i].getLabel() + " <";
                    tg.setForegroundColor(TextColor.ANSI.YELLOW);
                } else {
                    label = "  " + options[i].getLabel();
                    tg.setForegroundColor(TextColor.ANSI.WHITE);
                }
                tg.putString((cols - label.length()) / 2, rows / 2 - 1 + i * 2, label);
            }

            // Instructions
            String hint = "Flêches haut/bas pour naviguer, Entree pour valider";
            tg.setForegroundColor(TextColor.ANSI.CYAN);
            tg.putString((cols - hint.length()) / 2, rows / 2 + 5, hint);

            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException("Erreur d'affichage du menu pause", e);
        }
    }

    @Override
    public void displayInventory() {
        try {
            screen.clear();
            TextGraphics tg = screen.newTextGraphics();

            int cols = screen.getTerminalSize().getColumns();
            int rows = screen.getTerminalSize().getRows();

            // Titre
            String title = "=== Inventaire ===";
            tg.setForegroundColor(TextColor.ANSI.YELLOW);
            tg.putString((cols - title.length()) / 2, 1, title);

            // Items dans l'inventaire
            GameEngine engine = GameEngine.getInstance();
            InventoryState inventoryState = (InventoryState) engine.getCurrentState();
            List<Item> options = inventoryState.getInventory().getItems();
            int selected = inventoryState.getSelectedIndex();

            for (int i = 0; i < options.size(); i++) {
                String label;
                if (i == selected) {
                    label = "> " + options.get(i).getName() + " <";
                    tg.setForegroundColor(TextColor.ANSI.YELLOW);

                    int maxWidth = cols / 4;
                    int startX = 2;
                    int startY = 1;
                    int currentLine = startY + 1;

                    String className = "[" + options.get(i).getClass().getSimpleName() + "]";
                    tg.setForegroundColor(TextColor.ANSI.CYAN);
                    tg.putString(startX, startY, className);

                    tg.setForegroundColor(TextColor.ANSI.WHITE);
                    String description = options.get(i).getDescription();

                    if (description != null) {

                        for (int j = 0; j < description.length(); j += maxWidth) {
                            int end = Math.min(j + maxWidth, description.length());
                            String segment = description.substring(j, end);

                            tg.putString(startX, currentLine, segment);
                            currentLine++;

                            if (currentLine >= rows - 3) break;
                        }
                    }

                    tg.setForegroundColor(TextColor.ANSI.GREEN);
                    tg.putString(startX, currentLine++, "Entree pour " + options.get(i).getStringUtilisation());
                    tg.setForegroundColor(TextColor.ANSI.RED);
                    tg.putString(startX, currentLine++, "F2 pour jeter l'objet ");

                    tg.setForegroundColor(TextColor.ANSI.YELLOW);

                } else {
                    label = "  " + options.get(i).getName();
                    tg.setForegroundColor(TextColor.ANSI.WHITE);
                }
                tg.putString((cols - label.length()) / 2, i+2, label);
            }

            // Instructions
            String hint = "Flêches haut/bas pour naviguer, Entree pour valider";
            tg.setForegroundColor(TextColor.ANSI.CYAN);
            tg.putString((cols - hint.length()) / 2, rows / 2 + 5, hint);

            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException("Erreur d'affichage du menu pause", e);
        }
    }
    public void displaySelectedDescriptionItem(){
        try{
            System.out.println("passage ici");
            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException("Erreur d'affichage du menu pause", e);
        }
    }
}
