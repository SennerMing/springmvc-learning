package club.musician.controller;

import club.musician.entity.Address;
import club.musician.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@Controller
@RequestMapping("/sessionAttributes")
@SessionAttributes(value = {"user"}, types = {String.class})
public class SessionAttributesController {

    /**
     * 这个user是放在了请求域中，在controller上添加了@SessionAttributes那么这个user
     * 既会在requestScope中也会在sessionAcope中
     *
     * @param map
     * @return
     */
    @RequestMapping("/testSession1")
    public String testSession(Map<String, Object> map) {

        User user = new User("SennerMing", 12, "SennerMing@musician.club.com",
                "123456", new Address("JiangSu", "nanjing"));

        map.put("user", user);//使用的是@SessionAttributes的value属性值
        map.put("school", "SennerMing School"); //使用的是@SessionAttributes的type属性值
        return "success";
    }


}
