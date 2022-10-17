package me.xiaozhangup.hayerquest;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestLoader {
    public static List<Quest> once = new ArrayList<>();

    public static void loadQuest() {
        var config = HayerQuest.plugin.getConfig();

        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (config.getString("Once." + i + ".Name") == null) break;
            var q = config.getStringList("Once." + i + ".Items").stream().toList();
            List<ItemStack> list = new ArrayList<>();
            q.forEach((s -> {
                var item = Arrays.stream(s.split(":")).toList();
                list.add(new ItemStack(Material.getMaterial(item.get(0)), Integer.parseInt(item.get(1))));
            }));
            var rew = Arrays.stream(config.getString("Once." + i + ".Reward").split(":")).toList();
            Quest quest = new Quest(
                    config.getString("Once." + i + ".Name"),
                    list,
                    null,
                    Integer.valueOf(rew.get(0)),
                    Integer.valueOf(rew.get(1)),
                    Integer.valueOf(rew.get(2)),
                    i,
                    config.getString("Once." + i + ".Content")
            );
            once.add(quest);
        }
    }

}
