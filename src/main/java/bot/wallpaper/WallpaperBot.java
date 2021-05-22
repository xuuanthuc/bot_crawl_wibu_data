package bot.wallpaper;

import bot.BaseBot;
import model.config.ConfigQuery;
import model.news.NewDetail;
import model.news.NewInfo;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import util.AppData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WallpaperBot extends BaseBot {
    List<NewDetail> data;
    int current, total;

    public WallpaperBot(int maxThread, long restTime) {
        super(maxThread, restTime);
        data = new ArrayList<>();
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
            System.out.println(url);
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            NewInfo news = gson.fromJson(json, NewInfo.class);
            int max = appStorage.config.news.maxPage;
            for (int newIndex = 1; newIndex < Math.min(news.lastPage, max); newIndex++) {
                List<NewDetail> newDetails = getNewsData(query, newIndex);
                if (newDetails != null) {
                    for (int newDetailIndex = 0; newDetailIndex < newDetails.size(); newDetailIndex++) {
                        NewDetail buffer = newDetails.get(newDetailIndex);
                        buffer.type = query.id;
                        newDetails.set(newDetailIndex, buffer);
                    }
                    data.addAll(newDetails);
                }
                Thread.sleep(AppData.threadSleepDefault);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<NewDetail> getNewsData(ConfigQuery query, int i) {
        try {
            String url = String.format(appStorage.config.news.url, query.name, i);
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            NewInfo news = gson.fromJson(json, NewInfo.class);
            if (news != null && news.data != null && news.data.size() > 0) {
                return news.data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    void updateDatabase() {
        try {
            FileUtils.write(new File(AppData.Config.outNewsFile), gson.toJson(data), AppData.charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}