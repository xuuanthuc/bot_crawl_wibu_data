package bot.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewDetail {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("type")
    @Expose
    public Integer type;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("comments_count")
    @Expose
    public Integer commentsCount;
    @SerializedName("views")
    @Expose
    public Integer views;
    @SerializedName("has_videos")
    @Expose
    public Boolean hasVideos;
    @SerializedName("is_featured")
    @Expose
    public Boolean isFeatured;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;
    @SerializedName("published_at")
    @Expose
    public String publishedAt;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("genre")
    @Expose
    public NewGenre genre;
    @SerializedName("thumbnail_small")
    @Expose
    public String thumbnailSmall;
}