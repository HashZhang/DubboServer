package com.cn.hash.server.client.validation.pic.service.impl;

import com.cn.hash.server.client.validation.pic.domain.ValidationPic;
import com.cn.hash.server.client.validation.pic.factory.ValidationPNG;
import com.cn.hash.server.client.validation.pic.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by 862911 on 2016/4/11.
 */
@Service
public class ValidationServiceImpl implements ValidationService {
    @Autowired
    private ValidationPNG validationPNG;
    public ValidationPic getValidationPicture() throws IOException {
        return validationPNG.nextPic();
    }
}
