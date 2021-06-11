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
