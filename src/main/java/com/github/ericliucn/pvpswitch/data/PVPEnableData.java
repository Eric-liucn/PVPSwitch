package com.github.ericliucn.pvpswitch.data;

import com.github.ericliucn.pvpswitch.Keys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableBooleanData;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractBooleanData;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.persistence.InvalidDataException;

import java.util.Optional;

public class PVPEnableData extends AbstractBooleanData<PVPEnableData, PVPEnableData.Immutable> {

    protected PVPEnableData(boolean value) {
        super(Keys.PVP_ENABLE, value, true);
    }

    @Override
    public Optional<PVPEnableData> fill(DataHolder dataHolder, MergeFunction overlap) {
        Optional<PVPEnableData> optionalPVPEnableData = dataHolder.get(PVPEnableData.class);
        if (optionalPVPEnableData.isPresent()){
            PVPEnableData pvpEnableData = optionalPVPEnableData.get();
            PVPEnableData merged = overlap.merge(this, pvpEnableData);
            this.setValue(merged.getValue());
        }
        return Optional.of(this);
    }

    @Override
    public Optional<PVPEnableData> from(DataContainer container) {
        if (container.contains(Keys.PVP_ENABLE)){
            container.getBoolean(Keys.PVP_ENABLE.getQuery()).ifPresent(this::setValue);
            return Optional.of(this);
        }
        return Optional.empty();
    }

    @Override
    public PVPEnableData copy() {
        return new PVPEnableData(true);
    }

    @Override
    public Immutable asImmutable() {
        return new Immutable(this.value);
    }

    @Override
    public int getContentVersion() {
        return 0;
    }

    public static class Immutable extends AbstractImmutableBooleanData<Immutable, PVPEnableData> {

        protected Immutable(boolean value) {
            super(Keys.PVP_ENABLE, value, true);
        }

        @Override
        public PVPEnableData asMutable() {
            return new PVPEnableData(this.value);
        }

        @Override
        public int getContentVersion() {
            return 0;
        }
    }

    public static class Builder implements DataManipulatorBuilder<PVPEnableData, Immutable> {

        @Override
        public PVPEnableData create() {
            return new PVPEnableData(true);
        }

        @Override
        public Optional<PVPEnableData> createFrom(DataHolder dataHolder) {
            Optional<Boolean> optionalBoolean = dataHolder.get(Keys.PVP_ENABLE);
            return optionalBoolean.map(PVPEnableData::new);
        }

        @Override
        public Optional<PVPEnableData> build(DataView container) throws InvalidDataException {
            if (container.contains(Keys.PVP_ENABLE)){
                return Optional.of(new PVPEnableData(container.getBoolean(Keys.PVP_ENABLE.getQuery()).orElse(true)));
            }
            return Optional.empty();
        }
    }
}
