package hr.tvz.pandza.hardwareapp.model;

import hr.tvz.pandza.hardwareapp.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Integer quantity;

    @OneToMany
    private Set<Review> review;

    public Hardware() {}

    public Hardware(Long id, String code, String name, Double price, Type type, Integer quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }
}
