package life.majiang.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author: zhangyq<p>
 * Date: 13:07 2020/4/18 <p>
 * Description: <p>
 */

@Controller
public class PublishController {

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

}
