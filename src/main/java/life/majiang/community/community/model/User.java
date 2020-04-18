package life.majiang.community.community.model;

import lombok.Data;

/**
 * Author: 18089748<p>
 * Date: 2020/4/17 <p>
 * Description: <p>
 */

@Data
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private long gmtCreate;
    private long gmtModified;
    private String avatarUrl;

}
