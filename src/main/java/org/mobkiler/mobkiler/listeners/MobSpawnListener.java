package org.mobkiler.mobkiler.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;
import org.mobkiler.mobkiler.Mobkiler;
import org.mobkiler.mobkiler.managers.MobSpawnerManager;

public class MobSpawnListener implements Listener {
    private final MobSpawnerManager spawnerManager;

    public MobSpawnListener() {
        this.spawnerManager = Mobkiler.getInstance().getSpawnerManager();
    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {
        Entity entity = event.getEntity();

        // فقط ماب‌های Monster
        if (!(entity instanceof Monster)) {
            return;
        }

        spawnerManager.scheduleForBlastAndJump(entity);

    }
}
