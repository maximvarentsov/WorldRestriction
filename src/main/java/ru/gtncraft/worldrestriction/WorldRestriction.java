package ru.gtncraft.worldrestriction;

import org.bukkit.plugin.java.JavaPlugin;

public final class WorldRestriction extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        new Listeners(this);
    }
}
