package com.shop.model.domain;

public class User_address {
  private Long user_address_id;
  private Long user_id;
  private Long address_id;

  public Long getUser_address_id() {
    return user_address_id;
  }

  public void setUser_address_id(Long user_address_id) {
    this.user_address_id = user_address_id;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public Long getAddress_id() {
    return address_id;
  }

  public void setAddress_id(Long address_id) {
    this.address_id = address_id;
  }
}
