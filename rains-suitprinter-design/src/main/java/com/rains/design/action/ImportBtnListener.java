package com.rains.design.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

import com.rains.design.constant.TPDConsts;
import com.rains.design.filter.TPDFileFilter;
import com.rains.design.panel.DesignContainer;
import com.rains.design.unit.JsonUtils;
import com.rains.design.unit.LogUtil;
import com.rains.design.vo.DesignVO;

public class ImportBtnListener extends MouseAdapter {
	private DesignContainer container;

	public ImportBtnListener(DesignContainer container) {
		this.container = container;
	}

	public void mouseClicked(MouseEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new TPDFileFilter());
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		chooser.setCurrentDirectory(TPDConsts.DEAULT_DIR);
		if (chooser.showOpenDialog(container) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				String json = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
				DesignVO design = JsonUtils.convertToObject(json, DesignVO.class);
				container.resetDesignVO(design);
			}
			catch (Exception ex) {
				LogUtil.disError(container, ex);
			}
		}
		container.requestFocus();
	}
}
