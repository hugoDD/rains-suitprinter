package com.rains.design.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.rains.design.panel.ConfigDialog;
import com.rains.design.unit.LogUtil;

public class ConfigDlgApplyMouseListener extends MouseAdapter {
	private ConfigDialog dialog;

	public ConfigDlgApplyMouseListener(ConfigDialog dialog) {
		this.dialog = dialog;
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		try {
			dialog.setSuccess();
			dialog.setVisible(false);
		}
		catch (Exception ex) {
			LogUtil.disError(dialog, ex);
		}
	}
}