package m;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewGenre {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("slug")
    @Expose
    public String slug;
}