package me.xiaozhangup.hayerquest;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.bank.BankItem;
import com.iridium.iridiumskyblock.database.User;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Quest {
    Integer id;
    String name;
    List<ItemStack> items;
    List<ItemStack> iReward;
    Integer value;
    Integer crystal;
    Integer exp;
    Integer money;
    String content;

    public Quest(String n, List<ItemStack> request, Integer point, Integer c, Integer e, Integer m, Integer i, String con, List<ItemStack> ire) {

        items = request;
        iReward = ire;
        value = point;
        name = n;

        id = i;

        crystal = c;
        exp = e;
        money = m;
        content = con;
    }

    public List<ItemStack> getiReward() {
        return iReward;
    }

    public void setiReward(List<ItemStack> iReward) {
        this.iReward = iReward;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public void setItems(List<ItemStack> items) {
        this.items = items;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getCrystal() {
        return crystal;
    }

    public void setCrystal(Integer crystal) {
        this.crystal = crystal;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public void giveReward(Player p) {

        BankItem bcrystal = IridiumSkyblock.getInstance().getBankItems().crystalsBankItem;
        BankItem bmoney = IridiumSkyblock.getInstance().getBankItems().moneyBankItem;

        User user = IridiumSkyblock.getInstance().getUserManager().getUser(p);
        var is = user.getIsland();
        if (is.isEmpty()) return;
        var island = is.get();

        DataMaster.addLandBank(island, bcrystal, crystal);
        DataMaster.addLandBank(island, bmoney, money);
        island.setExperience(island.getTotalExperience() + exp);
    }

}
