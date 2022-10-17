package me.xiaozhangup.hayerquest.ui;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.database.User;
import me.xiaozhangup.hayerquest.DataMaster;
import me.xiaozhangup.hayerquest.Quest;
import me.xiaozhangup.hayerquest.QuestLoader;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import static me.xiaozhangup.hayerquest.HayerQuest.mm;

public class TreeBook {

    public static void open(Player p) {

        User user = IridiumSkyblock.getInstance().getUserManager().getUser(p);
        var is = user.getIsland();
        if (is.isEmpty()) return;
        var island = is.get();

        if (DataMaster.getLastDone(island) >= QuestLoader.once.size()) {
            var book = Book.book(mm.deserialize("Todo"), mm.deserialize("HAPPYLAND Dev"), mm.deserialize("      <b><black>空岛任务</black></b> <b><gold>当前</gold></b>\n<dark_gray><click:run_command:'/is'><-返回</click></dark_gray>\n\n你完成了所有任务!\n但这并不代表结束了,因为我们会一直向其中添加任务."));
            p.openBook(book);
            return;
        }

        Quest quest = QuestLoader.once.get(DataMaster.getLastDone(island));

        final Component[] item = {mm.deserialize("")};
        quest.getItems().forEach((itemStack -> {
            item[0] = item[0].append(Component.translatable(itemStack.translationKey()).append(mm.deserialize("*" + itemStack.getAmount() + " ")));
        }));

        final Component[] re = {mm.deserialize("")};
        quest.getiReward().forEach((itemStack -> {
            re[0] = re[0].append(Component.translatable(itemStack.translationKey()).append(mm.deserialize("*" + itemStack.getAmount() + " ")));
        }));

        Component component = mm.deserialize("      <b><black>空岛任务</black></b> <b><gold>当前</gold></b>\n<dark_gray><click:run_command:'/is'><-返回</click></dark_gray>  <gray>任务号: " + quest.getId() + "</gray>\n");
        component = component.append(mm.deserialize(
                "\n名称: " + quest.getName() + "\n介绍: <dark_gray>" + quest.getContent() + "</dark_gray>\n\n" + "完成奖励: \n" + quest.getCrystal() + "*水晶 "
                        + quest.getExp() + "*岛屿经验 " + quest.getMoney() + "*金钱 ").append(re[0]).append(mm.deserialize("\n\n翻到下一页查看需要的物品和提交这个任务")
        ));

        var book = Book.book(mm.deserialize("Todo"), mm.deserialize("HAPPYLAND Dev"), component, item[0].append(mm.deserialize("\n\n<b><dark_green><click:run_command:'/ispull " + quest.getId() + "'>点此提交</click></dark_green></b>")));

        p.openBook(book);
    }

}
