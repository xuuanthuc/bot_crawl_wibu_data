
package model.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfigDatabase {
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("port")
    @Expose
    public Integer port;
    @SerializedName("user")
    @Expose
    public String user;
    @SerializedName("pass")
    @Expose
    public String pass;
}
