package view;

import utils.observer.Listener;

public interface GameView extends Listener {
    void displayGame();
    void displayMainMenu();
    void displayPauseMenu();
    void displayInventory();
}
