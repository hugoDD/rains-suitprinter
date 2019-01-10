package com.rains.printer.builder;

import com.rains.printer.layer.TextLayer;
import com.rains.printer.Layer;

import java.awt.*;

/**
 * @author zhouhao
 * @since 1.0
 */
public class TextLayerBuilder extends AbstractLayerBuilder {

    public TextLayerBuilder() {
        super("text");
    }

    @Override
    protected Layer doBuild() {
        TextLayer textLayer = new TextLayer();
        textLayer.setText(getString("text", ""));
        textLayer.setX(getInt("x", 0));
        textLayer.setY(getInt("y", 0));
        textLayer.setWidth(getInt("width", -1));
        textLayer.setHeight(getInt("height", -1));
        textLayer.setAlign(TextLayer.Align.from(getString("align", TextLayer.Align.left.name())));
        textLayer.setVerticalAlign(TextLayer.VerticalAlign.from(getString("vertical-align", TextLayer.VerticalAlign.top.name())));

        textLayer.setFont(getFont(null));
        textLayer.setColor(getColor(Color.BLACK));
        return textLayer;
    }
}
