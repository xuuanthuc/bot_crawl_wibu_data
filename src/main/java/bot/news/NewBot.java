package bot.news;

import bot.BaseBot;
import model.config.ConfigQuery;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import util.AppData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NewBot extends BaseBot {
    private List<NewDetail> data;
    private NewBotDB database;
    private int current, total;

    public NewBot(int maxThread, long restTime) {
        super(maxThread, restTime);
        data = new ArrayList<>();
        database = new NewBotDB();
        current = total = 0;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Start crawler for NewBot");
        List<ConfigQuery> queries = appStorage.config.news.query;
        current = 0;
        total = queries.size();
        for (ConfigQuery query : queries) {
            executor.execute(() -> {
                getNews(query);
                current++;
                if (current == total) {
                    updateDatabase();
                    complete();
                    System.out.println("NewBot complete");
                }
            });
        }
    }

    private void getNews(ConfigQuery query) {
        try {
            String url = String.format(appStorage.config.news.url, query.name, 1);
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            NewInfo news = gson.fromJson(json, NewInfo.class);
            int maxPage = appStorage.config.news.maxPage;
            for (int newIndex = 1; newIndex < Math.min(news.lastPage, maxPage); newIndex++) {
                List<NewDetail> newDetails = getNewsData(query, newIndex);
                if (newDetails != null) {
                    for (int newDetailIndex = 0; newDetailIndex < newDetails.size(); newDetailIndex++) {
                        NewDetail buffer = newDetails.get(newDetailIndex);
                        buffer.type = query.id;
                        newDetails.set(newDetailIndex, buffer);
                    }
                    data.addAll(newDetails);
                }
                int max = appStorage.config.news.sleepMax;
                int min = appStorage.config.news.sleepMin;
                int sleepTime = random.nextInt(max - min + 1) + min;
                Thread.sleep(sleepTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<NewDetail> getNewsData(ConfigQuery query, int i) {
        String url = "";
        String json = "";
        try {
            url = String.format(appStorage.config.news.url, query.name, i);
            System.out.println(url);
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            json = response.body().string();
            NewInfo news = gson.fromJson(json, NewInfo.class);
            if (news != null && news.data != null && news.data.size() > 0) {
                return news.data;
            }
        } catch (Exception e) {
            System.out.println(String.format("getNewsData Failed : url[%s] - error[%s]", url, e.getMessage()));
        }
        return null;
    }

    void updateDatabase() {
        try {
            FileUtils.write(new File(appStorage.jarPath + AppData.Config.Output.news), gson.toJson(data), AppData.charset);
            database.inserts(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}