package com.rains.design.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

import com.rains.design.constant.TPDConsts;
import com.rains.design.panel.DesignContainer;
import com.rains.design.unit.JsonTool;
import com.rains.design.unit.JsonUtils;
import com.rains.design.unit.LogUtil;
import com.rains.design.vo.DesignVO;

public class ExportBtnListener extends MouseAdapter {
	private DesignContainer container;

	public ExportBtnListener(DesignContainer container) {
		this.container = container;
	}

	public void mouseClicked(MouseEvent e) {
		DesignVO vo = container.getDesignVO();
		if (vo != null) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setCurrentDirectory(TPDConsts.DEAULT_DIR);
			if (chooser.showOpenDialog(container) == JFileChooser.APPROVE_OPTION) {
				File dir = chooser.getSelectedFile();
				String data = JsonUtils.convertToString(vo);
				data = JsonTool.formatJson(data);
				File file = new File(dir.getAbsolutePath() + File.separator + System.currentTimeMillis() + TPDConsts.F_NAME);
				try {
					Files.write(Paths.get(file.getAbsolutePath()), data.getBytes());
				}
				catch (IOException e1) {
					LogUtil.disError(container, e1);
				}
			}

		}
		container.requestFocus();
	}
}
