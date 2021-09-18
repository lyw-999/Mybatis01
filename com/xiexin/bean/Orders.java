package com.xiexin.bean;

import java.io.Serializable;
import java.time.Period;
import java.util.List;

/**
 * orders
 * @author 
 */
public class Orders implements Serializable {
    private Integer ordersId;

    private Integer personId;

    private Double totalPrice;

    private String addr;

    private static final long serialVersionUID = 1L;

    // 多对1  要写 1方的对象
    private Person person;

    @Override
    public String toString() {
        return "Orders{" +
                "ordersId=" + ordersId +
                ", personId=" + personId +
                ", totalPrice=" + totalPrice +
                ", addr='" + addr + '\'' +
                ", person=" + person +
                ", orderDetails=" + orderDetails +
                '}';
    }

    private List<OrderDetail> orderDetails;

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }



}