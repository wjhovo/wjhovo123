CREATE TABLE market_users
(
  id        CHAR(20) PRIMARY KEY,
  name      CHAR(20)  NOT NULL COMMENT '名字',
  institute CHAR(50)  NOT NULL COMMENT '所属学院',
  qq        CHAR(15) COMMENT 'QQ',
  phone     CHAR(11) COMMENT '手机',
  address   CHAR(50)  NOT NULL COMMENT '联系地址',
  head_img  CHAR(255) NOT NULL DEFAULT '' COMMENT '用户头像链接'
);

CREATE TABLE market_goods
(
  id         INT PRIMARY KEY AUTO_INCREMENT,
  title      CHAR(50)     NOT NULL COMMENT '商品标题',
  detail     CHAR(255)    NOT NULL COMMENT '商品描述',
  price      DOUBLE(10,2) NOT NULL COMMENT '商品价格',
  type       CHAR(20)     NOT NULL COMMENT '商品类别',
  poster     CHAR(20)     NOT NULL COMMENT '发布人',
  post_time  DATE         NOT NULL COMMENT '发布时间',
  view       INT          NOT NULL DEFAULT 0 COMMENT '浏览量',
  image_path CHAR(255)             DEFAULT '' COMMENT '图片路径',
  FOREIGN KEY (poster) references market_users (id)
);