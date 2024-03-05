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
		
		JLabel DepositnumberCheck = new JLabel("�Ա��� ������ ���¹�ȣ: ");
		JTextField ChecknumberField = new JTextField();
		JLabel DepositpwdCheck = new JLabel("�Ա��� ������ ��й�ȣ: ");
		JTextField CheckpwdField = new JTextField();
		JLabel Depositmoney = new JLabel("�Ա��� �ݾ�: ");
		JTextField moneyField = new JTextField();
		
		JButton yes = new JButton("Ȯ��");
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) throws NumberFormatException {
				String number = ChecknumberField.getText();
				String pwd = CheckpwdField.getText();
				try {
					int money = Integer.parseInt(moneyField.getText());
					if(!DepositAccount.NUMBERChecking(number)) {
						JOptionPane.showMessageDialog(depositframe, "���¹�ȣ�� Ȯ�����ּ���");
						return;
					}
					if(!DepositAccount.pwdChecking(pwd)) {
						JOptionPane.showMessageDialog(depositframe, "��й�ȣ�� Ȯ�����ּ���");
						return;
					}
					if(!DepositAccount.MAXmoneyChecking(money)) {
						JOptionPane.showMessageDialog(depositframe, "���뿹�� ���¿��� �ּ� 1������ �ִ� 10������� �Աݰ����մϴ�");
						return;
					}
					if(DepositAccount.Account2Check()) { //���°� �������� �ƴ��� Ȯ���ϱ� 
						if(!DepositAccount.AccountMONEYlimit(Integer.toString(money))) {
							JOptionPane.showMessageDialog(depositframe, "���ݰ��´� �����ݾ׸� �Ա� �����մϴ�");
							return;
						}
						if(DepositAccount.CanAccountChange(Integer.toString(money))) {//�̹� �Ա����� ���ݸ��� �޼�
							JOptionPane.showMessageDialog(depositframe, "���� ��ǥ �޼�");
							JOptionPane.showMessageDialog(depositframe, "�ݸ� 10%�� �ԱݵǾ����ϴ�");
							DepositAccount depositaccount = new DepositAccount(number, pwd, money);
							DepositAccount.deposit(money);
							DepositAccount.ChangeAccount(number, pwd);
							JOptionPane.showMessageDialog(depositframe, "���¹�ȣ: " + number + ", " + Integer.toString(money) + "�� �ԱݿϷ�");
							DepositAccount.DepositCreatelog(number, money);
							DepositAccount.DepositAccountGoalCreatelog(number);
							depositframe.dispose();
							new Main();
						}
						else { //���� ���� �޼� �ƴҶ�
							DepositAccount depositaccount = new DepositAccount(number, pwd, money);
							DepositAccount.deposit(money);
							JOptionPane.showMessageDialog(depositframe, "���¹�ȣ: " + number + ", " + Integer.toString(money) + "�� �ԱݿϷ�");
							DepositAccount.DepositCreatelog(number, money);
							depositframe.dispose();
							new Main();
						}
					}
					else { //���ݰ��°� �ƴҶ�
						DepositAccount depositaccount = new DepositAccount(number, pwd, money);
						DepositAccount.deposit(money);
						JOptionPane.showMessageDialog(depositframe, "���¹�ȣ: " + number + ", " + Integer.toString(money) + "�� �ԱݿϷ�");
						DepositAccount.DepositCreatelog(number, money);
						depositframe.dispose();
						new Main();
					}
				} catch(NumberFormatException n) {
					JOptionPane.showMessageDialog(depositframe, "���뿹�� ���¿��� �ִ� 10����� �Աݰ����մϴ�");
				}
			}
		});
		JButton Cancle = new JButton("���");
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
