package club.musician.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/requestParam")
public class RequestParamController {
    private final static String SUCCESS = "success";

    /**
     * @RequestParam来映射请求参数
     * value:值即请求参数名称
     * required：该参数是否必须，默认为false
     * defaultValue：请求参数的默认值
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("testRp1")
    public String testRequestParam(@RequestParam(value = "name",required = false) String name,
                                   @RequestParam(value = "age",defaultValue = "0") Integer age) {
        System.out.println("testRequestParam()..." + name + "  " + age);
        return SUCCESS;
    }


}
