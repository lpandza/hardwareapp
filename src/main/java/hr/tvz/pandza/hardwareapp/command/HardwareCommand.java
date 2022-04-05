package hr.tvz.pandza.hardwareapp.command;

import hr.tvz.pandza.hardwareapp.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
public class HardwareCommand {
    @NotBlank(message = "Code must not be empty")
    private String code;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotNull(message = "Price must not be empty")
    @Positive(message = "Price must be positive number")
    private Double price;

    @NotBlank(message = "Type must not be empty")
    private Type type;

    @NotNull(message = "Quantity must not be empty")
    @PositiveOrZero(message = "Quantity must be positive number or zero")
    private Integer quantity;
}
