package hr.tvz.pandza.hardwareapp.model;

import hr.tvz.pandza.hardwareapp.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hardware {
    private Long id;
    private String code;
    private String name;
    private Double price;
    private Type type;
    private Integer quantity;

    public Hardware() {

    }


}
