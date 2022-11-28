package me.xiaozhangup.hayerquest;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.database.User;
import me.xiaozhangup.hayerquest.ui.TreeBook;
import me.xiaozhangup.hayerquest.utils.ItemChecker;
import me.xiaozhangup.hayerquest.utils.command.Command;
import me.xiaozhangup.hayerquest.utils.manager.ConfigManager;
import me.xiaozhangup.hayerquest.utils.manager.ListenerManager;
import me.xiaozhangup.hayerquest.utils.tools.IString;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class HayerQuest extends JavaPlugin {

    public static Plugin plugin;
    public static ListenerManager listenerManager = new ListenerManager();
    public static MiniMessage mm = MiniMessage.miniMessage();
    private static Economy econ = null;
    public static String prefix = "&8[&x&0&9&C&6&F&9空岛&8] ";

    public static Economy getEconomy() {
        return econ;
    }

    @Override
    public void onEnable() {
        plugin = this;
        setupEconomy();

        saveDefaultConfig();
        reloadConfig();

//        listenerManager.addListeners(
//                new PlayerEvent(), new WorldEvent()
//        );
//        listenerManager.register();

        Command.register("istodo", (commandSender, command, s, inside) -> {
            TreeBook.open((Player) commandSender);
            return true;
        });
        Command.register("isquestreload", (commandSender, command, s, inside) -> {
            QuestLoader.once.clear();
            QuestLoader.loadQuest();
            commandSender.sendMessage(mm.deserialize("已经重载了所有任务"));
            return true;
        });
        Command.register("ispull", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;

            User user = IridiumSkyblock.getInstance().getUserManager().getUser(p);
            var is = user.getIsland();
            if (is.isEmpty()) return false;
            var island = is.get();

            if (Integer.parseInt(inside[0]) - 1 != DataMaster.getLastDone(island)) return false;
            if (ItemChecker.check(p, QuestLoader.once.get(Integer.parseInt(inside[0]) - 1))) {
                p.sendMessage(IString.addColor(prefix + "&a你的任务提交完成! 请查看书本来知晓下一个任务的内容."));
            } else {
                p.sendMessage(IString.addColor(prefix + "&c任务提交失败.请注意有NBT物品是无法用于任务提交的!"));
            }
            return false;
        });

        ConfigManager.createFile("done");

        QuestLoader.loadQuest();

    }

    private void setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            econ = economyProvider.getProvider();
        }
    }

}
