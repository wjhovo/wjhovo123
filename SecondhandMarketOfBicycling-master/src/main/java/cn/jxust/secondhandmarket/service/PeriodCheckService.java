package cn.jxust.secondhandmarket.service;

import cn.jxust.secondhandmarket.config.Config;
import cn.jxust.secondhandmarket.mapper.GoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class PeriodCheckService {

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private GoodService goodService;

    @Autowired
    private Config config;

    /**
     * 执行定时任务
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void run() {
        periodDeleteGood();
        periodDeleteImage();
    }

    /**
     * 定时删除据发布时间超过15天的商品
     */
    private void periodDeleteGood() {
        List<Integer> expireItem = goodMapper.selExpireItem();
        if (!expireItem.isEmpty()) {
            for (Integer id : expireItem) {
                goodService.delGood(id);
            }
        }
    }

    /**
     * 定时删除16天前发布的图片,防止出现没有删除干净的情况
     */
    private void periodDeleteImage() {
        File origin  = new File(config.getFileRoot() + config.getOriginPath());
        File compact = new File(config.getFileRoot() + config.getCompactPath());

        deleteFile(origin, 16);
        deleteFile(compact, 16);
    }

    /**
     * 删除过期文件
     *
     * @param dir 待删除文件所在的目录
     */
    private void deleteFile(File dir, int day) {
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.lastModified() + 1000 * 60 * 60 * 24 * day < System.currentTimeMillis()) {
                    file.delete();
                }
            }
        }
    }
}
