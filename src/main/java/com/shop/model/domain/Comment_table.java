package com.shop.model.domain;


import java.util.Date;

public class Comment_table {
  private Long comment_table_id;
  private Long goods_id;
  private String comment;
  private String username;
  private float rate;
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  private Date comment_date;

  public Date getComment_date() {
    return comment_date;
  }

  public void setComment_date(Date comment_date) {
    this.comment_date = comment_date;
  }

  public Long getComment_table_id() {
    return comment_table_id;
  }

  public void setComment_table_id(Long comment_table_id) {
    this.comment_table_id = comment_table_id;
  }

  public Long getGoods_id() {
    return goods_id;
  }

  public void setGoods_id(Long goods_id) {
    this.goods_id = goods_id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public float getRate() {
    return rate;
  }

  public void setRate(float rate) {
    this.rate = rate;
  }
}
