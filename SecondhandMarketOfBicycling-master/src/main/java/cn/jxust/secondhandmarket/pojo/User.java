package cn.jxust.secondhandmarket.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User {
    private String id;
    //名字
    private String name;
    //所属学院
    private String institute;
    //qq
    private String qq;
    //手机
    private String phone;
    //联系地址
    private String address;

    //头像链接
    private String headImg;

    @JsonIgnore
    public boolean isNoChange() {
        return id == null || (name == null && institute == null && qq == null && phone == null && address == null && headImg == null);
    }

    @JsonIgnore
    public boolean isEmpty() {
        return id == null || name == null || institute == null || qq == null || phone == null || address == null || headImg == null;
    }

}
