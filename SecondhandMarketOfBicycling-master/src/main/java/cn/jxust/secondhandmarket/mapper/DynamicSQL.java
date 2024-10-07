package cn.jxust.secondhandmarket.mapper;

import cn.jxust.secondhandmarket.pojo.Good;
import cn.jxust.secondhandmarket.pojo.User;
import org.apache.ibatis.jdbc.SQL;

public class DynamicSQL {
    public String updUser(User user) {
        return new SQL() {{
            UPDATE("market_users");
            if (user.getName() != null) {
                SET("name=#{name}");
            }
            if (user.getInstitute() != null) {
                SET("institute=#{institute}");
            }
            if (user.getQq() != null) {
                SET("qq=#{qq}");
            }
            if (user.getPhone() != null) {
                SET("phone=#{phone}");
            }
            if (user.getAddress() != null) {
                SET("address=#{address}");
            }
            if (user.getHeadImg() != null) {
                SET("head_img=#{headImg}");
            }
            WHERE("id=#{id}");
        }}.toString();
    }

    public String addGood(Good good) {
        return new SQL() {{
            INSERT_INTO("market_goods");
            VALUES("title", "#{title}");
            VALUES("detail", "#{detail}");
            VALUES("price", "#{price}");
            VALUES("type", "#{type}");
            VALUES("poster", "#{poster}");
            VALUES("post_time", "#{postTime}");
            if (good.getImagePath() != null) {
                VALUES("image_path", "#{imagePath}");
            }
        }}.toString();
    }

    public String updGood(Good good) {
        return new SQL() {{
            UPDATE("market_goods");
            if (good.getTitle() != null) {
                SET("title=#{title}");
            }
            if (good.getDetail() != null) {
                SET("detail=#{detail}");
            }
            if (good.getPrice() != null) {
                SET("price=#{price}");
            }
            if (good.getType() != null) {
                SET("type=#{type}");
            }
            if (good.getPostTime() != null) {
                SET("post_time=#{postTime}");
            }
            if (good.getImagePath() != null) {
                SET("image_path=#{imagePath}");
            }
            WHERE("id=#{id}");
        }}.toString();
    }

    public String selAll(String type, String keyword, String userId) {
        return new SQL() {{
            SELECT("market_goods.id AS id");
            SELECT("title");
            SELECT("price");
            SELECT("type");
            SELECT("post_time");
            SELECT("image_path");
            SELECT("view");
            SELECT("market_users.name AS posterName");
            SELECT("market_users.institute AS posterInstitute");
            SELECT("market_users.address AS address");
            SELECT("market_users.head_img AS headImg");
            FROM("market_goods");
            LEFT_OUTER_JOIN("market_users on market_goods.poster = market_users.id");
            if (type != null) {
                WHERE("type=#{type}");
            }
            if (keyword != null) {
                WHERE("title LIKE CONCAT(CONCAT('%',#{keyword}),'%')");
            }
            if (userId != null) {
                WHERE("poster=#{userId}");
            }
            ORDER_BY("post_time DESC,id DESC");
        }}.toString();
    }
}
