package net.tvburger.sjawl.config.example;

import net.tvburger.sjawl.config.InvalidSettingException;
import net.tvburger.sjawl.config.MissingSettingException;
import net.tvburger.sjawl.config.Specification;
import net.tvburger.sjawl.config.impl.AbstractConfigurationParser;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ExampleConfigurationParser extends AbstractConfigurationParser<ExampleConfiguration> {

    public static final class Constants {

        public static final List<String> FIELD_NAME_PLAYERS = Collections.singletonList("players");
        public static final List<String> FIELD_NAME_MAX_GAME_LENGTH = Collections.singletonList("max_length");
        public static final List<String> FIELD_NAME_IS_SUPER_GAME = Collections.singletonList("super");

        public static final int DEFAULT_MAX_GAME_LENGTH = 3600;
        public static final boolean DEFAULT_IS_SUPER_GAME = false;

        private Constants() {
        }

    }

    public ExampleConfigurationParser() {
        super(ExampleConfiguration.class);
    }

    @Override
    public ExampleConfiguration parseConfiguration(Specification specification) throws MissingSettingException, InvalidSettingException {
        return new ExampleConfiguration(
                parsePlayers(specification),
                parseMaxGameLength(specification),
                parseIsSuperGame(specification));
    }

    private Map<String, ExampleConfiguration.Player> parsePlayers(Specification specification) throws MissingSettingException, InvalidSettingException {
        Map<String, ExampleConfiguration.Player> players = new LinkedHashMap<>();
        for (List<String> playerField : specification.getDefined(Constants.FIELD_NAME_PLAYERS)) {
            ExampleConfiguration.Player player = parsePlayer(playerField, specification);
            players.put(player.getName(), player);
        }
        return players;
    }

    private ExampleConfiguration.Player parsePlayer(List<String> playerField, Specification specification) throws MissingSettingException, InvalidSettingException {
        int score = ParserUtil.parseInt(playerField, specification);
        String name = playerField.get(playerField.size() - 1);
        return new ExampleConfiguration.Player(name, score);
    }

    private int parseMaxGameLength(Specification specification) throws MissingSettingException, InvalidSettingException {
        return specification.hasSetting(Constants.FIELD_NAME_MAX_GAME_LENGTH) ?
                ParserUtil.parseInt(Constants.FIELD_NAME_MAX_GAME_LENGTH, specification) :
                Constants.DEFAULT_MAX_GAME_LENGTH;
    }

    private boolean parseIsSuperGame(Specification specification) throws MissingSettingException, InvalidSettingException {
        return specification.hasSetting(Constants.FIELD_NAME_IS_SUPER_GAME) ?
                ParserUtil.parseBoolean(Constants.FIELD_NAME_IS_SUPER_GAME, specification) :
                Constants.DEFAULT_IS_SUPER_GAME;
    }

}
