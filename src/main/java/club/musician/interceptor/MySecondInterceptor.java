package club.musician.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MySecondInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器的MyInterceptor2的preHandle()");
        request.setAttribute("cost", System.currentTimeMillis());


        /**
         * 验证用户是否登录
         */
        String name = (String) request.getSession().getAttribute("name");
        if (name != null) {
            if ("zhangsan".equals(name)) {
                System.out.println("是张三可以登录的");
            }else{
                request.setAttribute("tips","Interceptor非张三不能登录！");
                request.getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);
                return false;
            }
        }else{
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器的MyInterceptor2的postHandle()");
        if (modelAndView != null) {
            modelAndView.addObject("interceptor", "postHandle");
//            modelAndView.setViewName("other");
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器的MyInterceptor2的afterCompletion()");
        long startTime = (long) request.getAttribute("cost");
        long endTime = System.currentTimeMillis();
        long cost = endTime - startTime;
        System.out.println(cost);
    }
}
