package club.musician.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ant")
public class RequestMappingAnt {

    public final static String SUCCESS = "success";


    @RequestMapping("/testAnt/*/abc")
    public String testAntPath() {
        System.out.println("testAntPath.......");

        return SUCCESS;
    }

}
