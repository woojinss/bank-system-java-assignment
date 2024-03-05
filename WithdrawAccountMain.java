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
		
		
		JLabel WithdrawnumberCheck = new JLabel("����� ������ ���¹�ȣ: ");
		JTextField ChecknumberField = new JTextField();
		JLabel WithdrawpwdCheck = new JLabel("����� ������ ��й�ȣ: ");
		JTextField CheckpwdField = new JTextField();
		JLabel Withdrawmoney = new JLabel("����� �ݾ�: ");
		JTextField moneyField = new JTextField();
		
		JButton yes = new JButton("Ȯ��");
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = ChecknumberField.getText();
				String pwd = CheckpwdField.getText();
				try {
					int money = Integer.parseInt(moneyField.getText());
					if(!WithdrawAccount.NUMBERChecking(number)) {
						JOptionPane.showMessageDialog(withdrawframe, "���¹�ȣ�� Ȯ�����ּ���");
						return;
					}
					if(!WithdrawAccount.pwdChecking(pwd)) {
						JOptionPane.showMessageDialog(withdrawframe, "��й�ȣ�� Ȯ�����ּ���");
						return;
					}
					if(!WithdrawAccount.MONEYChecking(money)) {
						JOptionPane.showMessageDialog(withdrawframe, "�ݾ� Ȥ�� �ܾ��� Ȯ�����ּ���");
						return;
					}
					if(WithdrawAccount.Account2Check()) {//���� �������� �Ǵ�
						JDialog AccountChange = new JDialog();
						AccountChange.setSize(600, 100);
						JLabel Warning = new JLabel("���� ����Ϸ��� ���´� ���ݰ����Դϴ�. ��� �Ŀ� ���뿹�� ���·� ��ȯ�˴ϴ�");
						JButton sure = new JButton("Ȯ��");
						sure.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								WithdrawAccount withdrawaccount = new WithdrawAccount(number, pwd, money);
								WithdrawAccount.withdraw(money);
								WithdrawAccount.AccountChange(number, pwd);
								JOptionPane.showMessageDialog(withdrawframe, "���¹�ȣ: " + number + ", " + Integer.toString(money) + "�� ��ݿϷ�");
								WithdrawAccount.WithdrawCreatelog(number, money);
								WithdrawAccount.WithdrawAccountFailCreatelog(number);
								AccountChange.setVisible(false);
								withdrawframe.setEnabled(true);
								withdrawframe.dispose();
								new Main();
							}
						});
						JButton nope = new JButton("���");
						nope.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								AccountChange.setVisible(false);
								withdrawframe.setEnabled(true);
								return;
							}
						});
						Font font1 = new Font("���� ���", Font.PLAIN, 15);
						Warning.setFont(font1);
						
						AccountChange.add(Warning, "North");
						AccountChange.add(nope, "West");
						AccountChange.add(sure, "East");
						AccountChange.setVisible(true);
					}
					else { //���ݰ��°� �ƴҶ�
						WithdrawAccount withdrawaccount = new WithdrawAccount(number, pwd, money);
						WithdrawAccount.withdraw(money);
						JOptionPane.showMessageDialog(withdrawframe, "���¹�ȣ: " + number + ", " + Integer.toString(money) + "�� ��ݿϷ�");
						WithdrawAccount.WithdrawCreatelog(number, money);
						withdrawframe.dispose();
						new Main();
					}
				} catch(NumberFormatException n) {
					JOptionPane.showMessageDialog(withdrawframe, "�ݾ� Ȥ�� �ܾ��� Ȯ�����ּ���");
				}
			}
		});
		JButton Cancle = new JButton("���");
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
