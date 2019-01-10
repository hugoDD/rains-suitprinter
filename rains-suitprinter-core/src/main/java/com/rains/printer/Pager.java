package com.rains.printer;

import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zhouhao
 * @since 1.0
 */
@Data
public class Pager {
    private List<Layer> layers;

    private int orientation = 1;

    private String baseImage;


    public BufferedImage createImage() throws IOException {
        //底图
        File baseFile = new File(this.baseImage);
        return  ImageIO.read(baseFile);
    }
}
