package com.shop.model.domain;

/**
 * Created by 18240 on 2017/7/24.
 */
public class OrderGoods {
    private Goods goods;
    private Order_form order;
    private Address address;
    private User buyer;
    private User seller;

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setOrder(Order_form order) {
        this.order = order;
    }

    public Order_form getOrder() {
        return order;
    }

    public Address getAddress(){return address; }

    public void setAddress(Address address){this.address = address; }
}
