package com.shop.model.domain;


import java.util.Date;

public class Promotion {
  private Long promotion_id;
  private String name;
  private Long type;
  private Double discount;
  private Double full;
  private Double cut;
  private Long buy;
  private Long give;
  private Date from_time;
  private Date to_time;

  public Long getPromotion_id() {
    return promotion_id;
  }

  public void setPromotion_id(Long promotion_id) {
    this.promotion_id = promotion_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  public Double getDiscount() {
    return discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  public Double getFull() {
    return full;
  }

  public void setFull(Double full) {
    this.full = full;
  }

  public Double getCut() {
    return cut;
  }

  public void setCut(Double cut) {
    this.cut = cut;
  }

  public Long getBuy() {
    return buy;
  }

  public void setBuy(Long buy) {
    this.buy = buy;
  }

  public Long getGive() {
    return give;
  }

  public void setGive(Long give) {
    this.give = give;
  }

  public Date getFrom_time() {
    return from_time;
  }

  public void setFrom_time(Date from_time) {
    this.from_time = from_time;
  }

  public Date getTo_time() {
    return to_time;
  }

  public void setTo_time(Date to_time) {
    this.to_time = to_time;
  }
}
