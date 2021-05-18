package b;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaseBot {
    public interface BaseConfig {
        int threadNumber = 5;
        int pageMaxPerCategory = 100;
    }

    private boolean isRunning = false;
    protected Gson gson = new Gson();
    protected OkHttpClient client = new OkHttpClient();
    protected ExecutorService executor = Executors.newFixedThreadPool(BaseConfig.threadNumber);

    public BaseBot() {
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
}
