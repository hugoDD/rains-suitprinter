package com.rains.printer.builder;


import com.rains.printer.Layer;

import java.util.Map;

/**
 * @author zhouhao
 * @since 1.0
 */
public interface LayerBuilder {
    String getType();

    Layer build(Map<String, Object> config);
}
