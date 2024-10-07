package cn.jxust.secondhandmarket.service;


import cn.jxust.secondhandmarket.BaseTest;
import cn.jxust.secondhandmarket.exception.NullDataException;
import cn.jxust.secondhandmarket.pojo.Good;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GoodServiceTest extends BaseTest {

    @Autowired
    private GoodService goodService;

    @Test
    public void selAllGoodTest() throws NullDataException {
        List<Good> goods = goodService.selAll(1, 2, null, null, null);
        System.out.println();
    }
}
