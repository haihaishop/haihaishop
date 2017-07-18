package com.shop.model.domain;

public class Goods {
  private Long goods_id;
  private String goods_name;
  private Long solder_id;
  private Double price;
  private String info;
  private Long views_time;
  private java.sql.Date date;
  private Long sold_number;
  private Long count;
  private String picture;
  private Long promotion_id;

  public Long getGoods_id() {
    return goods_id;
  }

  public void setGoods_id(Long goods_id) {
    this.goods_id = goods_id;
  }

  public String getGoods_name() {
    return goods_name;
  }

  public void setGoods_name(String goods_name) {
    this.goods_name = goods_name;
  }

  public Long getSolder_id() {
    return solder_id;
  }

  public void setSolder_id(Long solder_id) {
    this.solder_id = solder_id;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public Long getViews_time() {
    return views_time;
  }

  public void setViews_time(Long views_time) {
    this.views_time = views_time;
  }

  public java.sql.Date getDate() {
    return date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }

  public Long getSold_number() {
    return sold_number;
  }

  public void setSold_number(Long sold_number) {
    this.sold_number = sold_number;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public Long getPromotion_id() {
    return promotion_id;
  }

  public void setPromotion_id(Long promotion_id) {
    this.promotion_id = promotion_id;
  }
}
