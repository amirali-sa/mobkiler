package org.mobkiler.mobkiler;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;
import org.mobkiler.mobkiler.listeners.MobSpawnListener;
import org.mobkiler.mobkiler.managers.MobSpawnerManager;

import java.util.HashMap;
import java.util.UUID;

public final class Mobkiler extends JavaPlugin implements Listener {
    private static Mobkiler instance;
    private MobSpawnerManager spawnerManager;

    public static Mobkiler getInstance() {
        return instance;
    }

    public MobSpawnerManager getSpawnerManager() {
        return spawnerManager;
    }

    @Override
    public void onEnable() {
        instance = this;
        spawnerManager = new MobSpawnerManager(this);

        getLogger().info("MobKiler enabled! Monsters will go BOOM!");

        // register listener
        getServer().getPluginManager().registerEvents(new MobSpawnListener(), this);
    }

    @Override
    public void onDisable() {
        spawnerManager.clearMobs();
        getLogger().info("MobKiler disabled!");
    }
}
