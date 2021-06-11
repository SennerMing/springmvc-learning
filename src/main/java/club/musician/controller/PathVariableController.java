package club.musician.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pathVariable")
public class PathVariableController {

    private final static String SUCCESS = "success";

    /**
     * @PathVariable：可以来映射URL中的占位符，到目标方法的参数中
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        System.out.println("PathVariable..." + id);
        return SUCCESS;
    }

}
