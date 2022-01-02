package com.airClient;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


class LoginWindow extends Frame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageButton btLogin, btBack;
	TextField tfId, tfPassWord;

	public LoginWindow() {
		setBounds(200, 200, 220, 150);
		setTitle("KUT AIR");
		setVisible(true);
		setResizable(false);
		System.out.println("login window");
		
		ImageIcon igId= new ImageIcon("id.png");
		JLabel lbId = new JLabel(igId);
		
		ImageIcon igPassword= new ImageIcon("password.png");
		JLabel lbPassWord = new JLabel(igPassword);
		
		tfId = new TextField(10);
		tfPassWord = new TextField(10);
		setLayout(new FlowLayout());
		setResizable(false);
		
		btLogin = new ImageButton("login.png");
		btBack = new ImageButton("back.png");

		add(lbId);
		add(tfId);
		add(lbPassWord);
		add(tfPassWord);
		tfPassWord.setEchoChar('*');
		add(btLogin);
		add(btBack);
		btLogin.addActionListener(this);
		btBack.addActionListener(this);

		this.addWindowListener(new Windowadapter(this));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btLogin) {

			try {
				String query = "select * from client where id ='"
						+ tfId.getText().toString() + "' and " + "password = '"
						+ tfPassWord.getText().toString() + "'";
				StartWindow.rs = StartWindow.stmt.executeQuery(query);

				if (false == StartWindow.rs.next()) {
					new DialogSuccess("로그인 실패", tfId.getText(), "는 존재하지 않습니다.",
							150, 100);
					System.out.println("you enter the StartWindow");
					return;
				} else {
					new DialogSuccess("로그인 성공", tfId.getText(), "님 안녕하세요!",
							150, 100);
					StartWindow.userId = tfId.getText();
					StartWindow.UserPassWor = tfPassWord.getText();
					System.out.println(tfId.getText() + "님이로그인되었습니다. 비번은"
							+ tfPassWord.getText());
					new AfterLoginWindow();
					this.dispose();
					return;
				}
			} catch (SQLException e1) {
				System.err.println("error sql = " + e1);
			}

		} else if (e.getSource() == btBack) {

			System.out.println("you enter the StartWindow");
			new StartWindow();
			this.dispose();
			return;

		}
	}

}

class ImageButton extends JButton {
	public ImageButton(String img){
		this(new ImageIcon(img));
	}
	
	public ImageButton(ImageIcon icon){
		setIcon(icon);
		setMargin(new Insets(0, 0, 0, 0));
		setIconTextGap(0);
		setBorderPainted(false);
		setBorder(null);
		setText(null);
		setSize(icon.getImage().getWidth(null),icon.getImage().getHeight(null));
	}
}
