package com.shop.model.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
  private Long user_id;
  private String username;
  private String nick_name;
  private String password;
  private String email;
  private String phone;
  private String name;
  private String id_card;
  private Long role_id;
  private boolean sex;
  private String is_authentication;
  private Date create_date;
  private boolean has_store;
  private String image;

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public boolean isHas_store() {
    return has_store;
  }

  public void setHas_store(boolean has_store) {
    this.has_store = has_store;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setCreate_date(Date date){
    this.create_date = date;
  }

  public Date getCreate_date(){
    return create_date;
  }

  public String getNick_name() {
    return nick_name;
  }

  public void setNick_name(String nick_name) {
    this.nick_name = nick_name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId_card() {
    return id_card;
  }

  public void setId_card(String id_card) {
    this.id_card = id_card;
  }

  public Long getRole_id() {
    return role_id;
  }

  public void setRole_id(Long role_id) {
    this.role_id = role_id;
  }

  public boolean getSex() {
    return sex;
  }

  public void setSex(boolean sex) {
    this.sex = sex;
  }

  public String getIs_authentication() {
    return is_authentication;
  }

  public void setIs_authentication(String is_authentication) {
    this.is_authentication = is_authentication;
  }
}
