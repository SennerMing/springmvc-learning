package club.musician.controller;

import club.musician.exception.GenderException;
import club.musician.exception.MyUserException;
import club.musician.exception.NameException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/interceptor")
public class InterceptorController {

    @RequestMapping("/testInterceptor.do")
    public ModelAndView doSome(String userName, Integer gender) throws MyUserException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", userName);
        modelAndView.addObject("gender", gender);
        modelAndView.setViewName("result");
        System.out.println("InterceptorController doSome()....");
        return modelAndView;
    }
}
