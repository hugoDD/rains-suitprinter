package com.rains.design.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.rains.design.panel.DesignContainer;
import com.rains.design.panel.OccupyDialog;
import com.rains.design.unit.LogUtil;
import com.rains.design.vo.Layer;

public class OccupyEditMouseListener extends MouseAdapter {

	private DesignContainer designContainer;
	private OccupyDialog dialog;

	public OccupyEditMouseListener(DesignContainer container) {
		designContainer = container;
		dialog = new OccupyDialog(designContainer);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Layer vo = designContainer.getNowOccupyVO();
		if (vo != null) {
			dialog.show(vo);
		}
		else {
			LogUtil.disError(designContainer, "请选中需要修改的的占位符！");
		}
		designContainer.requestFocus();
	}

}