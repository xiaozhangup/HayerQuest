package me.xiaozhangup.hayerquest.utils;

import com.iridium.iridiumcore.dependencies.xseries.XMaterial;
import com.iridium.iridiumcore.utils.InventoryUtils;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.database.User;
import me.xiaozhangup.hayerquest.DataMaster;
import me.xiaozhangup.hayerquest.Quest;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemChecker {

    public static boolean check(Player p, Quest quest) {
        Inventory inventory = p.getInventory();

        User user = IridiumSkyblock.getInstance().getUserManager().getUser(p);
        var is = user.getIsland();
        if (is.isEmpty()) return false;
        var island = is.get();

        for (ItemStack itemStack : quest.getItems()) {
            if (getMaterialAmount(inventory, itemStack.getType()) <= itemStack.getAmount()) {
                return false;
            }
        }

        for (ItemStack itemStack : quest.getItems()) {
            removeAmount(inventory, itemStack.getType(), itemStack.getAmount());
        }
        DataMaster.addOnceDone(island, quest.getId());
        return true;
    }

    public static int getMaterialAmount(Inventory inventory, Material material) {
        int total = 0;
        ItemStack[] var3 = inventory.getContents();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            ItemStack item = var3[var5];
            if (item != null && material.equals(item.getType()) && !item.hasItemMeta()) {
                total += item.getAmount();
            }
        }

        return total;
    }

    public static void removeAmount(Inventory inventory, Material material, int amount) {
        int removed = 0;
        int index = 0;
        ItemStack[] var5 = inventory.getContents();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            ItemStack itemStack = var5[var7];
            if (itemStack == null) {
                ++index;
            } else {
                if (removed >= amount) {
                    break;
                }

                if (material == itemStack.getType()) {
                    if (removed + itemStack.getAmount() <= amount) {
                        removed += itemStack.getAmount();
                        inventory.setItem(index, (ItemStack)null);
                    } else {
                        itemStack.setAmount(itemStack.getAmount() - (amount - removed));
                        removed += amount;
                    }
                }

                ++index;
            }
        }

    }

}
