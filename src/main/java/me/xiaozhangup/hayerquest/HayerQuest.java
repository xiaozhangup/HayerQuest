package me.xiaozhangup.hayerquest;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.api.IridiumSkyblockAPI;
import com.iridium.iridiumskyblock.bank.BankItem;
import me.xiaozhangup.hayerquest.listeners.PlayerEvent;
import me.xiaozhangup.hayerquest.listeners.WorldEvent;
import me.xiaozhangup.hayerquest.ui.TreeBook;
import me.xiaozhangup.hayerquest.utils.command.Command;
import me.xiaozhangup.hayerquest.utils.manager.ConfigManager;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import me.xiaozhangup.hayerquest.utils.manager.ListenerManager;

import java.util.HashMap;
import java.util.Timer;

public class HayerQuest extends JavaPlugin {

    public static Plugin plugin;
    public static ListenerManager listenerManager = new ListenerManager();
    Timer timer = new Timer();
    private static Economy econ = null;
    public static HashMap<Integer, HashMap<String, Integer>> works = new HashMap<>();

    public static BankItem crystal = IridiumSkyblock.getInstance().getBankItems().crystalsBankItem;
    public static BankItem money = IridiumSkyblock.getInstance().getBankItems().moneyBankItem;
    public static BankItem experience = IridiumSkyblock.getInstance().getBankItems().experienceBankItem;

    public static MiniMessage mm = MiniMessage.miniMessage();

    public static Economy getEconomy() {
        return econ;
    }

    @Override
    public void onEnable() {
        plugin = this;
        setupEconomy();

        saveDefaultConfig();
        reloadConfig();

        listenerManager.addListeners(
                new PlayerEvent(), new WorldEvent()
        );
        listenerManager.register();

        Command.register("istodo", (commandSender, command, s, inside) -> {
            TreeBook.open((Player) commandSender);
            return true;
        });
        Command.register("isback", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            switch (inside[0]) {
                case "total" -> TreeBook.openTotal(p);
                case "once" -> TreeBook.openOnce(p);
            }
            return false;
        });
        Command.register("ispull", (commandSender, command, s, inside) -> {
            /*your way*/
            return false;
        });
        Command.register("isopen", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            switch (inside[0]) {
                case "t" -> {
                    TreeBook.openTotalByType(p, TotalType.valueOf(inside[1]));
                }
                case "o" -> {

                }
            }
            return false;
        });

        ConfigManager.createFile("land");
        ConfigManager.createFile("done");

        QuestLoader.loadQuest();

        timer.schedule(new DataPasser(), 0, 10000);

    }

    private void setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            econ = economyProvider.getProvider();
        }
    }

}
