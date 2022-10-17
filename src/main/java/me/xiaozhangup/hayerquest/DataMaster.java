package me.xiaozhangup.hayerquest;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.bank.BankItem;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.IslandBank;
import me.xiaozhangup.hayerquest.utils.manager.ConfigManager;

import java.util.List;

import static me.xiaozhangup.hayerquest.DataPasser.filename;

public class DataMaster {

    private final static String oncelog = "done";

    public static void addPoint(Island island, String type) {
        var map = HayerQuest.works.get(island.getId());
        var curry = map.get(type);
        map.put(type, ++curry);
        HayerQuest.works.put(island.getId(), map);
    }

    public static Integer getTotalCurry(Island island, String type) {
        return ConfigManager.getConfig(filename).getInt(island.getId() + "." + type);
    }

    public static void addOnceDone(Island island, String id) {
        var list = ConfigManager.getConfig(oncelog).getStringList(String.valueOf(island.getId()));
        list.add(id);
        ConfigManager.writeConfig(oncelog, String.valueOf(island.getId()), list);
    }

    public static List<String> getOnceDone(Island island) {
        return ConfigManager.getConfig(oncelog).getStringList(String.valueOf(island.getId()));
    }

    public static void addLandBank (Island island, BankItem bankItem, double integer) {
        IslandBank islandBank = IridiumSkyblock.getInstance().getIslandManager().getIslandBank(island, bankItem);
        islandBank.setNumber(islandBank.getNumber() + integer);
    }

}
