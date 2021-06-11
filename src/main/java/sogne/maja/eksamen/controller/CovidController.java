package sogne.maja.eksamen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CovidController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/updateParish")
    public String update() {
        return "updateParish";
    }

    @GetMapping("/infectionPressure")
    public String infection() {
        return "infectionPressure";
    }

    @GetMapping("/parishes")
    public String parishes() {
        return "parishes";
    }

}
