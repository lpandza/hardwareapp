package hr.tvz.pandza.hardwareapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDto {

    private Long id;
    private String title;
    private String text;
    private Integer score;


}
