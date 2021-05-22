package util;

import com.google.gson.Gson;
import model.config.Config;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class AppStorage {
    private static AppStorage single_instance = null;

    public static AppStorage getInstance() {
        if (single_instance == null)
            single_instance = new AppStorage();

        return single_instance;
    }

    public String jarPath;
    public Config config;

    private AppStorage() {
        jarPath = System.getProperty("user.dir");
    }

    public void loadConfig(Gson gson) {
        try {
            String dir = jarPath;

            File file = new File(dir + AppData.Config.Input.config);
            String string = FileUtils.readFileToString(file, AppData.charset);
            config = gson.fromJson(string, Config.class);
            config.app.timeCheck = config.app.timeCheck * 60 * 1000;
            config.news.restTime = config.news.restTime * 60 * 1000;
            config.wallpaper.restTime = config.wallpaper.restTime * 60 * 1000;
            System.out.println(gson.toJson(config));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
