package com.rains.design.panel;

import com.rains.design.PrintDesignFrame;
import com.rains.design.action.ArrowBinding;
import com.rains.design.action.OccupyKeyListener;
import com.rains.design.action.OccupyMouseListener;
import com.rains.design.component.OccupyComponent;
import com.rains.design.constant.Direction;
import com.rains.design.constant.TPDConsts;
import com.rains.design.vo.DesignVO;
import com.rains.design.vo.Layer;
import com.rains.design.vo.SysConfigVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 设计容器，最外层
 *
 * @author liubq
 */
public class DesignContainer extends JPanel {

    // 设计模板
    private DesignPanel designPanel;

    // 菜单模板
    private MenuPanel menuPanel;

    // 滚动条
    private JScrollPane scrollPane;

    // 当前选中的占位符
    private OccupyComponent nowtxt;

    // 窗口
    private PrintDesignFrame frame;

    /**
     * 窗口
     *
     * @return 窗口
     */
    public PrintDesignFrame getFrame() {
        return frame;
    }

    /**
     * 初始化
     */
    public DesignContainer(PrintDesignFrame frame) {
        this.frame = frame;
        ActionMap actionMap = getActionMap();
        int condition = JComponent.WHEN_FOCUSED;
        InputMap inputMap = getInputMap(condition);

        for (Direction direction : Direction.values()) {
            inputMap.put(direction.getKeyStroke(), direction.getText());
            actionMap.put(direction.getText(), new ArrowBinding(direction.getText(), this));
        }
        this.setLayout(new BorderLayout());
        designPanel = new DesignPanel(this);
        menuPanel = new MenuPanel(this);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(designPanel);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(menuPanel, BorderLayout.NORTH);
    }

    /**
     * 加入一个占位符
     */
    public void addNewOccupy(Layer vo) {
        OccupyComponent txt = new OccupyComponent();
        OccupyMouseListener m = new OccupyMouseListener(this);
        txt.addMouseListener(m);
        txt.addMouseMotionListener(m);
        txt.addKeyListener(new OccupyKeyListener(this));
        designPanel.add(txt);
        txt.initInfo(vo);
    }

    /**
     * 移动位置
     *
     * @param e
     */
    public void moveTxt(KeyEvent e) {
        if (nowtxt != null) {
            if (KeyEvent.VK_LEFT == e.getKeyCode()) {
                moveTxt("left");
            } else if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
                moveTxt("right");
            } else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
                moveTxt("down");
            } else if (KeyEvent.VK_UP == e.getKeyCode()) {
                moveTxt("up");
            }
        }
    }

    /**
     * 移动位置
     *
     * @param e
     */
    public void moveTxt(ActionEvent e) {
        moveTxt(e.getActionCommand());
    }

    /**
     * 移动位置
     *
     * @param commond
     */
    private void moveTxt(String commond) {
        if (nowtxt != null) {
            if ("right".equalsIgnoreCase(commond)) {
                nowtxt.setBounds(nowtxt.getX() + 1, nowtxt.getY(), nowtxt.getWidth(), nowtxt.getHeight());
            } else if ("left".equalsIgnoreCase(commond)) {
                int value = nowtxt.getX() - 1;
                value = value < TPDConsts.BORDER ? TPDConsts.BORDER : value;
                nowtxt.setBounds(value, nowtxt.getY(), nowtxt.getWidth(), nowtxt.getHeight());
            } else if ("down".equalsIgnoreCase(commond)) {
                nowtxt.setBounds(nowtxt.getX(), nowtxt.getY() + 1, nowtxt.getWidth(), nowtxt.getHeight());
            } else if ("up".equalsIgnoreCase(commond)) {
                int value = nowtxt.getY() - 1;
                value = value < TPDConsts.BORDER ? TPDConsts.BORDER : value;
                nowtxt.setBounds(nowtxt.getX(), value, nowtxt.getWidth(), nowtxt.getHeight());
            }
        }
    }

    /**
     * 选择按下
     *
     * @param thisTxt
     */
    public void selectPress(OccupyComponent thisTxt) {
        if (nowtxt != null) {
            nowtxt.setBorder(null);
        }
        thisTxt.setBorder(BorderFactory.createLineBorder(Color.orange, 1));
        nowtxt = (OccupyComponent) thisTxt;
    }

    /**
     * 选择释放
     */
    public void selectRelease() {
        if (nowtxt != null) {
            nowtxt.setBorder(null);
        }
    }

    /**
     * 选择释放
     */
    public void removeNowTxt() {
        selectRelease();
        if (nowtxt != null) {
            this.designPanel.remove(this.nowtxt);
            this.designPanel.repaint();
            this.designPanel.updateUI();
        }
        nowtxt = null;
    }

    /**
     * 更新配置，弹出对话框点击应用，响应
     *
     * @param vo
     */
    public void resetOccupy(Layer vo) {
        nowtxt.initInfo(vo);
    }

    /**
     * 取得当前选中配置
     */
    public Layer getNowOccupyVO() {
        if (nowtxt == null) {
            return null;
        }
        return nowtxt.getInfo();
    }

    /**
     * 获得焦点
     */
    public void requestFouse() {
        nowtxt.requestFocusInWindow();
    }

    // 图片
    private ImageIcon image;

    /**
     * 插入底片
     *
     * @param image
     */
    public void resetImg(ImageIcon image) {
        this.image = image;
        designPanel.rebuild(image);
        designPanel.setSize(image.getIconWidth() + 200, image.getIconHeight() + 200);
    }

    /**
     * 取得设计结果
     *
     * @return
     */
    public DesignVO getDesignVO() {
        DesignVO dVO = new DesignVO();
        dVO.setWidth(frame.getWidth());
        dVO.setHeight(frame.getHeight());
        dVO.setDesignWidth(designPanel.getWidth());
        dVO.setDesignHeight(designPanel.getHeight());
        dVO.setMenuWidth(menuPanel.getWidth());
        dVO.setMenuHeight(menuPanel.getHeight());
        dVO.setConfigVO(this.getConfigVO());
        if (image != null) {
            dVO.setImgWidth(image.getIconWidth());
            dVO.setImgHeight(image.getIconHeight());
        }
        Component[] components = designPanel.getComponents();
        List<Layer> list = new ArrayList<>();
        Layer vo;
        OccupyComponent txt;
        for (Component component : components) {
            if (component instanceof OccupyComponent) {
                txt = (OccupyComponent) component;
                vo = txt.getInfo();
                vo.setX(txt.getX());
                vo.setY(txt.getY());
                list.add(vo);
            }
        }
        dVO.setLayers(list);
        return dVO;
    }

    /**
     * 配置信息
     *
     * @return
     */
    public SysConfigVO getConfigVO() {
        return config;
    }

    // 配置信息
    private SysConfigVO config = new SysConfigVO();

    /**
     * 配置信息
     *
     * @param vo
     */
    public void setConfigVO(SysConfigVO vo) {
        if (vo != null) {
            config = vo;
        }
    }

    /**
     * 取得设计结果
     *
     * @return
     */
    public void resetDesignVO(DesignVO vo) {
        if (vo == null) {
            return;
        }
        frame.setSize(vo.getWidth(), vo.getHeight());
        menuPanel.setSize(vo.getMenuWidth(), vo.getMenuHeight());
        designPanel.removeAll();
        for (Layer data : vo.getLayers()) {
            this.addNewOccupy(data);
        }
        designPanel.setPreferredSize(new Dimension(vo.getDesignWidth(), vo.getDesignHeight()));
        this.setConfigVO(vo.getConfigVO());
        scrollPane.updateUI();
    }

}
