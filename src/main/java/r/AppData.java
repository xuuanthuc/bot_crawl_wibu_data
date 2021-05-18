package r;

public class AppData {
    private static AppData single_instance = null;

    public static AppData getInstance() {
        if (single_instance == null)
            single_instance = new AppData();

        return single_instance;
    }

    public String jarPath;

    private AppData() {
        jarPath = System.getProperty("user.dir");
    }
}
