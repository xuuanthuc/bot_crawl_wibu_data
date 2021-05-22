package model.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewInfo {
    @SerializedName("current_page")
    @Expose
    public Integer currentPage;
    @SerializedName("first_page_url")
    @Expose
    public String firstPageUrl;
    @SerializedName("from")
    @Expose
    public int from;
    @SerializedName("last_page")
    @Expose
    public int lastPage;
    @SerializedName("last_page_url")
    @Expose
    public String lastPageUrl;
    @SerializedName("next_page_url")
    @Expose
    public String nextPageUrl;
    @SerializedName("path")
    @Expose
    public String path;
    @SerializedName("per_page")
    @Expose
    public int perPage;
    @SerializedName("prev_page_url")
    @Expose
    public Object prevPageUrl;
    @SerializedName("to")
    @Expose
    public Integer to;
    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("data")
    @Expose
    public List<NewDetail> data = null;
}