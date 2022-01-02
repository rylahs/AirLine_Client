package com.airClient;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;


class DomainReservationWindow extends Frame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static String sStartPostion, sDestniPosition, sStartYear, sStartMonth,
			sStartDay, sPersonAdult, sEndYear, sEndMonth, sEndDay,
			sPersonChild, sSeatSort;

	ImageButton BtRegister, BtBack;
	Checkbox usually, first;
	CheckboxGroup bg, CgSeatSet;
	JComboBox<String> cbStartPosition, cbYear, cbMonth, cbDay, cbDestiYear,
			cbDestiMonth, cbDestiDay, cbDestniposition, cbAdult, cbChild;
	String lsStartPosition[] = { "광주", "군산", "대구", "부산", "김포", "인천", "여수",
			"울산", "원주", "제주", "진주", "청주", "포항" };
	String lsDestniPosition[] = { "광주", "부산", "여수", "울산", "제주", "진주", "포항" };

	String cbyear[] = { "2014", "2013", "2015", "2016", "2017", "2018", "2019",
			"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027",
			"2028", "2029", "2030" };
	String cbmonth[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
			"11", "12" };
	String cbdate[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
			"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
			"22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	String lsPersonAdult[] = { "1", "2", "3", "4", "5", "6",
			"7", "8", "9","10" };
	String lsPersonChild[] = {"0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9","10" };

	public DomainReservationWindow() {
		setTitle("KUT AIR");
		setBounds(200, 200, 300, 290);
		setVisible(true);
		System.out.println("domain register");
		JPanel pn1 = new JPanel();
		JPanel pn2 = new JPanel();
		JPanel pn3 = new JPanel();
		JPanel pn4 = new JPanel();
		JPanel pn5 = new JPanel();
		JPanel pn6 = new JPanel();
		Label lb1 = new Label("좌석 선택", Label.CENTER);
		Label lb2 = new Label("도시선택", Label.CENTER);
		Label lb3 = new Label("예약 인원", Label.CENTER);
		Label lb4 = new Label("날짜 범위", Label.CENTER);
		Label lb5 = new Label("출발 날짜", Label.CENTER);
		Label lbadult = new Label("성인",Label.CENTER);
		Label Lbchild = new Label("아이", Label.CENTER);

		CgSeatSet = new CheckboxGroup();
		usually = new Checkbox("일반석", CgSeatSet, true);

		first = new Checkbox("프레스티지석", CgSeatSet, false);
		cbStartPosition = new JComboBox<String>(lsStartPosition);

		cbYear = new JComboBox<String>(cbyear);
		cbMonth = new JComboBox<String>(cbmonth);
		cbDay = new JComboBox<String>(cbdate);

		cbDestiYear = new JComboBox<String>(cbyear);
		cbDestiMonth = new JComboBox<String>(cbmonth);
		cbDestiDay = new JComboBox<String>(cbdate);

		cbDestniposition = new JComboBox<String>(lsDestniPosition);
		cbAdult = new JComboBox<String>(lsPersonAdult);
		cbChild = new JComboBox<String>(lsPersonChild);
		setLayout(new FlowLayout(FlowLayout.LEADING));
		BtRegister = new ImageButton("next.png");
		BtBack = new ImageButton("back.png");
		add(pn5);
		pn5.add(lb1);
		pn5.add(usually);
		pn5.add(first);
		add(pn2);
		pn2.add(lb2);
		pn2.add(cbStartPosition);
		pn2.add(cbDestniposition);
		add(pn3);
		pn3.add(lb3);
		pn3.add(lbadult);
		pn3.add(cbAdult);
		pn3.add(Lbchild);
		pn3.add(cbChild);
		add(pn4);
		pn4.add(lb5);
		pn4.add(cbYear);
		pn4.add(cbMonth);
		pn4.add(cbDay);

		add(pn1);
		pn1.add(lb4);
		pn1.add(cbDestiYear);
		pn1.add(cbDestiMonth);
		pn1.add(cbDestiDay);

		add(pn6);
		pn6.add(BtRegister);
		pn6.add(BtBack);

		BtRegister.addActionListener(this);
		BtBack.addActionListener(this);
		this.addWindowListener(new Windowadapter(this));
		setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == BtRegister) {

			if (false == StartWindow.celculate(
					Integer.parseInt(cbYear.getSelectedItem().toString()),
					Integer.parseInt(cbMonth.getSelectedItem().toString()),
					Integer.parseInt(cbDay.getSelectedItem().toString()))) {

				System.out.println("fail reservation");
				new StartWindow();
				this.dispose();

				return;
			} else {

				if (0 == CgSeatSet.getSelectedCheckbox().getLabel().toString()
						.compareTo("일반석")) {
					sSeatSort = "economic_seat";
				} else {
					sSeatSort = "business_seat";
				}

				System.out.println("success reservation");
				sStartDay = cbDay.getSelectedItem().toString();
				sDestniPosition = cbDestniposition.getSelectedItem().toString();
				sStartMonth = cbMonth.getSelectedItem().toString();
				sPersonAdult = cbAdult.getSelectedItem().toString();
				sPersonChild = cbChild.getSelectedItem().toString();
				sStartPostion = cbStartPosition.getSelectedItem().toString();
				sStartYear = cbYear.getSelectedItem().toString();
				sEndDay = cbDestiDay.getSelectedItem().toString();
				sEndMonth = cbDestiMonth.getSelectedItem().toString();
				sEndYear = cbDestiYear.getSelectedItem().toString();
				
				if(Integer.valueOf(sStartYear+sStartMonth+ sStartDay)>
						Integer.valueOf(sEndYear+sEndMonth+ sEndDay))
				{
					new DialogSuccess("경고", "날짜 선택이", "잘못 되었습니다",
							150, 100);
					return;
				}

				System.out.println("you enter the RegisterDetail Window");
				new RegisterDetail();

				setVisible(false);
				this.dispose();

			}

		} else if (e.getSource() == BtBack) {

			System.out.println("you enter user afterLogWindow");
			new AfterLoginWindow();
			setVisible(false);
			dispose();
			this.dispose();
			return;

		}
	}

}

