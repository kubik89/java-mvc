package com.vb.javajunemvc;

// для створення movie в формі

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MovieCreateDto {

    private String title;
    private int duration;
    private int directorId;
}
