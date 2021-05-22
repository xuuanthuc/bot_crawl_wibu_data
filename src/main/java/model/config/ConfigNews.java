
package model.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConfigNews {
    @SerializedName("url")
    @Expose
    public String url = null;
    @SerializedName("query")
    @Expose
    public List<ConfigQuery> query = null;
    @SerializedName("maxPage")
    @Expose
    public Integer maxPage;
    @SerializedName("maxThread")
    @Expose
    public Integer maxThread;
    @SerializedName("restTime")
    @Expose
    public Long restTime;
    @SerializedName("sleepMin")
    @Expose
    public Integer sleepMin;
    @SerializedName("sleepMax")
    @Expose
    public Integer sleepMax;
}
