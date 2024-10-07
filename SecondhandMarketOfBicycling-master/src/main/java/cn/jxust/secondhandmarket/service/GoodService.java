package cn.jxust.secondhandmarket.service;

import cn.jxust.secondhandmarket.config.Config;
import cn.jxust.secondhandmarket.exception.NullDataException;
import cn.jxust.secondhandmarket.mapper.GoodMapper;
import cn.jxust.secondhandmarket.pojo.Good;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GoodService {

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private Config config;

    @Autowired
    private ImageService imageService;

    /**
     * 添加商品
     *
     * @param good 商品信息
     * @return 是否成功
     */
    public boolean addGood(Good good) {
        //创建发布时间
        good.setPostTime(new Date());
        //合理化价格
        rationalizePrice(good);
        return goodMapper.insertOne(good) == 1;
    }

    /**
     * 删除商品
     *
     * @param id id
     * @return 是否成功
     */
    public boolean delGood(int id) {
        Good   good      = goodMapper.selectOne(id);
        String imagePath = good.getImagePath();
        if (imagePath != null && !imagePath.isEmpty()) {
            String[] images = imagePath.split(",");
            //删除商品对应图片
            for (String image : images) {
                imageService.deleteImage(image);
            }
        }
        return goodMapper.deleteOne(id) == 1;
    }

    /**
     * 修改商品
     *
     * @param good 商品信息
     * @return 是否成功
     */
    public boolean updGood(Good good) {
        //更新发布时间
        good.setPostTime(new Date());
        //合理化价格
        rationalizePrice(good);
        return goodMapper.updateOne(good) == 1;
    }

    /**
     * 获取商品列表
     *
     * @param page     当前页数
     * @param pageSize 每页条数
     * @param type     商品类型
     * @param keyword  搜索关键词
     * @param userId   用户id
     * @return 商品列表
     */
    public List<Good> selAll(int page, int pageSize, String type, String keyword, String userId) throws NullDataException {

        PageHelper.startPage(page, pageSize);
        List<Good> goods = goodMapper.selectAllBrief(type, keyword, userId);
        PageHelper.clearPage();

        if (goods == null) {
            throw new NullDataException();
        }

        //将图片路径字符串替换成图片地址列表
        for (Good good : goods) {
            convertImagePath(good, true);
        }

        return goods;
    }

    /**
     * 查看单件商品详细信息
     *
     * @param id id
     * @return 商品信息
     */
    public Good selOne(int id) throws NullDataException {
        Good good = goodMapper.selectOne(id);

        if (good == null) {
            throw new NullDataException();
        }

        //转换图片路径
        convertImagePath(good, false);

        return good;
    }


    /*==============================以下为本类私有方法================================*/

    /**
     * 合理化价格(截取2位小数)
     *
     * @param good 商品信息
     */
    private void rationalizePrice(Good good) {
        if (good.getPrice() != null) {
            BigDecimal bg    = new BigDecimal(good.getPrice());
            double     price = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            good.setPrice(price);
        }
    }

    /**
     * 将图片路径字符串转换成字符串列表
     *
     * @param good    商品信息
     * @param compact 是否压缩
     */
    private void convertImagePath(Good good, boolean compact) {
        //存在图片路径信息时才进行转换,避免空指针
        if (good.getImagePath() != null && !good.getImagePath().isEmpty()) {

            String urlPrefix   = config.getImageUrlPrefix();
            String originPath  = config.getOriginPath();
            String compactPath = config.getCompactPath();

            List<String> list = new ArrayList<>();
            //分割字符串
            String[] images = good.getImagePath().split(",");
            //重新组装字符串
            for (String image : images) {
                if (compact) {
                    list.add(urlPrefix + compactPath + image + ".jpg");
                } else {
                    list.add(urlPrefix + originPath + image + ".jpg");
                }
            }
            good.setImages(list);
        }
    }
}
