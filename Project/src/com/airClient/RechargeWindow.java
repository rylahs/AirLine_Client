package com.airClient;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class RechargeWindow extends Frame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	boolean isIdCheck = false;

	int nCash, nMileage;
	ImageButton btOk, btBack;
	TextField tfcashFiyld ,tfMileageFiyld;
	
	Label lbtxMileage;
	Label lbtxCash;
	
	public RechargeWindow() {
		setTitle("KUT AIR");
		setBounds(200, 200, 200, 200);
		setVisible(true);
		JPanel TxMileage = new JPanel();
		JPanel TxCash = new JPanel();
		JPanel EdMileage = new JPanel();
		JPanel EdCash = new JPanel();
		JPanel Button = new JPanel();
		
		String query = "select mileage, cash from client where id = '"
				+ StartWindow.userId+"'";
		try {
			StartWindow.rs = StartWindow.stmt.executeQuery(query);
			
			if (StartWindow.rs.next())
			{
				nMileage = StartWindow.rs.getInt(1);
				nCash = StartWindow.rs.getInt(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		lbtxMileage = new Label("현재 Mileage : "+ nMileage, Label.CENTER);
		lbtxCash = new Label("현재 Cash : "+nCash , Label.CENTER);
		Label lbMileage = new Label("입금 Mileage", Label.CENTER);
		Label ldCash = new Label("입금 Cash", Label.CENTER);
		
		tfcashFiyld  = new TextField(10);
		tfMileageFiyld = new TextField(7);
		
		setLayout(new GridLayout(5, 1));
		setResizable(false);
		
		btOk = new ImageButton("next.png");
		btBack = new ImageButton("back.png");

		add(TxMileage);
		TxMileage.add(lbtxMileage);
		
		add(TxCash);
		TxCash.add(lbtxCash);
		
		add(EdMileage);
		EdMileage.add(lbMileage);
		EdMileage.add(tfMileageFiyld);
		tfMileageFiyld.setText("0");
		add(EdCash);
		EdCash.add(ldCash);
		EdCash.add(tfcashFiyld);
		tfcashFiyld.setText("0");
		
		add(Button);
		Button.add(btOk);
		Button.add(btBack);
		
		btOk.addActionListener(this);
		btBack.addActionListener(this);

		this.addWindowListener(new Windowadapter(this));
	}

	/**
	 * actioPermformed 이밴트 처리하는 부분.
	 */

	public void actionPerformed(ActionEvent e) {

		String query;
		
		if (e.getSource() == btOk) {
			
			if(Integer.parseInt(tfcashFiyld.getText())< 0)
			{
				System.out.println("insert minus cash");
				new DialogSuccess("경고", "캐쉬를 뺼수.", "없습니다",
						150, 100);
				return;
			}
			
			int cash = nCash + Integer.parseInt(tfcashFiyld.getText());
			System.out.println("cash : "+cash);
			query = "update client set cash = "+cash+" where id = '"+StartWindow.userId+"'";
			
			try {
				StartWindow.conn.commit();
				
				StartWindow.stmt.executeUpdate(query);
				System.out.println("insert cash Update Success");
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				System.out.println("insert cash Update Not Success");
				e2.printStackTrace();
			}
			nCash = cash;
			
			if(Integer.parseInt(tfMileageFiyld.getText())> nMileage)
			{
				
				try {
			
					StartWindow.conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				System.out.println("insert minus mileage");
				new DialogSuccess("경고", "마일리지의 양보다", "출금량이 더 큽니다",
						150, 100);
				
				setVisible(false);
				lbtxMileage.setText("현재 Mileage : "+ nMileage);
				lbtxCash.setText("현재 Cash : "+nCash);
				setVisible(true);
				
				return;
			}
			
			String query2;
			
			cash = nCash + Integer.parseInt(tfMileageFiyld.getText());
			int mileage = nMileage - Integer.parseInt(tfMileageFiyld.getText());
			System.out.println("cash : "+cash);
			query2 = "update client set cash = "+cash+", mileage = "+ mileage
					+" where id = '"+StartWindow.userId+"'";
			
			try {
				StartWindow.stmt.executeUpdate(query2);
				System.out.println("insert mileage Update Success");
				StartWindow.conn.commit();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				System.out.println("insert mileage Update Not Success");
				e2.printStackTrace();
			}
			
			System.out.println("you enter afterwindow");
			new AfterLoginWindow();
			this.dispose();
		}
		else 
		{
			System.out.println("you enter afterwindow");
			new AfterLoginWindow();
			this.dispose();
		}
	}
	
}