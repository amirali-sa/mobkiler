package org.mobkiler.mobkiler.managers;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.mobkiler.mobkiler.Mobkiler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MobSpawnerManager {
    private final Mobkiler plugin;
    private static final Long WAIT_TICKS_FOR_JUMP = 80L;
    private static final Long WAIT_TICKS_FOR_EXPLOSION = 20L;
    private static final Map<UUID, Entity> spawnedMobs = new HashMap<>();

    public MobSpawnerManager(Mobkiler plugin) {
        this.plugin = plugin;
    }

    public void scheduleForBlastAndJump(Entity entity) {
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            spawnedMobs.putIfAbsent(entity.getUniqueId(), entity);
            jumpEntity(entity);
            scheduleForExplosion(entity);
        }, WAIT_TICKS_FOR_JUMP);
    }

    public void scheduleForExplosion(Entity entity) {
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            explodeEntity(entity);
        }, WAIT_TICKS_FOR_EXPLOSION);
    }

    public void clearMobs() {
        spawnedMobs.clear();
    }

    private void jumpEntity(Entity entity) {
        if (!spawnedMobs.containsKey(entity.getUniqueId())) return;

        double y = entity.getVelocity().getY() + 1.5;
        entity.setVelocity(entity.getVelocity().add(new Vector(0, y,0)));
    }

    private void explodeEntity(Entity entity) {
        if (!spawnedMobs.containsKey(entity.getUniqueId())) return;

        if (!entity.isValid() || entity.isDead()) {
            spawnedMobs.remove(entity.getUniqueId());
            return;
        }

        Location location = entity.getLocation();
        location.getWorld().createExplosion(location, 2.0f);
    }
}
