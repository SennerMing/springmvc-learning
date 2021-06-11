package club.musician.controller;

import club.musician.entity.User;
import club.musician.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;

    @RequestMapping(value = "/getUserById.do")
    @ResponseBody
    public User getUser(@RequestParam("id") Integer id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "/register.do")
    public ModelAndView addUser(User user) {

        try {
            byte[] bb = user.getUserName().getBytes("ISO-8859-1");
            String userName = new String(bb, "UTF-8");
            user.setUserName(userName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String tips = "注册失败";
        ModelAndView modelAndView = new ModelAndView();
        //调用Service
        int nums = userService.addUser(user);
        if (nums > 0) {
            tips = "学生[" + user.getUserName() + "]注册成功！";
        }
        //添加数据
        modelAndView.addObject("tips", tips);
        //指定页面
        modelAndView.setViewName("result");
        return modelAndView;
    }


}
