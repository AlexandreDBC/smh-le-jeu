package tests.model.command;

import model.command.ChangeZoneCommand;
import model.entity.Player;
import model.inventory.Key;
import model.world.Building;
import model.world.Position;
import model.world.Street;
import model.world.Zone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangeZoneCommandTest {

    private Player player;
    private Zone startingZone;
    private Position startingPosition;

    @BeforeEach
    public void setUp() {
        this.player = new Player("Test");
        this.startingZone = new Street("Zone Depart", 20, 20);
        this.startingPosition = new Position(startingZone, 10, 10);
        this.player.setPosition(startingPosition);
    }

    @Test
    public void testGetLabel() {
        Street destination = new Street("Foret", 10, 10);
        ChangeZoneCommand command = new ChangeZoneCommand(player, destination);

        assertEquals("Aller dans Foret", command.getLabel());
    }

    @Test
    public void testExecuteToNormalZone() {
        Street destination = new Street("Plaine", 30, 20);
        ChangeZoneCommand command = new ChangeZoneCommand(this.player, destination);

        command.execute();

        assertEquals(destination, this.player.getPosition().getZone());
    }

    @Test
    public void testExecuteToUnlockedBuildingWithoutKey() {
        Building unlockedBuilding = new Building("Maison", 10, 10, true);
        unlockedBuilding.unlock();
        ChangeZoneCommand command = new ChangeZoneCommand(player, unlockedBuilding);

        command.execute();

        assertEquals(unlockedBuilding, player.getPosition().getZone());
    }

    @Test
    public void testExecuteToUnLockedBuildingWithKey() {
        Building lockedBuilding = new Building("Donjon", 20, 20, true);
        Key correctKey = new Key("Cle Donjon", "Cle en fer", lockedBuilding);
        lockedBuilding.setKey(correctKey);

        ChangeZoneCommand command = new ChangeZoneCommand(player, lockedBuilding);

        command.execute();

        assertTrue(lockedBuilding.isLocked());
    }

    @Test
    public void testExecuteToUnLockedBuildingWithWrongKey() {
        Building lockedBuildingWrong = new Building("Donjon", 20, 20, true);

        Building lockedBuilding = new Building("Donjon", 20, 20, true);
        Key correctKey = new Key("Cle Donjon", "Cle en fer", lockedBuilding); // Variable non utilisée par la commande ci-dessous

        ChangeZoneCommand command = new ChangeZoneCommand(player, lockedBuildingWrong);

        command.execute();

        assertTrue(lockedBuilding.isLocked());
    }

    @Test
    public void testExecuteTryToUnLockedBuildingWithoutAnyKey() {
        Building lockedBuildingWrong = new Building("Donjon", 20, 20, true);

        ChangeZoneCommand command = new ChangeZoneCommand(player, lockedBuildingWrong);

        command.execute();

        assertTrue(lockedBuildingWrong.isLocked());
    }
}