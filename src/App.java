import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import controller.GameController;
import controller.MenuController;
import model.core.*;
import view.AdapterFromTerminalToGameView;

public class App {
    public static void main(String[] args) throws Exception {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);

        GameEngine engine = GameEngine.getInstance();
        AdapterFromTerminalToGameView view = new AdapterFromTerminalToGameView(screen);
        engine.addListener(view);

        engine.update();

        GameController gameController = new GameController(engine, view);
        MenuController menuController = new MenuController(gameController);

        boolean running = true;
        while (running) {
            KeyStroke keyStroke = screen.readInput();
            if (keyStroke.getKeyType() == KeyType.EOF) {
                break;
            }

            if (engine.getCurrentState() instanceof MenuState) {
                running = menuController.handleInput(keyStroke.getKeyType());
            } else if (engine.getCurrentState() instanceof PlayingState) {
                running = gameController.handlePlayingInput(keyStroke.getKeyType());
            } else if (engine.getCurrentState() instanceof PauseState) {
                running = gameController.handlePauseInput(keyStroke.getKeyType());
            }
            else if (engine.getCurrentState() instanceof InventoryState) {
                running = gameController.handleInventoryInput(keyStroke.getKeyType());
            }
        }

        screen.stopScreen();
    }
}
