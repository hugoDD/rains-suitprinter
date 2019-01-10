package com.rains.design.action;

import com.rains.design.panel.DesignContainer;
import com.rains.design.unit.DataItemUtil;
import com.rains.design.unit.HtmlUtil;
import com.rains.design.unit.JsonUtils;
import com.rains.design.unit.LogUtil;
import com.rains.design.vo.DataVO;
import com.rains.design.vo.DesignVO;
import com.rains.design.vo.Layer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class PreviewBtnListener extends MouseAdapter {
    private DesignContainer container;

    public PreviewBtnListener(DesignContainer container) {
        this.container = container;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DesignVO vo = container.getDesignVO();
        try {
            Map<String, DataVO> dataMap = new HashMap<String, DataVO>();
            for (Layer oVO : vo.getLayers()) {
//                if (OutType.LIST.getCode().equals(oVO.getOutType())) {
//                    dataMap.put(oVO.getName(),
//                            new DataVO(DataItemUtil.build(oVO.getName(), oVO.getName(), oVO.getName(), oVO.getName())));
//                } else {
//                    dataMap.put(oVO.getName(), new DataVO(DataItemUtil.build(oVO.getName())));
//                }
                dataMap.put(oVO.getText(),new DataVO(DataItemUtil.build(oVO.getText())));
            }
            String html = HtmlUtil.buildHtml(JsonUtils.convertToString(vo), dataMap);
            HtmlUtil.preview(html);
        } catch (Exception e1) {
            LogUtil.disError(container, e1);
        }
        container.requestFocus();
    }
}
