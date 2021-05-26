package bot.wallpaper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WallpaperSize {
    @SerializedName("height")
    @Expose
    public Long height;
    @SerializedName("width")
    @Expose
    public Long width;
}
