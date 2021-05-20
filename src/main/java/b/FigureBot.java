package b;

import m.NewDetail;
import m.NewInfo;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FigureBot extends BaseBot {
    private interface Config extends BaseConfig {
        String root = "https://tinanime.com/api/genres/nhan-vat/news/?p=%s";
    }

    List<NewDetail> data;

    public FigureBot() {
        data = new ArrayList<>();
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Start crawler for AnimeNewBot");
        executor.execute(() -> {
            try {
                String url = String.format(Config.root, 1);
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();
                String json = response.body().string();

                NewInfo news = gson.fromJson(json, NewInfo.class);
                if (news != null) {
//                    for (int i = 0; i < news.lastPage; i++) {
                    for (int i = 1; i < 50; i++) {
                        System.out.println(i);
                        List<NewDetail> temp = getNewsData(i);
                        if (temp != null) {
                            data.addAll(temp);
                            updateDatabase();
                        }
                        Thread.sleep(3000);
                    }

                }
                complete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private List<NewDetail> getNewsData(int i) {
        try {
            String url = String.format(Config.root, i);
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
            FileUtils.write(new File("out/figure.json"), gson.toJson(data), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
