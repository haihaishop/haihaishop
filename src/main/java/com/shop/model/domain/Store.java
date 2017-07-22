package com.my.db;

public class Store {
  private Long store_id;
  private Long user_id;
  private String store_name;
  private String store_info;
  private java.sql.Timestamp create_date;
  private Long store_status;

  public Long getStore_id() {
    return store_id;
  }

  public void setStore_id(Long store_id) {
    this.store_id = store_id;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public String getStore_name() {
    return store_name;
  }

  public void setStore_name(String store_name) {
    this.store_name = store_name;
  }

  public String getStore_info() {
    return store_info;
  }

  public void setStore_info(String store_info) {
    this.store_info = store_info;
  }

  public java.sql.Timestamp getCreate_date() {
    return create_date;
  }

  public void setCreate_date(java.sql.Timestamp create_date) {
    this.create_date = create_date;
  }

  public Long getStore_status() {
    return store_status;
  }

  public void setStore_status(Long store_status) {
    this.store_status = store_status;
  }
}
