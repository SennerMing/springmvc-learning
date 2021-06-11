package club.musician.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cookieValue")
public class CookieValueController {

    @RequestMapping("testCv1")
    public String testCookieValue(@CookieValue("JSESSIONID") String cv) {
        System.out.println("testCookieValue" + cv);
        return "success";
    }
}
