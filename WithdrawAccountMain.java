package bank;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class WithdrawAccountMain {
	public WithdrawAccountMain() {
		
		JFrame withdrawframe = new JFrame("Bank");
		withdrawframe.setSize(500, 250);
		withdrawframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridLayout(4, 3));
		
		
		JLabel WithdrawnumberCheck = new JLabel("출금할 계좌의 계좌번호: ");
		JTextField ChecknumberField = new JTextField();
		JLabel WithdrawpwdCheck = new JLabel("출금할 계좌의 비밀번호: ");
		JTextField CheckpwdField = new JTextField();
		JLabel Withdrawmoney = new JLabel("출금할 금액: ");
		JTextField moneyField = new JTextField();
		
		JButton yes = new JButton("확인");
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = ChecknumberField.getText();
				String pwd = CheckpwdField.getText();
				try {
					int money = Integer.parseInt(moneyField.getText());
					if(!WithdrawAccount.NUMBERChecking(number)) {
						JOptionPane.showMessageDialog(withdrawframe, "계좌번호를 확인해주세요");
						return;
					}
					if(!WithdrawAccount.pwdChecking(pwd)) {
						JOptionPane.showMessageDialog(withdrawframe, "비밀번호를 확인해주세요");
						return;
					}
					if(!WithdrawAccount.MONEYChecking(money)) {
						JOptionPane.showMessageDialog(withdrawframe, "금액 혹은 잔액을 확인해주세요");
						return;
					}
					if(WithdrawAccount.Account2Check()) {//적금 계좌인지 판단
						JDialog AccountChange = new JDialog();
						AccountChange.setSize(600, 100);
						JLabel Warning = new JLabel("현재 출금하려는 계좌는 적금계좌입니다. 출금 후에 보통예금 계좌로 전환됩니다");
						JButton sure = new JButton("확인");
						sure.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								WithdrawAccount withdrawaccount = new WithdrawAccount(number, pwd, money);
								WithdrawAccount.withdraw(money);
								WithdrawAccount.AccountChange(number, pwd);
								JOptionPane.showMessageDialog(withdrawframe, "계좌번호: " + number + ", " + Integer.toString(money) + "원 출금완료");
								WithdrawAccount.WithdrawCreatelog(number, money);
								WithdrawAccount.WithdrawAccountFailCreatelog(number);
								AccountChange.setVisible(false);
								withdrawframe.setEnabled(true);
								withdrawframe.dispose();
								new Main();
							}
						});
						JButton nope = new JButton("취소");
						nope.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								AccountChange.setVisible(false);
								withdrawframe.setEnabled(true);
								return;
							}
						});
						Font font1 = new Font("맑은 고딕", Font.PLAIN, 15);
						Warning.setFont(font1);
						
						AccountChange.add(Warning, "North");
						AccountChange.add(nope, "West");
						AccountChange.add(sure, "East");
						AccountChange.setVisible(true);
					}
					else { //적금계좌가 아닐때
						WithdrawAccount withdrawaccount = new WithdrawAccount(number, pwd, money);
						WithdrawAccount.withdraw(money);
						JOptionPane.showMessageDialog(withdrawframe, "계좌번호: " + number + ", " + Integer.toString(money) + "원 출금완료");
						WithdrawAccount.WithdrawCreatelog(number, money);
						withdrawframe.dispose();
						new Main();
					}
				} catch(NumberFormatException n) {
					JOptionPane.showMessageDialog(withdrawframe, "금액 혹은 잔액을 확인해주세요");
				}
			}
		});
		JButton Cancle = new JButton("취소");
		Cancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				withdrawframe.dispose();
				new Main();
			}
		});
		
		panel.add(WithdrawnumberCheck);
		panel.add(ChecknumberField);
		panel.add(WithdrawpwdCheck);
		panel.add(CheckpwdField);
		panel.add(Withdrawmoney);
		panel.add(moneyField);
		panel.add(yes);
		panel.add(Cancle);
		withdrawframe.add(panel);
		withdrawframe.setVisible(true);
	}
}
