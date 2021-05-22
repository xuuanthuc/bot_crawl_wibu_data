
package model.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfigDatabase {
    @SerializedName("domain")
    @Expose
    public String domain;
    @SerializedName("port")
    @Expose
    public Integer port;
    @SerializedName("schema")
    @Expose
    public String schema;
    @SerializedName("user")
    @Expose
    public String user;
    @SerializedName("pass")
    @Expose
    public String pass;
    @SerializedName("table")
    @Expose
    public ConfigDatabaseTable table;
}
