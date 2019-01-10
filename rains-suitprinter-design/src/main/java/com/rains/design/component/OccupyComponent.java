package com.rains.design.component;

import com.rains.design.vo.Layer;

import javax.swing.*;
import java.awt.*;

/**
 * 占位符对象
 *
 * @author liubq
 */
public class OccupyComponent extends JTextArea {

    // 数据
    private Layer data;

    /**
     * 安装信息初始化占位符
     *
     * @param vo
     */
    public void initInfo(Layer vo) {
        data = vo;
        resetInfo(vo);

    }

    /**
     * 安装信息初始化占位符
     *
     * @param vo
     */
    private void resetInfo(Layer vo) {
        if (vo != null) {
            this.setText(vo.getText());
            this.setBounds((int) vo.getX(), (int) vo.getY(), 80, 18);
            this.setBackground(Color.GRAY);
            if (vo.getFontFamily() != null) {
                Font font = new Font(vo.getFontFamily(), Font.PLAIN, vo.getFontSize());
                this.setFont(font);
                int i = Integer.parseInt(vo.getColor().substring(1), 16);
                Color color = new Color(i);
                this.setForeground(color);
            }
            this.setEditable(false);
        }
    }

    /**
     * 取得信息
     *
     * @return
     */
    public Layer getInfo() {
        data.setX(this.getX());
        data.setY(this.getY());
        return data;
    }
}
