package com.vb.javajunemvc;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Я створюю свій клас який буде стукати на сервіс іншої програми, витягувати дані які прийдуть і передавати
// їх далі на веб сторінку.
// в даному випадку по запиту http:\\localhost:8081/movies?page=1 і авторизації username=vbodnar3 приходять
// такі дані
//  "movies": [
//        {
//            "movieId": 6,
//            "title": "Movie3",
//            "duration": 57,
//            "directorName": "Dir1"
//        }, і інші такі ж обєкти
//        ],
//        "totalPages": 3
// оскільки я відображаю на веб лише movies, то мені не треба мати totalPages, то
// - створюю тут новий клас MovieDto
// - і називаю ліст по назві масиву який приходить movies (якщо треба змінити назву, то використаю @JsonProperty)
@NoArgsConstructor
@Data
public class MovieGetDto {

    private List<MovieDto> movies;
}
