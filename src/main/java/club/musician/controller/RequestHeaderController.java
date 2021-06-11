package club.musician.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/requestHeader")
public class RequestHeaderController {

    @RequestMapping("testRh1")
    public String testRh1(@RequestHeader("Accept-Language")String al) {
        System.out.println("testRh1...." + al);
        return "success";
    }
}
