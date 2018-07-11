package com.waheedsabir.shop.services;

public class Top_shop_itemlist {

    String id ;
    String  thumb ;
    String  name ;
    String  price ;
    String  images ;

    public Top_shop_itemlist(String id, String thumb, String name, String price, String images) {
        this.id = id;
        this.thumb = thumb;
        this.name = name;
        this.price = price;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
