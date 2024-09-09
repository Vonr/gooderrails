package dev.qther.gooderrails;


import com.jcraft.jogg.Packet;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class GooderRailsCommand {
    static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("gooderrails").then(CommandManager.literal("toggle").executes((c) -> {
            var player = c.getSource().getPlayer();
            if (player == null) {
                c.getSource().sendError(Text.of("Must be a player to run this command."));
                return 0;
            }

            var uuid = player.getUuid();
            if (GooderRails.ENABLED_PLAYERS.remove(uuid)) {
                c.getSource().sendMessage(Text.of("You are no longer using gooder rail placements."));
            } else {
                GooderRails.ENABLED_PLAYERS.add(uuid);
                c.getSource().sendMessage(Text.of("You are now using gooder rail placements."));
            }

            return 1;
        })));
    }
}
