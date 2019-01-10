package com.rains.design.action;

import com.rains.design.component.OccupyComponent;
import com.rains.design.constant.TPDConsts;
import com.rains.design.panel.DesignContainer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OccupyMouseListener extends MouseAdapter {

    private DesignContainer designContainer;

    public OccupyMouseListener(DesignContainer container) {
        designContainer = container;


    }

    // 这两组x和y为鼠标点下时在屏幕的位置和拖动时所在的位置
    int newX, newY, oldX, oldY;

    // 这两个坐标为组件当前的坐标
    int startX, startY;

    @Override
    public void mousePressed(MouseEvent e) {
        // 此为得到事件源组件
        OccupyComponent cp = (OccupyComponent) e.getSource();
        // 当鼠标点下的时候记录组件当前的坐标与鼠标当前在屏幕的位置
        startX = cp.getX();
        startY = cp.getY();
        oldX = e.getXOnScreen();
        oldY = e.getYOnScreen();
        designContainer.selectPress(cp);

    }

    @Override

    public void mouseDragged(MouseEvent e) {
        OccupyComponent cp = (OccupyComponent) e.getSource();
        // 拖动的时候记录新坐标
        newX = e.getXOnScreen();
        newY = e.getYOnScreen();
        // 设置bounds,将点下时记录的组件开始坐标与鼠标拖动的距离相加
        int x = startX + (newX - oldX);
        x = x < TPDConsts.BORDER ? TPDConsts.BORDER : x;
        int y = startY + (newY - oldY);
        y = y < TPDConsts.BORDER ? TPDConsts.BORDER : y;
        cp.setBounds(x, y, cp.getWidth(), cp.getHeight());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {

            OccupyComponent cp = (OccupyComponent) e.getSource();
            if (!cp.isEditable()) {
                cp.setEditable(true);
                cp.requestFocus();
                cp.addKeyListener(new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if ((e.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                            cp.setText(cp.getInfo().getText());
                            cp.setEditable(false);
                            cp.requestFocus();
                            cp.selectAll();
                        } else if ((e.getKeyCode() == KeyEvent.VK_ENTER)
                                && e.isControlDown()) {
                            cp.getInfo().setText(cp.getText());
                            cp.requestFocus();
                            cp.selectAll();

                        }

                    }
                });
//				Layer data = cp.getInfo();
//				cp.initInfo(data);
            }
        }
        super.mouseClicked(e);
    }


}