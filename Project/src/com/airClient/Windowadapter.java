package com.airClient;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


class Windowadapter extends WindowAdapter {
	Frame m;

	Windowadapter(Frame m) {
		this.m = m;
	}

	public void windowClosing(WindowEvent e) {
		m.dispose();
		System.out.println("GoodBy...");
		System.exit(1);
	}
}
