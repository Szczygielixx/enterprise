package lab.bookings.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @RequestMapping(value = {"/", "/welcome"})
    public String index(@RequestParam(required = false) String name,
                        Model model) {
        if (name == null || name.isEmpty()) {
            name = "Stranger";
        }
        model.addAttribute("name", name);
        return "index";
    }
} 