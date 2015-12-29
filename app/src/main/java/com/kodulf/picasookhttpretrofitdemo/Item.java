package com.kodulf.picasookhttpretrofitdemo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 15-12-29.
 */
public class Item
{
    private String userIcon;
    private String userName;
    private String content;
    private String image;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    private long userId;
    public Item() {
    }


    @Override
    public String toString() {
        return "Item{" +
                "userIcon='" + userIcon + '\'' +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public Item(JSONObject object) throws JSONException {
        if(!object.isNull("user")) {//因为有的是匿名发布的
            userIcon = object.getJSONObject("user").getString("icon");
            userName = object.getJSONObject("user").getString("login");
            userId= object.getJSONObject("user").getLong("id");
        }
        if(!object.isNull("image")) {
            image = object.getString("image");
        }
        content =object.getString("content");
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Item(String userIcon, String userName, String content, String image) {
        this.userIcon = userIcon;
        this.userName = userName;
        this.content = content;
        this.image = image;
    }
}
