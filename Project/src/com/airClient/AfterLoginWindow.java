package com.airClient;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class AfterLoginWindow extends Frame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageButton btImfo, btDoRever, btOverReser, btRechargeCash,btLogout;

	public AfterLoginWindow() {

		setTitle("KUT AIR");
		setBounds(200, 200, 220, 150);
		setVisible(true);
		setResizable(false);
		setLayout(new GridLayout(5, 1));
		setBounds(200, 200, 220, 150);
		setResizable(false);
		btImfo = new ImageButton("information.png");
		btDoRever = new ImageButton("innation.png");
		btOverReser = new ImageButton("outnation.png");
		btRechargeCash = new ImageButton("cashcash.png");
		btLogout = new ImageButton("logout.png");

		add(btImfo);
		add(btDoRever);
		add(btOverReser);
		add(btRechargeCash);
		add(btLogout);
		btImfo.addActionListener(this);
		btDoRever.addActionListener(this);
		btOverReser.addActionListener(this);
		btLogout.addActionListener(this);
		btRechargeCash.addActionListener(this);

		this.addWindowListener(new Windowadapter(this));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btImfo) {

			new TabWindow();
			this.setVisible(false);

		} else if (e.getSource() == btDoRever) {

			new DomainReservationWindow();
			this.dispose();

		} else if (e.getSource() == btOverReser) {
			System.out.println("you enter the OverseaReservation window");
			new OverseaReservation();
			this.dispose();
		} else if (e.getSource() == btLogout) {
			System.out.println("you enter LogWindow");
			new StartWindow();
			this.dispose();
		} else if( e.getSource() == btRechargeCash){
			System.out.println("you enter rechargeWindow");
			new RechargeWindow();
			this.dispose();
		}
			
	}

}
