package com.thesis.springboot.vetclinicsystemcore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;

@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Min(value = 1, message = "Quantity should have a minimum value of 1")
    private Integer quantity;
    @Min(value = 1, message = "Sub Total should have a minimum value of 1")
    private Float subTotal;
    @Min(value = 1, message = "Total should have a minimum value of 1")
    private Float total;
    @Enumerated(EnumType.STRING)
    @Column(name = "orderStatus")
    private orderStatus status = orderStatus.INPROGRESS;
    private String orderNumber;
    private String confirmed_timestamp;

    @JsonIgnoreProperties("orders")
    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    @JsonIgnoreProperties("orders")
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public OrderProduct() {
    }

    
    public OrderProduct(Integer id,
            @Min(value = 1, message = "Quantity should have a minimum value of 1") Integer quantity,
            @Min(value = 1, message = "Sub Total should have a minimum value of 1") Float subTotal,
            @Min(value = 1, message = "Total should have a minimum value of 1") Float total, orderStatus status,
            String orderNumber, String confirmed_timestamp) {
        this.id = id;
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.total = total;
        this.status = status;
        this.orderNumber = orderNumber;
        this.confirmed_timestamp = confirmed_timestamp;
    }


    public enum orderStatus {
        INPROGRESS, COMPLETED
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public orderStatus getStatus() {
        return status;
    }

    public void setStatus(orderStatus status) {
        this.status = status;
    }

    public String getConfirmed_timestamp() {
        return confirmed_timestamp;
    }

    public void setConfirmed_timestamp(String confirmed_timestamp) {
        this.confirmed_timestamp = confirmed_timestamp;
    }



    public String getOrderNumber() {
        return orderNumber;
    }


    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    @Override
    public String toString() {
        return "OrderProduct [id=" + id + ", quantity=" + quantity + ", subTotal=" + subTotal + ", total=" + total
                + ", status=" + status + ", orderNumber=" + orderNumber + ", confirmed_timestamp=" + confirmed_timestamp
                + ", inventory=" + inventory + ", client=" + client + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((subTotal == null) ? 0 : subTotal.hashCode());
        result = prime * result + ((total == null) ? 0 : total.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
        result = prime * result + ((confirmed_timestamp == null) ? 0 : confirmed_timestamp.hashCode());
        result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderProduct other = (OrderProduct) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (subTotal == null) {
            if (other.subTotal != null)
                return false;
        } else if (!subTotal.equals(other.subTotal))
            return false;
        if (total == null) {
            if (other.total != null)
                return false;
        } else if (!total.equals(other.total))
            return false;
        if (status != other.status)
            return false;
        if (orderNumber == null) {
            if (other.orderNumber != null)
                return false;
        } else if (!orderNumber.equals(other.orderNumber))
            return false;
        if (confirmed_timestamp == null) {
            if (other.confirmed_timestamp != null)
                return false;
        } else if (!confirmed_timestamp.equals(other.confirmed_timestamp))
            return false;
        if (inventory == null) {
            if (other.inventory != null)
                return false;
        } else if (!inventory.equals(other.inventory))
            return false;
        if (client == null) {
            if (other.client != null)
                return false;
        } else if (!client.equals(other.client))
            return false;
        return true;
    }


    

    

   

    


    

    
}
