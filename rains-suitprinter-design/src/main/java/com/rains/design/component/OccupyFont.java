package com.rains.design.component;

import javax.swing.JButton;

import com.rains.design.unit.FontUtil;
import com.rains.design.vo.FontColorVO;

/**
 * 字体样式控件
 * 
 * @author liubq
 */
public class OccupyFont extends JButton {
	private FontColorVO inVO;

	public OccupyFont(String name) {
		super(name);

	}

	public void refresh(LCFont font) {
		inVO = FontUtil.convert(font);
	}

	public FontColorVO getData() {
		return inVO;
	}

	public void setData(FontColorVO vo) {
		if (vo != null) {
			this.inVO = vo.clone();
		}
		else {
			this.inVO = null;
		}
	}
}
