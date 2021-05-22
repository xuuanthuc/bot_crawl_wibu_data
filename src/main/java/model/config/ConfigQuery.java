package model.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfigQuery {
    @SerializedName("id")
    @Expose
    public Integer id = null;
    @SerializedName("name")
    @Expose
    public String name = null;
}
