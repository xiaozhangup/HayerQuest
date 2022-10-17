package me.xiaozhangup.hayerquest.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemChecker {

    public static boolean check(Player p, List<ItemStack> list) {
        Inventory inventory = p.getInventory();
        for (ItemStack itemStack : list) {
            if (!inventory.contains(itemStack)) {
                return false;
            }
        }

        for (ItemStack itemStack : list) {
            inventory.remove(itemStack);
        }
        return true;
    }

}
