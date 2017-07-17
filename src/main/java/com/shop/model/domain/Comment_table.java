package com.shop.model.domain;

public class Comment_table {
  private Long comment_table_id;
  private Long goods_id;
  private Long user_id;
  private String comment;
  private Long rate;

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

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Long getRate() {
    return rate;
  }

  public void setRate(Long rate) {
    this.rate = rate;
  }
}
