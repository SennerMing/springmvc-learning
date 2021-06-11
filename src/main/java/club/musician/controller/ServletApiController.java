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
