
package model.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfigApp {
    @SerializedName("timeCheck")
    @Expose
    public Integer timeCheck;
}
