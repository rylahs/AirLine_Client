package com.airClient;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class TabWindow extends Frame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageButton btCencal = new ImageButton("next.png");
	private ImageButton b1;
	
	private String sDate, sStartPosition, sDestinationPosition, sToTalCost,
			sSeatImformation, sPlainNumber, sToTalUser, sStart_day, sLand_day;

	private String sTempStartPosition = "", sTempDestinationPosition = "",
			sTempToTalCost = "", sTempSeatImformation = "",
			sTempPlainNumber = "", sTempToTalUser = "",
			sTempStart_day="",sTempLand_day = "",
			sTempDate= "";

	private Box recoder() {

		Box panel = new Box(BoxLayout.X_AXIS);
		JLabel label = new JLabel("���� ���  " + "user" + ":", JLabel.LEFT);
		label.setAlignmentY(JLabel.TOP_ALIGNMENT);

		String sRecord = "";
		String query = "select * from Flightrecord where client_id = '"
				+ StartWindow.userId.toString() + "'";

		JTextArea text = new JTextArea(10, 16);
		JScrollPane scrollpane1 = new JScrollPane();
		scrollpane1
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		text.setAlignmentY(JTextField.TOP_ALIGNMENT);

		try {
			StartWindow.rs = StartWindow.stmt.executeQuery(query);
			while (StartWindow.rs.next()) {

				sRecord += "����� ID: " + StartWindow.rs.getString(1); // rs.getInt("num");
				sRecord += " ,��� ����: " + StartWindow.rs.getString(11);
				sRecord += " ,�����: " + StartWindow.rs.getString(2);
				sRecord += " ,������: " + StartWindow.rs.getString(3);
				sRecord += " ,�� �Һ�ð�: " + StartWindow.rs.getString(4);
				sRecord += " ,�� ���: " + StartWindow.rs.getString(5);
				sRecord += " ,�¼� ����: " + StartWindow.rs.getString(6);
				sRecord += " ,���� �� �¼�: " + StartWindow.rs.getString(7);
				sRecord += " ,���� ��ȣ: " + StartWindow.rs.getString(8);
				sRecord += " ,����� ���̵�: " + StartWindow.rs.getString(9);
				sRecord += " ,�� �ο�: " + StartWindow.rs.getString(10);
				sRecord += "\n";
				text.append(sRecord);
				sRecord = "";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		text.setEditable(false);

		int position = text.getText().length();
		text.setCaretPosition(position);
		// label.add(scrollpane1);
		panel.add(label);
		panel.add(text);
		panel.add(scrollpane1, BorderLayout.CENTER);
		scrollpane1.getViewport().add(text, null);
		scrollpane1.getVerticalScrollBar().setValue(
				scrollpane1.getVerticalScrollBar().getMinimum());
		return panel;
	}

	private Box cencal() {

		Box panel = new Box(BoxLayout.Y_AXIS);

		JPanel infor1 = new JPanel();
		JPanel infor2 = new JPanel();

		infor1.setLayout(new GridLayout(8, 1));
		infor2.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel label = new JLabel("ȯ�� ", JLabel.CENTER);
		JLabel JlStartPosition, JlDestinationPosition;
		JLabel JlToTalCost, JlSeatImformation;
		JLabel JlPlainNumber, JlToTalUser;
		JLabel Jldate;
		label.setAlignmentY(JLabel.TOP_ALIGNMENT);

		sDate = "�����: ";
		sStartPosition = "����� : ";
		sDestinationPosition = "������ : ";
		sToTalCost = "�Ѻ�� : ";
		sSeatImformation = "�¼� ���� : ";
		sPlainNumber = "���� ��ȣ : ";
		sToTalUser = "�� ž�� ��: ";
		sStart_day = "��� ����";
		sLand_day = "���� ����";

		String query = "select start_place, end_place, total_cost, "
				+ "seat_sort, flight_num, total_user ,flydate from Flightrecord where client_id = '"
				+ StartWindow.userId.toString() + "'";
		try {
			StartWindow.rs = StartWindow.stmt.executeQuery(query);
			while (StartWindow.rs.next()) {

				sTempStartPosition = StartWindow.rs.getString(1);
				sTempDestinationPosition = StartWindow.rs.getString(2);
				sTempToTalCost = StartWindow.rs.getString(3);
				sTempSeatImformation = StartWindow.rs.getString(4);
				sTempPlainNumber = StartWindow.rs.getString(5);
				sTempToTalUser = StartWindow.rs.getString(6);
				sTempDate = StartWindow.rs.getString(7);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sStartPosition += sTempStartPosition;
		sDestinationPosition += sTempDestinationPosition;
		sToTalCost += sTempToTalCost;
		sSeatImformation += sTempSeatImformation;
		sPlainNumber += sTempPlainNumber;
		sToTalUser += sTempToTalUser;
		sDate += sTempDate;

		Jldate = new JLabel(sDate);
		JlStartPosition = new JLabel(sStartPosition);
		JlDestinationPosition = new JLabel(sDestinationPosition);
		JlToTalCost = new JLabel(sToTalCost);
		JlSeatImformation = new JLabel(sSeatImformation);
		JlPlainNumber = new JLabel(sPlainNumber);
		JlToTalUser = new JLabel(sToTalUser);

		infor1.add(label);
		infor1.add(JlStartPosition);
		infor1.add(JlDestinationPosition);
		infor1.add(JlPlainNumber);
		infor1.add(JlSeatImformation);
		infor1.add(JlToTalCost);
		infor1.add(JlToTalUser);
		infor1.add(Jldate);
		
		infor2.add(btCencal);

		panel.add(infor1);
		panel.add(infor2);

		btCencal.addActionListener(this);

		return panel;
	}

	private Box imformation() {

		Box panel = new Box(BoxLayout.Y_AXIS);

		JPanel infor1 = new JPanel();
		JPanel infor2 = new JPanel();
		JPanel infor3 = new JPanel();
		JPanel infor4 = new JPanel();
		JPanel infor5 = new JPanel();
		JPanel infor6 = new JPanel();
		JPanel infor7 = new JPanel();

		infor1.setLayout(new FlowLayout(FlowLayout.LEFT));
		infor2.setLayout(new FlowLayout(FlowLayout.LEFT));
		infor3.setLayout(new FlowLayout(FlowLayout.LEFT));
		infor4.setLayout(new FlowLayout(FlowLayout.LEFT));
		infor5.setLayout(new FlowLayout(FlowLayout.LEFT));
		infor6.setLayout(new FlowLayout(FlowLayout.LEFT));
		infor7.setLayout(new FlowLayout(FlowLayout.LEFT));

		String query = "select * from client where id = '"
				+ StartWindow.userId.toString() + "' and " + "password "
				+ "= '" + StartWindow.UserPassWor.toString() + "'";

		String sId = "";
		String sPassword = "";
		String sName = "";
		String sSex = "";
		String sPhone = "";
		String sEMail = "";
		String sEMailAdrress = "";
		String sAddress = "";
		int nCash = 0;
		int nMileage = 0;
		String sPassPortNum = "";
		String sLimit_passport = "";

		try {
			StartWindow.rs = StartWindow.stmt.executeQuery(query);
			while (StartWindow.rs.next()) {

				sId = StartWindow.rs.getString(1); // rs.getInt("num");
				sPassword = StartWindow.rs.getString(2); // rs.getString("name");
				sName = StartWindow.rs.getString(3); // rs.getString("jumin");
				sSex = StartWindow.rs.getString(4);
				sPhone = StartWindow.rs.getString(5);
				sEMail = StartWindow.rs.getString(6);
				sEMailAdrress = StartWindow.rs.getString(7);
				sAddress = StartWindow.rs.getString(8);
				nCash = StartWindow.rs.getInt(10);
				nMileage = StartWindow.rs.getInt(9);
				sPassPortNum = StartWindow.rs.getString(11);

				if (null != StartWindow.rs.getDate(12)) {
					sLimit_passport = StartWindow.rs.getDate(12).toString();
				} else {
					sLimit_passport = "NULL";
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel JlID = new JLabel(sId);
		JLabel JlName = new JLabel(sName);
		JLabel JlSex = new JLabel(sSex);
		JLabel JlPassPort = new JLabel(sPassPortNum);
		JLabel JlAddress = new JLabel(sEMailAdrress + " " + sAddress);
		JLabel JlHanphoneNumber = new JLabel(sPhone);
		JLabel text7 = new JLabel("" + sLimit_passport);

		infor1.add(new JLabel("�� ID"));
		infor1.add(JlID);
		infor2.add(new JLabel("����"));
		infor2.add(JlName);
		infor3.add(new JLabel("����"));
		infor3.add(JlSex);
		infor4.add(new JLabel("PASSPORT"));
		infor4.add(JlPassPort);
		infor5.add(new JLabel("�ּ�"));
		infor5.add(JlAddress);
		infor6.add(new JLabel("�ڵ��� ��ȣ"));
		infor6.add(JlHanphoneNumber);
		infor7.add(new JLabel("���� ������"));
		infor7.add(text7);

		panel.add(infor1);
		panel.add(infor2);
		panel.add(infor3);
		panel.add(infor4);
		panel.add(infor5);
		panel.add(infor6);
		panel.add(infor7);
		return panel;
	}

	public TabWindow() {
		setTitle("KUT AIR");
		// TODO Auto-generated constructor stub

		JTabbedPane jtp = new JTabbedPane();
		jtp.addTab("������", recoder());
		jtp.addTab("��������", imformation());
		jtp.addTab("���", cencal());

		add(jtp, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);

		setBounds(200, 200, 500, 300);

		b1 = new ImageButton("back.png");
		add(b1, BorderLayout.AFTER_LAST_LINE);

		addWindowListener(new Windowadapter(this));
		setResizable(false);
		setVisible(true);

		b1.addActionListener(this);

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == b1) {

			System.out.println("you enter afterwindow");
			new AfterLoginWindow();
			this.dispose();

		} else if (arg0.getSource() == btCencal) {
			System.out.println("cencer the revservation");

			String sSeatImformation;
			String query;
			int nSeatNum = 0 , nCash = 0;

			if (0 == sTempSeatImformation.compareTo("first_seat")) {
				sSeatImformation = "first_seat";

			} else if (0 == sTempSeatImformation.compareTo("business_seat")) {
				sSeatImformation = "business_seat";
			} else {
				sSeatImformation = "economic_seat";
			}

			query = "select "+sSeatImformation+" from flying" + " where flight_num = '"
					+ sTempPlainNumber + "'";
			try {
				StartWindow.rs = StartWindow.stmt.executeQuery(query);
				while (StartWindow.rs.next()) {

					nSeatNum = StartWindow.rs.getInt(1);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			nSeatNum += Integer.valueOf(sTempToTalUser);
			
			query = "select cash from client" + " where id = '"
					+ StartWindow.userId + "'";
			try {
				StartWindow.rs = StartWindow.stmt.executeQuery(query);
				while (StartWindow.rs.next()) {

					nCash = StartWindow.rs.getInt(1);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			nCash += Integer.valueOf(sTempToTalCost);
			
			
			  query =
			  "update client set cash = "+nCash +" where id = '"+StartWindow.userId+"'";
			  
			  try {
					StartWindow.stmt.executeUpdate(query);
					StartWindow.conn.commit();
					System.out.println("Uesr cash Update Success");
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					try {
						StartWindow.conn.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("Uesr cash Update Not Success");
					e2.printStackTrace();
				}
			  
			  query =
			  "update flying set "+ sSeatImformation +" ="+nSeatNum +" where flight_num = '"
					+ sTempPlainNumber + "'";
			   
			  try {
					StartWindow.stmt.executeUpdate(query);
					StartWindow.conn.commit();
					System.out.println("flying Imformation Update Success");
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					try {
						StartWindow.conn.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("flying Imformation Update Not Success");
					e2.printStackTrace();
				}
			  
			  
			  query =
					  "delete from Flightrecord where flight_num ='"+sTempPlainNumber +"' and"
					  +" start_place = '"+sTempStartPosition+"' and end_place = '"+sTempDestinationPosition+"'";
					   
					  try {
							StartWindow.stmt.executeUpdate(query);
							StartWindow.conn.commit();
							System.out.println("flightrecord Imformation delete Success");
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							try {
								StartWindow.conn.rollback();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							System.out.println("flightrecord Imformation delete Not Success");
							e2.printStackTrace();
						}
			  
			  new DialogSuccess("ȯ�� ����",sTempPlainNumber, "�� ���� ȯ�� �Ϸ��߽��ϴ�.",
						150, 100);
			  
			  System.out.println("you enter afterwindow");
				new AfterLoginWindow();
				this.dispose();
		}
	}
}
