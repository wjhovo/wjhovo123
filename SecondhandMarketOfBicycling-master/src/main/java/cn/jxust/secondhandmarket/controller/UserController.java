package cn.jxust.secondhandmarket.controller;

import cn.jxust.secondhandmarket.exception.InvalidArgsException;
import cn.jxust.secondhandmarket.exception.NullDataException;
import cn.jxust.secondhandmarket.pojo.Message;
import cn.jxust.secondhandmarket.pojo.User;
import cn.jxust.secondhandmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 增加用户
     * 字段有:id,name,institute,qq,phone,address,headImg
     *
     * @param user 用户信息
     */
    @PostMapping("/addUser")
    public Message addUser(User user) throws InvalidArgsException {
        argValidate(user.isEmpty());
        return serviceValidate(userService.addUser(user));
    }

    /**
     * 删除用户
     *
     * @param id id
     */
    @PostMapping("/delUser")
    public Message delUser(String id) throws InvalidArgsException {
        argValidate(id == null);
        return serviceValidate(userService.delUser(id));
    }

    /**
     * 修改用户
     * 字段有:id,name,institute,qq,phone,address,headImg
     *
     * @param user 用户信息
     */
    @PostMapping("/updUser")
    public Message updUser(User user) throws InvalidArgsException {
        argValidate(user.isNoChange());
        return serviceValidate(userService.updUser(user));
    }

    /**
     * 查看用户信息
     *
     * @param id id
     */
    @PostMapping("/selUser")
    public Message selUser(String id) throws InvalidArgsException, NullDataException {
        argValidate(id == null);
        return serviceValidate(true, userService.selUser(id));
    }
}
