package com.shop.model.domain;

public class Address {
  private Long address_id;
  private String province;
  private String city;
  private String country;
  private String detail_address;
  private String zip_code;

  public Long getAddress_id() {
    return address_id;
  }

  public void setAddress_id(Long address_id) {
    this.address_id = address_id;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getDetail_address() {
    return detail_address;
  }

  public void setDetail_address(String detail_address) {
    this.detail_address = detail_address;
  }

  public String getZip_code() {
    return zip_code;
  }

  public void setZip_code(String zip_code) {
    this.zip_code = zip_code;
  }
}
