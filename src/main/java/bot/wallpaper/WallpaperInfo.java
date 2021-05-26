package bot.wallpaper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WallpaperInfo {
    @SerializedName("items")
    @Expose
    public List<WallpaperItems> items = null;
    @SerializedName("navigation")
    @Expose
    public Object navigation;
}

