package net.tvburger.sjawl.config.example;

import net.tvburger.sjawl.config.ConfigurationProvider;
import org.junit.Test;

public final class ExampleTest {

    ////////
    // HERE IS THE SEPARATION OF CONCERNS:
    // -----------------------------------
    // Depending on the binding by the RUNTIME of net.tvburger.sjawl.config.ext.SpecificationLoader the appropriate
    // source for the configuration is used.
    //
    // The INTRODUCER of the ExampleConfiguration is responsible for binding the ConfigurationFactory.
    //
    // We as USER don't determine anything, we only obtain the Configuration we need.
    ////

    @Test
    public void testDemo() throws Exception {
        ConfigurationProvider provider = ConfigurationProvider.Singleton.get();
        ExampleConfiguration configuration = provider.getConfiguration(ExampleConfiguration.class);
        System.out.println("Players: " + configuration.getPlayers());
        System.out.println("MaxGameLength: " + configuration.getMaxGameLength());
        System.out.println("IsSuperGame: " + configuration.isSuperMatch());
    }

}
