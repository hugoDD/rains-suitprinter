package com.rains.printer.builder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.rains.printer.layer.ImageLayer;
import lombok.extern.slf4j.Slf4j;
import com.rains.printer.Layer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author zhouhao
 * @since 1.0
 */
@Slf4j
public class ImageLayerBuilder extends AbstractLayerBuilder {
    public ImageLayerBuilder() {
        super("img");
    }


    @Override
    protected Layer doBuild() {
        ImageLayer layer = new ImageLayer();

        int height = getInt("height", 100);
        int width = getInt("width", 100);

        layer.setHeight(height);
        layer.setWidth(width);

        layer.setImage(createImage(width, height));
        layer.setX(getInt("x", 100));
        layer.setY(getInt("y", 100));

        return layer;
    }

    static BufferedImage createBase64(int width, int height, String content) throws Exception {

        byte[] imageBase64Data = Base64.getDecoder().decode(content);
        try (InputStream inputStream = new ByteArrayInputStream(imageBase64Data)) {
            return ImageIO.read(inputStream);
        }
    }

    static BufferedImage createQrCode(int width, int height, String content) throws Exception {
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        BitMatrix bitMatrix = new MultiFormatWriter()
                .encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    static BufferedImage createBarCode(int width, int height, String content) throws Exception {
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        BitMatrix bitMatrix = new MultiFormatWriter()
                .encode(content, BarcodeFormat.CODE_128, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    private Image createImage(int width, int height) {
        String type = getString("imgType", "static");
        String imageData = getString("imgData", "");
        try {
            if ("static".equals(type)) {
                if (imageData.startsWith("http")) {
                    try (InputStream inputStream = RequestBuilder.requestStream(imageData)) {
                        return ImageIO.read(inputStream);
                    }
                } else if (imageData.startsWith("file")) {
                    try (InputStream inputStream = new FileInputStream(new File(new URL(imageData).getFile()).getAbsolutePath())) {
                        return ImageIO.read(inputStream);
                    }
                } else {
                    try (InputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(imageData))) {
                        return ImageIO.read(inputStream);
                    }
                }
            } else if ("qrCode".equals(type)) {
                return createQrCode(width, height, imageData);
            } else if ("base64".equals(type)) {
                return createBase64(width, height, imageData);
            } else if ("barCode".equals(type)) {
                return createBarCode(width, height, imageData);
            }
        } catch (Exception e) {
            log.error("解析图片失败:{},{}", type, imageData, e);
        }
        return null;
    }
}
