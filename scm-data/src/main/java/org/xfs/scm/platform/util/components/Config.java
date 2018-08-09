package org.xfs.scm.platform.util.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {

    @Value("${config.imagePath}")
    private String imagePath;   //图片服务器地址

    public Config() {
    }

    public String getImagePath() {
        return imagePath;
    }
}
