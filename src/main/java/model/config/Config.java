
package model.config;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Config {
    @SerializedName("domains")
    @Expose
    public List<ConfigDomain> domains = null;
    @SerializedName("database")
    @Expose
    public List<ConfigDatabase> database = null;
    @SerializedName("news")
    @Expose
    public ConfigNews news;
    @SerializedName("wallpaper")
    @Expose
    public ConfigWallpaper wallpaper;
    @SerializedName("app")
    @Expose
    public ConfigApp app;
}
