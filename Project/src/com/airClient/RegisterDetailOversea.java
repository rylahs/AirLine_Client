package com.airClient;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;


public class RegisterDetailOversea extends Frame implements ActionListener, ItemListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	int nTotalEnterUser;
	Label lbTime;
	Label lbCost;
	Label lbPossibleMan;
	Label lbTotalUserk;
	int nSeat[] = new int[1000];
	int nCost[] = new int[1000];
	int nMileage[] = new int[1000];
	int nQueryIndex= 0;
	
	ImageButton BtReservation, BtBack;
	ButtonGroup bg, bg2;
	JComboBox<String> cbTime;
	String sDate[] = new String[1000];
	String sFlight_num[] = new String[1000];
	String sAirplane_id[] = new String[1000];
	String ssStartTime[] = new String[1000];
	String sStartTime[];
	String sEndTimep[] = new String[1000];
	RegisterDetail frame2;

	public RegisterDetailOversea() {
		setTitle("KUT AIR");
		setBounds(200, 200, 300, 290);
		setVisible(true);
		
		// sDestniPosition,sStartYear,sStartMonth,sStartDay,sPersonAdult,
		// sEndYear,sEndMonth,sEndDay,sPersonChild,sSeatSort;

		String query, query1;

		if (0 == OverseaReservation.sSeatSort.compareTo("economic_seat")) {
			query = "select  economic_seat,start_hour, start_min , start_sec , end_hour ,end_min ,end_sec ,flight_num, airplane_id ,mileage,start_date from flying where start_place ='"
					+ OverseaReservation.sStartPostion
					+ "'"
					+ " and end_place ='"
					+ OverseaReservation.sDestniPosition
					+ "'"
					+ " and economic_seat > 0 and start_date >="+Integer.valueOf(OverseaReservation.sStartYear
					+OverseaReservation.sStartMonth+ OverseaReservation.sStartDay) +" and start_date <="
					+ Integer.valueOf(OverseaReservation.sEndYear+ OverseaReservation.sEndMonth+ OverseaReservation.sEndDay);

			query1 = "select economic_cost from cost where week = 'false' and peak = 'false' and departure ='"
					+ OverseaReservation.sStartPostion + "'"
					+ " and dest ='" + OverseaReservation.sDestniPosition
					+ "'";
		} else if(0 == OverseaReservation.sSeatSort.compareTo("business_seat")) {
			query = "select business_seat ,start_hour, start_min , start_sec , end_hour ,end_min ,end_sec ,flight_num, airplane_id ,mileage,start_date from flying where start_place ='"
					+ OverseaReservation.sStartPostion
					+ "'"
					+ " and end_place ='"
					+ OverseaReservation.sDestniPosition
					+ "'"
					+ " and business_seat > 0 and start_date >="+Integer.valueOf(OverseaReservation.sStartYear
					+OverseaReservation.sStartMonth+ OverseaReservation.sStartDay) +" and start_date <="
					+ Integer.valueOf(OverseaReservation.sEndYear+ OverseaReservation.sEndMonth+ OverseaReservation.sEndDay);

			query1 = "select business_cost from cost where week = 'false' and peak = 'false' and departure ='"
					+ OverseaReservation.sStartPostion + "'"
					+ " and dest ='" + OverseaReservation.sDestniPosition
					+ "'";

		}else {
			query = "select first_seat ,start_hour, start_min , start_sec , end_hour ,end_min ,end_sec ,flight_num, airplane_id ,mileage,start_date from flying where start_place ='"
					+ OverseaReservation.sStartPostion
					+ "'"
					+ " and end_place ='"
					+ OverseaReservation.sDestniPosition
					+ "'"
					+ " and first_seat > 0 and start_date >="+Integer.valueOf(OverseaReservation.sStartYear
					+OverseaReservation.sStartMonth+ OverseaReservation.sStartDay) +" and start_date <="
					+ Integer.valueOf(OverseaReservation.sEndYear+ OverseaReservation.sEndMonth+ OverseaReservation.sEndDay);

			System.out.println(query);
			
			query1 = "select first_cost from cost where week = 'false' and peak = 'false' and departure ='"
					+ OverseaReservation.sStartPostion + "'"
					+ " and dest ='" + OverseaReservation.sDestniPosition
					+ "'";

		}

		int i = 0;

		try {
			StartWindow.rs = StartWindow.stmt.executeQuery(query);
			while (StartWindow.rs.next()) {
				nSeat[i] = StartWindow.rs.getInt(1);
				sDate[i] = StartWindow.rs.getString(11);
				ssStartTime[i] =StartWindow.rs.getDate(11)+"/"+ StartWindow.rs.getString(2) + ":"
						+ StartWindow.rs.getString(3) + ":"
						+ StartWindow.rs.getString(4);
				sEndTimep[i] =String.valueOf(Integer.parseInt(StartWindow.rs.getString(5))
						-Integer.parseInt(StartWindow.rs.getString(2)))+":"
						+String.valueOf(Integer.parseInt(StartWindow.rs.getString(6))
								-Integer.parseInt(StartWindow.rs.getString(3)))+":"
								+String.valueOf(Integer.parseInt(StartWindow.rs.getString(7))
								-Integer.parseInt(StartWindow.rs.getString(4)));
				sFlight_num[i] =StartWindow.rs.getString(8);
				sAirplane_id[i] = StartWindow.rs.getString(9);
				nMileage[i] = StartWindow.rs.getInt(10);
				
				i++;
			}
			
			sStartTime = new String[i];
			
			for(int j = 0; j <i ; j++)
			{
				sStartTime[j] = ssStartTime[j];
			}

			
			System.out.println("i :"+i);

			if (i == 0) {
				new DialogSuccess("?????? ????????", "???? ????", " ???????? ????????.",
						150, 100);
				new AfterLoginWindow();
				System.out.println("???? ?????? ????????.");
				this.dispose();
				return ;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		i =0;
		
		try {
			StartWindow.rs = StartWindow.stmt.executeQuery(query1);
			while (StartWindow.rs.next()) {

				nCost[i] = StartWindow.rs.getInt(1);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPanel pn1 = new JPanel();
		JPanel pn2 = new JPanel();
		JPanel pn3 = new JPanel();
		JPanel pn4 = new JPanel();
		JPanel pn6 = new JPanel();
		JPanel pnTotalUser = new JPanel();
		
		Label lb1 = new Label("???? ???? : ", Label.CENTER);
		Label lb2 = new Label("???? : ", Label.CENTER);
		Label lb3 = new Label("???? ???? : ", Label.CENTER);
		Label lb4 = new Label("???? ???? : ", Label.CENTER);
		Label lbTotalUser = new Label("???? ???? ",Label.CENTER);

		lbTime = new Label(sEndTimep[0], Label.CENTER);
		nTotalEnterUser =Integer.parseInt(OverseaReservation.sPersonAdult)
		+Integer.parseInt(OverseaReservation.sPersonChild);

		lbTotalUserk = new Label(nTotalEnterUser+"",Label.CENTER);
		
		lbCost = new Label(""+nCost[0]*nTotalEnterUser, Label.CENTER);
		lbPossibleMan = new Label(""+nSeat[0], Label.CENTER);

		cbTime = new JComboBox<String>(sStartTime);
		setLayout(new GridLayout(6, 1));
		BtReservation = new ImageButton("next.png");
		BtBack = new ImageButton("back.png");

		cbTime.addItemListener(this);
		
		add(pn4);
		pn4.add(lb4);
		pn4.add(cbTime);

		add(pn1);
		pn1.add(lb1);
		pn1.add(lbTime);

		add(pn2);
		pn2.add(lb2);
		pn2.add(lbCost);

		add(pn3);
		pn3.add(lb3);
		pn3.add(lbPossibleMan);
		
		add(pnTotalUser);
		pnTotalUser.add(lbTotalUser);
		pnTotalUser.add(lbTotalUserk);

		add(pn6);
		pn6.add(BtReservation);
		pn6.add(BtBack);
		BtReservation.addActionListener(this);
		BtBack.addActionListener(this);
		this.addWindowListener(new Windowadapter(this));
		setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == BtReservation) {
			// ?????? ???? ???????????? ?????? ???? ?????????? ??????
			int money = 0;
			int mileage = 0;
			String query = "select cash , mileage from client where id = '"+StartWindow.userId+"'";
			
			try {
				StartWindow.rs = StartWindow.stmt.executeQuery(query);
				if(StartWindow.rs.next())
				{
					money = StartWindow.rs.getInt(1);
					mileage = StartWindow.rs.getInt(2);
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(money <Integer.parseInt(lbCost.getText()))
			{
				new DialogSuccess("?????? ??????????.","??????","??????????.",
						150, 100);
				new AfterLoginWindow();
				dispose();
				return;
			}

			if(nTotalEnterUser >=nSeat[nQueryIndex])
			{
				new DialogSuccess("?????? ??????????.","??????","??????????.",
						150, 100);
				new AfterLoginWindow();
				dispose();
				return;
			}
			
			//update client;
			money -= Integer.parseInt(lbCost.getText());
			mileage += nMileage[nQueryIndex]*nTotalEnterUser;
			query = "update client set cash = "+money+", mileage = "+mileage+" where id = '"+StartWindow.userId+"'";
			
			try {
				StartWindow.stmt.executeUpdate(query);
				StartWindow.conn.commit();
				System.out.println("Uesr Imformation Update Success");
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				try {
					StartWindow.conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Uesr Imformation Update Not Success");
				e2.printStackTrace();
			}
			//update
			
			if (0 == OverseaReservation.sSeatSort.compareTo("economic_seat"))
			{
				int nSeatNumber = nSeat[nQueryIndex]-nTotalEnterUser;
				query  = "update flying set economic_seat = "+nSeatNumber+" where flight_num = '"+sFlight_num[nQueryIndex]+"'";
			}
			else if(0 == OverseaReservation.sSeatSort.compareTo("business_seat"))
			{
				int nSeatNumber = nSeat[nQueryIndex]-nTotalEnterUser;
				query = "update flying set business_seat= "+nSeatNumber+" where flight_num = '"+sFlight_num[nQueryIndex]+"'";
			}
			else
			{
				int nSeatNumber = nSeat[nQueryIndex]-nTotalEnterUser;
				query = "update flying set first_seat= "+nSeatNumber+" where flight_num = '"+sFlight_num[nQueryIndex]+"'";
			}
			
			try {
				StartWindow.stmt.executeUpdate(query);
				StartWindow.conn.commit();
				System.out.println("plain Imformation Update Success");
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				try {
					StartWindow.conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("plain Imformation Update Not Success");
				e2.printStackTrace();
			}
			
			
			String query1 = "INSERT INTO Flightrecord VALUES('"+StartWindow.userId+"','"+OverseaReservation.sStartPostion+"','"+OverseaReservation.sDestniPosition+"','"+ lbTime.getText()+"','"+lbCost.getText()+
					"','"+OverseaReservation.sSeatSort+"','"+lbPossibleMan.getText()+"','"+sFlight_num[nQueryIndex] +"','"+sAirplane_id[nQueryIndex] +"','"+nTotalEnterUser+"','"+sDate[nQueryIndex].replace("-", "")+"')";
			
			try {
				StartWindow.stmt.executeUpdate(query1);
				
				StartWindow.conn.commit();
				new DialogSuccess("?????? ????.","??????","??????????????",
						150, 100);
				dispose();
				new AfterLoginWindow();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
				
		} else if (e.getSource() == BtBack) {

			System.out.println("you enter user startWindow");
			new AfterLoginWindow();
			this.dispose();
			return;

		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JComboBox<String> A=(JComboBox<String>)e.getSource();
		nQueryIndex = A.getSelectedIndex();
		setVisible(false);
		lbTime.setText(sEndTimep[A.getSelectedIndex()]);
		lbCost.setText(""+nCost[0]);
		lbPossibleMan.setText(""+nSeat[A.getSelectedIndex()]);
		setVisible(true);
		
	}

}
