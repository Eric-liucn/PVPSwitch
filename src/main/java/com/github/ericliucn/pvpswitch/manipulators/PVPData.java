package com.github.ericliucn.pvpswitch.manipulators;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.manipulator.DataManipulator;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.value.BaseValue;
import org.spongepowered.api.data.value.immutable.ImmutableValue;

import java.util.Optional;
import java.util.Set;

public class PVPData implements DataManipulator<PVPData, ImmutablePVP> {

    @Override
    public Optional<PVPData> fill(DataHolder dataHolder, MergeFunction overlap) {
        return Optional.empty();
    }

    @Override
    public Optional<PVPData> from(DataContainer container) {

        return Optional.empty();
    }

    @Override
    public <E> PVPData set(Key<? extends BaseValue<E>> key, E value) {
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
    public PVPData copy() {
        return null;
    }

    @Override
    public Set<Key<?>> getKeys() {
        return null;
    }

    @Override
    public Set<ImmutableValue<?>> getValues() {
        return null;
    }

    @Override
    public ImmutablePVP asImmutable() {
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
}
