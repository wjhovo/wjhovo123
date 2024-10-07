package cn.jxust.secondhandmarket.controller;

import cn.jxust.secondhandmarket.exception.InvalidArgsException;
import cn.jxust.secondhandmarket.pojo.Message;

public class BaseController {
    /**
     * 验证参数
     *
     * @param argValidate 验证条件
     */
    void argValidate(boolean argValidate) throws InvalidArgsException {
        if (argValidate) {
            throw new InvalidArgsException();
        }
    }

    /**
     * 验证业务逻辑(不带返回数据)
     *
     * @param serviceValidate 服务执行结果验证
     */
    Message serviceValidate(boolean serviceValidate) {
        return serviceValidate(serviceValidate, null);
    }

    /**
     * 验证业务逻辑(带返回数据)
     *
     * @param serviceValidate 服务执行结果验证
     * @param data            返回的数据
     */
    Message serviceValidate(boolean serviceValidate, Object data) {
        if (serviceValidate) {
            return data == null ? Message.OK : Message.msg(Message.OK, data);
        } else {
            return Message.OPERATE_FAIL;
        }
    }
}