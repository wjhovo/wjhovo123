package cn.jxust.secondhandmarket.service;

import cn.jxust.secondhandmarket.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PeriodCheckServiceTest extends BaseTest {
    @Autowired
    private PeriodCheckService service;

    @Test
    public void deleteTest(){
        service.run();
    }
}
