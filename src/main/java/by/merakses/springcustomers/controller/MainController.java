package by.merakses.springcustomers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private static final String HOME_PAGE_NAME = "index";
    private static final String ABOUT_PAGE_NAME = "about";

    @GetMapping
    public String getHomePage() {
        return HOME_PAGE_NAME;
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return ABOUT_PAGE_NAME;
    }
}
