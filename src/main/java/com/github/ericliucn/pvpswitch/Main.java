package com.github.ericliucn.pvpswitch;

import com.github.ericliucn.pvpswitch.data.PVPEnableData;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataRegistration;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.AttackEntityEvent;
import org.spongepowered.api.event.game.GameRegistryEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.TypeTokens;

import java.util.Optional;

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
    public void onInit(GameInitializationEvent event){
        instance = this;
        DataRegistration.builder()
                .dataClass(PVPEnableData.class)
                .dataImplementation(PVPEnableData.class)
                .immutableClass(PVPEnableData.Immutable.class)
                .immutableImplementation(PVPEnableData.Immutable.class)
                .builder(new PVPEnableData.Builder())
                .id("pvp_enable")
                .name("PVP Enable")
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

    @Listener
    public void registerCommands(GameStartedServerEvent event){
        CommandSpec enable = CommandSpec.builder()
                .description(Text.of("开启PVP模式"))
                .arguments(GenericArguments.optional(GenericArguments.player(Text.of("player"))))
                .executor((src, args) -> {
                    Optional<Player> optionalPlayer = args.getOne("player");
                    Player player = optionalPlayer.orElseGet(() -> ((src instanceof Player) ? (Player) src : null));
                    if (player != null){
                        player.getOrCreate(PVPEnableData.class).ifPresent(player::offer);
                        player.offer(Keys.PVP_ENABLE, true);
                        player.sendMessage(Utils.toText("&aPVP已开启"));
                    }else {
                        src.sendMessage(Utils.toText("&4该指令必须由玩家执行或者指定玩家名"));
                    }
                    return CommandResult.success();
                })
                .permission("pvpswitch.enable")
                .build();

        CommandSpec disable = CommandSpec.builder()
                .description(Text.of("开启PVP模式"))
                .arguments(GenericArguments.optional(GenericArguments.player(Text.of("player"))))
                .executor((src, args) -> {
                    Optional<Player> optionalPlayer = args.getOne("player");
                    Player player = optionalPlayer.orElseGet(() -> ((src instanceof Player) ? (Player) src : null));
                    if (player != null){
                        player.getOrCreate(PVPEnableData.class).ifPresent(player::offer);
                        player.offer(Keys.PVP_ENABLE, false);
                        player.sendMessage(Utils.toText("&aPVP已关闭"));
                    }else {
                        src.sendMessage(Utils.toText("&4该指令必须由玩家执行或者指定玩家名"));
                    }
                    return CommandResult.success();
                })
                .permission("pvpswitch.disable")
                .build();

        CommandSpec base = CommandSpec.builder()
                .description(Text.of("开启PVP模式"))
                .arguments(GenericArguments.optional(GenericArguments.player(Text.of("player"))))
                .executor((src, args) -> {
                    PaginationList.builder()
                            .contents(
                                    Utils.toText("&d/pvpswitch enable <player> 打开PVP模式"),
                                    Utils.toText("&d/pvpswitch disable <player> 关闭PVP模式")
                            )
                            .title(Utils.toText("&ePVP SWITCH"))
                            .padding(Utils.toText("&a="))
                            .sendTo(src);
                    return CommandResult.success();
                })
                .child(enable, "enable", "e")
                .child(disable, "d")
                .build();

        Sponge.getCommandManager().register(this, base, "pvpswitch", "pvp");
    }

    public Logger getLogger() {
        return logger;
    }

    @Listener
    public void onAttack(AttackEntityEvent event){
        if (event.getTargetEntity() instanceof Player & event.getSource() instanceof Player){
            Player target = ((Player) event.getTargetEntity());
            Player source = ((Player) event.getSource());
            if (!target.supports(Keys.PVP_ENABLE)){
                target.getOrCreate(PVPEnableData.class).ifPresent(target::offer);
            }
            if (!source.supports(Keys.PVP_ENABLE)){
                source.getOrCreate(PVPEnableData.class).ifPresent(source::offer);
            }
            target.get(Keys.PVP_ENABLE).ifPresent(enable -> {
                if (!enable){
                    event.setCancelled(true);
                    target.sendMessage(Utils.toText("&e你受到了来自玩家 &4" + source.getName() + " &e的攻击，" +
                            "但你的PVP模式处于关闭状态"));
                    source.sendMessage(Utils.toText("&e你攻击的玩家未开启PVP"));
                }
            });
            source.get(Keys.PVP_ENABLE).ifPresent(enable -> {
                if (!enable){
                    event.setCancelled(true);
                    source.sendMessage(Utils.toText("&e你的PVP处于关闭状态"));
                }
            });
        }
    }

}