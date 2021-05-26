package bot.wallpaper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WallpaperItems {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("type")
    @Expose
    public Integer type;
    @SerializedName("contentType")
    @Expose
    public String contentType;
    @SerializedName("downloadCount")
    @Expose
    public Long downloadCount;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("tags")
    @Expose
    public List<String> tags = null;
    @SerializedName("authorId")
    @Expose
    public String authorId;
    @SerializedName("authorName")
    @Expose
    public String authorName;
    @SerializedName("authorAvatarUrl")
    @Expose
    public String authorAvatarUrl;
    @SerializedName("shareUrl")
    @Expose
    public String shareUrl;
    @SerializedName("thumbUrl")
    @Expose
    public String thumbUrl;
    @SerializedName("downloadReference")
    @Expose
    public String downloadReference;
    @SerializedName("size")
    @Expose
    public Long size;
    @SerializedName("licenseType")
    @Expose
    public Long licenseType;
    @SerializedName("previewUrl")
    @Expose
    public String previewUrl;
    @SerializedName("wallpaperSize")
    @Expose
    public WallpaperSize wallpaperSize;

}

