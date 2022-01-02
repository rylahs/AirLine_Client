package com.airClient;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import com.mysql.jdbc.NdbLoadBalanceExceptionChecker;

class StartWindow extends Frame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageButton joinButton, loginButton, connectButton;
	private Label title1, title2, title3;
	private TextField tfIp;
	
	static private String sDriver, sUser , sPass, sDbURL;
	
	public static Connection conn;
	public static Statement stmt;
	public static ResultSet rs;

	public static String userId;
	public static String UserPassWor;

	/**
	 * start main window part used system part argument
	 * 
	 * @param args
	 */

	public static void main(String args[]) {

		/*
		 * mysql connection part
		 */

		sDriver = "com.mysql.jdbc.Driver";
		sUser = "root";
		sPass = "1234";
		
		new StartWindow();
	}

	public static boolean celculate(int _nYear, int _nMonth, int _nDay) {
		if ((2 == _nMonth && _nDay > 28) || (6 == _nMonth && _nDay > 30)
				|| (9 == _nMonth && _nDay > 30)
				|| (11 == _nMonth && _nDay > 30)
				|| (3 == _nMonth && _nDay > 30)) {
			new DialogAlarm(_nMonth, _nDay);
			return false;
		}

		return true;
	}

	/**
	 * constuctor
	 */
	public StartWindow() {
		setTitle("KUT AIR");
		// TODO Auto-generated constructor stub

		setBounds(200, 200, 200, 200);
		setVisible(true);
		addWindowListener(new Windowadapter(this));
		
		title1 = new Label("KOREA TECH", Label.CENTER);
		title2 = new Label("Airline Client", Label.CENTER);
		title3 = new Label("Server IP",Label.CENTER);
		
		connectButton = new ImageButton("access.png");
		joinButton = new ImageButton("join.png");
		loginButton = new ImageButton("login.png");
		JPanel pn1 = new JPanel();
		JPanel pn2 = new JPanel();
		JPanel pn3 = new JPanel();
		JPanel pn4 = new JPanel();
		tfIp = new TextField(10);
		tfIp.setText("127.0.0.1");
		setLayout(new GridLayout(4, 1));
		setResizable(false);

		pn1.add(title1);
		pn3.add(title2);
		pn2.add(joinButton);
		pn2.add(loginButton);
		pn2.add(connectButton);
		pn4.add(title3);
		pn4.add(tfIp);
		
		add(pn1);
		add(pn3);
		add(pn4);
		add(pn2);
		
		connectButton.addActionListener(this);
		joinButton.addActionListener(this);
		loginButton.addActionListener(this);

	}

	/**
	 * actionPerform part
	 */

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == joinButton) {
			System.out.println("you enter the join part");
			new JoinWindow();
			this.dispose();

		} else if (e.getSource() == loginButton) {
			System.out.println("you enter the login part");
			new LoginWindow();
			this.dispose();

		} else if (e.getSource() == connectButton) {
			System.out.println(tfIp.getText()+" :during connection....");
			
			sDbURL = "jdbc:mysql://"+tfIp.getText() +":3306/airline";
			
			try {
				Class.forName(sDriver);
				conn = DriverManager.getConnection(sDbURL, sUser, sPass);
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				rs = null;
				System.out.println("Driver found! Connection Good!");
			} catch (SQLException se) {
				new DialogSuccess("연결 실패", tfIp.getText(), "로 연결 실패",
						150, 100);
				System.out.println("sql error");
			} catch (ClassNotFoundException cne) {
				new DialogSuccess("연결 실패", tfIp.getText(), "로 연결 실패",
						150, 100);
				System.out.println("jdbc driver not founded!");
			}
			
			new DialogSuccess("연결 성공", tfIp.getText(), "로 연결 성공",
					150, 100);
			
			return;
		}
	}

}


class DialogSuccess extends Frame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DialogSuccess(String _sTitle, String _sComment1, String _sComment2,
			int _size_x, int _size_y) {
		Dialog d = new Dialog(this, _sTitle);
		Label LbMsg1 = new Label(_sComment1, Label.CENTER);
		Label LbMsg2 = new Label(_sComment2, Label.CENTER);
		d.setLayout(new GridLayout(2, 1));

		d.add(LbMsg1);
		d.add(LbMsg2);
		d.setSize(_size_x, _size_y);
		d.setLocation(300, 300);

		d.addWindowListener(new DialogAdapter(this));
		d.setVisible(true);
		d.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}

class DialogAlarm extends Frame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DialogAlarm(int _nMonth, int _nDay) {
		Dialog d = new Dialog(this, "경고");
		Label lbMsg = new Label(_nMonth + "/" + _nDay, Label.CENTER);
		Label lbCommant = new Label("는 없는 날짜 입니다.", Label.CENTER);
		d.setLayout(new GridLayout(2, 1));

		d.add(lbMsg);
		d.add(lbCommant);
		d.setSize(100, 100);
		d.setLocation(300, 300);

		d.addWindowListener(new DialogAdapter(this));
		d.setVisible(true);
		d.setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
