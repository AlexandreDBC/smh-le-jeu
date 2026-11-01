package model.core;

import model.inventory.Inventory;
import utils.observer.AbstractListenable;

public class GameEngine extends AbstractListenable{

    private static GameEngine instance;

    private GameState currentState;

    private Game currentGame;

    private MenuState menuState;
    private PlayingState playingState;
    private PauseState pauseState;
    private GameOverState gameOverState;
    private InventoryState inventoryState;

    private GameEngine() {
        menuState = new MenuState();
        playingState = new PlayingState();
        pauseState = new PauseState();
        gameOverState = new GameOverState();
        inventoryState = new InventoryState(new Inventory());
        this.setState(menuState);
    }

    public static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    public void setState(GameState state) {
        if (currentState != null) {
            currentState.exit();
        }
        currentState = state;
        currentState.enter();
        notifyListeners();
    }

    public void update() {
        if (currentState != null) {
            currentState.update();
            notifyListeners();
        }
    }

    public void setCurrentGame(Game game) {
        this.currentGame = game;
        if (game != null) {
            inventoryState.setInventory(game.getPlayer().getInventory());
        }
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public MenuState getMenuState() {
        return menuState;
    }

    public PlayingState getPlayingState() {
        return playingState;
    }

    public InventoryState getInventoryState() {
        return inventoryState;
    }

    public PauseState getPauseState() {
        return pauseState;
    }

    public GameOverState getGameOverState() {
        return gameOverState;
    }
}
