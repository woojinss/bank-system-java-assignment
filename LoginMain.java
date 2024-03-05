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
		
		//�α��� ��ư
		JButton LoginButton = new JButton("�α���");
		LoginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ư Ŭ�� �̺�Ʈ
				String id = IDField.getText();
				String pwd = new String(PasswordField.getPassword());
				if(!User.ValidInput(id, pwd)) {
					JOptionPane.showMessageDialog(loginframe, "��ȿ���� ���� �Է��Դϴ�");
					return;
				}
				if(!User.Login(id, pwd)) {
					JOptionPane.showMessageDialog(loginframe, "�������� ���� ȸ���Դϴ�");
					return;
				}
				JOptionPane.showMessageDialog(loginframe, "�α��� ����");
				User.nowloginId = id;
				User.nowloginPwd = pwd;
				new Main();
				loginframe.dispose();
			}
		});
		
		//ȸ�����Թ�ư
		JButton RegisterButton = new JButton("ȸ������");
		RegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ư Ŭ�� �̺�Ʈ
				String id = IDField.getText();
				String pwd = new String(PasswordField.getPassword());
				if(!User.ValidInput(id, pwd)) {
					JOptionPane.showMessageDialog(loginframe, "��ȿ���� ���� �Է��Դϴ�");
					return;
				}
				if(User.IDcheck(id)) {
					JOptionPane.showMessageDialog(loginframe, "�̹� �����ϴ� ���̵��Դϴ�");
					return;
				}
				if(!User.lengthcheck(id, pwd)) {
					JOptionPane.showMessageDialog(loginframe, "���̵�� 10�ڸ� ����, ��й�ȣ�� 8�ڸ� ���Ϸ� �Է��ؾ��մϴ�");
					return;
				}
				User user = new User(IDField.getText(), new String(PasswordField.getPassword()));
				User.MakeAccount(user);
				JOptionPane.showMessageDialog(loginframe, "���� ����");
			}
		});
		
		Font font = new Font("���� ���", Font.PLAIN, 20);
		
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
