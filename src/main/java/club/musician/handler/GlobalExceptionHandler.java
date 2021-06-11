package club.musician.handler;

import club.musician.exception.GenderException;
import club.musician.exception.NameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 控制器的增强，额外方法，AOP编程
 *  必须让SpringMVC框架知道这个注解修饰的类，所在的包名，需要在SpringMVC框架配置文件汇总，声明组件扫描器
 *      指定@ControllerAdvice所在包名
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //定义方法来处理发生的异常

    /**
     * 处理异常的方法和控制器方法一样，可以有多个参数，
     * 可以由modelAndView，String，void，对象类型的返回值
     *
     * 可以有个形参，这个形参表示controller中抛出的异常对象,
     * 可以通过该对象获取异常信息
     *
     * 还要为函数添加一个注解
     * @ExceptionHandelr(异常的class)：表示异常的类型，当发生此异常时，由此方法(handler)进行处理
     */
    @ExceptionHandler(value = {NameException.class})
    public ModelAndView doNameException(Exception e) {
        //处理NameException
        /**
         * 异常发生时，我们要做些什么
         *  1.记录异常，保存到数据库中，日志文件中
         *      发生时间，哪个方法发生的，异常错误内容
         *  2.发送通知，把异常信息通过邮件，短信，微信发送给相关的人员
         *  3.给用户比较友好的提示
         */

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tips", "用户的姓名必须是zhangsan");
        modelAndView.addObject("ex", e);
        modelAndView.setViewName("nameerror");
        return modelAndView;
    }


    @ExceptionHandler(value = {GenderException.class})
    public ModelAndView doGenderException(Exception e) {
        //处理NameException
        /**
         * 异常发生时，我们要做些什么
         *  1.记录异常，保存到数据库中，日志文件中
         *      发生时间，哪个方法发生的，异常错误内容
         *  2.发送通知，把异常信息通过邮件，短信，微信发送给相关的人员
         *  3.给用户比较友好的提示
         */

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tips", "性别不能大于3");
        modelAndView.addObject("ex", e);
        modelAndView.setViewName("gendererror");
        return modelAndView;
    }

    //处理其他异常，NameException，GenderException以外的不知名的异常
    /**
     * 不加value就，如果与上面NameException和GenderException都不匹配，那么就走这个方法
     * 他相当于
     * if(){
     *
     * }else if(){
     *
     * }else{
     *    相当于在这
     * }
     * @param e
     * @return
     */
    @ExceptionHandler
    public ModelAndView doOtherException(Exception e) {
        //处理NameException
        /**
         * 异常发生时，我们要做些什么
         *  1.记录异常，保存到数据库中，日志文件中
         *      发生时间，哪个方法发生的，异常错误内容
         *  2.发送通知，把异常信息通过邮件，短信，微信发送给相关的人员
         *  3.给用户比较友好的提示
         */

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tips", "未知错误");
        modelAndView.addObject("ex", e);
        modelAndView.setViewName("error");
        return modelAndView;
    }


}
