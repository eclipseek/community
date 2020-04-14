package life.majiang.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author: zhangyq<p>
 * Date: 22:19 2020/4/13 <p>
 * Description: <p>
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String hello() {
        return "index";
    }

}
