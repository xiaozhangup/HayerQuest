package me.xiaozhangup.hayerquest;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.bank.BankItem;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.IslandBank;
import me.xiaozhangup.hayerquest.utils.manager.ConfigManager;

import java.util.List;

public class DataMaster {

    private final static String oncelog = "done";

    public static void addOnceDone(Island island, Integer id) {
        ConfigManager.writeConfig(oncelog, String.valueOf(island.getId()), id);
    }

    public static Integer getLastDone(Island island) {
        int anInt = ConfigManager.getConfig(oncelog).getInt(String.valueOf(island.getId()));
        return anInt;
    }

    public static void addLandBank(Island island, BankItem bankItem, double integer) {
        IslandBank islandBank = IridiumSkyblock.getInstance().getIslandManager().getIslandBank(island, bankItem);
        islandBank.setNumber(islandBank.getNumber() + integer);
    }

}
