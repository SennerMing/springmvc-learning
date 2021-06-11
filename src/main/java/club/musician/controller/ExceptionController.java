package club.musician.controller;

import club.musician.exception.GenderException;
import club.musician.exception.MyUserException;
import club.musician.exception.NameException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/exception")
public class ExceptionController {


    @RequestMapping("/testException.do")
    public ModelAndView doSome(String userName,Integer gender) throws MyUserException {
        ModelAndView modelAndView = new ModelAndView();
        //根据请求参数抛出异常
        if (!"zhangsan".equals(userName)) {
            throw new NameException("姓名不正确！");
        }
        if (gender == null || gender > 3) {
            throw new GenderException("性别不正确！");
        }

        modelAndView.addObject("userName", userName);
        modelAndView.addObject("gender", gender);
        modelAndView.setViewName("result");

        return modelAndView;
    }


}
