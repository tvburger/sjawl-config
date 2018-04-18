package net.tvburger.sjawl.config.example;

import net.tvburger.sjawl.config.*;

import java.util.*;

public final class ExampleSpecificationParser {

    public static final class Constants {

        public static final List<String> FIELD_NAME_PLAYERS = Collections.singletonList("players");
        public static final List<String> FIELD_NAME_MAX_GAME_LENGTH = Collections.singletonList("max_length");
        public static final List<String> FIELD_NAME_IS_SUPER_GAME = Collections.singletonList("super");

        public static final int DEFAULT_MAX_GAME_LENGTH = 3600;
        public static final boolean DEFAULT_IS_SUPER_GAME = false;

        private Constants() {
        }

    }

    public ExampleConfiguration parseExampleConfiguration(Specification specification) throws MissingSettingException, InvalidSettingException {
        return new ExampleConfiguration(
                parsePlayers(specification),
                parseMaxGameLength(specification),
                parseIsSuperGame(specification));
    }

    public Map<String, ExampleConfiguration.Player> parsePlayers(Specification specification) throws MissingSettingException, InvalidSettingException {
        Map<String, ExampleConfiguration.Player> players = new LinkedHashMap<>();
        for (List<String> playerField : specification.getDefined(Constants.FIELD_NAME_PLAYERS)) {
            ExampleConfiguration.Player player = parsePlayer(playerField, specification);
            players.put(player.getName(), player);
        }
        return players;
    }

    public ExampleConfiguration.Player parsePlayer(List<String> playerField, Specification specification) throws MissingSettingException, InvalidSettingException {
        int score = ParserUtil.parseInt(playerField, specification);
        String name = playerField.get(playerField.size() - 1);
        return new ExampleConfiguration.Player(name, score);
    }

    public int parseMaxGameLength(Specification specification) throws MissingSettingException, InvalidSettingException {
        return specification.hasSetting(Constants.FIELD_NAME_MAX_GAME_LENGTH) ?
                ParserUtil.parseInt(Constants.FIELD_NAME_MAX_GAME_LENGTH, specification) :
                Constants.DEFAULT_MAX_GAME_LENGTH;
    }

    public boolean parseIsSuperGame(Specification specification) throws MissingSettingException, InvalidSettingException {
        return specification.hasSetting(Constants.FIELD_NAME_IS_SUPER_GAME) ?
                ParserUtil.parseBoolean(Constants.FIELD_NAME_IS_SUPER_GAME, specification) :
                Constants.DEFAULT_IS_SUPER_GAME;
    }

}
