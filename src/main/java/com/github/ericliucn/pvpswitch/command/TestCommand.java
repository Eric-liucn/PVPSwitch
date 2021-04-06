package com.github.ericliucn.pvpswitch.command;

import com.github.ericliucn.pvpswitch.Keys;
import com.github.ericliucn.pvpswitch.manipulators.PVPData;
import com.github.ericliucn.pvpswitch.manipulators.PVPDataBuilder;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class TestCommand implements CommandCallable {

    @Override
    public CommandResult process(CommandSource source, String arguments) throws CommandException {
        if (source instanceof Player){
            Player player = ((Player) source);
            player.offer(Keys.PVP_ENABLE, true);
            boolean b = player.toContainer().getBoolean(Keys.PVP_ENABLE.getQuery()).get();
            player.sendMessages(Text.of(b));
        }
        return CommandResult.success();
    }

    @Override
    public List<String> getSuggestions(CommandSource source, String arguments, @Nullable Location<World> targetPosition) throws CommandException {
        return null;
    }

    @Override
    public boolean testPermission(CommandSource source) {
        return true;
    }

    @Override
    public Optional<Text> getShortDescription(CommandSource source) {
        return Optional.empty();
    }

    @Override
    public Optional<Text> getHelp(CommandSource source) {
        return Optional.empty();
    }

    @Override
    public Text getUsage(CommandSource source) {
        return null;
    }

}
