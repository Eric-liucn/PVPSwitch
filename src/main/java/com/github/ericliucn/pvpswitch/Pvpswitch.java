package com.github.ericliucn.pvpswitch;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.data.DataRegistration;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = "pvpswitch",
        name = "Pvpswitch",
        description = "Pvpswitch",
        authors = {
                "Eric12324"
        }
)
public class Pvpswitch {

    public static Pvpswitch instance;

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        instance = this;
    }

    @Listener
    public void onInit(GameInitializationEvent event){
        DataRegistration.builder()
                .dataClass()
    }
}