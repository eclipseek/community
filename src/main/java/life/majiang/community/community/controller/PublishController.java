package life.majiang.community.community.controller;

import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Author: zhangyq<p>
 * Date: 13:07 2020/4/18 <p>
 * Description: <p>
 */

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            Model model) {

        // 用于页面回显信息
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (Objects.isNull(title) || title.isEmpty()) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (Objects.isNull(description) || description.isEmpty()) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (Objects.isNull(tag) || tag.isEmpty()) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        // 查询 cookie 中携带的 token --> 根据 token 查询用户信息 --> 将用户信息，设置到 session 中
        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if (Objects.nonNull(user)) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }

        // 如果根据 没有 token 这个 cookie，或 根据 token 查询用户信息为空，
        // 说明用户尚未登录
        if (Objects.isNull(user)) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        // 封装用户发布的问题
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(System.currentTimeMillis());

        // 持久化用户问题
        questionMapper.create(question);

        // 重定向到首页
        return "redirect:/";
    }

}
