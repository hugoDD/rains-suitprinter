package com.rains;

import java.awt.EventQueue;

import com.rains.design.PrintDesignFrame;

public class PrintDesignMain {

	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			try {
				PrintDesignFrame frame = new PrintDesignFrame();
				frame.setVisible(true);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});

//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PrintDesignFrame frame = new PrintDesignFrame();
//					frame.setVisible(true);
//				}
//				catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
}