package cn.jxust.secondhandmarket.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "setting")
@Setter
@Getter
public class Config {
    //图片储存的根目录
    private String fileRoot;

    //原图存储相对路径
    private String originPath;

    //缩略图相对存储路径
    private String compactPath;

    //图片地址前缀
    private String imageUrlPrefix;
}
