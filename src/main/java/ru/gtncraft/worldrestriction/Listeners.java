package ru.gtncraft.worldrestriction;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.world.StructureGrowEvent;

import java.util.Collection;

class Listeners implements Listener {
    private final Collection<String> worlds;

    public Listeners(final WorldRestriction plugin) {
        worlds = plugin.getConfig().getStringList("worlds");
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    boolean protectedWorld(final World world) {
        return worlds.contains(world.getName());
    }

    boolean hasPermissions(final Player player) {
        return player.hasPermission("worldrestriction.bypass");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onSignChange(final SignChangeEvent event) {
        if (protectedWorld(event.getPlayer().getWorld())) {
            if (hasPermissions(event.getPlayer())) {
                String[] lines = event.getLines();
                for (int i = 0; i < lines.length; i++) {
                    String line = ChatColor.translateAlternateColorCodes('&', lines[i]);
                    event.setLine(i, line);
                }
            } else {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onFoodChange(final FoodLevelChangeEvent event) {
        if (protectedWorld(event.getEntity().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onEntityDamage(final EntityDamageEvent event) {
        if (protectedWorld(event.getEntity().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onPlayerExpChange(final PlayerExpChangeEvent event) {
        if (protectedWorld(event.getPlayer().getWorld())) {
            event.setAmount(0);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onBlockBurn(final BlockBurnEvent event) {
        if (protectedWorld(event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onBlockIgnite(final BlockIgniteEvent event) {
        if (event == null || event.getPlayer() == null) {
            return;
        }
        if (protectedWorld(event.getPlayer().getWorld())) {
            if (!hasPermissions(event.getPlayer())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onBlockBreak(final BlockBreakEvent event) {
        if (protectedWorld(event.getPlayer().getWorld())) {
            if (!hasPermissions(event.getPlayer())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onBlockPlace(final BlockPlaceEvent event) {
        if (protectedWorld(event.getPlayer().getWorld())) {
            if (!hasPermissions(event.getPlayer())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onPlayerBucketFill(final PlayerBucketFillEvent event) {
        if (protectedWorld(event.getPlayer().getWorld())) {
            if (!hasPermissions(event.getPlayer())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onPlayerBucketEmpty(final PlayerBucketEmptyEvent event) {
        if (protectedWorld(event.getBlockClicked().getWorld())) {
            if (!hasPermissions(event.getPlayer())) {
                event.setCancelled(true);
            }
        }
    }

    // disable join, quit and death messages
    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onPlayerJoin(final PlayerJoinEvent event) {
        if (protectedWorld(event.getPlayer().getWorld())) {
            Player player = event.getPlayer();
            Location spawn = player.getWorld().getSpawnLocation();
            player.teleport(spawn);
        }
        event.setJoinMessage(null);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onPlayerQuit(final PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onPlayerDeath(final PlayerDeathEvent event) {
        event.setDeathMessage(null);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onBlockFromTo(final BlockFromToEvent event) {
        if (protectedWorld(event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onBlockFade(final BlockFadeEvent event) {
        if (protectedWorld(event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onStructureGrow(final StructureGrowEvent event) {
        if (protectedWorld(event.getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onBlockGrow(final BlockGrowEvent event) {
        if (protectedWorld(event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onPlayerInterract(final PlayerInteractEvent event) {
        if (protectedWorld(event.getPlayer().getWorld())) {
            if (!hasPermissions(event.getPlayer())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onBlockForm(final BlockFormEvent event) {
        if (protectedWorld(event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onLeavesDecay(final LeavesDecayEvent event) {
        if (protectedWorld(event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onBlockSpread(final BlockSpreadEvent event) {
        if (protectedWorld(event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }
}
