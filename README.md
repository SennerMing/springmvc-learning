# SpringMVC

## 1 SpringMVC的理解

### 1.1 什么是MVC

**MVC是三个单词的首字母缩写，它们是Model（模型）、View（视图）和Controller（控制）。**

这个模式认为，程序不论简单或复杂，从结构上看，都可以分成三层。

```markdown
1）最上面的一层，是直接面向最终用户的"视图层"（View）。它是提供给用户的操作界面，是程序的外壳。
2）最底下的一层，是核心的"数据层"（Model），也就是程序需要操作的数据或信息。
3）中间的一层，就是"控制层"（Controller），它负责根据用户从"视图层"输入的指令，选取"数据层"中的数据，然后对其进行相应的操作，产生最终结果。
```

这三层是紧密联系在一起的，但又是互相独立的，每一层内部的变化不影响其他层。每一层都对外提供接口（Interface），供上面一层调用。这样一来，软件就可以实现模块化，修改外观或者变更数据都不用修改其他层，大大方便了维护和升级。

### 1.2 Spring MVC简介

SpringMVC 是基于Spring的一个框架，实际上就是Spring Framework的一个模块，它是专门是做web开发的。可以理解成是Servlet的一个升级版，我们使用Servlet做开发是比较核心的，也是最基础的，在功能上比较弱，开发起来比较的不方便。

SpringMVC出现的目的就是为了解决这些痛点的，SpringMVC是在Servlet的基础上加入了一些功能，让我们做web开发更方便。

Spring是一个大的容器，通过IOC控制并管理我们的Java对象，使用<bean>标签，或者通过@Component注解进行创建对象，SpringMVC也是一个容器，他是专门放置控制器的容器对象的，对其进行控制及管理。

简单的来讲，我们要做得是使用@Controller创建控制器对象，把对象放入到SpringMVC容器中，把创建的对象作为控制器使用，这个控制器对象能够接收用户的请求，显示处理结果，就当作是一个Servlet使用。

我们使用@Controller注解创建的是一个普通的类对象，不是Servlet，SpringMVC对其赋予了控制器对象的额外的能力。

```markdown
一、 当用于发送了一个请求给Tomcat服务器，这个请求肯定还是到了Servlet中，那我们SpringMVC是怎样让@Controller完成这个控制器的功能的呢？
答：SpringMVC的底层呢有一个类：DispacherServlet，这个类实际上是一个Servlet，他负责接收用户的所有请求，用户把请求给了DispacherServlet，之后这个DispacherServlet把请求转发给了我们的@Controller对象，最后是@Controller对象进行用户请求的处理的。

流程是这样的：
index.jsp ---> DispatcherServlet(中央调度器) ----> 转发，分配给@Controller对象(最终由它进行处理的) ----> 将结果返回个DispatcherServlet（中央调度器）
```

```markdown
SpringMVC简介
SpringMVC也叫Spring Web MVC，是Spring框架的一部分，是在Spring3.0之后发布的。
```

```markdown
SpringMVC优点
1.基于MVC架构，功能分工明确，解耦合
2.容易理解上手快，使用简单
3.作为Spring框架的一部分，能够使用Spring的IOC和AOP，方便整合Mybatis，Hibernate，JPA等其他框架
4.SpringMVC强化了注解的使用，在控制器，业务层，持久层都可使用注解，特别方便
```

**回忆之前在老师公司**

Spring框架提供了轻量级MVC框架，类似Struts2

```markdown
学习内容：
	框架环境的搭建
	第一个Controller/Action的开发
	接收请求参数
	作用域操作
	跳转
```

```markdown
特点：
	SpringMVC天然集成Spring(SpringMVC就是Spring技术的一部分)
	SpringMVC自带小Spring工厂，SpringMVC控制器可以使用Spring工厂管理
	SpringMVC性能比Struts2略高
	SpringMVC映射控制器访问路径可以使用注解，就比较方便，程序猿可以专注于业务逻辑的开发
```

再次回顾：

SpringMVC是做web开发框架，实际是Spring框架中的一个模块。

SpringMVC的使用时基于Spring的，容器的概念IOC，SpringMVC会创建容器，WebApplicationContext，SpringMVC作为容器是创建和管理控制器对象的，使用@Controller创建控制器对象。

三层框架的对应

- 界面层，接收用户请求，显示处理结果的
- 业务层，处理业务逻辑的，Spring创建Service与DAO，工具类等对象
- 持久层，访问数据库的，对数据增删改查，Mybatis

SpringMVC底层访问依然是Servlet，DispatcherServlet(中央调度器)

DispatcherServlet会在服务器启动时进行初始化

- 创建WebApplicationContext，读取配置文件，进而创建控制器对象
- 是一个Servlet，要接收用户的请求，显示处理的结果



## 2 SpringMVC开发

需求：用户在页面上发起一个请求，请求交给SpringMVC的控制器对象，并显示请求的处理结果(在结果页面显示一个欢迎语句)。

实现步骤

1. 新建Web maven工程
2. 加入依赖
   1. Spring-webmvc的依赖，他就代表着SpringMVC的框架，它会间接的把Spring框架的依赖都加入到项目中
   2. JSP及Servlet的依赖，SpringMVC帮我们包装了这个Servlet，并没有将其代替
3. 重点：在web.xml中注册SpringMVC框架的核心对象，DispatcherServlet这个中央调度器
   1. DispatcherServlet叫做中央调度器，他是一个Servlet的，他的父类是集成HttpServlet
   2. DispatcherSservlet也叫做前端控制器(front controller)，负责接收用户提交的请求，调用其他的控制器对象，并把请求的处理结果显示给用户
4. 创建一个发起请求的页面，已有的index.jsp
5. 创建控制器类
   1. 在类上面加入@Controller注解，创建对象，并放入到SpringMVC容器中
   2. 在类中的方法上加入@RequestMapping注解
6. 创建SpringMVC的配置文件(Spring的配置文件一样)
   1. 声明组件扫描器，指定@Controller注解所在的包名
   2. 声明视图解析器，帮助处理视图的



SpringMVC处理web**请求中的注解**

@Controller:创建控制器类的对象，接收请求，处理请求

@RequestMappding:请求映射时，把请求绑定到一个方法，让这个方法处理请求

- value：请求uri地址，唯一值
- method：请求的方式，使用RequestMethod类的枚举类



SpringMVC处理web**请求中的参数接收**

HttpServletRequest,HttpServletRespones,HttpSession可以直接出现在处理器方法的参数位置

逐个接收参数，处理器方法的形参名和请求中参数名一样，按名称对应赋值

解决请求中post方式中有中文乱码，使用过滤器解决问题，CharacterEncodingFilter





### 2.1 加入依赖

pom.xml

```xml
<!--
        加的是Servlet的依赖
     -->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.3.5</version>
    </dependency>
```

### 2.2 配置中央调度器

Web.xml

