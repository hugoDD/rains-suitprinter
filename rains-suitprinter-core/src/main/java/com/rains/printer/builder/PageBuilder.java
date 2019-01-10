package com.rains.printer.builder;


import com.rains.printer.Pager;

import java.util.List;

/**
 * @author zhouhao
 * @since 1.0
 */
public interface PageBuilder {
    List<Pager> build(String config);
}
