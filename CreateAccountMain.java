package bank;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class CreateAccountMain extends Frame {
	public CreateAccountMain() {
		JFrame createframe = new JFrame("Bank");
		createframe.setSize(400, 400);
		createframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridLayout(4, 3));
		
		JLabel CreateHello = new JLabel("� ������ ���¸� �����Ͻðڽ��ϱ�?");
		
		
		//-------------------------------------------------------------------------------------- ���뿹��
		JDialog Account1_1 = new JDialog(this, "���»���(���뿹��)", true);
		Account1_1.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //������ ����Ű ����
		Account1_1.setSize(600, 200);
		JPanel panel1 = new JPanel(new GridLayout(3, 3));
		JLabel id1 = new JLabel("���̵�: ");
		JTextField idField1 = new JTextField();
		JLabel accountpwd1 = new JLabel("���º�й�ȣ(4�ڸ�): ");
		JTextField pwdField1 = new JTextField();
		JButton yes1 = new JButton("Ȯ��");
		yes1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Account();
				String id = idField1.getText();
				String pwd = pwdField1.getText();
				if(!CreateAccount1.IDChecking(id)) {
					JOptionPane.showMessageDialog(Account1_1, "���� �α��� ���� ���̵� �ƴմϴ�");
					return;
				}
				if(!CreateAccount1.PWDChecking(pwd)) {
					JOptionPane.showMessageDialog(Account1_1, "��й�ȣ�� ���� 4�ڸ��� �Է��ؾ��մϴ�");
					return;
				}
				if(!CreateAccount1.MAXchecking()) {
					JOptionPane.showMessageDialog(Account1_1, "���´� �ִ� 5������ ���� �� �ֽ��ϴ�");
					return;
				}
				CreateAccount1 account = new CreateAccount1(idField1.getText(), pwdField1.getText());
				CreateAccount1.CreateAcount(account);
				JOptionPane.showMessageDialog(Account1_1, "���뿹�� ������ �Ϸ�Ǿ����ϴ�");
				CreateAccount1.Createlog();
				createframe.setVisible(true);
				Account1_1.setVisible(false);
			}
		});
		JButton no1 = new JButton("���");
		no1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createframe.setVisible(true);
				Account1_1.setVisible(false);
			}
		});
		
		panel1.add(id1);
		panel1.add(idField1);
		panel1.add(accountpwd1);
		panel1.add(pwdField1);
		panel1.add(yes1);
		panel1.add(no1);
		Account1_1.add(panel1);

		JButton Account1 = new JButton("���뿹��");
		Account1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createframe.setVisible(false);
				Account1_1.setVisible(true);
			}
		});
		//--------------------------------------------------------------------------------------
		
		
		
		//-------------------------------------------------------------------------------------- ����
		JDialog Account2_1 = new JDialog(this, "���»���(����)", true);
		Account2_1.setSize(500, 300);
		Account2_1.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //������ ����Ű ����
		JPanel panel2 = new JPanel(new GridLayout(5, 3));
		JLabel id2 = new JLabel("���̵�: ");
		JTextField idField2 = new JTextField();
		JLabel accountpwd2 = new JLabel("���º�й�ȣ(4�ڸ�): ");
		JTextField pwdField2 = new JTextField();
		JLabel Checkmoney = new JLabel("�Աݱݾ�(5����, 10����, 15����, 20����): ");
		JTextField moneyField = new JTextField();
		JLabel Checkgoal = new JLabel("�Ⱓ(6���� �̻�, 24���� ����): ");
		JTextField GoalField = new JTextField();
		JButton yes2 = new JButton("Ȯ��");
		yes2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Account();
				String id = idField2.getText();
				String pwd = pwdField2.getText();
				String money = moneyField.getText();
				String goal = GoalField.getText();
				if(!CreateAccount2.IDChecking(id)) {
					JOptionPane.showMessageDialog(Account2_1, "���� �α��� ���� ���̵� �ƴմϴ�");
					return;
				}
				if(!CreateAccount2.PWDChecking(pwd)) {
					JOptionPane.showMessageDialog(Account2_1, "��й�ȣ�� ���� 4�ڸ��� �Է��ؾ��մϴ�");
					return;
				}
				if(!CreateAccount2.MONEYChecking(money)) {
					JOptionPane.showMessageDialog(Account2_1, "�Աݱݾ��� Ȯ�����ּ���. ���ڷθ� �Է� �����մϴ�");
					return;
				}
				if(!CreateAccount2.MAXchecking()) {
					JOptionPane.showMessageDialog(Account2_1, "���´� �ִ� 5������ ���� �� �ֽ��ϴ�");
					return;
				}
				if(!CreateAccount2.GOALChecking(goal)) {
					JOptionPane.showMessageDialog(Account2_1, "�Ⱓ�� Ȯ�����ּ���");
					return;
				}
				CreateAccount2 account = new CreateAccount2(idField2.getText(), pwdField2.getText(), moneyField.getText(), GoalField.getText());
				CreateAccount2.CreateAcount(account);
				JOptionPane.showMessageDialog(Account2_1, "���� ������ �Ϸ�Ǿ����ϴ�");
				CreateAccount2.Createlog();
				createframe.setVisible(true);
				Account2_1.setVisible(false);
			}
		});
		JButton no2 = new JButton("���");
		no2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createframe.setVisible(true);
				Account2_1.setVisible(false);
			}
		});
		
		panel2.add(id2);
		panel2.add(idField2);
		panel2.add(accountpwd2);
		panel2.add(pwdField2);
		panel2.add(Checkmoney);
		panel2.add(moneyField);
		panel2.add(Checkgoal);
		panel2.add(GoalField);
		panel2.add(yes2);
		panel2.add(no2);
		Account2_1.add(panel2);

		JButton Account2 = new JButton("����");
		Account2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createframe.setVisible(false);
				Account2_1.setVisible(true);
			}
		});
		//--------------------------------------------------------------------------------------
		
		
		JButton Cancle = new JButton("���");
		Cancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createframe.dispose();
				new Main();
			}
		});
		
		panel.add(CreateHello);
		panel.add(Account1);
		panel.add(Account2);
		panel.add(Cancle);
		createframe.add(panel);
		createframe.setVisible(true);
	}
}
