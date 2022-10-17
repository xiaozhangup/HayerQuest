package me.xiaozhangup.hayerquest;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Quest {

    QuestType type;
    TotalType act;
    String id;
    String name;
    List<ItemStack> items;
    Integer value;

    Integer crystal;
    Integer exp;
    Integer money;

    public QuestType getType() {
        return type;
    }

    public void setType(QuestType type) {
        this.type = type;
    }

    public TotalType getAct() {
        return act;
    }

    public void setAct(TotalType act) {
        this.act = act;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Quest(QuestType questType, String n, TotalType action, List<ItemStack> request, Integer point, Integer c, Integer e, Integer m, String i) {
        type = questType;
        act = action;
        items = request;
        value = point;
        name = n;

        id = i;

        crystal = c;
        exp = e;
        money = m;
    }



    public void giveReward(Player p) {
        //todo
    }

}
