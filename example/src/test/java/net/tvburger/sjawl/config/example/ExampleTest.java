package net.tvburger.sjawl.config.example;

import net.tvburger.sjawl.config.ConfigurationProvider;
import org.junit.Test;

public final class ExampleTest {

    @Test
    public void testDemo() throws Exception {
        ConfigurationProvider provider = ConfigurationProvider.Singleton.get();
        ExampleConfiguration configuration = provider.getConfiguration(ExampleConfiguration.class);
        printConfiguration(configuration);
    }

    private void printConfiguration(ExampleConfiguration configuration) {
        System.out.println("ExampleConfiguration:");
        System.out.println("- players:");
        for (ExampleConfiguration.Player player : configuration.getPlayers().values()) {
            System.out.println(String.format("    - %s: %d", player.getName(), player.getScore()));
        }
        System.out.println("- max game length: " + configuration.getMaxGameLength());
        System.out.println("- is super game: " + configuration.isSuperMatch());
    }

}
