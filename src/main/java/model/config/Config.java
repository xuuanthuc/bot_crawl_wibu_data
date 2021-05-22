
package model.config;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Config {
    @SerializedName("domains")
    @Expose
    public ConfigDomain domain = null;
    @SerializedName("database")
    @Expose
    public ConfigDatabase database = null;
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
