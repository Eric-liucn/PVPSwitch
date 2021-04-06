package com.github.ericliucn.pvpswitch.manipulators;

import com.github.ericliucn.pvpswitch.Keys;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableBooleanData;
import org.spongepowered.api.data.value.ValueFactory;
import org.spongepowered.api.data.value.mutable.Value;

public class ImmutablePVP extends AbstractImmutableBooleanData<ImmutablePVP, PVPData> {


    protected ImmutablePVP(boolean value) {
        super(Keys.PVP_ENABLE, value, true);
    }

    @Override
    public PVPData asMutable() {
        return new PVPData(this.value);
    }

    @Override
    public int getContentVersion() {
        return 1;
    }
}
