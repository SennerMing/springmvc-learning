package club.musician.controller;

import club.musician.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Controller
@RequestMapping("/void")
public class VoidController {

    @RequestMapping("/testVoid")
    public void testVoid(HttpServletResponse response, User user) {

        System.out.println("testVoid:" + user);
        response.setContentType("application/json;charset=utf-8");
        try {
            Writer writer = response.getWriter();
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(user);

            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
