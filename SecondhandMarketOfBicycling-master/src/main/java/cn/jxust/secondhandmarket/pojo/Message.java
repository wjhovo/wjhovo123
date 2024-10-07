package cn.jxust.secondhandmarket.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Message {

    public static final Message OK                 = msg(0, "OK");
    public static final Message SERVER_ERROR       = msg(500, "服务器内部错误");
    public static final Message METHOD_NOT_SUPPORT = msg(501, "请求方式错误,仅支持POST请求");
    public static final Message INVALID_ARGS       = msg(502, "缺少参数或存在非法参数");
    public static final Message OPERATE_FAIL       = msg(503, "操作失败,请检查参数是否正确");
    public static final Message NULL_DATA          = msg(504, "所查数据为空");
    public static final Message NO_IMAGE           = msg(510, "图片数据为空");
    public static final Message INVALID_FILE       = msg(511, "文件格式非法,只能上传图片文件");

    //状态码
    private int    code;
    //提示信息
    private String msg;
    //数据
    private Object data;

    private Message(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //内部使用的获取本类实例方法,不带数据
    private static Message msg(int code, String msg) {
        return new Message(code, msg, null);
    }

    //对外暴露的获取本类实例方法,可以传入数据
    public static Message msg(Message message, Object data) {
        return new Message(message.code, message.msg, data);
    }
}
