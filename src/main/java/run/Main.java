package run;

import bot.news.NewBot;
import bot.wallpaper.WallpaperBot;
import com.google.gson.Gson;
import util.AppStorage;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        AppStorage.getInstance().loadConfig(gson);

        Control control = new Control();
        control.run();
    }

    public static class Control {
        long delay, period;
        Timer timer;
        AppStorage appStorage;
        NewBot newBot;
        WallpaperBot wallpaperBot;

        Control() {
            appStorage = AppStorage.getInstance();
            timer = new Timer();
            delay = 0;
            period = appStorage.config.app.timeCheck;

            newBot = new NewBot(
                    appStorage.config.news.maxThread,
                    appStorage.config.news.restTime
            );
            wallpaperBot = new WallpaperBot(
                    appStorage.config.wallpaper.maxThread,
                    appStorage.config.wallpaper.restTime
            );
        }

        void run() {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    long now = System.currentTimeMillis();
                    if (newBot.isNeedRun(now)) newBot.run();
                    if (wallpaperBot.isNeedRun(now)) wallpaperBot.run();
                }
            }, delay, period);
        }
    }
}
