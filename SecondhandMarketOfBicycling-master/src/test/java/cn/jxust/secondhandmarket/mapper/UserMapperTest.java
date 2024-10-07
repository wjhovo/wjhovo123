package cn.jxust.secondhandmarket.mapper;

import cn.jxust.secondhandmarket.BaseTest;
import cn.jxust.secondhandmarket.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void addUserTest() {
        User user = new User();
        user.setId("1520163487");
        user.setName("太空人");
        user.setInstitute("信息学院");
        user.setAddress("本部3栋");
        user.setPhone("17766666666");
        user.setQq("666666");
        user.setHeadImg("http://www.wx.com/headImg.jpg");
        userMapper.insertOne(user);
    }

    @Test
    public void delUserTest() {
        userMapper.deleteOne("1234567890");
    }

    @Test
    public void updUserTest() {
        User user = new User();
        user.setId("1234567890");
        user.setName("李四");
        user.setInstitute("文法学院");
        user.setQq("666666666");
        user.setPhone("17799999999");
        user.setAddress("本部16栋");
        user.setHeadImg("http://www.jxust.cn/image.jpg");
        userMapper.updateOne(user);
    }

    @Test
    public void selUserTest() {
        System.out.println(userMapper.selectOne("1234567890"));
    }

}
