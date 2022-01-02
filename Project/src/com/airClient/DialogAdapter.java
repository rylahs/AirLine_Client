package com.airClient;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class DialogAdapter extends WindowAdapter {
	Frame m;

	DialogAdapter(Frame m) {
		this.m = m;
	}

	public void windowClosing(WindowEvent e) {
		m.dispose();
		System.out.println("GoodBy...");
	}
}