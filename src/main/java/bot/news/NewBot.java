package bot.news;

import bot.BaseBot;
import model.config.ConfigQuery;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import util.AppData;
import util.AppSql;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class NewBot extends BaseBot {
    List<NewDetail> data;
    int current, total;

    public NewBot(int maxThread, long restTime) {
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
        String url = "";
        String json = "";
        try {
            url = String.format(appStorage.config.news.url, query.name, i);
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
            appSql.connect(connection -> {
                try {
                    connection.setAutoCommit(false);
                    String sqlInsert = String.format(
                            "INSERT INTO %s " +
                                    "(id, type, title, description, commentsCount, views, hasVideos, isFeatured, slug, thumbnail, publishedAt, content, genre, thumbnailSmall) " +
                                    "VALUES " +
                                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                            appStorage.config.database.table.news
                    );
                    PreparedStatement statement = connection.prepareStatement(sqlInsert);
                    for (int index = 0; index < data.size(); index++) {
                        NewDetail item = data.get(index);
                        statement.setInt(1, item.id);
                        statement.setInt(2, item.type);
                        statement.setString(3, item.title);
                        statement.setString(4, item.description);
                        statement.setInt(5, item.commentsCount);
                        statement.setInt(6, item.views);
                        statement.setBoolean(7, item.hasVideos);
                        statement.setBoolean(8, item.isFeatured);
                        statement.setString(9, item.slug);
                        statement.setString(10, item.thumbnail);
                        statement.setString(11, item.publishedAt);
                        statement.setString(12, item.content);
                        statement.setString(13, gson.toJson(item.genre));
                        statement.setString(14, item.thumbnailSmall);
                        statement.addBatch();
                    }
                    int[] updateCounts = statement.executeBatch();
                    connection.commit();
                    connection.setAutoCommit(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
