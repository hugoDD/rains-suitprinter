package com.rains.printer.builder;


import com.rains.printer.layer.LineLayer;
import com.rains.printer.Layer;

import java.awt.*;


/**
 * @author zhouhao
 * @since 1.0
 */
public class LineLayerBuilder extends AbstractLayerBuilder {
    public LineLayerBuilder() {
        super("line");
    }
    @Override
    protected Layer doBuild() {
        LineLayer lineLayer = new LineLayer();

        lineLayer.setX(getInt("x1", 0));
        lineLayer.setY(getInt("y1", 0));
        lineLayer.setEndX(getInt("x2", 0));
        lineLayer.setEndY(getInt("y2", 0));

        lineLayer.setColor(getColor(Color.BLACK));
        lineLayer.setFont(getFont(null));
        return lineLayer;
    }
}
