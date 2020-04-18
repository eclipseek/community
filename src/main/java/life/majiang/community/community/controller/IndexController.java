package life.majiang.community.community.controller;

import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Author: zhangyq<p>
 * Date: 22:19 2020/4/13 <p>
 * Description: <p>
 */

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;		// 注入 mapper

    @GetMapping("/")
    public String hello(HttpServletRequest request) {
        // 从请求中获取 cookie 信息
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            // 如果有 key 为 token 的 cookie
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                // 根据 cookie 的值，去数据库查找对应的用户
                User user = userMapper.findByToken(token);
                // 找到用户了，说明可以保持用户逇登录态
                if (Objects.nonNull(user)) {
                    // 将用户信息，写入到 session 中
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }

        return "index";
    }

}