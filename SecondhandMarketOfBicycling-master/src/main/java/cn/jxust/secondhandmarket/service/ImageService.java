package cn.jxust.secondhandmarket.service;

import cn.jxust.secondhandmarket.config.Config;
import cn.jxust.secondhandmarket.exception.InvalidFileException;
import cn.jxust.secondhandmarket.exception.NoImageException;
import lombok.Cleanup;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * 图片处理服务
 */
@Service
public class ImageService {

    @Autowired
    private Config config;

    private Logger logger = LoggerFactory.getLogger(ImageService.class);

    /**
     * 保存传过来的图片
     *
     * @param images 图片
     * @return 图片保存路径
     */
    public String saveImage(MultipartFile[] images) throws NoImageException, IOException, InvalidFileException {

        //验证图片是否合法
        validateImage(images);

        //判断文件夹是否存在
        validateFilePath();

        //图片处理
        StringBuffer result = new StringBuffer();
        for (MultipartFile image : images) {
            String fileName = image.getOriginalFilename();
            if (fileName != null && !fileName.isEmpty()) {

                //生成uuid,用于生成文件名
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");

                //保存原图
                if (image.getContentType().contains("jpeg")) {
                    //格式为jpg时不进行转码
                    @Cleanup OutputStream os = new FileOutputStream(config.getFileRoot() + config.getOriginPath() + uuid + ".jpg");
                    IOUtils.copy(image.getInputStream(), os);
                } else {
                    //格式为非jpg时进行转码
                    Thumbnails.of(image.getInputStream())
                            .scale(1)
                            .outputFormat("jpg")
                            .toFile(config.getFileRoot() + config.getOriginPath() + uuid);
                }

                //保存缩略图
                Thumbnails.of(image.getInputStream())
                        .scale(0.5)
                        .outputFormat("jpg")
                        .outputQuality(0.5)
                        .toFile(config.getFileRoot() + config.getCompactPath() + uuid);

                result.append(uuid).append(',');
            }
        }

        return result.deleteCharAt(result.length() - 1).toString();
    }

    /**
     * 删除指定图片
     *
     * @param imageId 图片uuid
     * @return 是否成功
     */
    public boolean deleteImage(String imageId) {
        File originImage  = new File(config.getFileRoot() + config.getOriginPath() + imageId + ".jpg");
        File compactImage = new File(config.getFileRoot() + config.getCompactPath() + imageId + ".jpg");

        boolean originFlag  = false;
        boolean compactFlag = false;
        if (originImage.exists()) {
            originFlag = originImage.delete();
            if (!originFlag) {
                logger.error(originImage.getAbsolutePath() + "删除失败!");
            }
        }
        if (compactImage.exists()) {
            compactFlag = compactImage.delete();
            if (!compactFlag) {
                logger.error(compactImage.getAbsolutePath() + "删除失败!");
            }
        }

        return originFlag && compactFlag;
    }

    /**
     * 验证储存文件夹是否存在,没有则自动创建
     */
    private void validateFilePath() throws IOException {
        String originPath  = config.getFileRoot() + config.getOriginPath();
        String compactPath = config.getFileRoot() + config.getCompactPath();
        File   originFile  = new File(originPath);
        File   compactFile = new File(compactPath);
        if (!originFile.exists() && !originFile.mkdirs()) {
            throw new IOException("创建原图储存文件夹失败!");
        }
        if (!compactFile.exists() && !compactFile.mkdirs()) {
            throw new IOException("创建缩略图储存文件夹失败!");
        }
    }

    /**
     * 验证上传的文件是否合法
     *
     * @param images 上传的图片
     */
    private void validateImage(MultipartFile[] images) throws InvalidFileException, NoImageException {
        //判断图片是否存在
        if (images == null) {
            throw new NoImageException();
        }

        long size = 0;
        for (MultipartFile image : images) {
            size += image.getSize();
        }
        if (size == 0) {
            throw new NoImageException();
        }

        //验证文件格式
        for (MultipartFile image : images) {
            if (image.getSize() != 0 && !image.getContentType().contains("image")) {
                throw new InvalidFileException();
            }
        }
    }
}
