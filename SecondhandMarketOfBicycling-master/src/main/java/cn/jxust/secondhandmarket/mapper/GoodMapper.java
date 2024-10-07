package cn.jxust.secondhandmarket.mapper;

import cn.jxust.secondhandmarket.pojo.Good;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodMapper {
    /**
     * 添加一条商品记录
     *
     * @param good 商品信息
     * @return 影响行数
     */
    @InsertProvider(type = DynamicSQL.class, method = "addGood")
    int insertOne(Good good);

    /**
     * 查看单件商品详情
     *
     * @param id id
     * @return 商品信息
     */
    @Select("SELECT market_goods.id," +
            "detail," +
            "title," +
            "price," +
            "type," +
            "post_time," +
            "image_path," +
            "view," +
            "market_users.id AS poster," +
            "market_users.name AS posterName," +
            "market_users.institute AS posterInstitute," +
            "market_users.qq AS qq," +
            "market_users.phone AS phone," +
            "market_users.address AS address, " +
            "market_users.head_img AS headImg " +
            "FROM market_goods " +
            "LEFT JOIN market_users on market_goods.poster = market_users.id " +
            "WHERE market_goods.id = #{id};" +
            //浏览量+1
            "UPDATE market_goods SET view=view+1 WHERE id=#{id}")
    Good selectOne(int id);

    /**
     * 删除一条商品记录
     *
     * @param id id
     * @return 影响行数
     */
    @Delete("DELETE FROM market_goods WHERE id=#{id}")
    int deleteOne(int id);

    /**
     * 修改一条商品记录
     *
     * @param good 商品信息
     * @return 影响行数
     */
    @UpdateProvider(type = DynamicSQL.class, method = "updGood")
    int updateOne(Good good);

    /**
     * 获取所有商品(不带描述)
     *
     * @return 商品列表
     */
    @SelectProvider(type = DynamicSQL.class, method = "selAll")
    List<Good> selectAllBrief(@Param("type") String type, @Param("keyword") String keyword, @Param("userId") String userId);

    /**
     * 获取距发布时间超过15天的商品
     */
    @Select("SELECT id FROM market_goods WHERE (DATE_ADD(post_time, INTERVAL 15 DAY) < CURRENT_DATE) LIMIT 100")
    List<Integer> selExpireItem();
}
