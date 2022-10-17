package me.xiaozhangup.hayerquest.ui;

import me.xiaozhangup.hayerquest.Quest;
import me.xiaozhangup.hayerquest.QuestLoader;
import me.xiaozhangup.hayerquest.TotalType;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

import static me.xiaozhangup.hayerquest.HayerQuest.mm;

public class TreeBook {

    public static Book mainBook = Book.book(mm.deserialize("Todo"), mm.deserialize("HAPPYLAND Dev"), mm.deserialize("  <b><gray>空岛任务</gray></b> <b><gold>总览</gold></b>\n<dark_gray><click:run_command:'/is'><-返回</click></dark_gray>\n\n<black><hover:show_text:'<gray>单击打开分页</gray>'><click:run_command:'/isback total'>1.累计性任务</click></hover>\n<hover:show_text:'<gray>单击打开分页</gray>'><click:run_command:'/isback once'>2.收集性任务</click></hover></black>\n\n<dark_red>请在上方选择你想了解的任务种类</dark_red>"));
    public static Book totolChoose = Book.book(mm.deserialize("Todo"), mm.deserialize("HAPPYLAND Dev"), mm.deserialize("  <b><gray>空岛任务</gray></b> <b><gold>累计</gold></b>\n<dark_gray><click:run_command:'/istodo'><-返回</click></dark_gray>\n\n<black><click:run_command:'/isopen t craft'>1.合成</click>\n<click:run_command:'/isopen t cut'>2.剪羊毛</click>\n<click:run_command:'/isopen t fish'>3.钓鱼</click>\n<click:run_command:'/isopen t tool'>4.用废工具</click>\n<click:run_command:'/isopen t ench'>5.附魔</click>\n<click:run_command:'/isopen t kill'>6.击杀</click></black>\n\n<dark_red>请在上方选择你想了解的任务种类</dark_red>"));

    public static void open(Player p) {
        p.openBook(mainBook);
    }

    public static void openTotal(Player p) {
        p.openBook(totolChoose);
    }

    public static void openOnce(Player p) {
        var book = Book.book(mm.deserialize("Todo"), mm.deserialize("HAPPYLAND Dev"));
        book.pages(

        );
    }

    public static void openTotalByType(Player p, TotalType type) {
        var book = Book.book(mm.deserialize("Todo"), mm.deserialize("HAPPYLAND Dev"));

        List<Component> list = null;

        List<Quest> questList = null;

        QuestLoader.total.forEach((quest -> {
            if (quest.getAct() == type) {
                questList.add(quest);
            }
        }));



        book.pages(
            list
        );
    }

}
