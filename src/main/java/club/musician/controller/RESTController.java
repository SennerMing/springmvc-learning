package club.musician.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * REST 风格的URL
 *  以CRUD为例：
 *      新增: /order   POST
 *      修改: /order/1 PUT
 *      删除: /order/1 DELETE
 *      获取: /order/1 GET
 *
 *  如何发送PUT和DELETE请求：
 *      1、需要配置HiddenHttpMethodFilter
 *      2、需要发送POST请求
 *      3、需要在发送post请求时，携带一个name="_method"的隐藏域，值为PUT或是DELETE
 *
 *  在SpringMVC的目标方法中如何得到ID呢？
 *      使用@PathVariable注解进行获取
 */
@Controller
@RequestMapping("/rest")
public class RESTController {

    private final static String SUCCESS = "success";


    @RequestMapping(value = "/testDelete/{id}", method = RequestMethod.DELETE)
    public String testDelete(@PathVariable("id") Integer id) {
        System.out.println("REST TEST DELETE...." + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testPost", method = RequestMethod.POST)
    public String testPost() {
        System.out.println("REST TEST POST....");
        return SUCCESS;
    }


    @RequestMapping(value = "/testPut/{id}", method = RequestMethod.PUT)
    public String testPut(@PathVariable("id") Integer id) {
        System.out.println("REST TEST PUT...." + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testGet/{id}", method = RequestMethod.GET)
    public String testGet(@PathVariable("id") Integer id) {
        System.out.println("REST TEST GET...." + id);
        return SUCCESS;
    }

}
