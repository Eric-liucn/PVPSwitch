package com.github.ericliucn.pvpswitch.manipulators;

import com.github.ericliucn.pvpswitch.Keys;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.persistence.InvalidDataException;

import java.util.Optional;

public class PVPDataBuilder implements DataManipulatorBuilder<PVPData, ImmutablePVP> {

    @Override
    public PVPData create() {
        return new PVPData(true);
    }

    @Override
    public Optional<PVPData> createFrom(DataHolder dataHolder) {
        return create().fill(dataHolder);
    }

    @Override
    public Optional<PVPData> build(DataView container) throws InvalidDataException {
        return Optional.of(new PVPData(container.getBoolean(Keys.PVP_ENABLE.getQuery()).orElse(true)));
    }

}
