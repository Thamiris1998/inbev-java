package com.application.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

@Data
public class Product implements Serializable {

    @Id
    private String id = ObjectId.get().toHexString();
    private String name;
    private String description;
    private String brand;
    private Double price;
}
