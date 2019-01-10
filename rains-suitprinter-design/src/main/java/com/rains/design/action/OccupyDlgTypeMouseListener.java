package com.rains.design.action;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import com.rains.design.panel.OccupyDialog;
import com.rains.design.unit.LogUtil;

public class OccupyDlgTypeMouseListener implements java.awt.event.ActionListener {
    private OccupyDialog occupyDialog;

    public OccupyDlgTypeMouseListener(OccupyDialog dialog) {
	this.occupyDialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	JComboBox<?> outType = (JComboBox<?>) e.getSource();
	String selType = (String) outType.getSelectedItem();
	try {
	    occupyDialog.refreshOutType(selType);
	}
	catch (Exception e1) {
	    LogUtil.disError(occupyDialog, e1);
	}
    }

}
