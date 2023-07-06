package org.time.simplemoresound;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.projectiles.ProjectileSource;

public final class SimpleMoreSound extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    /**
     * 改变手上物品
     * @param e
     */
    @EventHandler
    public void onChange(PlayerItemHeldEvent e) {
        if (e.isCancelled()) return;
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, SoundCategory.PLAYERS, 1, 2);
    }

    @EventHandler
    public void onShoot(ProjectileHitEvent e) {
        if (e.isCancelled()) return;
        if (e.getHitEntity() == null) return;
        ProjectileSource projectileSource = e.getEntity().getShooter();
        if (projectileSource instanceof Player) {
            Player player = (Player) e.getEntity().getShooter();
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1f, 0);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (e.isCancelled()) return;
        Bukkit.getScheduler().runTask(this, () -> {
            for (Player player: Bukkit.getOnlinePlayers()) player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS,1, 1);
        });
    }

    @EventHandler
    public void onCmd(PlayerCommandPreprocessEvent e) {
        if (e.isCancelled()) return;
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1, 1);
    }
}
