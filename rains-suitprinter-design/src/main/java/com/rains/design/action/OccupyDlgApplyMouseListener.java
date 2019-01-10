package com.rains.design.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.rains.design.panel.OccupyDialog;
import com.rains.design.unit.LogUtil;

public class OccupyDlgApplyMouseListener extends MouseAdapter {
	private OccupyDialog occupyDialog;

	public OccupyDlgApplyMouseListener(OccupyDialog dialog) {
		occupyDialog = dialog;
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		try {
			occupyDialog.checkData();
			occupyDialog.refreshOccupy();
		}
		catch (Exception e1) {
			LogUtil.disError(occupyDialog, e1);
		}
	}
}