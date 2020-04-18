package life.majiang.community.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.community.dto.AccessTokenDTO;
import life.majiang.community.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Author: zhangyq<p>
 * Date: 23:06 2020/4/15 <p>
 * Description: <p>
 *     发送请求到 github，并解析结果
 */
@Component
public class GithubProvider {

    // 发送 post 请求到 github，获取 access token
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        try (Response response = client.newCall(request).execute()) {
            // str 格式是 access_token=18f3ae726ed442e46d9b7558d9c736d9c73dfb8b&scope=user&token_type=bearer
            String str = response.body().string();
            String token = str.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 发送 get 请求到 github，获取用户信息
    public GithubUser getUser(String accessToken) {
        OkHttpClient client =  new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String userJson = response.body().string();
            GithubUser user = JSON.parseObject(userJson, GithubUser.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
