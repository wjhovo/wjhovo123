package cn.jxust.secondhandmarket.service;

import cn.jxust.secondhandmarket.exception.NullDataException;
import cn.jxust.secondhandmarket.mapper.UserMapper;
import cn.jxust.secondhandmarket.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 增加用户
     *
     * @param user 用户信息
     * @return 是否成功
     */
    public boolean addUser(User user) {
        return userMapper.insertOne(user) == 1;
    }

    /**
     * 删除用户
     *
     * @param id id
     * @return 是否成功
     */
    public boolean delUser(String id) {
        return userMapper.deleteOne(id) == 1;
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 是否成功
     */
    public boolean updUser(User user) {
        return userMapper.updateOne(user) == 1;
    }

    /**
     * 获取用户信息
     *
     * @param id id
     * @return 用户信息
     */
    public User selUser(String id) throws NullDataException {
        User user = userMapper.selectOne(id);
        if (user == null) {
            throw new NullDataException();
        } else {
            return user;
        }
    }
}
