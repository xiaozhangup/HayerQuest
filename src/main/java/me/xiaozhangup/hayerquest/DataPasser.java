package me.xiaozhangup.hayerquest;

import me.xiaozhangup.hayerquest.utils.manager.ConfigManager;

import java.util.Timer;
import java.util.TimerTask;

public class DataPasser extends TimerTask {

    public static final String filename = "land";

    @Override
    public void run() {
        try {
            HayerQuest.works.forEach((landId, dateChain) -> {
                dateChain.forEach((s, i) -> {
                    var path = landId + "." + s;
                    int curry = ConfigManager.getConfig(filename).getInt(path);
                    ConfigManager.writeConfig(
                            filename,
                            path,
                            curry + i
                    );
                });

                //清理这个任务
                HayerQuest.works.remove(landId);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
