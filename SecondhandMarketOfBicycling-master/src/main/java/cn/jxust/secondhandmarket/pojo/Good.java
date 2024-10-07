package cn.jxust.secondhandmarket.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Good {
    private Integer id;

    //商品标题
    private String title;

    //商品详情
    private String detail;

    //商品价格
    private Double price;

    //商品分类
    private String type;

    //发布人
    private String poster;

    //发布时间
    private Date postTime;

    //浏览量
    private Integer view;

    //商品图片
    private List<String> images;

    //原始图片路径信息
    @JsonIgnore
    private String imagePath;

    //发布人名字
    private String posterName;

    //发布人学院
    private String posterInstitute;

    //发布人QQ
    private String qq;

    //发布人手机
    private String phone;

    //发布人联系地址
    private String address;

    //头像链接
    private String headImg;

    @JsonIgnore
    public boolean isNoCharge() {
        return id == null || (title == null && detail == null && price == null && type == null && poster == null);
    }

    @JsonIgnore
    public boolean isEmpty() {
        return title == null && detail == null && price == null && type == null && poster == null;
    }

}
