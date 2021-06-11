package club.musician.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/forwardRedirect")
public class ForwardRedirectController {

    /**
     * 处理器方法返回ModelAndView，实现转发forward
     * 语法：setViewName("forward:视图文件的完整路径")
     * forward: 不和视图解析器一同使用，就相当于项目中没有视图解析器
     * @return
     */
    @RequestMapping("/forward.do")
    public ModelAndView testForward() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tips", "Forward你好你好");
        //这个是转发
//        modelAndView.setViewName("result.jsp");
        //这个是显示转发，但是和上面操作感觉效果一样，意义不大
        /**
         * 那么这个意义何在呢？
         *  比如你的项目中，存在着视图解析器，但是你要转发的页面，
         *  没有再视图解析器配置的前缀配置的目录下，这个时候就可以使用这个forward
         */
        modelAndView.setViewName("forward:/WEB-INF/jsp/result.jsp");
        return modelAndView;
    }


    /**
     * 处理器方法返回ModelAndView，实现redirect
     * 语法：setViewName("redirect:视图完整的路径")
     * redirect：不能和视图解析器一起使用，就相当于项目中没有视图解析器
     * @return
     */
    @RequestMapping("/redirect.do")
    public ModelAndView testRedirect(String userName,Integer gender) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tips", "redirect成功啦");
        modelAndView.addObject("userName", userName);
        modelAndView.addObject("gender", gender);
        //做重定向
        modelAndView.setViewName("redirect:/result.jsp");
        //由浏览器用户请求，用户地址栏：
        // http://localhost:8080/springmvc_learning_war_exploded/result.jsp?tips=redirect%E6%88%90%E5%8A%9F%E5%95%A6&userName=asda&gender=1
        /**
         * 框架对重定向的一个操作，框架会把Model中简单类型的数据，转换为字符串使用，
         * 作为result.jsp的get请求参数来使用，目的是在/redirect.do和result.jsp两次请求之间传递数据
         *
         * 这样传递数据，那么result.jsp中的EL表达式，是无法取到值的
         * 因为是两次request的请求，不在同一个作用域了
         * 获取请求参数
         * <%=request.getParameter("userName")%>
         */


        return modelAndView;
    }


}
