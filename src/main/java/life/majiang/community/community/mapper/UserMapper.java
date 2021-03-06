package life.majiang.community.community.mapper;

import life.majiang.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Author: 18089748<p>
 * Date: 2020/4/17 <p>
 * Description: <p>
 */

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User findById(Integer id);

    @Insert("insert into user(name, account_id, token, gmt_create, gmt_modified, avatar_url) " +
            "values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

}
