package me.xiaozhangup.hayerquest.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.User;
import me.xiaozhangup.hayerquest.DataMaster;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class PlayerEvent implements Listener {

    @EventHandler
    public void onPlayerCraft(CraftItemEvent e) {
        eventAdd((OfflinePlayer) e.getWhoClicked(), "craft");
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {
        eventAdd(e.getPlayer(), "fish");
    }

    @EventHandler
    public void onPlayerSmelt(PlayerShearEntityEvent e) {
        eventAdd(e.getPlayer(), "cut");
    }

    @EventHandler
    public void onPlayerEnch(EnchantItemEvent e) {
        eventAdd(e.getEnchanter(), "ench");
    }

    @EventHandler
    public void onPlayerTool(PlayerItemBreakEvent e) {
        eventAdd(e.getPlayer(), "tool");
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity().getType() == EntityType.PLAYER) return;
        eventAdd(e.getEntity().getKiller(), "kill");
    }

    private static void eventAdd(OfflinePlayer p, String type) {
        User user = IridiumSkyblock.getInstance().getUserManager().getUser(p);
        var island = user.getIsland();
        if (island.isEmpty()) return;
        DataMaster.addPoint(island.get() , type);
    }
}
