package com.shop.model.domain;

/**
 * Created by 18240 on 2017/7/24.
 */
public class OrderGoods {
    private Goods goods;
    private Order_form order;

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
}
