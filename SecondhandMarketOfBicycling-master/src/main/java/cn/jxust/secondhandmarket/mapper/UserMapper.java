package cn.jxust.secondhandmarket.mapper;

import cn.jxust.secondhandmarket.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return 影响条数
     */
    @Insert("INSERT INTO market_users VALUES(#{id},#{name},#{institute},#{qq},#{phone},#{address},#{headImg})")
    int insertOne(User user);

    /**
     * 删除用户
     *
     * @param id id
     * @return 影响条数
     */
    @Delete("DELETE FROM market_users WHERE id=#{id}")
    int deleteOne(String id);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 影响条数
     */
    @UpdateProvider(type = DynamicSQL.class, method = "updUser")
    int updateOne(User user);

    /**
     * 查看用户信息
     *
     * @param id id
     */
    @Select("SELECT * FROM market_users WHERE id=#{id}")
    User selectOne(String id);
}
