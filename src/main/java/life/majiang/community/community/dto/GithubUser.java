package life.majiang.community.community.dto;

/**
 * Author: zhangyq<p>
 * Date: 23:39 2020/4/15 <p>
 * Description: <p>
 */

public class GithubUser {
    private String id;
    private String name;
    private String bio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
