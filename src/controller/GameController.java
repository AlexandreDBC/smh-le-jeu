package controller;

import com.googlecode.lanterna.input.KeyType;
import model.command.ChangeZoneCommand;
import model.command.Command;
import model.command.ConsumeCommand;
import model.command.DropCommand;
import model.command.EquipCommand;
import model.command.PickUpCommand;
import model.core.*;
import model.inventory.*;
import model.entity.Player;
import model.world.CampusFactory;
import model.world.Cell;
import model.world.Zone;
import view.GameView;

import java.util.ArrayList;
import java.util.List;


public class GameController {
    private GameEngine gameEngine;
    private GameView gameView;

    public GameController(GameEngine gameEngine, GameView gameView) {
        this.gameEngine = gameEngine;
        this.gameView = gameView;
    }

    public GameView getGameView() {
        return gameView;
    }

    public void startGame() {
        Player player = new Player("Joueur");
        Game game = Game.builder()
                .withPlayer(player)
                .withCampus(CampusFactory.createEnsimag(player))
                .withLevel(Level.EASY)
                .build();
        gameEngine.setCurrentGame(game);
        rebuildActions();
        gameEngine.setState(gameEngine.getPlayingState());
    }

    public void rebuildActions() {
        Game game = gameEngine.getCurrentGame();
        if (game == null) return;

        List<Command> commands = new ArrayList<>();
        Player player = game.getPlayer();
        Zone zone = player.getPosition().getZone();

        for (Zone connected : zone.getConnectedZones()) {
            commands.add(new ChangeZoneCommand(player, connected));
        }

        for (Cell cell : zone.getCells()) {
            commands.add(new PickUpCommand(player, cell));
        }

        game.setActions(commands);
    }

    public void handleInput(String input) {
    }

    public void pauseGame() {
        gameEngine.setState(gameEngine.getPauseState());
    }

    public void resumeGame() {
        rebuildActions();
        gameEngine.setState(gameEngine.getPlayingState());
    }

    public void showInventory() { gameEngine.setState(gameEngine.getInventoryState());}

    public void quitToMenu() {
        gameEngine.setCurrentGame(null);
        gameEngine.setState(gameEngine.getMenuState());
    }

    public boolean handlePlayingInput(KeyType keyType) {
        Game game = gameEngine.getCurrentGame();
        if (game == null) return true;
        List<Command> actions = game.getActions();
        int idx = game.getSelectedIndex();

        switch (keyType) {
            case ArrowUp:
                if (!actions.isEmpty()) {
                    game.setSelectedIndex((idx - 1 + actions.size()) % actions.size());
                }
                gameEngine.update();
                return true;
            case ArrowDown:
                if (!actions.isEmpty()) {
                    game.setSelectedIndex((idx + 1) % actions.size());
                }
                gameEngine.update();
                return true;
            case Enter:
                if (!actions.isEmpty()) {
                    actions.get(idx).execute();
                    rebuildActions();
                    gameEngine.update();
                }
                return true;
            case Escape:
                pauseGame();
                return true;
            case F1:
                showInventory();
                return true;
            default:
                return true;
        }
    }

    public boolean handlePauseInput(KeyType keyType) {
        PauseState pauseState = (PauseState) gameEngine.getCurrentState();
        switch (keyType) {
            case ArrowUp:
                pauseState.previousOption();
                gameEngine.update();
                return true;
            case ArrowDown:
                pauseState.nextOption();
                gameEngine.update();
                return true;
            case Enter:
                return handlePauseSelection(pauseState.getSelectedOption());
            case Escape:
                resumeGame();
                return true;
            default:
                return true;
        }
    }

    private boolean handlePauseSelection(PauseOption option) {
        switch (option) {
            case REPRENDRE:
                resumeGame();
                return true;
            case MENU:
                quitToMenu();
                return true;
            default:
                return true;
        }
    }

    public boolean handleInventoryInput(KeyType keyType) {
        InventoryState inventoryState = (InventoryState) gameEngine.getCurrentState();
        switch (keyType) {
            case ArrowUp:
                inventoryState.previousOption();
                gameEngine.update();
                return true;
            case ArrowDown:
                inventoryState.nextOption();
                gameEngine.update();
                return true;
            case Enter:
                if(inventoryState.getInventory().getItems().isEmpty()) {
                    return true;
                }
                return handleInventorySelection(inventoryState.getSelectedOption());
            case Escape:
                resumeGame();
                return true;
            case F2:
                if(inventoryState.getInventory().getItems().isEmpty()) {
                    return true;
                }
                new DropCommand(this.gameEngine.getCurrentGame().getPlayer(), inventoryState.getSelectedOption()).execute();
                this.gameEngine.update();
                return true;
            default:
                return true;
        }
    }

    private boolean handleInventorySelection(Item item) {
        if(item instanceof Equipable){
            new EquipCommand(this.gameEngine.getCurrentGame().getPlayer(), (Equipable) item).execute();
        }
        else if(item instanceof Food){
            new ConsumeCommand(this.gameEngine.getCurrentGame().getPlayer(), (Food) item).execute();
        }
        else if(item instanceof Key){
            // nothing to do
        }
        else
            return false;
        this.gameEngine.update();
        return true;
    }

    // Pour les tests

    public GameEngine getGameEngine() {
        return gameEngine;
    }
}
