package bank;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class LoginMain extends Frame {
	static String truepath = System.getProperty("user.dir");
	static String path = System.getProperty("user.home") + File.separator + "bank\\";
	static File SaveFolder =  new File(path);
	public static void main(String[] args) {
		
		SaveFolder.mkdirs();
		
		JFrame loginframe = new JFrame("Bank");
		loginframe.setSize(300, 200);
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridLayout(4, 3));
		
		JLabel BankTitle = new JLabel("BANK");
		
		JLabel IDLabel = new JLabel("ID: ");
		JTextField IDField = new JTextField();
		JLabel PasswordLabel = new JLabel("Password: ");
		JPasswordField PasswordField = new JPasswordField();
		
		//로그인 버튼
		JButton LoginButton = new JButton("로그인");
		LoginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//버튼 클릭 이벤트
				String id = IDField.getText();
				String pwd = new String(PasswordField.getPassword());
				if(!User.ValidInput(id, pwd)) {
					JOptionPane.showMessageDialog(loginframe, "유효하지 않은 입력입니다");
					return;
				}
				if(!User.Login(id, pwd)) {
					JOptionPane.showMessageDialog(loginframe, "가입하지 않은 회원입니다");
					return;
				}
				JOptionPane.showMessageDialog(loginframe, "로그인 성공");
				User.nowloginId = id;
				User.nowloginPwd = pwd;
				new Main();
				loginframe.dispose();
			}
		});
		
		//회원가입버튼
		JButton RegisterButton = new JButton("회원가입");
		RegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//버튼 클릭 이벤트
				String id = IDField.getText();
				String pwd = new String(PasswordField.getPassword());
				if(!User.ValidInput(id, pwd)) {
					JOptionPane.showMessageDialog(loginframe, "유효하지 않은 입력입니다");
					return;
				}
				if(User.IDcheck(id)) {
					JOptionPane.showMessageDialog(loginframe, "이미 존재하는 아이디입니다");
					return;
				}
				if(!User.lengthcheck(id, pwd)) {
					JOptionPane.showMessageDialog(loginframe, "아이디는 10자리 이하, 비밀번호는 8자리 이하로 입력해야합니다");
					return;
				}
				User user = new User(IDField.getText(), new String(PasswordField.getPassword()));
				User.MakeAccount(user);
				JOptionPane.showMessageDialog(loginframe, "가입 성공");
			}
		});
		
		Font font = new Font("맑은 고딕", Font.PLAIN, 20);
		
		panel.add(BankTitle);
		panel.add(new JLabel());
		panel.add(IDLabel);
		panel.add(IDField);
		panel.add(PasswordLabel);
		panel.add(PasswordField);
		panel.add(LoginButton);
		panel.add(RegisterButton);
		
		loginframe.add(panel);
		loginframe.setVisible(true);
	}
}
