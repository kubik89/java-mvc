package com.vb.javajunemvc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

// всі поля, які приходять із іншого сервісу які буду використовувати далі на веб сторінці
// RestTemplate - це клієнт від Spring який вміє надсилати реквести по урлі
@NoArgsConstructor
@Data
public class MovieDto {
// @JsonProperty("movieId") - змінити назву вхідного поля із movieId на id
    @JsonProperty("movieId")
    private int id;

    private String title;
    private int duration;
    private String directorName;
}
