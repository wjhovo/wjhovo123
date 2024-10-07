package cn.jxust.secondhandmarket.controller;

import cn.jxust.secondhandmarket.exception.InvalidArgsException;
import cn.jxust.secondhandmarket.exception.NullDataException;
import cn.jxust.secondhandmarket.pojo.Good;
import cn.jxust.secondhandmarket.pojo.Message;
import cn.jxust.secondhandmarket.service.GoodService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/good")
public class GoodController extends BaseController {

    @Autowired
    private GoodService goodService;

    /**
     * 增加商品
     *
     * @param good 商品信息
     *             字段:title,detail,price,type,poster,imagePath
     */
    @PostMapping("/addGood")
    public Message addGood(Good good) throws InvalidArgsException {
        argValidate(good.isEmpty());
        return serviceValidate(goodService.addGood(good));
    }

    /**
     * 删除商品
     *
     * @param id id
     */
    @PostMapping("/delGood")
    public Message delGood(Integer id) throws InvalidArgsException {
        argValidate(id == null);
        return serviceValidate(goodService.delGood(id));
    }

    /**
     * 修改商品信息
     *
     * @param good 商品信息
     *             字段:id,title,detail,price,type,imagePath
     */
    @PostMapping("/updGood")
    public Message updGood(Good good) throws InvalidArgsException {
        argValidate(good.isNoCharge());
        return serviceValidate(goodService.updGood(good));
    }

    /**
     * 查看商品列表
     *
     * @param page     当前页数,默认1
     * @param pageSize 每页条数,默认10
     * @param type     商品类型,不传则默认查全部
     * @param keyword  商品标题关键字,可用于模糊查询
     * @param userId   用户id,可用于查看指定用户发布的商品
     */
    @PostMapping("/selAll")
    public Message selAll(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          String type,
                          String keyword,
                          String userId) throws NullDataException {

        //分页相关参数
        List<Good>     goods           = goodService.selAll(page, pageSize, type, keyword, userId);
        PageInfo<Good> pageInfo        = new PageInfo<>(goods);
        boolean        hasNextPage     = pageInfo.isHasNextPage();
        boolean        hasPreviousPage = pageInfo.isHasPreviousPage();
        int            pageNum         = pageInfo.getPageNum();

        //返回的结果对象
        Map<String, Object> result = new HashMap<>();
        result.put("hasNext", hasNextPage);
        result.put("hasPrevious", hasPreviousPage);
        result.put("currentPage", pageNum);
        result.put("goods", pageInfo.getList());

        return serviceValidate(true, result);
    }

    /**
     * 查看单件商品详细信息
     *
     * @param id id
     */
    @PostMapping("/selOne")
    public Message selOne(Integer id) throws InvalidArgsException, NullDataException {
        argValidate(id == null);
        return serviceValidate(true, goodService.selOne(id));
    }
}
