package com.github.ericliucn.pvpswitch.manipulators;

import com.github.ericliucn.pvpswitch.Keys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractBooleanData;
import org.spongepowered.api.data.merge.MergeFunction;

import java.util.Optional;

public class PVPData extends AbstractBooleanData<PVPData, ImmutablePVP> {

    protected PVPData(boolean value) {
        super(Keys.PVP_ENABLE, value, true);
    }

    @Override
    public Optional<PVPData> fill(DataHolder dataHolder, MergeFunction overlap) {
        PVPData merged = overlap.merge(this, dataHolder.get(PVPData.class).orElse(new PVPData(true)));
        setValue(merged.value);
        return Optional.of(this);
    }

    @Override
    public Optional<PVPData> from(DataContainer container) {
        container.getBoolean(Keys.PVP_ENABLE.getQuery()).ifPresent(aBoolean -> this.value = aBoolean);
        return Optional.of(this);
    }

    @Override
    public PVPData copy() {
        return new PVPData(this.value);
    }

    @Override
    public ImmutablePVP asImmutable() {
        return new ImmutablePVP(this.value);
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

}
