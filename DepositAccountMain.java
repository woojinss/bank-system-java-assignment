package bank;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class DepositAccountMain {
	public DepositAccountMain() {
		JFrame depositframe = new JFrame("Bank");
		depositframe.setSize(500, 250);
		depositframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridLayout(4, 3));
		
		JLabel DepositnumberCheck = new JLabel("입금할 계좌의 계좌번호: ");
		JTextField ChecknumberField = new JTextField();
		JLabel DepositpwdCheck = new JLabel("입금할 계좌의 비밀번호: ");
		JTextField CheckpwdField = new JTextField();
		JLabel Depositmoney = new JLabel("입금할 금액: ");
		JTextField moneyField = new JTextField();
		
		JButton yes = new JButton("확인");
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) throws NumberFormatException {
				String number = ChecknumberField.getText();
				String pwd = CheckpwdField.getText();
				try {
					int money = Integer.parseInt(moneyField.getText());
					if(!DepositAccount.NUMBERChecking(number)) {
						JOptionPane.showMessageDialog(depositframe, "계좌번호를 확인해주세요");
						return;
					}
					if(!DepositAccount.pwdChecking(pwd)) {
						JOptionPane.showMessageDialog(depositframe, "비밀번호를 확인해주세요");
						return;
					}
					if(!DepositAccount.MAXmoneyChecking(money)) {
						JOptionPane.showMessageDialog(depositframe, "보통예금 계좌에는 최소 1원부터 최대 10억원까지 입금가능합니다");
						return;
					}
					if(DepositAccount.Account2Check()) { //계좌가 적금인지 아닌지 확인하기 
						if(!DepositAccount.AccountMONEYlimit(Integer.toString(money))) {
							JOptionPane.showMessageDialog(depositframe, "적금계좌는 설정금액만 입금 가능합니다");
							return;
						}
						if(DepositAccount.CanAccountChange(Integer.toString(money))) {//이번 입금으로 적금만기 달성
							JOptionPane.showMessageDialog(depositframe, "적금 목표 달성");
							JOptionPane.showMessageDialog(depositframe, "금리 10%가 입금되었습니다");
							DepositAccount depositaccount = new DepositAccount(number, pwd, money);
							DepositAccount.deposit(money);
							DepositAccount.ChangeAccount(number, pwd);
							JOptionPane.showMessageDialog(depositframe, "계좌번호: " + number + ", " + Integer.toString(money) + "원 입금완료");
							DepositAccount.DepositCreatelog(number, money);
							DepositAccount.DepositAccountGoalCreatelog(number);
							depositframe.dispose();
							new Main();
						}
						else { //적금 만기 달성 아닐때
							DepositAccount depositaccount = new DepositAccount(number, pwd, money);
							DepositAccount.deposit(money);
							JOptionPane.showMessageDialog(depositframe, "계좌번호: " + number + ", " + Integer.toString(money) + "원 입금완료");
							DepositAccount.DepositCreatelog(number, money);
							depositframe.dispose();
							new Main();
						}
					}
					else { //적금계좌가 아닐때
						DepositAccount depositaccount = new DepositAccount(number, pwd, money);
						DepositAccount.deposit(money);
						JOptionPane.showMessageDialog(depositframe, "계좌번호: " + number + ", " + Integer.toString(money) + "원 입금완료");
						DepositAccount.DepositCreatelog(number, money);
						depositframe.dispose();
						new Main();
					}
				} catch(NumberFormatException n) {
					JOptionPane.showMessageDialog(depositframe, "보통예금 계좌에는 최대 10억까지 입금가능합니다");
				}
			}
		});
		JButton Cancle = new JButton("취소");
		Cancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				depositframe.dispose();
				new Main();
			}
		});
		
		panel.add(DepositnumberCheck);
		panel.add(ChecknumberField);
		panel.add(DepositpwdCheck);
		panel.add(CheckpwdField);
		panel.add(Depositmoney);
		panel.add(moneyField);
		panel.add(yes);
		panel.add(Cancle);
		depositframe.add(panel);
		depositframe.setVisible(true);
	}
}
