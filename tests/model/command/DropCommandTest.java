package tests.model.command;

import model.command.DropCommand;
import model.entity.Player;
import model.inventory.Weapon;
import model.world.Position;
import model.world.Street;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DropCommandTest {

    private Player hero;
    private Street forest;
    private Weapon sword;

    @BeforeEach
    public void setUp() {
        hero = new Player("Arthur");
        forest = new Street("Foret", 25, 25);
        sword = new Weapon("Epee en bois", "Petite epee", 5);

        hero.setPosition(new Position(forest, 3, 5));
        hero.getInventory().addItem(sword);
    }

    @Test
    public void testExecuteDropsItemInZone() {
        assertTrue(hero.getInventory().getItems().contains(sword));
        assertFalse(forest.getItems().contains(sword));

        DropCommand dropCmd = new DropCommand(hero, sword);
        dropCmd.execute();

        assertFalse(hero.getInventory().getItems().contains(sword), "L'epee devrait etre retiree de l'inventaire du joueur.");
        assertTrue(forest.getItems().contains(sword), "L'epee devrait se trouver sur le sol de la zone.");
    }

    @Test
    public void testExecuteDropsItemInSameCellTwice() {
        Weapon sword2 = new Weapon("Epee en fer", "Lourde epee", 10);
        hero.getInventory().addItem(sword2);

        DropCommand dropCmd1 = new DropCommand(hero, sword);
        dropCmd1.execute();

        DropCommand dropCmd2 = new DropCommand(hero, sword2);
        dropCmd2.execute();

        assertTrue(hero.getInventory().getItems().contains(sword2), "L'epee 2 doit rester dans l'inventaire");
        assertFalse(forest.getItems().contains(sword2), "L'epee 2 ne doit pas etre sur le sol");

        assertTrue(forest.getItems().contains(sword), "L'epee 1 doit toujours etre au sol");
    }
}