```xml
<!--
        声明，注册SpringMVC的核心对象DispatcherServlet(中央控制器)
        这个contextConfiguration设置的是FrameworkServlet的参数，会被用以在创建ApplicationContext时
        使用XML配置文件进行来初始化的

        默认的配置文件/WEB-INF/[<servlet-name>-springDispatcherServlet]-servlet.xml
    -->
    <servlet>
        <servlet-name>springDispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <!-- DispatcherServlet 拦截所有请求-->
    <servlet-mapping>
        <servlet-name>springDispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```

Spring-mvc.xml

```xml
<context:component-scan base-package="club.musician"/>

    <!-- 配置视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
```

controller

```java
package club.musician.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {


    /**
     * 使用@RequestMapping来映射URL的请求
     * 返回值会通过视图解析器解析为实际的物理视图，对于InternalResourceViewResolver视图解析器，会做如下解析：
     *      prefix + returnVal + 后缀这样的方式得到实际的物理视图，然后做转发操作
     *      /WEB-INF/views/success.jsp
     * @return
     */
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello world");
        return "success";
    }
}
```

/WEB-INF/views/success.jsp可以随便写

### 2.3 RequestMapping

这个@RequestMapping用法，@Requestmappding(value={"/some.do","/first.do"})

```java

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
```



@RequestMappding还支持ANT风格资源地址的映射请求

一、ANT三种通配符

1.  ? : 匹配文件名中的一个字符
2. *: 匹配文件名中的任意字符
3. ** : 匹配多层路径

二、@RequestMapping还支持ANT风格的URL

1. /user/*/createUser   : 匹配/user/aaa/createUser、/user/bbb/createUser等URL
2. /user/**/careateUser : 匹配/user/createUser、/user/aa/bb/createUser等
3. /user/createUser??:匹配/user/createUseraa、/user/createUserbb等URL

```java
 @RequestMapping("/testAnt/*/abc")
    public String testAntPath() {
        System.out.println("testAntPath.......");

        return SUCCESS;
    }

```

### 2.4 PathVariable

@PathVariable映射URL绑定的占位符

- 带占位符的URL是Spring3.0新增的功能，该功能在SpringMVC向REST目标挺近发展过程中具有里程碑的意义
- 通过@PathVariable可以将URL中占位符参数绑定到控制器方法的入参中：URL中的{xxx}占位符可以通过@PathVariable("xxx")绑定到操作方法的入参中。

```java
@RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        System.out.println("PathVariable..." + id);
        return SUCCESS;
    }
```

#### 2.4.1 REST

Resources - 资源：URI即为每一个资源的第一无二的识别符，网络上的一个实体，或者说是网络上的一个具体信息。

Representation - 表现层：把资源具体呈现出来的形式，xml、json、二进制格式。

State Transfer - 状态转化：如果客户端想要操作服务器，必须通过某种手段，让服务器发生状态转化，而这种转化是建立在表现层之上的，所以就是“表现层状态转化”。

具体说就是Http协议里面，四个表示操作方式的动词：GET,POST,PUT,DELETE。

他们分别对应四种基本操作：**GET用来获取资源**，**POST用来新建资源**，**PUT用来更新资源**，**DELETE用来删除资源**

举个例子：

```markdown
实例：
 - /order/1 Http GET    :  得到id=1的order
 - /order   Http POST   :  新增order
 - /order/1 Http DELETE :  删除id=1的order
 - /order/1 Http PUT		:  更新id=1的order
```

HiddenHttpMethodFilter : 浏览器Form表单只支持GET和POST请求，而DELTE,PUT等method并不支持，Spring3.0添加了一个HiddenHttpMethodFilter这个过滤器，可以将这些请求转换为标准的http方法，是的支持GET,POST,PUT与DELETE请求。 

#### 2.4.2 HiddenHttpMethodFilter

Web.xml

```xml
<!-- HiddenHttpMethodFilter 可以将POST请求转换为DELETE或POST请求 -->
    <filter>
        <filter-name>hiddenHttpMethodFilter</filter-name>
        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
    </filter>

    <filter-mapping>
        <filter-name>hiddenHttpMethodFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

#### 2.4.3 REST用法

前台发送

```html
<!-- REST 风格开始 -->

<form action="rest/testDelete/1" method="post">
    <input type="text" name="_method" value="DELETE">
    <input type="submit" value="TEST REST DELETE">
</form><br>

<a href="rest/testGet/1">TEST REST GET</a><br>


<form action="rest/testPut/3" method="post">
    <input type="text" name="_method" value="PUT">
    <input type="submit" value="TEST REST PUT">
</form><br>

<form action="rest/testPost" method="post">
    <input type="text" name="_method" value="POST">
    <input type="submit" value="TEST REST POST">
</form><br>

