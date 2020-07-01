package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RestController
public class DefaultController {
    DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy - EEEE", new Locale("ru"));
    LocalDate localDate = LocalDate.now();

    @RequestMapping("/")
    public String webMethod() {
        String string = "Текущая дата - " + dayFormat.format(localDate);
        return string;
    }

}
