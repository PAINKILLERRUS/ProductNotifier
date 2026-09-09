package by.developing.example.productmmcroservice.service.dto;

import java.math.BigDecimal;


public class CreateProductDTO {
    private String title;
    private BigDecimal price;
    private Integer quantity;

    public CreateProductDTO(String title, BigDecimal price, Integer quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public CreateProductDTO() {
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
