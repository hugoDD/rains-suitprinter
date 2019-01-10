package com.rains.design.action;

import com.rains.design.panel.DesignContainer;
import com.rains.design.vo.Layer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OccupyBtnListener extends MouseAdapter {
    private DesignContainer main;

    public static int index = 0;

    public OccupyBtnListener(DesignContainer design) {
        main = design;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Layer vo = new Layer();
        String name = "name" + index++;
        vo.setText("#" + name + "#");
        vo.setType("text");
        vo.setWidth(80);
        vo.setHeight(18);
        Layer nowVO = main.getNowOccupyVO();
        if (nowVO != null) {
            vo.setX(nowVO.getY() + nowVO.getWidth() + 10);
            vo.setY(nowVO.getY() + nowVO.getHeight() + 10);
        }
        main.addNewOccupy(vo);
        main.requestFocus();
    }
}
