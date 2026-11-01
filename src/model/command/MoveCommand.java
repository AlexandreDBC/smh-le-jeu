package model.command;

import model.entity.Player;
import utils.Direction;

public class MoveCommand implements Command {
    private Player player;
    private Direction direction;

    public MoveCommand(Player player, Direction direction) {
        this.player = player;
        this.direction = direction;
    }

    @Override
    public String getLabel() {
        return "Se deplacer " + direction.name();
    }

    @Override
    public void execute() {
        //player.move(direction);
    }

}
