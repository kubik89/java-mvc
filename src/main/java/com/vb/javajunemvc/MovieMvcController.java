package com.vb.javajunemvc;

import com.sun.net.httpserver.Headers;
import lombok.extern.log4j.Log4j;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.Base64;

@Controller
public class MovieMvcController {

    // створюю окремо клас RestTemplateConfig в якому опишу RestTemplate з якого сюди буде приходити Bean
    @Autowired
    @Qualifier(value = "movieRestTemplate")
    private RestTemplate restTemplate;

    // ми створюємо Get запит по урлі;
// HttpEntity.EMPTY - хедери, а саме Authorization хедер (шифрований пароль і логін);
// дані прийдуть класу MovieGetDto
// Model - передає дані, що прийшли, на веб сторінку як модель
    @GetMapping("/")
    public String index(Model model) {

        String user = "vbodnar3";
        String password = "12345223";
        String credentials = user + ":" + password;
        String auth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", auth);

        HttpEntity<Void> httpEntity = new HttpEntity<Void>(headers);

        ResponseEntity<MovieGetDto> responseEntity = restTemplate.exchange("http://localhost:8081/movies?page=1&size=20",
                HttpMethod.GET, httpEntity, MovieGetDto.class);
// перевіряємо чи статус відповіді з урли коректний, тоді повернути body із урли
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            MovieGetDto body = responseEntity.getBody();
            model.addAttribute("movies", body.getMovies());
            model.addAttribute("cinemaName", "Cinema 1");
// передаємо атрибут із пост запиту, який нижче
            model.addAttribute("newMovie", new MovieCreateDto());
        }

        return "index";
    }

    @PostMapping("/movie")
    public String saveMovie(MovieCreateDto movie) {
        String user = "vbodnar3";
        String password = "12345223";
        String credentials = user + ":" + password;
        String auth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", auth);

        HttpEntity<MovieCreateDto> httpEntity = new HttpEntity<>(movie, headers);

// по урлі ми передаємо в httpEntity логін та пароль, і поля з класу MovieDto (важливо щоб співпадали назви з тими
// полями, які приймаються на сервісі, який записує в базу)

        restTemplate.exchange("http://localhost:8081/movies", HttpMethod.POST, httpEntity, MovieDto.class);
        return "redirect:/";
    }
}
