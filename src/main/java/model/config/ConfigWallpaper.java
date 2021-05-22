
package model.config;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfigWallpaper {
    @SerializedName("query")
    @Expose
    public List<String> query = null;
    @SerializedName("maxPage")
    @Expose
    public Integer maxPage;
    @SerializedName("maxThread")
    @Expose
    public Integer maxThread;
    @SerializedName("restTime")
    @Expose
    public Long restTime;
}
