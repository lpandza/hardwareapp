package hr.tvz.pandza.hardwareapp.model;

import hr.tvz.pandza.hardwareapp.enums.Type;

public class Hardware {
    private String code;
    private String name;
    private Double price;
    private Type type;
    private Integer quantity;

    public Hardware(String code, String name, Double price, Type type, Integer quantity) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Hardware{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", quantity=" + quantity +
                '}';
    }
}
