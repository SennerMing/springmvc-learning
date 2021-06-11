package club.musician.controller;

import club.musician.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/responseBody")
public class ResponseBodyController {


    /**
     * 返回对象框架的处理流程
     *  1.框架会把返回User类型，调用框架中的ArrayList<HttpMessageConverter>中每个类的canWrite()方法
     *      检查哪个HttpMessageConverter接口实现类能够处理User类型的数据--MappdingJackson2HttpMessageConverter
     *
     *  2.框架会调用实现类的write()，MappingJackson2HttpMessageConverter的write()方法
     *      把SennerMing这个User对象转换为json,调用Jackson的ObjectMapper实现转换为json
     *      使用"application/json;charset=UTF-8"作为contentType
     *
     *  3.框架会调用@ResponseBody把上一步的的结果输出到浏览器，ajax请求处理完成
     * @param id
     * @return
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Integer id) {
        User user = new User();
        user.setUserName("SennerMing");
        user.setAge(12);
        user.setEmail("SennerMing@musician.club");
        return user;
    }

    /**
     * 会被转换成JSONArray，而且还会保留顺序的
     * @return
     */
    @RequestMapping("/getUsers")
    @ResponseBody
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserName("SennerMing");
        user.setAge(12);
        user.setEmail("SennerMing@musician.club");
        users.add(user);
        return users;
    }


    /**
     * 默认使用"text/plain;charset=ISO-8859-1"作为contentType，导致中文有乱码的
     * 解决方案：给RequestMapping增加一个属性produces，使用这个属性指定新的contentType
     * 那他为什么不能使用之前配置好的修改字符集编码的过滤器呢？
     * 因为它是使用response直接写回用户的，他并不走哪个过滤器
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getMessage",produces = {"text/plain;charset=UTF-8"})
    public String getMessage() {
        return "Hello Spring MVC 你好你好！";

    }

}
