package controller;

import com.googlecode.lanterna.input.KeyType;

import model.core.GameEngine;
import model.core.MenuOption;
import model.core.MenuState;

public class MenuController {

    private final GameController gameController;

    public MenuController(GameController gameController) {
        this.gameController = gameController;
    }

    private MenuState getMenuState() {
        return (MenuState) GameEngine.getInstance().getCurrentState();
    }

    /**
     * Gère une entrée clavier dans le menu.
     * @return true si le menu doit continuer, false si on doit en sortir.
     */
    public boolean handleInput(KeyType keyType) {
        boolean running;
        switch (keyType) {
            case ArrowUp:
                getMenuState().previousOption();
                running = true;
                break;
            case ArrowDown:
                getMenuState().nextOption();
                running = true;
                break;
            case Enter:
                running = handleSelection(getMenuState().getSelectedOption());
                break;
            case Escape:
                running = false;
                break;
            default:
                running = true;
                break;
        }
        if (running && GameEngine.getInstance().getCurrentState() instanceof MenuState) {
            GameEngine.getInstance().update();
        }
        return running;
    }

    private boolean handleSelection(MenuOption option) {
        switch (option) {
            case NOUVELLE_PARTIE:
                newGame();
                return true;
            case PARAMETRES:
                configureSettings();
                return true;
            case QUITTER:
                exit();
                return false;
            default:
                return true;
        }
    }

    public void newGame() {
        gameController.startGame();
    }

    public void configureSettings() {
        // TODO: ouvrir l'écran de paramètres
    }

    public void exit() {
        // TODO: nettoyage avant fermeture si nécessaire
    }
}
