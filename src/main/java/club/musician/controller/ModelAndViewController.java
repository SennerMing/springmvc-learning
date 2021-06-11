package club.musician.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;


/**
 * 能处理请求的都是控制器(处理器)：XxxXxxController能处理请求，叫做后端控制器(Backend Controller)
 */
@Controller
@RequestMapping("/modelAndView")
public class ModelAndViewController {

    /**
     * 目标方法的返回值，可以是ModelAndView
     *      可以包含视图和模型的信息
     *
     * 无论你返回的是String还是Map最终Spring都会转成ModelAndView
     *      SpringMVC会把ModelAndView的 model中的数据，放到request域对象中
     *
     * @return
     */
    @RequestMapping("testMv1")
    public ModelAndView testModelAndView() {
        System.out.println("testModelAndView");
        String viewName = "success";
        ModelAndView modelAndView = new ModelAndView();
        /**
         * 指定视图，指定视图的完整路径
         * 框架对视图执行forward操作，request.getRequestDispatcher("/success.jsp").forward(...)
         */
        modelAndView.setViewName(viewName);
        //添加模型数据到ModelAndView中
        /**
         * 框架在请求的最后把数据放到request作用域
         * request.setAttribute("time",new Date());
         */
        modelAndView.addObject("time", new Date());
        return modelAndView;
    }

}
