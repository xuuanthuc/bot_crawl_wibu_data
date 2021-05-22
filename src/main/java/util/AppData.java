package util;

public interface AppData {
    String charset = "utf-8";
    int threadDefault = 5;
    int threadSleepDefault = 1000;

    interface Config {
        String configFolder = "/config";
        String configFile = configFolder + "/config.json";

        String outFolder = "/out";
        String outNewsFile = outFolder + "/news.json";
    }
}
