package life.majiang.community.community.controller;

import life.majiang.community.community.dto.AccessTokenDTO;
import life.majiang.community.community.dto.GithubUser;
import life.majiang.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: zhangyq<p>
 * Date: 22:42 2020/4/15 <p>
 * Description: <p>
 *     https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/
 *
 */

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;


    /**
     * 用于 github 回调的方法。
     * @param code  github 返回的授权码
     * @param state 发送 authorize 请求时，携带的 state，若不一致则忽略该回调请求。
     * @return java.lang.String
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();   // shift + enter
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_secret(clientSecret);
        // 发送请求到 github，获取获取 access token
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);          // 快速创建变量：ctrl + alt + v
        // 发送请求到 github，获取用户信息
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println("username = " + user.getName());

        return "index";
    }

}
