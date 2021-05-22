package bot;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import util.AppData;
import util.AppSql;
import util.AppStorage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaseBotDB {
    protected AppStorage appStorage;
    protected AppSql appSql;
    protected Gson gson;

    public BaseBotDB() {
        this.appStorage = AppStorage.getInstance();
        this.appSql = new AppSql();
        this.gson = new Gson();
    }
}
