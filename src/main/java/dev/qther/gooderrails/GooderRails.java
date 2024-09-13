package dev.qther.gooderrails;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.UUID;

public class GooderRails implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("mendedminecarts");

    public static final Set<UUID> ENABLED_PLAYERS = new ObjectOpenHashSet<>();

    @Override
    public void onInitialize() {
        LOGGER.info("It's Gooder Rails-ing time");

        CommandRegistrationCallback.EVENT.register((d, r, e) -> {
            GooderRailsCommand.register(d);
        });
    }
}
