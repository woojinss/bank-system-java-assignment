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
		
		JLabel HelloUser = new JLabel("어서오세요 " + User.nowloginId + " 님");
		
		//회원탈퇴 확인 다이어로그
		//----------------------------------------------------------------------------------------------
		JDialog Check = new JDialog(this, "회원탈퇴", true);
		Check.setSize(200, 100);
		JLabel real = new JLabel("정말 탈퇴하시겠습니까?");
		JButton sure = new JButton("확인");
		sure.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File file1 = new File(LoginMain.path + User.nowloginId + "'s Accounts.txt");
				File file2 = new File(LoginMain.path + User.nowloginId + "'s Accounts balance.txt");
				file1.delete();
				file2.delete();
				User.remove("ID: " + User.nowloginId + ", PASSWORD: " + User.nowloginPwd + "|||");
				JOptionPane.showMessageDialog(Check, "탈퇴가 완료되었습니다");
				Check.setVisible(false);
				LoginMain.main(null);
				mainframe.dispose();
			}
		});
		JButton no = new JButton("취소");
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Check.setVisible(false);
			}
		});
		Font font1 = new Font("맑은 고딕", Font.PLAIN, 15);
		real.setFont(font1);
		
		Check.add(real, "North");
		Check.add(no, "West");
		Check.add(sure, "East");
		//----------------------------------------------------------------------------------------------
		
		//계좌개설버튼
		JButton CreateAccount = new JButton("계좌개설");
		CreateAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateAccountMain();
				mainframe.dispose();
			}
		});
		
		//계좌해지버튼
		JButton DeleteAccount = new JButton("계좌해지");
		DeleteAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteAccountMain();
				mainframe.dispose();
			}
		});
		
		//계좌입금버튼
		JButton Deposit = new JButton("계좌입금");
		Deposit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DepositAccountMain();
				mainframe.dispose();
			}
		});
		
		//계좌출금버튼
		JButton Withdraw = new JButton("계좌출금");
		Withdraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new WithdrawAccountMain();
				mainframe.dispose();
			}
		});
		
		//로그아웃버튼
		JButton Logout = new JButton("로그아웃");
		Logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginMain.main(null);
				mainframe.dispose();
			}
		});
		
		//회원탈퇴버튼
		JButton DeleteUser = new JButton("회원탈퇴");
		DeleteUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Check.setVisible(true);
			}
		});
		
		//사용내역버튼
		JButton Log = new JButton("사용내역");	
		Log.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LogMain();
				mainframe.dispose();
			}
		});
		
		//사용내역버튼
		JButton CheckAccount = new JButton("내 계좌");	
		CheckAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MyAccount();
				mainframe.dispose();
			}
		});
		
		Font font = new Font("맑은 고딕", Font.PLAIN, 20);
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
