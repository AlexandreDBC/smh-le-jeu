package model.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.command.Command;
import model.entity.Player;
import model.world.Campus;

public class Game {
    private final Player player;
    private final Campus campus;
    private final Level level;

    private List<Command> actions = new ArrayList<>();
    private int selectedIndex = 0;
    private String message = null;

    public Game(Player player, Campus campus, Level level) {
        this.player = Objects.requireNonNull(player, "player cannot be null");
        this.campus = Objects.requireNonNull(campus, "campus cannot be null");
        this.level = Objects.requireNonNull(level, "level cannot be null");
    }

    public Game(Player player, Campus campus) {
        this(player, campus, Level.EASY);
    }

    public static GameBuilder builder() {
        return new GameBuilder();
    }

    public Player getPlayer() {
        return player;
    }

    public Campus getCampus() {
        return campus;
    }

    public Level getLevel() {
        return level;
    }

    public List<Command> getActions() {
        return actions;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public void setActions(List<Command> actions) {
        this.actions = actions;
        this.selectedIndex = 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void start(){

    }

    public void update(){

    }

    public boolean isFinished(){
        return false;
    }
}
