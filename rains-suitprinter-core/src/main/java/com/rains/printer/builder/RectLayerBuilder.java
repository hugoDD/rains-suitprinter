package com.rains.printer.builder;

import com.rains.printer.layer.RectLayer;
import com.rains.printer.Layer;

import java.awt.*;

/**
 * @author zhouhao
 * @since 1.0
 */
public class RectLayerBuilder extends AbstractLayerBuilder {
    public RectLayerBuilder() {
        super("rect");
    }

    @Override
    protected Layer doBuild() {
        RectLayer layer = new RectLayer();
        layer.setHeight(getInt("height", 100));
        layer.setWidth(getInt("width", 100));
        layer.setY(getInt("y", 0));
        layer.setX(getInt("x", 0));
        layer.setColor(getColor(Color.BLACK));
        return layer;
    }
}
