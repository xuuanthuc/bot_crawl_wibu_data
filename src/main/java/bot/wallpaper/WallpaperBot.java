package bot.wallpaper;

import bot.BaseBot;

public class WallpaperBot extends BaseBot {
    public WallpaperBot(int maxThread, long restTime) {
        super(maxThread, restTime);
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Start crawler for WallpaperBot");
        complete();
    }
}
