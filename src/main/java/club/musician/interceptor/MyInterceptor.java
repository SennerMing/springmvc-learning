package club.musician.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class MyInterceptor implements HandlerInterceptor {

    /**
     * 预处理方法
     *      是整个项目的入口，门户。当preHandle返回true，请求可以被处理
     *       preHandle返回false，请求到此为止
     *
     *  特点：
     *      1.在控制器方法执行之前先执行的，也就是说，用户请求首先到达此方法
     *      2.在这可以获取请求的信息，验证请求是否符合要求
     *          用户是否登录、用户是否有权限访问某个链接地址
     *          如果验证失败，我们可以截断此请求
     *          如果验证成功，可以放行此拦截
     *
     * @param request
     * @param response
     * @param handler -- 被拦截的控制器对象
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器的MyInterceptor1的preHandle()");
        request.setAttribute("cost", System.currentTimeMillis());

        //如果返回false 给浏览器一个返回结果
//        request.setAttribute("tips","你不行！");
//        request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
//        return false;//返回false就到此为止了,后面的postHandle，afterCompletion()这个返回真，才执行呢
        return true;
    }

    /**
     * 后处理方法
     *      他想执行基本的条件是，前面这个preHandle这个方法返回的是true，
     *      对原来的Comtroller中的方法的结果，进行调整
     *
     * 方法特点：
     *      1.是在我们的处理器方法之后执行的(Controller.doSome())
     *      2.它能够获取处理器方法的返回值，可以修改ModelAndView中的数据和视图，可以影响到最后的执行结果
     *      3.主要对原来的执行结果进行二次修正
     *
     * @param request
     * @param response
     * @param handler  -- 被拦截的处理器对象
     * @param modelAndView -- 处理器方法的返回值
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器的MyInterceptor1的postHandle()");
        if (modelAndView != null) {
            modelAndView.addObject("interceptor", "postHandle");
//            modelAndView.setViewName("other");
        }
    }


    /**
     * 最后执行的方法
     *      做一些时间的计算啦
     *      从preHandle() ----> controller ----> postHandle ----> afterCompletion的执行时间
     *
     *
     * 方法特点：
     *      1.是在请求处理完成后执行的，那什么是请求处理完成的时候呢？
     *          SpringMVC规定是当你的视图处理完成后，对视图执行了forward。就认为请求处理完成的
     *      2.一般做资源回收工作的，程序请求过程中创建了一些对象，在这里可以进行删除，把占用的内存的空间回收
     *
     * @param request
     * @param response
     * @param handler -- 被拦截的处理器对象
     * @param ex -- 程序中发生的异常
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器的MyInterceptor1的afterCompletion()");
        long startTime = (long) request.getAttribute("cost");
        long endTime = System.currentTimeMillis();
        long cost = endTime - startTime;
        System.out.println(cost);
    }
}
