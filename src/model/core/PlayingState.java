package model.core;

public class PlayingState implements GameState {

    @Override
    public void enter() {
        Game game = GameEngine.getInstance().getCurrentGame();
        if (game != null) {
            game.start();
        }
    }

    @Override
    public void update() {
        Game game = GameEngine.getInstance().getCurrentGame();
        if (game != null) {
            game.update();
        }
    }

    @Override
    public void exit() {
    }
}
