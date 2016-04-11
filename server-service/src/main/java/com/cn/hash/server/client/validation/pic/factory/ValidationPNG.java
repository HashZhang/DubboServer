package com.cn.hash.server.client.validation.pic.factory;

import com.cn.hash.server.client.validation.pic.domain.ValidationPic;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.*;
import org.patchca.service.Captcha;
import org.patchca.service.ConfigurableCaptchaService;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Hash Zhang on 2016/4/11.
 * the class to generate validation string picture in PNG with lwngth of 6
 *
 * @version 1.0.0
 */
@org.springframework.stereotype.Component
public class ValidationPNG {
    private ConfigurableCaptchaService configurableCaptchaService;
    private CurvesRippleFilterFactory curvesRippleFilterFactory;
    private MarbleRippleFilterFactory marbleRippleFilterFactory;
    private DoubleRippleFilterFactory doubleRippleFilterFactory;
    private WobbleRippleFilterFactory wobbleRippleFilterFactory;
    private DiffuseRippleFilterFactory diffuseRippleFilterFactory;
    private BASE64Encoder encoder;

    public ValidationPNG() {
        configurableCaptchaService = new ConfigurableCaptchaService();
        configurableCaptchaService.setColorFactory(new SingleColorFactory(new Color(25, 100, 210)));
        curvesRippleFilterFactory = new CurvesRippleFilterFactory(configurableCaptchaService.getColorFactory());
        marbleRippleFilterFactory = new MarbleRippleFilterFactory();
        doubleRippleFilterFactory = new DoubleRippleFilterFactory();
        wobbleRippleFilterFactory = new WobbleRippleFilterFactory();
        diffuseRippleFilterFactory = new DiffuseRippleFilterFactory();
        encoder = new BASE64Encoder();
    }

    /**
     * @return
     */
    public ValidationPic nextPic() throws IOException {
        int num = ThreadLocalRandom.current().nextInt(4);
        switch (num) {
            case 0:
                configurableCaptchaService.setFilterFactory(curvesRippleFilterFactory);
                break;
            case 1:
                configurableCaptchaService.setFilterFactory(marbleRippleFilterFactory);
                break;
            case 2:
                configurableCaptchaService.setFilterFactory(doubleRippleFilterFactory);
                break;
            case 3:
                configurableCaptchaService.setFilterFactory(wobbleRippleFilterFactory);
                break;
            case 4:
                configurableCaptchaService.setFilterFactory(diffuseRippleFilterFactory);
                break;
        }
        ValidationPic validationPic = new ValidationPic();
        Captcha captcha = configurableCaptchaService.getCaptcha();
        BufferedImage bufferedImage = captcha.getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] bytes = baos.toByteArray();
        validationPic.setPicture(encoder.encode(bytes));
        validationPic.setToken(captcha.getChallenge());
        return validationPic;
    }
}
