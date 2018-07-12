package com.dawoo.lotterybox.adapter.hall.child.adapterset;

/**
 *
 * 一个娱乐项
 * Created by benson on 17-12-25.
 */

public class Item {
    private int id;
    private String name;
    private String url;
    private String imgUrl;

    public Item() {
    }

    public Item(int id, String name, String url, String imgUrl) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
