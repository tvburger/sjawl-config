package net.tvburger.sjawl.config.example;

import net.tvburger.sjawl.config.Configuration;

import java.io.Serializable;
import java.util.Map;

public final class ExampleConfiguration implements Configuration, Serializable {

    public static class Player implements Serializable {

        private final String name;
        private final int score;

        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

    }

    private final Map<String, Player> players;
    private final int maxGameLength;
    private final boolean isSuperMatch;

    public ExampleConfiguration(Map<String, Player> players, int maxGameLength, boolean isSuperMatch) {
        this.players = players;
        this.maxGameLength = maxGameLength;
        this.isSuperMatch = isSuperMatch;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public int getMaxGameLength() {
        return maxGameLength;
    }

    public boolean isSuperMatch() {
        return isSuperMatch;
    }

}
