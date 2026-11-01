package model.command;

import model.entity.Player;
import model.inventory.Weapon;
import model.world.Position;
import model.world.Street;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PickUpCommandTest {

    private Player player;
    private Weapon weapon;
    private Weapon weapon2;
    private Street rue;

    @BeforeEach
    public void setUp() {
        player = new Player("Arthur");
        rue = new Street("rue", 30, 30);
        player.setPosition(new Position(rue, 20, 20));
        weapon = new Weapon("Excalibur", "Epee legendaire", 50);
        weapon2 = new Weapon("Excalibur2", "Epee legendaire2", 250);
        rue.getCellAt(18, 6).addItem(weapon);
        rue.getCellAt(6, 15).addItem(weapon);
        rue.getCellAt(7, 2).addItem(weapon2);
    }

    @Test
    public void testGetLabel() {
        PickUpCommand command = new PickUpCommand(player, rue.getCellAt(18, 6));

        assertEquals("Ramasser Excalibur", command.getLabel());
    }

    @Test
    public void testExecuteSuccess() {
        PickUpCommand command = new PickUpCommand(player, rue.getCellAt(7, 2));

        command.execute();

        assertTrue(player.getInventory().getItems().contains(weapon2));
        assertNull(rue.getCellAt(7, 2).getItem());
    }

    @Test
    public void testExecuteInventoryFull() {
        while (player.getInventory().getItems().size() < player.getInventory().maxCapacity) {
            player.getInventory().addItem(new Weapon("Objet", "Description", 0));
        }

        PickUpCommand command = new PickUpCommand(player, rue.getCellAt(18, 6));

        try {
            command.execute();
        } catch (Exception ignored) {
        }

        assertFalse(player.getInventory().getItems().contains(weapon));
        assertEquals(weapon, rue.getCellAt(18, 6).getItem());
    }
}