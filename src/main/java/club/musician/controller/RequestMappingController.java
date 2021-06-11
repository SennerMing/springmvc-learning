package club.musician.controller;

import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/springmvc")
public class RequestMappingController {
    private final static String SUCCEESS = "success";

    /**
     * RequestingMapping除了修饰方法还可以修饰类
     * 类定义处：提供初步的请求映射信息，相对于WEB应用的根路径
     * 方法处：提供进一步的细节映射信息，相对于类定义处的URL，若类定义处未标注@RequestMappding,
     *      则方法处标记的URL相对于WEB应用的的根路径
     * @return
     */
    @RequestMapping("/testRm")
    public String testRequestMapping() {
        System.out.println("Requesting Mapping....");
        return SUCCEESS;
    }


    /**
     * @RequestMapping除了可以使用请求URL映射请求以外，还可以使用请求方法、请求参数及请求头映射请求
     *
     * @RequestMapping的value、method、params及heads分别表示请求URL、请求方法、请求参数及请求头
     * 的映射条件，他们之间是与的关系，联合使用多个条件可以让请求映射更加精确化
     *
     * params和headers支持简单的表达式：
     *  - param1：表示请求必须包含名为param1的请求参数
     *  - !param1：表示请求不能包含名为param1的请求参数
     *  - param1 != value1：表示请求包含名为param1的请求参数，但其值不能为value1
     *  - {"param1=value1","param2"}：请求必须包含名为param1和param2的两个请求参数，
     *      且param1参数的值必须为value1
     *
     * @return
     */
    @RequestMapping(value="testRM2",method = RequestMethod.POST)
    public String testRm2() {
        System.out.println("Requesting Mapping 2....");
        return SUCCEESS;
    }


    /**
     * prams:
     * 通过：
     * <a href="springmvc/testRM3?username=SennerMing&age=11">username=SennerMing&age=11</a><br>
     * <a href="springmvc/testRM3?username=SennerMing">username=SennerMing</a><br>
     *
     * 不通过：
     * <a href="springmvc/testRM3?age=11">age=11</a><br>
     * @return
     */
    @RequestMapping(value = "testRM3",params = {"username","age!= 10"})
    public String testRM3(){
        System.out.println("Requesting Mapping 3....");
        return SUCCEESS;
    }


    /**
     * headers:
     * 按照请求头进行匹配
     *      注意不是key:value，而是key=value
     * @return
     */
    @RequestMapping(value = "testRM4",headers = {"Connection=keep-alive"})
    public String testRM4(){
        System.out.println("Requesting Mapping 4....");
        return SUCCEESS;
    }


    @RequestMapping("/testRm5")
    public String testRM5(String name, Integer age) {
        System.out.println("testRM5():" + name + "age" + age);
        return SUCCEESS;
    }

}
