package bot;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import util.AppData;
import util.AppStorage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaseBot {
    protected boolean isRunning;
    protected long lastRun;
    protected long restTime;
    protected AppStorage appStorage;
    protected Gson gson;
    protected OkHttpClient client;
    protected ExecutorService executor;

    public BaseBot(int maxThread, long restTime) {
        this.isRunning = false;
        this.restTime = restTime;
        this.lastRun = 0;
        this.appStorage = AppStorage.getInstance();
        this.gson = new Gson();
        this.client = new OkHttpClient();
        this.executor = Executors.newFixedThreadPool(Math.max(maxThread, AppData.threadDefault));
    }

    public void run() {
        isRunning = true;
    }

    public void complete() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isNeedRun(long now) {
        return (!isRunning && (lastRun + restTime < now));
    }
}
