package cn.jxust.secondhandmarket.controller;

import cn.jxust.secondhandmarket.exception.InvalidArgsException;
import cn.jxust.secondhandmarket.exception.InvalidFileException;
import cn.jxust.secondhandmarket.exception.NoImageException;
import cn.jxust.secondhandmarket.pojo.Message;
import cn.jxust.secondhandmarket.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 图片相关的控制器
 */
@RestController
@RequestMapping("/image")
public class ImageController extends BaseController {

    @Autowired
    private ImageService imageService;

    private Logger logger = LoggerFactory.getLogger(ImageService.class);

    /**
     * 接收传过来的图片
     *
     * @param images 图片
     */
    @PostMapping("/upload")
    public Message upload(MultipartFile[] images) {
        try {
            String result = imageService.saveImage(images);
            return Message.msg(Message.OK, result);
        } catch (NoImageException e) {
            return Message.NO_IMAGE;
        } catch (InvalidFileException e) {
            return Message.INVALID_FILE;
        } catch (IOException e) {
            logger.error(e.toString());
            return Message.SERVER_ERROR;
        }
    }

    /**
     * 删除图片
     *
     * @param imageId 图片uuid
     */
    @PostMapping("/delete")
    public Message delete(String imageId) throws InvalidArgsException {
        argValidate(imageId == null);
        return serviceValidate(imageService.deleteImage(imageId));
    }
}
