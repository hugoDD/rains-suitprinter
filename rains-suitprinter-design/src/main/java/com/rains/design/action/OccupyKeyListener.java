package com.rains.design.action;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.rains.design.panel.DesignContainer;

public class OccupyKeyListener extends KeyAdapter {
	private DesignContainer designContainer;

	public OccupyKeyListener(DesignContainer container) {
		designContainer = container;
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		designContainer.moveTxt(e);
	}

}