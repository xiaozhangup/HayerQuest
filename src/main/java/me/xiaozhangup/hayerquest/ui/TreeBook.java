package me.xiaozhangup.hayerquest.ui;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.User;
import me.xiaozhangup.hayerquest.*;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

import static me.xiaozhangup.hayerquest.HayerQuest.mm;

public class TreeBook {

    public static void open(Player p) {
        var book = Book.book(mm.deserialize("Todo"), mm.deserialize("HAPPYLAND Dev"));
        book.pages(

        );    }

}
