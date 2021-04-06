package com.github.ericliucn.pvpswitch;

import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.util.TypeTokens;

public class Keys {

    public static final Key<Value<Boolean>> PVP_ENABLE = Key.builder()
            .type(TypeTokens.BOOLEAN_VALUE_TOKEN)
            .query(DataQuery.of("pvpswitch", "pvp_enable"))
            .id("pvp:enable")
            .name("PVP Enable")
            .build();

}
