package model.core;

import model.entity.Player;
import model.world.Campus;
import model.world.Position;

public class GameBuilder {
    private Player player;
    private Campus campus;
    private Level level = Level.EASY;

    public GameBuilder withPlayer(Player player) {
        this.player = player;
        return this;
    }

    public GameBuilder withPlayer(String name) {
        this.player = new Player(name);
        return this;
    }
    public GameBuilder withCampus(Campus campus) {
        this.campus = campus;
        return this;
    }

    public GameBuilder withLevel(Level level) {
        if (level != null) {
            this.level = level;
        }
        return this;
    }

    public Game build() {
        return new Game(player, campus, level);
    }
}
