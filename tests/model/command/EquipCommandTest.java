package model.command;

import model.entity.Player;
import model.inventory.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EquipCommandTest {

    private Player player;
    private Weapon weapon;

    @BeforeEach
    public void setUp() {
        player = new Player("Arthur");
        weapon = new Weapon("Excalibur", "Epee legendaire", 50);
        player.getInventory().addItem(weapon);
    }

    @Test
    public void testGetLabel() {
        EquipCommand command = new EquipCommand(player, weapon);

        assertEquals("Equiper Excalibur", command.getLabel());
    }

    @Test
    public void testExecuteEquipCommand() {
        EquipCommand command = new EquipCommand(player, weapon);

        assertNull(player.getEquippedWeapon());

        command.execute();

        assertFalse(player.getInventory().getItems().contains(weapon));
        assertEquals(weapon, player.getEquippedWeapon());
    }
}