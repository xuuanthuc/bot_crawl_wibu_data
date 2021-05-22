package util;

public interface AppData {
    String charset = "utf-8";
    int threadDefault = 5;
    int threadSleepDefault = 5000;

    interface Config {
        interface Input {
            String folder = "/config";
            String config = folder + "/config.json";
        }

        interface Output {
            String folder = "/out";
            String news = folder + "/news.json";
        }
    }

<<<<<<< HEAD
        String outFolder = "/out";
        String outNewsFile = outFolder + "/news.json";
=======
    interface Database {
        interface MySQL {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://%s/%s?user=%s&password=%s";
        }
>>>>>>> f2a6b4f24de34357571f13cca8e94c1d87f0a024
    }
}
