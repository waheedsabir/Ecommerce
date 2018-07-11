package com.waheedsabir.shop.model;

public class Cart_GetSet {


    int _id ;
    String p_name ;
    String p_id ;
    String p_shop_id ;
    String p_shop_name ;
    String p_image ;
    String p_quanity ;
    String p_price ;
    String p_detail ;
    String p_shop_address ;
    String p_shop_contact ;

    public Cart_GetSet(String p_name, String p_id, String p_shop_id, String p_image, String p_quanity, String p_price, String p_detail, String p_shop_address, String p_shop_contact) {
        this.p_name = p_name;
        this.p_id = p_id;
        this.p_shop_id = p_shop_id;
        this.p_image = p_image;
        this.p_quanity = p_quanity;
        this.p_price = p_price;
        this.p_detail = p_detail;
        this.p_shop_address = p_shop_address;
        this.p_shop_contact = p_shop_contact;
    }

    public Cart_GetSet(int _id, String p_name, String p_id, String p_shop_id, String p_shop_name, String p_image, String p_quanity, String p_price, String p_detail, String p_shop_address, String p_shop_contact) {
        this._id = _id;
        this.p_name = p_name;
        this.p_id = p_id;
        this.p_shop_id = p_shop_id;
        this.p_shop_name = p_shop_name;
        this.p_image = p_image;
        this.p_quanity = p_quanity;
        this.p_price = p_price;
        this.p_detail = p_detail;
        this.p_shop_address = p_shop_address;
        this.p_shop_contact = p_shop_contact;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_shop_id() {
        return p_shop_id;
    }

    public void setP_shop_id(String p_shop_id) {
        this.p_shop_id = p_shop_id;
    }

    public String getP_shop_name() {
        return p_shop_name;
    }

    public void setP_shop_name(String p_shop_name) {
        this.p_shop_name = p_shop_name;
    }

    public String getP_image() {
        return p_image;
    }

    public void setP_image(String p_image) {
        this.p_image = p_image;
    }

    public String getP_quanity() {
        return p_quanity;
    }

    public void setP_quanity(String p_quanity) {
        this.p_quanity = p_quanity;
    }

    public String getP_price() {
        return p_price;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }

    public String getP_detail() {
        return p_detail;
    }

    public void setP_detail(String p_detail) {
        this.p_detail = p_detail;
    }

    public String getP_shop_address() {
        return p_shop_address;
    }

    public void setP_shop_address(String p_shop_address) {
        this.p_shop_address = p_shop_address;
    }

    public String getP_shop_contact() {
        return p_shop_contact;
    }

    public void setP_shop_contact(String p_shop_contact) {
        this.p_shop_contact = p_shop_contact;
    }

    @Override
    public String toString() {
        return p_name ;
    }
}
