package cn.jxust.secondhandmarket.mapper;

import cn.jxust.secondhandmarket.BaseTest;
import cn.jxust.secondhandmarket.pojo.Good;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class GoodMapperTest extends BaseTest {

    @Autowired
    private GoodMapper goodMapper;

    @Test
    public void addGoodTest() {
        Good good = new Good();
        good.setTitle("原子弹");
        good.setDetail("出售原子弹");
        good.setPrice(9999.0);
        good.setType("军火");
        good.setPoster("1234567890");
        good.setPostTime(new Date());
        goodMapper.insertOne(good);
    }

    @Test
    public void delGoodTest() {
        goodMapper.deleteOne(1);
    }

    @Test
    public void updGoodTest() {
        Good good = new Good();
        good.setId(3);
        good.setTitle("小破车");
        good.setDetail("出售小破车");
        good.setPrice(1000.0);
        good.setType("杂物");
        good.setPostTime(new Date());
        goodMapper.updateOne(good);
    }

    @Test
    public void selectAllBriefTest() {
        List<Good> goods = goodMapper.selectAllBrief(null, null, null);
        System.out.println(goods);
    }

    @Test
    public void selectAllByUser() {
        List<Good> goods = goodMapper.selectAllBrief(null, null, "1234567890");
        System.out.println(goods);
    }

    @Test
    public void selectOne() {
        Good good = goodMapper.selectOne(3);
        System.out.println(good);
    }
}
