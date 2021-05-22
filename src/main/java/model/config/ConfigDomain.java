
package model.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfigDomain {
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("port")
    @Expose
    public Integer port;
}
