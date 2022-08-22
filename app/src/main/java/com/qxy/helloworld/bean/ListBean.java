package com.qxy.helloworld.bean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Set;

@Entity(tableName = "listData")
public class ListBean {
    @SerializedName("active_time")
    private String activeTime;

    @SerializedName("description")
    private String description;

    @SerializedName("actors")
    private Set<String> actors;

    @SerializedName("areas")
    private Set<String> areas;

    @SerializedName("directors")
    private Set<String> directors;

    @SerializedName("discussion_hot")
    private long discussionHot;

    @SerializedName("hot")
    private long hot;

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("influence_hot")
    private long influenceHot;

    @SerializedName("maoyan_id")
    private long maoyanId;

    @SerializedName("name")
    private String name;

    @SerializedName("name_en")
    private String englishName;

    @SerializedName("poster")
    private String posterLinker;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("search_hot")
    private long searchHot;

    @SerializedName("tags")
    private Set<Set<String>> tags;

    @SerializedName("topic_hot")
    private long topicHot;

    @SerializedName("type")
    private int type;

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getActors() {
        return actors;
    }

    public void setActors(Set<String> actors) {
        this.actors = actors;
    }

    public Set<String> getAreas() {
        return areas;
    }

    public void setAreas(Set<String> areas) {
        this.areas = areas;
    }

    public Set<String> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<String> directors) {
        this.directors = directors;
    }

    public long getDiscussionHot() {
        return discussionHot;
    }

    public void setDiscussionHot(long discussionHot) {
        this.discussionHot = discussionHot;
    }

    public long getHot() {
        return hot;
    }

    public void setHot(long hot) {
        this.hot = hot;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInfluenceHot() {
        return influenceHot;
    }

    public void setInfluenceHot(long influenceHot) {
        this.influenceHot = influenceHot;
    }

    public long getMaoyanId() {
        return maoyanId;
    }

    public void setMaoyanId(long maoyanId) {
        this.maoyanId = maoyanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getPosterLinker() {
        return posterLinker;
    }

    public void setPosterLinker(String posterLinker) {
        this.posterLinker = posterLinker;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getSearchHot() {
        return searchHot;
    }

    public void setSearchHot(long searchHot) {
        this.searchHot = searchHot;
    }

    public Set<Set<String>> getTags() {
        return tags;
    }

    public void setTags(Set<Set<String>> tags) {
        this.tags = tags;
    }

    public long getTopicHot() {
        return topicHot;
    }

    public void setTopicHot(long topicHot) {
        this.topicHot = topicHot;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ListBean{" +
                "activeTime='" + activeTime + '\'' +
                ", description='" + description + '\'' +
                ", actors=" + actors +
                ", areas=" + areas +
                ", directors=" + directors +
                ", discussionHot=" + discussionHot +
                ", hot=" + hot +
                ", id=" + id +
                ", influenceHot=" + influenceHot +
                ", maoyanId=" + maoyanId +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", posterLinker='" + posterLinker + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", searchHot=" + searchHot +
                ", tags=" + tags +
                ", topicHot=" + topicHot +
                ", type=" + type +
                '}';
    }
}
