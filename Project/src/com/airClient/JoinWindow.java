package com.airClient;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;



class JoinWindow extends Frame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	boolean isIdCheck = false;

	ImageButton btRegister, btBack, btCheckDuplicate;
	TextField tfIdFiyld, tfPassword, tfName, tfEMail, tfPhoneFront,
			tfPhoneAfter, tfPassportNumber, tfAddress, tfMailAddress, tfMoney,
			tfPasswordCheck;
	Checkbox RbMale, RbFemale;
	CheckboxGroup bgSex;
	JComboBox<String> cbPhone, cbYear, cbMonth, cbDay;
	String cbhp[] = { "010", "011", "016", "017", "018", "019" };
	String cbyear[] = { "2013", "2014", "2015", "2016", "2017", "2018", "2019",
			"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027",
			"2028", "2029", "2030" };
	String cbmonth[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09",
			"10", "11", "12" };
	String cbdate[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09",
			"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

	public JoinWindow() {
		setTitle("KUT AIR");
		setBounds(200, 200, 350, 580);
		setVisible(true);
		JPanel pn1 = new JPanel();
		JPanel pn2 = new JPanel();
		JPanel pn3 = new JPanel();
		JPanel pn4 = new JPanel();
		JPanel pn5 = new JPanel();
		JPanel pn6 = new JPanel();
		JPanel pn7 = new JPanel();
		JPanel pn8 = new JPanel();
		JPanel pn9 = new JPanel();
		JPanel JpPassWordCheck = new JPanel();
		JPanel JpMailAddress = new JPanel();
		JPanel JpAddress = new JPanel();
		JPanel JpMoney = new JPanel();
		
		ImageIcon igAddress= new ImageIcon("address.png");
		JLabel lbAddress = new JLabel(igAddress);
		
		ImageIcon igmailaddress= new ImageIcon("mailaddress.png");
		JLabel lbMailAddress = new JLabel(igmailaddress);
		
		ImageIcon igId= new ImageIcon("id.png");
		JLabel lbID = new JLabel(igId);
		
		ImageIcon igPassword= new ImageIcon("password.png");
		JLabel ldPassword = new JLabel(igPassword);
		
		ImageIcon igIdcheck= new ImageIcon("idcheck.png");
		JLabel LdPasswordCheck = new JLabel(igIdcheck);
		
		ImageIcon igName= new ImageIcon("name.png");
		JLabel lbName = new JLabel(igName);
		
		ImageIcon igMail= new ImageIcon("mail.png");
		JLabel lbEMail = new JLabel(igMail);
		
		ImageIcon igPhonenum= new ImageIcon("phonenum.png");
		JLabel lbPhone = new JLabel(igPhonenum);
		
		ImageIcon igSex= new ImageIcon("se.png");
		JLabel lbSex = new JLabel(igSex);
		
		ImageIcon igPassportnum= new ImageIcon("passportnum.png");
		JLabel lbPassportNumber = new JLabel(igPassportnum);
		
		ImageIcon igEndday= new ImageIcon("endday.png");
		JLabel lbExpire = new JLabel(igEndday);
		
		ImageIcon igMoney= new ImageIcon("money.png");
		JLabel lbMoney = new JLabel(igMoney);
		tfMoney = new TextField(7);
		tfAddress = new TextField(15);
		tfMailAddress = new TextField(5);
		tfIdFiyld = new TextField(15);
		tfPassword = new TextField(20);
		tfPasswordCheck = new TextField(20);
		tfName = new TextField(10);
		tfEMail = new TextField(30);
		tfPhoneFront = new TextField(4);
		tfPhoneAfter = new TextField(4);
		tfPassportNumber = new TextField(20);
		bgSex = new CheckboxGroup();
		RbMale = new Checkbox("남성", bgSex, true);
		RbFemale = new Checkbox("여성", bgSex, false);
		cbPhone = new JComboBox<String>(cbhp);
		cbYear = new JComboBox<String>(cbyear);
		cbMonth = new JComboBox<String>(cbmonth);
		cbDay = new JComboBox<String>(cbdate);
		setLayout(new FlowLayout(FlowLayout.LEADING));
		setResizable(false);
		
		btRegister = new ImageButton("next.png");
		btBack = new ImageButton("back.png");
		btCheckDuplicate = new ImageButton("check.png");

		add(pn1);
		pn1.add(lbID);
		pn1.add(tfIdFiyld);
		pn1.add(btCheckDuplicate);
		add(pn2);
		pn2.add(ldPassword);
		pn2.add(tfPassword);
		tfPassword.setEchoChar('+');
		add(JpPassWordCheck);
		JpPassWordCheck.add(LdPasswordCheck);
		JpPassWordCheck.add(tfPasswordCheck);
		tfPasswordCheck.setEchoChar('*');
		add(pn3);
		pn3.add(lbName);
		pn3.add(tfName);
		add(pn4);
		pn4.add(lbSex);
		pn4.add(RbMale);
		pn4.add(RbFemale);
		add(JpMailAddress);
		JpMailAddress.add(lbMailAddress);
		JpMailAddress.add(tfMailAddress);
		add(JpAddress);
		JpAddress.add(lbAddress);
		JpAddress.add(tfAddress);
		add(pn5);
		pn5.add(lbEMail);
		pn5.add(tfEMail);
		add(pn6);
		pn6.add(lbPhone);
		pn6.add(cbPhone);
		pn6.add(tfPhoneFront);
		pn6.add(tfPhoneAfter);
		add(pn8);
		pn8.add(lbPassportNumber);
		pn8.add(tfPassportNumber);
		add(pn9);
		pn9.add(lbExpire);
		pn9.add(cbYear);
		pn9.add(cbMonth);
		pn9.add(cbDay);
		add(JpMoney);
		JpMoney.add(lbMoney);
		JpMoney.add(tfMoney);
		add(pn7);
		pn7.add(btRegister);
		pn7.add(btBack);

		btCheckDuplicate.addActionListener(this);
		btRegister.addActionListener(this);
		btBack.addActionListener(this);

		this.addWindowListener(new Windowadapter(this));
	}

	/**
	 * actioPermformed 이밴트 처리하는 부분.
	 */

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btRegister) {

			if (0 == tfPassword.getText().compareTo(tfPasswordCheck.getText())) {

			} else {
				new DialogSuccess("가입 실패", "비밀번호가", "잘못 됬습니다..", 100, 100);
				return;
			}

			if (0 == tfIdFiyld.getText().compareTo("")
					|| 0 == tfAddress.getText().compareTo("")
					|| 0 == tfEMail.getText().compareTo("")
					|| 0 == tfIdFiyld.getText().compareTo("")
					|| 0 == tfMailAddress.getText().compareTo("")
					|| 0 == tfMoney.getText().compareTo("")
					|| 0 == tfName.getText().compareTo("")
					|| 0 == tfPassportNumber.getText().compareTo("")
					|| 0 == tfPassword.getText().compareTo("")
					|| 0 == tfPhoneAfter.getText().compareTo("")
					|| 0 == tfPhoneFront.getText().compareTo("")) {
				this.dispose();
				System.out.println(tfIdFiyld.getText() + "빈칸 에러.");

				new DialogSuccess("가입 실패", "입력", "값이 없습니다.", 100, 100);

				new StartWindow();

				return;
			}

			if (false == StartWindow.celculate(
					Integer.parseInt(cbYear.getSelectedItem().toString()),
					Integer.parseInt(cbMonth.getSelectedItem().toString()),
					Integer.parseInt(cbDay.getSelectedItem().toString()))) {

				System.out.println("fail register");
				new StartWindow();
				this.dispose();

				return;
			} else {
				try {

					String query = "select * from client where id ='"
							+ tfIdFiyld.getText() + "'";
					StartWindow.rs = StartWindow.stmt.executeQuery(query);

					if (false == StartWindow.rs.next()) {

						String gender;

						if (0 == bgSex.getSelectedCheckbox().getLabel()
								.toString().compareTo("남성")) {
							gender = "M";
						} else {
							gender = "W";
						}

						String query1 = "INSERT INTO client VALUES (" + "'"
								+ tfIdFiyld.getText() + "', '"
								+ tfPassword.getText() + "', '"
								+ tfName.getText() + "', '" + gender + "', '"
								+ cbPhone.getSelectedItem().toString() + "-"
								+ tfPhoneFront.getText() + "-"
								+ tfPhoneAfter.getText() + "', '"
								+ tfEMail.getText() + "', '"
								+ tfMailAddress.getText() + "', '"
								+ tfAddress.getText() + "', " + 0 + ", "
								+ Integer.parseInt(tfMoney.getText()) + ", '"
								+ tfPassportNumber.getText() + "' ,'"
								+ cbYear.getSelectedItem().toString() + "-"
								+ cbMonth.getSelectedItem().toString() + "-"
								+ cbDay.getSelectedItem().toString() + "')";
						StartWindow.stmt.executeUpdate(query1);
						
						StartWindow.conn.commit();
						
						this.dispose();
						System.out.println(tfIdFiyld.getText() + "님이가입되었습니다");

						new DialogSuccess("가입 성공", tfIdFiyld.getText(),
								"가입에 성공하셨습니다.", 150, 100);

						new StartWindow();
						return;
					} else {
						this.dispose();
						System.out.println(tfIdFiyld.getText() + "아이디는 존재합니다.");
						StartWindow.conn.rollback();
						new DialogSuccess("가입 실패", tfIdFiyld.getText(),
								"가입에 실패하였습니다.", 150, 100);

						new StartWindow();
						return;
					}

				} catch (SQLException e1) {
					System.err.println("error sql = " + e1);
				}

			}

		} else if (e.getSource() == btBack) {

			System.out.println("you enter the start window");
			new StartWindow();
			this.dispose();

		} else if (e.getSource() == btCheckDuplicate) {
			String query = "select * from client where id ='"
					+ tfIdFiyld.getText() + "'";
			try {
				StartWindow.rs = StartWindow.stmt.executeQuery(query);

				if (false == StartWindow.rs.next()) {

					new DialogSuccess("사용 가능 아이디입니다", tfIdFiyld.getText(),
							"사용 가능한 아이디입니다.", 200, 100);
					isIdCheck = true;
					return;
				} else {
					new DialogSuccess("사용 할 수 없습니다.", tfIdFiyld.getText(),
							"사용 할 수 없는 아이디 입니다.", 200, 100);
					isIdCheck = false;
					return;
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}