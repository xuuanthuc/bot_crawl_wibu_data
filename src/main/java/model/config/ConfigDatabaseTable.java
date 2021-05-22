
package model.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfigDatabaseTable {
    @SerializedName("news")
    @Expose
    public String news;
    @SerializedName("wallpaper")
    @Expose
    public String wallpaper;
}

