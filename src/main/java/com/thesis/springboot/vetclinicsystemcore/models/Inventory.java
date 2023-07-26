package com.thesis.springboot.vetclinicsystemcore.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min=2, message = "Product Name should have atleast 2 characters")
    private String name;
    @Size(min=2, message = "Category should have atleast 2 characters")
    private String category;
    @Min(value = 0, message = "Stock should have a minimum of 0 value")
    private Integer stock;
    @Min(value = 1, message = "Price should have a minimum of 1 value")
    private Float price;
    @Column(columnDefinition = "LONGTEXT")
    private String base64Data;

    @JsonIgnoreProperties("inventory")
    @OneToMany(mappedBy = "inventory" , cascade = CascadeType.ALL)
    private List<OrderProduct> orders;

    public Inventory() {
    }

    public Inventory(Integer id, @Size(min = 2, message = "Product Name should have atleast 2 characters") String name,
            @Size(min = 2, message = "Category should have atleast 2 characters") String category,
            @Min(value = 1, message = "Stock should have a minimum of 1 value") Integer stock,
            @Min(value = 1, message = "Price should have a minimum of 1 value") Float price, String base64Data) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.base64Data = base64Data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }

    public List<OrderProduct> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderProduct> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Inventory [id=" + id + ", name=" + name + ", category=" + category + ", stock=" + stock + ", price="
                + price + ", base64Data=" + base64Data + ", orders=" + orders + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((stock == null) ? 0 : stock.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((base64Data == null) ? 0 : base64Data.hashCode());
        result = prime * result + ((orders == null) ? 0 : orders.hashCode());
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
        Inventory other = (Inventory) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (stock == null) {
            if (other.stock != null)
                return false;
        } else if (!stock.equals(other.stock))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (base64Data == null) {
            if (other.base64Data != null)
                return false;
        } else if (!base64Data.equals(other.base64Data))
            return false;
        if (orders == null) {
            if (other.orders != null)
                return false;
        } else if (!orders.equals(other.orders))
            return false;
        return true;
    }

    
    

    
    
}
