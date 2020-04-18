package life.majiang.community.community.model;

import lombok.Data;

/**
 * Author: zhangyq<p>
 * Date: 16:47 2020/4/18 <p>
 * Description: <p>
 */

@Data
public class Question {

    private int id;
    private String title;
    private String description;
    private String tag;
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;

}
