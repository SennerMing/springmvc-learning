package club.musician.controller;

import club.musician.entity.Address;
import club.musician.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pojoBind")
public class PojoBindController {

    @RequestMapping("testPb1")
    public String testPojoBind(User user) {
        System.out.println(user);
        return "success";
    }

}
