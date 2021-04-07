package com.github.ericliucn.pvpswitch;

import com.github.ericliucn.pvpswitch.command.TestCommand;
import com.github.ericliucn.pvpswitch.manipulators.ImmutablePVP;
import com.github.ericliucn.pvpswitch.manipulators.PVPData;
import com.github.ericliucn.pvpswitch.manipulators.PVPDataBuilder;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataRegistration;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.event.game.GameRegistryEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.util.TypeTokens;

@Plugin(
        id = "pvpswitch",
        name = "Pvpswitch",
        description = "Pvpswitch",
        authors = {
                "Eric12324"
        }
)
public class Main {

    public static Main instance;

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        instance = this;
        Sponge.getCommandManager().register(this, new TestCommand(), "test");
    }

    @Listener
    public void onInit(GameInitializationEvent event){
        DataRegistration.builder()
                .dataClass(PVPData.class)
                .dataImplementation(PVPData.class)
                .immutableClass(ImmutablePVP.class)
                .immutableImplementation(ImmutablePVP.class)
                .builder(new PVPDataBuilder())
                .id("pvp_data")
                .name("PVP Data")
                .build();
    }

    @Listener
    public void onKeyRegister(GameRegistryEvent.Register<Key<?>> event){
        Keys.PVP_ENABLE = Key.builder()
                .type(TypeTokens.BOOLEAN_VALUE_TOKEN)
                .query(DataQuery.of("pvpswitch", "pvp_enable"))
                .id("pvp:enable")
                .name("PVP Enable")
                .build();
    }

    public Logger getLogger() {
        return logger;
    }
}