<!-- REST 风格结束 -->
```



后台接收

```java
@RequestMapping(value = "/testDelete/{id}", method = RequestMethod.DELETE)
    public String testDelete(@PathVariable("id") Integer id) {
        System.out.println("REST TEST DELETE...." + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testPost", method = RequestMethod.POST)
    public String testPost() {
        System.out.println("REST TEST POST....");
        return SUCCESS;
    }


    @RequestMapping(value = "/testPut/{id}", method = RequestMethod.PUT)
    public String testPut(@PathVariable("id") Integer id) {
        System.out.println("REST TEST PUT...." + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testGet/{id}", method = RequestMethod.GET)
    public String testGet(@PathVariable("id") Integer id) {
        System.out.println("REST TEST GET...." + id);
        return SUCCESS;
    }
```

### 2.5 RequestParam

使用@RequestParam可以进行请求参数的绑定，传递给请求的方法 ，value：参数名称，required：是否必须，默认为true，表示请求参数中必须包含对应的参数，若不存在，则抛出异常

```java
@Controller
@RequestMapping("/requestParam")
public class RequestParamController {
    private final static String SUCCESS = "success";

    /**
     * @RequestParam来映射请求参数
     * value:值即请求参数名称
     * required：该参数是否必须，默认为false
     * defaultValue：请求参数的默认值
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("testRp1")
    public String testRequestParam(@RequestParam(value = "name",required = false) String name,
                                   @RequestParam(value = "age",defaultValue = "0") Integer age) {
        System.out.println("testRequestParam()..." + name + "  " + age);
        return SUCCESS;
    }

}
```



### 2.6 RequestHeader

请求头中包含了若干个属性，服务器可根据此获得客户端的信息，通过@RequestHeader即可将请求头中的属性值绑定到处理方法的入参中，很少用到

```java
package club.musician.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/requestHeader")
public class RequestHeaderController {

    @RequestMapping("testRh1")
    public String testRh1(@RequestHeader("Accept-Language")String al) {
        System.out.println("testRh1...." + al);
        return "success";
    }
}
```



### 2.7 CookieValue

@CookieValue可以让处理方法入参绑定某个Cookie的值

```JAVA
package club.musician.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cookieValue")
public class CookieValueController {

    @RequestMapping("testCv1")
    public String testCookieValue(@CookieValue("JSESSIONID") String cv) {
        System.out.println("testCookieValue" + cv);
        return "success";
    }
}
```



### 2.8 POJO对象绑定请求

SpringMVC会按照请求参数名和POJO属性名进行自动匹配，自动为该对象填充属性值，支持级联属性，如dept.deptId、dept.address.tel等

```java
package club.musician.controller;

import club.musician.entity.Address;
import club.musician.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pojoBind")
public class PojoBindController {

    @RequestMapping("testPb1")
    public String testPojoBind(User user) {
        System.out.println(user);
        return "success";
    }
}
```



### 2.9 Servlet原生参数

Servlet中有许多原生的API我们在SpringMVC的控制器中能使用的到

- HttpServletRequest
- HttpServletResponse
- HttpSession
- java.security.Principal
- Locale
- InputStream
- OutputStream
- Reader
- Writer

```java
package club.musician.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Controller
@RequestMapping("servletApi")
public class ServletApiController {

    /**
     * - HttpServletRequest
     * - HttpServletResponse
     * - HttpSession
     * - java.security.Principal
     * - Locale
     * - InputStream
     * - OutputStream
     * - Reader
     * - Writer
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("testApi1")
    public void testApi(HttpServletRequest request, HttpServletResponse response,Writer writer) {
        System.out.println("testApi() request" + request);
        System.out.println("testApi() response" + response);
        try {
            writer.write("hello Spring mvc");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```



### 2.10 处理模型数据

Spring MVC提供了以下几种途径输出模型数据：

- ModelAndView：处理方法返回值类型为ModelAndView时，方法体即可通过该对象添加模型数据
- Map及Model：入参为Org.springframework.ui.Model、org.springframework.ui.ModelMap或java.util.Map时，处理方法返回时，Map中的数据会被自动添加到模型中
- @SessionAttributes：将模型中的某个属性暂存到HttpSession中，以便过个请求之间可以共享这个属性
- @ModelAttribute：方法入参标注该注解后，入参的对象就会放到数据模型中



#### 2.10.1 ModelAndView

控制器处理方法的返回值如果为ModelAndView，则其既包含视图信息，也包含模型数据信息。

添加模型数据：

```java
ModelAndView addObject(String attributeName,Object attributeValue);
ModelAndView addAllObject(Map<String,?) modelMap
```

设置视图：

```java
void setView(View view);
void setViewName(String viewName);
```



#### 2.10.2 Map及Model

Spring MVC在内部使用了一个org.springframework.ui.Model接口存储数据模型

具体步骤

1. Spring MVC在调用方法前会创建一个隐含的模型对象作为模型数据的存储容器
2. 如果刚发的入参为Map或者Model类型，SpringMVC会将隐含模型的引用传递给这些入参。在方法体内，开发者可以通过这个入参对象访问到模型中的所有数据，也可以向模型中添加新的属性数据

ModelMap实现了Map接口，ExtendedModelMap实现Model并继承了ModelMap

```java
package club.musician.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("/mapAndModel")
public class MapAndModelController {


    /**
     * 这个map实际上是一个BindingAwareModelMap extends ExtendedModelMap
     * 而这个ExtendModelMap extends ModelMap implements Model
     * @param map
     * @return
     */
    @RequestMapping("testMap1")
    public String testMap(Map<String, Object> map) {
        System.out.println(map.getClass().getName());
        for(String key : map.keySet())
            System.out.println(key);
        map.put("names", Arrays.asList("Tom", "Jerry", "SennerMing"));
        return "success";
    }
}
```

#### 2.10.3 SessionAttributes

如果希望在多个请求之间共用某个模型属性数据，则可以在控制器类上标注一个@SessionAttributes，SpringMVC将在模型中对应的属性暂存到HttpSession中

@SessionAttributes除了可以通过属性名指定需要放到会话中的属性外，还可以通过模型属性的对象类型指定那些模型属性需要放到会话中

- @SessionAttributes(type=User.class)会将隐含模型中所有的类型为User.class的属性添加到会话中
- @SessionAttributes(value={"user1","user2"})
- @SessionAttributes(types={User.class,Department.class})
- @SessionAttributes(value={"user1","user2"},types={Department.class})

```java
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
```

#### 2.10.4 ModelAttribute

`@ModelAttribute`注解用于将方法的参数或方法的返回值绑定到指定的模型属性上，并返回给Web视图。具体用法整理如下：

##### 2.10.4.1 注释方法

下面的1），2），3）这三个例子类似，被`@ModelAttribute`注解注释的方法会在**此controller每个方法执行前**被执行，因此对于一个controller映射多个URL的用法来说，要谨慎使用。

1）`@ModelAttribute`注释void返回值的方法

```java
@Controller
public class HelloWorldController {

    @ModelAttribute
    public void populateModel(@RequestParam String abc, Model model) {
       model.addAttribute("attributeName", abc);
    }

    @RequestMapping(value = "/helloWorld")
    public String helloWorld() {
       return "helloWorld";
    }
}
```

**说明：**
这个例子，在获得请求`/helloWorld`后，`populateModel`方法在`helloWorld`方法之前先被调用，它把请求参数`（/helloWorld?abc=text）`加入到一个名为`attributeName`的model属性中，在它执行后`helloWorld`被调用，返回视图名`helloWorld`和model已由`@ModelAttribute`方法生产好了。

这个例子中model属性名称和model属性对象有model.addAttribute()实现，不过前提是要在方法中加入一个Model类型的参数。

当URL或者post中不包含参数时，会报错，其实不需要这个方法，完全可以把请求的方法写成下面的样子，这样缺少此参数也不会出错：

```java
@RequestMapping(value = "/helloWorld")
public String helloWorld(String abc) {
   return "helloWorld";
}
```

2）`@ModelAttribute`注释返回具体类的方法

```java
@ModelAttribute
public Account addAccount(@RequestParam String number) {
   return accountManager.findAccount(number);
}
```

**说明：** 

这种情况，model属性的名称没有指定，它由返回类型隐含表示，如这个方法返回Account类型，那么这个model属性的名称是`account`。这个例子中model属性名称有返回对象类型隐含表示，model属性对象的值就是方法的返回值。它无须要特定的参数。

3）`@ModelAttribute("attributeName")`注释返回具体类的方法

```java
@Controller
public class HelloWorldController {

    @ModelAttribute("attributeName")
    public String addAccount(@RequestParam String abc) {
       return abc;
    }

    @RequestMapping(value = "/helloWorld")
    public String helloWorld() {
       return "helloWorld";
    }
}
```

**说明：**
这个例子中使用`@ModelAttribute`注释，并使用注解指定的`attributeName`属性来指定model属性的名称。model属性对象的值就是方法的返回值。它无须要特定的参数。

4）`@ModelAttribute`和`@RequestMapping`同时注释一个方法

```java
@Controller
public class HelloWorldController {

    @RequestMapping(value = "/helloWorld.do")
    @ModelAttribute("attributeName")
    public String helloWorld() {
       return "hi";
    }
}
```

**说明：**
这时这个方法的返回值并不是表示一个视图名称，而是model属性的值，视图名称由`RequestToViewNameTranslator`根据请求”/helloWorld.do”转换为逻辑视图helloWorld。

Model属性名称由`@ModelAttribute("attributeName")`指定，相当于在request中封装了`key=attributeName，value=hi`。

##### 2.10.1.2 注释一个方法的参数

1）从model中获取

```
@Controller
public class HelloWorldController {

    @ModelAttribute("user")
    public User addAccount() {
       return new User("jz","123");
    }

    @RequestMapping(value = "/helloWorld")
    public String helloWorld(@ModelAttribute("user") User user) {
       user.setUserName("jizhou");
       return "helloWorld";
    }
}
```

**说明：**
在这个例子里，`@ModelAttribute("user") User user`注释方法参数，参数user的值来源于addAccount()方法中的model属性。此时如果方法体没有标注`@SessionAttributes("user")`，那么scope为request，如果标注了，那么scope为session。

2）从Form表单或URL参数中获取（实际上，不做此注释也能拿到user对象）

```
@Controller
public class HelloWorldController {

    @RequestMapping(value = "/helloWorld")
    public String helloWorld(@ModelAttribute User user) {
       return "helloWorld";
    }
}
```

**说明：**
注意这时候这个User类一定要有无参数的构造函数。

### 2.11 编码问题

注意：

​	在提交请求参数时，get请求方式中文没有乱码

​	使用post方式提交请求，中文有轮吗，需要使用过滤器处理乱码问题

过滤器可以自定义，也可以使用框架中提供的过滤器 CharacterEncodingFilter

web.xml

```xml
<!-- CharacterEncodingFilter解决请求参数乱码 问题 -->
<filter>
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <!-- 设置项目中的字符编码 -->
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
    <!-- 强制请求对象(HttpServletRequest)使用encoding编码的值 -->
    <init-param>
        <param-name>forceRequestEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
    <!-- 强制应答对象(HttpServletResponse)使用encoding编码的值 -->
    <init-param>
        <param-name>forceResponseEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>characterEncodingFilter</filter-name>
    <!-- 表示强制所有请求先通过此过滤器 -->
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

```markdown
1.CharacterEncodingFilter.doFilterInternal();
2.CompositeFilter.VirtualFilterChain.doFilter();//调用下一个过滤器
```





## 3.1 Spring MVC 视图

视图（View）和视图解析器（ViewResolver）的工作流程：

当请求处理方法处理完请求之后，会返回String、ModelAndView或View对象，如return “success”；但返回值最终都会被SpringMVC统一转为ModelAndView对象并返回；随后Spring就会用ViewResolver，把返回的ModelAndView对象中的View渲染给用户看（即返回给浏览器）

### 3.1 视图

视图View的作用是渲染数据，将数据以JSP、PDF、EXCEL等形式呈现给用户。SpringMVC通过View接口来支持视图，该接口提供了各种各样的视图，并且可以让用户自定义视图。

在客户端的每一次请求时，视图解析器ViewResolver都会产生一个新的视图View对象。

**视图View接口的实现类及部分简介如下**

| 视图类型              |                                                              | 简介                                                         |
| --------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| URL视图资源图         | InternalResourceView                                         | 将JSP或其他资源封装成一个视图。被视图解析器InternalResourceViewResolver默认使用。 |
| JstlView              | InternalResourceView的子类。如果JSP中使用了JSTL的国际化标签，就需要使用该视图类。 |                                                              |
| 文档视图              | AbstractExcelView                                            | Excel文档视图的抽象类。                                      |
| AbstractPdfView       | PDF文档视图的抽象类                                          |                                                              |
| 报表视图              | ConfigurableJasperReportsView                                | 常用的JasperReports报表视图                                  |
| JasperReportsHtmlView |                                                              |                                                              |
| JasperReportsPdfView  |                                                              |                                                              |
| JasperReportsXlsView  |                                                              |                                                              |
| JSON视图              | MappingJackson2JsonView                                      | 将数据通过Jackson框架的ObjectMapper对象，以JSON方式输出      |

### 3.2  视图解析器

SpringMVC提供了一个视图解析器的上级接口ViewResolver，所有具体的视图解析器必须实现该接口。

**常用的视图解析器实现类及简介如下**

| 视图解析器类型 |                              | 简介                                                         |
| -------------- | ---------------------------- | ------------------------------------------------------------ |
| 解析为bean     | BeanNameViewResolver         | 将视图解析后，映射成一个bean，视图的名字就是bean的id。       |
| 解析为映射文件 | InternalResourceViewResolver | 将视图解析后，映射成一个资源文件。例如将一个视图名为字符串“success.jsp”的视图解析后，映射成一个名为success的JSP文件。 |
|                | JasperReportsViewResolver    | 将视图解析后，映射成一个报表文件。                           |
| 解析为模板文件 | FreeMarkerViewResolver       | 将视图解析后，映射成一个FreeMarker模板文件。                 |
|                | VelocityViewResolver         | 将视图解析后，映射成一个Velocity模板文件。                   |
|                | VelocityLayoutViewResolver   | 将视图解析后，映射成一个Velocity模板文件。                   |

InternalResourceViewResolver是JSP最常用的视图解析器，可以通过`prefix`给响应字符串加上前缀，通过`suffix`加上后缀。例如我们之前曾在springMVC的配置文件中配置了一个视图解析器InternalResourceViewResolver，如下：

```xml
<beans>
<!-- 配置视图解析器：把handler处理类的返回值，加工成最终的视图路径-->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
<property name="prefix" value="/views/"></property>
<property name="suffix" value=".jsp"></property>
</bean>
</beans>
```

此外，视图解析器还可以通过解析JstlView进而实现国际化、通过解析`<mvc:view-controller>`进而指定请求的跳转路径、通过“redirect:”和“forward:”指定跳转方式等等。

### 3.3 JSP访问控制

JSP放在webapp下用户可以直接进行访问，我们可以将其放在WEB-INF/下，用户不可见的

```java
//可以通过
modelAndView.setViewName("/WEB-INF/xxx/xxx.jsp");
```

那像这样写，每次这样写/WEB-INF/每次都很麻烦，那怎么办呢？

```xml
<!-- 
		可以声明SpringMVC框架中的视图解析器，帮助开发人员设置视图文件的路径 
		覆盖里面参数，不需要再给他ID了
-->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
  <property name="prefix" value="/WEB-INF/views/"/>
  <property name="suffix" value=".jsp"/>
</bean>
```

```java
//当配置了视图解析器后，可以使用逻辑名称(文件名)，指定视图
modelAndView.setViewName("xxx");//视图解析器会自动在前面添加"/WEB-INF/xxx" + xxx + ".jsp"
```

### 3.4 返回ModelAndView

若处理器方法处理完后，需要跳转到其他资源，且又要在跳转forward的资源间传递数据，此时处理器方法返回ModelAndView比较好。当然，若要返回ModelAndView，则处理器方法中需要定义modelAndView对象。

在使用时，若该处理器方法只是进行跳转而不传递数据，或只是传递数据而并不向任何资源跳转(如对页面的Ajax异步响应)，此时若返回ModelAndView，则将总是有一部分多余：要么Model多余，要么View多余。即此时返回ModelAndView将不再合适。

### 3.5 返回String

配合着视图解析器，那就是视图解析器的名称；没有配置视图解析器的话，那么就表示完整的视图路径

### 3.6 返回Void

不能表示数据，也不能表示视图，我们在处理Ajax的时候就可以使用这个void返回值

咱么之前写Servlet的时候，doGet():void，我们响应Ajax请求的时候，都是用HttpServletResponse进行输出数据的，ajax请求服务器端返回的就是数据，和视图无关。

```java
package club.musician.controller;

import club.musician.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Controller
@RequestMapping("/void")
public class VoidController {

    @RequestMapping("/testVoid")
    public void testVoid(HttpServletResponse response, User user) {

        System.out.println("testVoid:" + user);
        response.setContentType("application/json;charset=utf-8");
        try {
            Writer writer = response.getWriter();
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(user);

            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
```

手工实现Ajax，获取json数据，代码有重复的，通过response进行json数据的返回，代码也是有重复的，这个框架是可以帮我们做的

### 3.7 返回Object

处理器方法也可以返回Object对象。这个object对象可以是Integer，String，自定义对象，Map，list等等。但返回的对象不是作为逻辑视图出现的，而是作为直接在页面显示的数据出现的。

返回对象，需要使用@ResponseBody注解，将转换后的JSON数据放入到响应体中。

对象有属性，属性就是数据，所以返回object表示的数据，和视图无关。可以使用对象表示数据，相应ajax请求

```markdown
现在做Ajax，主要使用json数据格式，实现步骤：
1. 加入处理json的工具库的依赖，spring，vc默认使用jsckson
2. 在SpringMVC配置文件中加入<mvc:annotation-driven>注解驱动,完成java对象转json字符串
3. 在处理器方法上加入@ResponseBody注解
```

springmvc处理器方法返回Object，可以转换为json输出到浏览器，相应ajax的内部原理

#### 3.7.1 <mvc:annotation-driven>注解驱动。

没加这个注解驱动前messageConverter中有四个转换器：

1.ByteArrayHttpMessageConverter

2.StringHttpMessageConverter

3.SourceHttpMessageConverter

4.AllEncompassingFormHttpMessageConverter

注解驱动实现的功能是，完成java对象到json、xml、test、二进制等数据格式的转换。

这个驱动在加入到SpringMVC配置文件后，回去自动创建HttpMessageConverter的7个实现类对象，包括：

MappingJackson2HttpMessageConverter(使用jackson工具库中的objectMapper实现java对象转json的功能)

**实现类**

| HttpMessageConverter接口实现类          | 作用                                                         |
| --------------------------------------- | ------------------------------------------------------------ |
| ByteArrayHttpMessageConverter           | 负责读取二进制格式的数据和写出二进制格式的数据               |
| StringHttpMessageConverter              | 负责读取字符串格式的数据和写出字符串格式的数据               |
| ResourceHttpMessageConverter            | 负责读取资源文件和写出资源文件数据                           |
| SourceHttpMessageConverter              | 能够读写来自Http的请求与相应的javax.xml.transform.Source，支持DOMSource，SAXSource和StreamSource的XML格式数据 |
| AllEncompassingFormHttpMessageConverter | 负责处理表单数据                                             |
| Jaxb2RootElementHttpMessageConverter    | 使用JAXB负责读取和写入xml标签格式的数据                      |
| MappingJackson2HttpMessageConverter     | 负责读取和写入json格式的数据。利用Jackson的ObjectMapper读写json数据，操作Object类型数据，可读取application/json，相应媒体类型为application/json |
| ResourceRegionHttpMessageConverter      | 负责ResourceRegion转换，ResourceRegion：资源实现的区域，由资源中的位置和该区域长度的字节计数具体化。 |

会涉及到一个HttpMessageConverter接口：消息转换器，它定义了java对象到上述数据格式转换的方法，这个接口有很多的实现类，就是为了完成转换的这个事。

- canWrite()作用检查处理器方法的返回值，能不能转换为response指定"Media-Type"，如果检查能转为json，他就返回true，Media-Type：表示格式，例如：json，xml等等

- write：把处理器方法的返回值对象，调用jackson中的objectMapper转换为json字符串

  json = objectMapper.writeValueAsString(user);

```java
package club.musician.controller;

import club.musician.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/responseBody")
public class ResponseBodyController {


    /**
     * 返回对象框架的处理流程
     *  1.框架会把返回User类型，调用框架中的ArrayList<HttpMessageConverter>中每个类的canWrite()方法
     *      检查哪个HttpMessageConverter接口实现类能够处理User类型的数据--MappdingJackson2HttpMessageConverter
     *  
     *  2.框架会调用实现类的write()，MappingJackson2HttpMessageConverter的write()方法
     *      把SennerMing这个User对象转换为json,调用Jackson的ObjectMapper实现转换为json
     *      使用"application/json;charset=UTF-8"作为contentType
     *  
     *  3.框架会调用@ResponseBody把上一步的的结果输出到浏览器，ajax请求处理完成
     * @param id
     * @return
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Integer id) {
        User user = new User();
        user.setUserName("SennerMing");
        user.setAge(12);
        user.setEmail("SennerMing@musician.club");
        return user;
    }
  
  	/**
     * 会被转换成JSONArray，而且还会保留顺序的
     * @return
     */
    @RequestMapping("/getUsers")
    @ResponseBody
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserName("SennerMing");
        user.setAge(12);
        user.setEmail("SennerMing@musician.club");
        users.add(user);
        return users;
    }	
}
```

#### 3.7.2 @ResponseBody返回String

这个时候返回的String就是数据了

```java
/**
 * 默认使用"text/plain;charset=ISO-8859-1"作为contentType，导致中文有乱码的
 * 解决方案：给RequestMapping增加一个属性produces，使用这个属性指定新的contentType
 * 那他为什么不能使用之前配置好的修改字符集编码的过滤器呢？因为它是使用response直接写回用户的，他并不走哪个过滤器
 * @return
 */
@ResponseBody
@RequestMapping(value = "getMessage",produces = {"text/plain;charset=UTF-8"})
public String getMessage() {
    return "Hello Spring MVC 你好你好！";

}
```

### 3.8 访问静态资源

研究中央调度器的url-pattern为"/"，比如我们访问/index.jsp，那么这个jsp是谁处理的呢？其实是这tomcat(jsp会转为servlet)，像这些个jquery.js、test.html啊还有img.jpg等文件其实也都是tomcat进行处理的，他们都是静态资源文件。

那怎么做的请求requestTest/getUser是谁处理呢？他是被我们的SpringMVC框架的DispatcherServlet拦截处理掉了。

```markdown
tomcat本身能处理静态资源的访问，像是.js、.jpg、.html都是由tomcat进行处理的，他们都是静态资源
tomcat根目录 ---> conf/web.xml ---> <servlet>有个DefaultServlet他是load-on-startup为1，服务器启动时就创建了
```

```xml
<!-- 看看这个Servlet上面的注释写的啥 -->
<!-- 
	The default servlet for all web applications,that serves static resources. it processes all requests that are not mapped to other servlets with servlet mappings(defined either here or in your own web.xml file).
1.处理静态资源
2.处理未映射到servlet的请求
-->
```

```markdown
那我们的DispatcherServlet设置了servlet-mapping 的 url-pattern设置为"/"了，那所有的请求路径，都会被我们的Spring的Dispatcherservlet拦截到了，就不会走Tomcat的DefaultServlet了

那么在默认情况下，这个DispatcherServlet(中央调度器)没有处理静态资源的能力:.js.html.jpg等，动态资源是可以访问的，那怎么才能让他有处理静态资源的能力呢？
```

#### 3.8.1 default-servlet-handler

声明了<mvc:default-servlet-handelr/>后，SpringMVC框架会在容器中创建DefaultServletHttpRequestHandler处理器对象，它会像一个检察员，对进入DispatcherServlet的URL进行筛查，如果发现是静态资源的请求，就将该请求转由Web应用服务器默认的Servlet进行处理，一般的服务器都有默认的Servlet。Tomcat就有一个DefaultServlet在/conf/web.xml中配置的，随服务器启动而启动的

```xml
<!--
        第一种处理静态资源的方式：
            1.需要在SpringMVC配置文件中加入下面这个标签
            
            2.原理是：加入这个标签后，框架会创建空气制对象DefaultServletHttpRequestHandler
            （类似创建我们自己的DefaultServlet,像是一Controller）,这个DefaultServletHttpRequestHandler
            会把接收到的请求(访问静态资源的)转发给tomcat的DefaultServlet
     -->
    <mvc:default-servlet-handler></mvc:default-servlet-handler>
```

这个缺点就是，需要使用的服务器有这个DefaultServlet的功能，否则就会失败

#### 3.8.2 resources

在Spring3.0版本后，Spring专门定义了专门用于处理静态资源访问请求的处理器ResourceHttpRequestHandler。并且添加了<mvc:resources/>标签，专门用于解决静态资源无法访问的问题，需要在SpringMVC的配置文件中添加如下形式的配置：

```xml
<mvc:resources location="/images/" mapping="/images/**">
```

location：表示静态资源所在的目录，当然，目录不要使用/WEB-INF/及其子目录

mapping：表示对该资源的请求(以/images/开始的请求，如/image/heihei.jpg，/images/haha.png等)。注意，后面是两个星号**。

```xml
<!--
    第二种处理静态资源的方式：
        mvc:resources加入后，框架会创建ResourceHttpRequestHandler这个处理器对象。
        让这个对象处理静态资源的访问，不依赖tomcat服务器

        mapping:访问静态资源的uri地址，可以使用通配符
        location:指定静态资源在我们项目中的目录位置

        image/**：表示image/p1.jpg,image/user/logo.gif,image/order/history/list.png
 -->

<mvc:resources mapping="/image/**" location="/image/"/>
<mvc:resources mapping="/html/**" location="/html/"/>
```

```xml
<!--
    上面这个写法，在实际生产情况下，还是比较费劲的，因为要写好多个啊，在实际生产情况下
     我们都在webapp下面新建一个static文件夹，所有静态资源的访问全都是以/static/开头的
 -->
<mvc:resources mapping="/static/**" location="/static/"/>
```

#### 3.8.3 配置静态资源的原因

如果在DispatcherServlet的url-pattern中配置了"/"的话，那么就需要配置这些静态资源的访问处理器了

但是如果你在DispatcherServlet中的url-pattern中配置了例如:"*.do"就不需要进行静态资源的配置



## 4 SpringMVC请求处理

```markdown
1.发起请求 ----> tomcat(web.xml ----> url-pattern --[find]--> DispatcherServlet) ---->DispactcherServlet

2.DispatcherServlet通过contextConfigLocation配置的springmvc-config.xml创建的ApplicationContext---[find]-->XxxXxxController.function()进行请求处理

3.框架执行处理，把得到的ModelAndView进行处理，转发到Xxx.jsp

request ---> DispatcherServlet ---> XxxXxxController
```

**中央调度器**

```markdown
中央调度器(DispatcherServlet)
1. 负责创建Springmvc容器对象，读取xml配置文件，创建文件中的Controller对象

2. 负责接收用户的请求，分配给自定义的Controller对象
```

**控制器**

```markdown
@Controller
@RequestMapping("/requestPath")

@Controller
@RequestMapping("/otherRequestPath")
等等...

请求来了，中央调度器会根据请求路径，找到对应的Controller来处理
```

### 4.1 执行过程分析

#### 4.1.1 Tomcat启动创建容器的过程

```markdown
1. 通过<load-on-startup>1<load-on-startup>，来创建DispatcherServlet的对象，DispatcherServlet它的父类是继承httpServlet的，他是一个Servlet，在被创建时，会执行init()方法
2 init()方法中会通过指定的XML配置文件进行创建XmlWebApplicationContext容器
```

```java
//发布为全局对象，供后续的使用 FrameworkServlet
if (this.publishContext) {
			// Publish the context as a servlet context attribute.
			String attrName = getServletContextAttributeName();
			getServletContext().setAttribute(attrName, wac);
		}
```



#### 4.1.2 请求处理

```markdown
1. 执行servlet的service方法
```

```java
/**
  * 重写父类的方法的实现，用以拦截PATCH请求
  * PUT方法用来替换资源，而patch方法用来更新部分资源，然而PATCH和POST都是非幂等的
	* Override the parent class implementation in order to intercept PATCH requests.
*/
@Override
protected void service(HttpServletRequest request, HttpServletResponse response)
```

service调用了doService，doService调用了DisPatcherServlet.doDispatch

## 5 路径

解决JSP页面中的问题，用"/"还是不用

```html
<a href="test/getUser">发起获得User的请求</a>
```

在jsp、html中使用的地址，都是在前端页面中的地址，都是相对地址

### 5.1 地址分类

1. 绝对地址，带有协议名称的是绝对地址，http://www.baidu.com，ftp://202.144.25.2

2. 相对地址，没有协议开头的，像是我们写的 test/getUser，这个地址不能单独使用

   - 使用的话，必须有一个参考地址，通过参考地址 + 相对地址本身才能指定资源。

   张三同学，1班有，3班也有，要确定是哪个，得需要个参考地址，3班张三

### 5.2 参考地址

#### 5.2.1 不加"/"

在你的页面中，访问地址不加"/"

```markdown
假如要访问的是：http://localhost:8080/projectName/index.jsp
路径：http://localhost:8080/projectName/
资源：index.jsp

在index.jsp发起的是test/getUser的请求，访问地址变为http://localhost:8080/projectName/test/getUser
结论：
	当你的地址，没有"/"开头，例如:tests/getUser，当你点击链接的时候，访问地址是当前页面的地址，加上链接上的地址
```

#### 5.2.2 加了"/"

```markdown
假如访问的地址是：/test/getUser
这个带"/"的参考地址是：http://localhost:8080 + 你写的 /test/getUser
```

如果你的资源不能访问了，可以加入EL表达式:${pageContext.request.contextPath}

```markdown
${pageContext.request.contextPath}/test/getUser发起请求
```

#### 5.2.3 注释掉视图解析器

```markdown
getUser中返回"/index.jsp"

访问路径:http://localhost:8080/projectName/test/getUser
点击 test/getUser 这个链接之后，地址栏路径变为：http://localhost:8080/projectName/test/test/getUser

解决方案：
1.加入${pageContext.request.contextPath}
2.加入base标签，是html语言中的标签，表示当前页面中访问地址的基地址，意思就是，你的页面中，没有"/"开头的地址，都是以base标签中的值为参考地址
	使用base中的地址 + 自己写的地址，这个base标签在head中
	<base href="http://localhost:8080/projectName/"/>
```

#### 5.2.4 basePath

在jsp中我们可以加入一段代码

```java
<%
  String basePaht = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"
  %/>
```

使用的话

```html
<base href="<%=basePath%>"/>
```

只对当前页面有效



## 6 SSM整合

SSM编程，即SpringMVC+Spring+Mybatis的整合，是之前最流行的JAVAEE开发技术架构，其实SSM整合的市值，仅仅就是将Mybatis整合进入Spring中，因为SpringMVC原本就是Spring的一部分，不用专门整合。

SSM整合的实现方式可以分为两种：基于XML配置的方式，基于注解的方式。

```markdown
SpringMVC：视图层，界面层，负责接收请求，显示处理结果的。
Spring：业务层，管理Service，DAO，工具类对象的
Mybatis：DAO持久层，访问数据库

用户发起请求 ---> SpringMVC接收 ----> Spring中的Service对象(处理业务逻辑) ----> Mybatis处理数据并返回

SSM的整合也叫做SSI（I也就是ibatis是mybatis的前身）
整合中有两个容器：
1.SpringMVC管理Controller控制器对象的
我们要做得是，把使用的对象交给合适的容器创建，管理。把Controller还有web开发的相关对象交给SpringMVC容器，这些web用的对象写在SpringMVC配置文件中

2.Spring容器，管理Service，DAO，工具类对象的
service，dao对象定义在Spring的配置文件中，让spring管理这些对象
```

那两个容器一个是SpringMVC还有一个是Spring，那怎么才能让这个两个容器建立起联系呢？怎么才能让这个SpringMVC的Controller去调用这个Spring容器中的service呢？

```markdown
那么这两个容器其实是存在联系的，SpringMVC的容器其实是Spring的子容器，类似于Java中的集成，是一种父子容器的关系，子容器是可以访问父容器中的对象的。子容器的Controller可以访问父容器的Service的
```



### 6.1 实现步骤

#### 6.1.1 导入依赖

加入依赖，SpringMVC，Mybatis，Jackson，mysql驱动，druid连接池，jsp，servlet依赖

```markdown

```

#### 6.1.2 配置容器及POST编码

写web.xml，注册DispatcherServlet，两点目的，一个是创建SpringMVC的容器对象，才能创建Controller类对象，第二个是，创建的是Servlet，这样才能接受用户的请求嘛。

注册Spring的监听器:ContextLoaderlistener，目的：创建Spring的容器对象，才能创建Service还有DAO对象。

注册字符集的过滤器，解决post请求乱码的问题

```xml
```

#### 6.1.3 控制器、业务层及持久层的创建



#### 6.1.4 SSM相关配置

SpringMVC的配置



Spring的配置



Mybatis主配置文件



数据库的属性配置文件



#### 6.1.5 完善代码

DAO接口、Mapper文件，service及其实现，controller，实体类



#### 6.1.6 JSP页面

## 7 请求重定向和转发

当处理器对请求处理完毕后，想其他资源进行跳转时，有两种跳转方式：请求转发与重定向。而根据所要跳转的资源类型，又可分为两类：跳转到页面与跳转到其他处理器。

注意，对于请求转发的页面，可以是WEB-INF中页面：而重定向的页面，是不能为WEB-INF中的。因为重定向相当于用户再次发出一次请求，而用户是不能直接访问WEB-INF中资源的。

请求转发，可以访问到WEB-INF中的资源

重定向，不可以访问WEB-INF中的资源

SpringMVC框架把原来的Servlet中的请求转发和重定向操作进行了封装。现在可以使用简单的方式实现转发和重定向。

Forward:表示转发，实现Request.getRequestDispatcher("xx.jsp").forward();

```markdown
用户发起请求给资源1，在服务器端呢，再由资源1发送请求给资源2，由资源2处理请求，再将结果返回给用户

对于用户而言，请求次数为1次，在服务器端呢，请求由资源1到资源2，这两个资源一起来处理请求的，是一次请求，然后地址栏呢，是不会变化的，这是转发。

因为转发是在服务器端内部实现的，因此他可以访问受保护的资源。
```

Redirect:表示重定向，实现response.sendRedirect("xx.jsp")

```markdown
用户首先向资源1发送请求，然后资源1告诉用户，你得访问资源2，然后，就把资源2的地址返回给用户了，用户浏览器就主动的访问资源2，请求次数是两次，而且呢用户的地址栏发生变化，是资源2的地址，由于请求呢是用户发起的，他就不能访问服务器内，受保护的资源了。
```

forward和这个redirect都是关键字，有一个共同的特点：不和视图解析器一同工作

### 7.1 请求转发

处理器方法返回ModelAndView时，需要在setViewName()指定的视图前添加forward:，且此时的视图不再与视图解析器一同工作，这样可以在配置了解析器时指定不同位置的视图。视图页面必须写出相对于项目根的路径。forward操作不需要视图解析器。

处理器方法返回String，在视图路径前面加入forward:视图完整路径

参考ForwardRedirectController.java

## 8 异常处理

SpringMVC框架处理异常的常用方式：使用@ExceptionHandler注解处理异常。

```markdown
统一的，全局的异常处理
把Controller中的所有异常都集中到一个地方，采用的是AOP的思想。把业务逻辑和异常处理代码分开-解耦合。

会使用到两个注解@ExceptionHandler，@ControllerAdvice
```

```markdown
异常处理步骤
1.自定义异常类MyUserException、再定义其子类NameException、GenderException
2.在Controller抛出NameException，GenderException
3.创建一个普通类，作为全局异常处理类
	1)在类的上面加入@ControllerAdvice
	2)在类中定义方法，方法的上面加入@ExceptionHandler
4.创建处理异常的视图页面
5.创建SpringMVC的配置文件
	1)组件扫描器，扫描@Controller注解
	2)组件扫描器，扫描@ControllerAdvice所在的包名
	3)声明注解驱动
```

### 8.1 @ExceptionHandler注解

使用注解@ExceptionHandler可以讲一个方法指定为异常处理方法。该注解只有一个可选的属性为value，为一个Class<?>数组，用于指定该注解的方法索要处理的异常类，即所要匹配的异常。

而被注解的方法，其返回值可以是ModelAndView、String或者void，方法名随意，方法的参数可以是Exception及其子类对象、HttpServletRequest、HttpServletResponse等，系统会自动为这些方法的参数赋值。

对于异常处理注解的用法，也可以直接将异常处理方法注解于Controller之中

### 8.2 自定义异常类

定义三个异常类：NameException、AgeException、MyUserException。其中MyUserException是另外两个异常的父类

```java
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
```

```xml
<!-- SpringMVC扫描这个handler包 -->
<context:component-scan base-package="club.musician.handler"/>
<!-- 异常handler的注解驱动 <mvc:annotation-driven/>上面已加过 -->
```

## 9 拦截器

SpringMVC中的Interceptor拦截器是非常重要和相当有用的，他的主要作用是拦截指定的用户请求，并进行相应的预处理和后处理。其拦截的时间点在”处理器映射器根据用户提交的请求映射除了所要执行的处理器类，并且也找到了要执行该处理器类的处理器适配器，在处理器适配器执行处理器之前“。当然，在处理器映射器映射出所要执行的处理器类时，已经将拦截器与处理器组合为了一个处理器的执行链，并返回给了中央调度器。

接口HandlerInterceptor，这个拦截器有点像那个过滤器，过滤器是用来过滤请求参数的，拦截器是用来拦截的，是用来做请求的预先处理工作的。

```markdown
拦截器是SpringMVC中的一种对象，需要实现HandlerInterceptor这个接口。

拦截器和过滤器类似，功能的侧重点不同，过滤器是用来过滤请求参数，设置编码字符集等工作。
拦截器是拦截用户的请求，做请求的判断处理的。

拦截器的特点：
	1)拦截器是全局的，可以对多个Controller做拦截
	2)一个项目中可以有一个或者多个拦截器，他们在一起拦截用户的请求，常用在：用户登录，权限检查，记录日志等。

拦截器的使用：
	1)定义类，实现HandelrInterceptor接口
	2)在SpringMVC中配置，声明拦截器，让框架知道拦截器的存在
	
拦截器的执行时机：
	1)在请求处理之前，也就是Controller类中的方法执行之前先被拦截
	2)在控制器方法执行之后也会被执行拦截器
	3)在请求处理完成之后，也会执行拦截器
```

```java
package club.musician.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    /**
     * 预处理方法
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
        System.out.println("拦截器的MyInterceptor的preHandler()");

        return true;
    }

    /**
     * 他想执行基本的条件是，前面这个preHandle这个方法返回的是true，
     *      对原来的Comtroller中的方法的结果，进行调整
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
        System.out.println("拦截器的MyInterceptor的postHandelr()");
    }


    /**
     * 最后执行的方法
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

    }
}
```

```xml
 <!-- SpringMVC中声明拦截器：拦截器可以由0个或者多个 -->
    <mvc:interceptors>
        <!-- 声明一个拦截器 -->
        <mvc:interceptor>
            <!--
                指定拦截的请求的uri的地址
                path:就是uri的自治，可以使用通配符 **
                    **:表示任意的字符，文件或者多级目录和目录中的问题

                请求地址：
                    http://localhost:8080myweb/interceptor/doSome.do

             -->
            <mvc:mapping path="/interceptor/**"/>
            <!-- 下面是拦截所有 -->
<!--            <mvc:mapping path="/**"/>-->
            <!-- 声明拦截器对象 -->
            <bean class="club.musician.interceptor.MyInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>
```

拦截器：看做是过个Controller中共用的功能，集中到拦截器中统一处理，使用的AOP的思想

### 9.1 多个拦截器

第一个拦截器preHandle=true，第二个拦截器preHandle=true

```markdown
拦截器1 preHandle
拦截器2 preHandle
Controller
拦截器2 postHandle
拦截器1 postHandle
拦截器2 afterCompletion
拦截器1 afterCompletion
```

第一个拦截器preHandle=true，第二个拦截器preHandle=false

```markdown
拦截器1 preHandle
拦截器2 preHandle
拦截器1 afterCompletion
```

第一个拦截器preHandle=false，第二个拦截器preHandle=true|false

```markdown
拦截器1 preHandle
```

### 9.2 过滤器和拦截器的区别

```markdown
1. 过滤器是Servlet中的对象，只要你的服务器负荷Servlet规范就能用；拦截器是SpringMVC框架中的对象，离开了这个容器(框架)啥都不是

2. 过滤器实现Filter接口的对象；拦截器是实现HandlerInterceptor接口

3. 过滤器是用来设置request，response的参数，属性的，侧重对数据的过滤；拦截器使用来验证请求的，能截断请求

4. 过滤器是在拦截器之前先执行的

5. 过滤器是Tomcat创建的对象，拦截器是SPringMVC创建的对象

6. 过滤器是一个执行时间点；拦截器有三个执行时间点

7. 过滤器可以处理jsp、js、html等等；拦截器是侧重拦截对controller的请求，如果你的请求不能被DispatcherServlet接收，这个请求就不会被拦截器拦截到
```



## 10 SpringMVC的执行流程

```markdown
1.用户发起一次请求，首先是到了DispatcherServlet中央调度器

2.DispatcherServlet中央调度器将请求转交到HandlerMapping(处理器映射器)中，HandlerMapping从容器中拿到对应的Controller对象
	- Handlermapping是框架中的一种对象，框架把实现了HandlerMapping接口的类都叫做映射器(多个)
	处理器映射器的作用，是根据用户的请求，从SpringMVC容器中获取处理器Controller对象
HandlerMapping相当于ctx.getBean("doSome.do") ---> 拿到对应处理"doSome.do"对应的Controller对象

3.HandlerMapping(处理器映射器)把找到的Controller对象，放入处理器执行链的类(HandlerExecutionChain)对象中，然后把这个执行链返回给DispatcherServlet
	- HandleMapping处理器映射器有很多种
	RequestMappingHandlerMapping，BeanNameUrlHandlerMapping(不推荐使用了)等等

	- HandlerExecutionChain放了两个属性
	属性一：private final Object handler;里面放的是处理器Controller
	属性二：private List<HandlerInterceptor> interceptorList;里面放的是项目中所有的拦截器

4.DispatcherServlet拿到这个HandlerExecutionChain之后呢，把HandlerExecutionChain交给了处理器适配器(HandlerAdapter这个适配器有多个)去执行处理器的方法，让执行结果返回给DispatcherServlet中央调度器
	- HandlerAdapter：SpringMVC框架中的对象，需要实现HandlerAdapter这个接口
	HandlerAdapter作用：执行处理器方法(调用Controller对象中的方法，得到返回值ModelAndView)
  
  - 没有注解之前，需要实现不同的接口才能作控制器使用
  	Controller ----> 对应一种映射器 ----> 一种映射器对应着一种适配器 
5.DispatcherServlet把4中获取的ModelAndView交给了视图解析器对象ViewResolver
	- 视图解析器：SpringMVC中的对象，需要实现ViewResolver接口(在一个项目中，可以有多个共存)
	作用：组成视图完整路径，使用前缀、后缀。并创建View对象，View是一个接口，是用来表示视图的，在框架里面JSP、Html不是String字符串表示的，而是使用View和他的实现类表示视图的。 modelAndView.setView(new RedirectView("/a.jsp"));
	
	JSP对应的就是InternalResourceView视图类，视图解析器，会创建InternalResourceView类对象，这个对象的里面，有个属性url=/WEB-INF/jsp/xxx.jsp

6.这个ViewResolver将ModelAndView解析完成后，jsp还是html还是什么的，将View视图返回给DispatcherServlet中央调度器

7.DispatcherServlet把6创建的View对象获取到，调用View自己的方法，把Model数据放入到request作用域。执行对象视图的forward。请求结束
	

```

