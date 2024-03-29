package club.musician.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {


    /**
     * 使用@RequestMapping来映射URL的请求
     * 返回值会通过视图解析器解析为实际的物理视图，对于InternalResourceViewResolver视图解析器，会做如下解析：
     *      prefix + returnVal + 后缀这样的方式得到实际的物理视图，然后做转发操作
     *      /WEB-INF/views/success.jsp
     * @return
     */
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello world");
        return "success";
    }

}
