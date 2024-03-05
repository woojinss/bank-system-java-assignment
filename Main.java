package bank;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;

public class Main extends LoginMain {
	public Main(){
		JFrame mainframe = new JFrame("Bank");
		mainframe.setSize(400, 400);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridLayout(5, 3));
		
		JLabel HelloUser = new JLabel("������� " + User.nowloginId + " ��");
		
		//ȸ��Ż�� Ȯ�� ���̾�α�
		//----------------------------------------------------------------------------------------------
		JDialog Check = new JDialog(this, "ȸ��Ż��", true);
		Check.setSize(200, 100);
		JLabel real = new JLabel("���� Ż���Ͻðڽ��ϱ�?");
		JButton sure = new JButton("Ȯ��");
		sure.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File file1 = new File(LoginMain.path + User.nowloginId + "'s Accounts.txt");
				File file2 = new File(LoginMain.path + User.nowloginId + "'s Accounts balance.txt");
				file1.delete();
				file2.delete();
				User.remove("ID: " + User.nowloginId + ", PASSWORD: " + User.nowloginPwd + "|||");
				JOptionPane.showMessageDialog(Check, "Ż�� �Ϸ�Ǿ����ϴ�");
				Check.setVisible(false);
				LoginMain.main(null);
				mainframe.dispose();
			}
		});
		JButton no = new JButton("���");
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Check.setVisible(false);
			}
		});
		Font font1 = new Font("���� ���", Font.PLAIN, 15);
		real.setFont(font1);
		
		Check.add(real, "North");
		Check.add(no, "West");
		Check.add(sure, "East");
		//----------------------------------------------------------------------------------------------
		
		//���°�����ư
		JButton CreateAccount = new JButton("���°���");
		CreateAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateAccountMain();
				mainframe.dispose();
			}
		});
		
		//����������ư
		JButton DeleteAccount = new JButton("��������");
		DeleteAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteAccountMain();
				mainframe.dispose();
			}
		});
		
		//�����Աݹ�ư
		JButton Deposit = new JButton("�����Ա�");
		Deposit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DepositAccountMain();
				mainframe.dispose();
			}
		});
		
		//������ݹ�ư
		JButton Withdraw = new JButton("�������");
		Withdraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new WithdrawAccountMain();
				mainframe.dispose();
			}
		});
		
		//�α׾ƿ���ư
		JButton Logout = new JButton("�α׾ƿ�");
		Logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginMain.main(null);
				mainframe.dispose();
			}
		});
		
		//ȸ��Ż���ư
		JButton DeleteUser = new JButton("ȸ��Ż��");
		DeleteUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Check.setVisible(true);
			}
		});
		
		//��볻����ư
		JButton Log = new JButton("��볻��");	
		Log.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LogMain();
				mainframe.dispose();
			}
		});
		
		//��볻����ư
		JButton CheckAccount = new JButton("�� ����");	
		CheckAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MyAccount();
				mainframe.dispose();
			}
		});
		
		Font font = new Font("���� ���", Font.PLAIN, 20);
		HelloUser.setFont(font);
		
		panel.add(HelloUser);
		panel.add(new JLabel());
		panel.add(CreateAccount);
		panel.add(DeleteAccount);
		panel.add(Deposit);
		panel.add(Withdraw);
		panel.add(Log);
		panel.add(CheckAccount);
		panel.add(Logout);
		panel.add(DeleteUser);
		mainframe.add(panel);
		mainframe.setVisible(true);
	}
}
