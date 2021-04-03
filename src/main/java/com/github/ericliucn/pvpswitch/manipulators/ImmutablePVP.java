package com.github.ericliucn.pvpswitch.manipulators;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.manipulator.ImmutableDataManipulator;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.data.value.immutable.ImmutableValue;

import java.util.Optional;
import java.util.Set;

public class ImmutablePVP implements ImmutableDataManipulator<ImmutablePVP, PVPData> {

    @Override
    public PVPData asMutable() {
        return null;
    }

    @Override
    public int getContentVersion() {
        return 0;
    }

    @Override
    public DataContainer toContainer() {
        return null;
    }

    @Override
    public <E> Optional<E> get(Key<? extends BaseValue<E>> key) {
        return Optional.empty();
    }

    @Override
    public <E, V extends BaseValue<E>> Optional<V> getValue(Key<V> key) {
        return Optional.empty();
    }

    @Override
    public boolean supports(Key<?> key) {
        return false;
    }

    @Override
    public Set<Key<?>> getKeys() {
        return null;
    }

    @Override
    public Set<ImmutableValue<?>> getValues() {
        return null;
    }
}
