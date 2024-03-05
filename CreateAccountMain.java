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
		
		JLabel CreateHello = new JLabel("어떤 종류의 계좌를 개설하시겠습니까?");
		
		
		//-------------------------------------------------------------------------------------- 보통예금
		JDialog Account1_1 = new JDialog(this, "계좌생성(보통예금)", true);
		Account1_1.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //윈도우 종료키 막기
		Account1_1.setSize(600, 200);
		JPanel panel1 = new JPanel(new GridLayout(3, 3));
		JLabel id1 = new JLabel("아이디: ");
		JTextField idField1 = new JTextField();
		JLabel accountpwd1 = new JLabel("계좌비밀번호(4자리): ");
		JTextField pwdField1 = new JTextField();
		JButton yes1 = new JButton("확인");
		yes1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Account();
				String id = idField1.getText();
				String pwd = pwdField1.getText();
				if(!CreateAccount1.IDChecking(id)) {
					JOptionPane.showMessageDialog(Account1_1, "현재 로그인 중인 아이디가 아닙니다");
					return;
				}
				if(!CreateAccount1.PWDChecking(pwd)) {
					JOptionPane.showMessageDialog(Account1_1, "비밀번호는 숫자 4자리로 입력해야합니다");
					return;
				}
				if(!CreateAccount1.MAXchecking()) {
					JOptionPane.showMessageDialog(Account1_1, "계좌는 최대 5개까지 가질 수 있습니다");
					return;
				}
				CreateAccount1 account = new CreateAccount1(idField1.getText(), pwdField1.getText());
				CreateAccount1.CreateAcount(account);
				JOptionPane.showMessageDialog(Account1_1, "보통예금 가입이 완료되었습니다");
				CreateAccount1.Createlog();
				createframe.setVisible(true);
				Account1_1.setVisible(false);
			}
		});
		JButton no1 = new JButton("취소");
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

		JButton Account1 = new JButton("보통예금");
		Account1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createframe.setVisible(false);
				Account1_1.setVisible(true);
			}
		});
		//--------------------------------------------------------------------------------------
		
		
		
		//-------------------------------------------------------------------------------------- 적금
		JDialog Account2_1 = new JDialog(this, "계좌생성(적금)", true);
		Account2_1.setSize(500, 300);
		Account2_1.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //윈도우 종료키 막기
		JPanel panel2 = new JPanel(new GridLayout(5, 3));
		JLabel id2 = new JLabel("아이디: ");
		JTextField idField2 = new JTextField();
		JLabel accountpwd2 = new JLabel("계좌비밀번호(4자리): ");
		JTextField pwdField2 = new JTextField();
		JLabel Checkmoney = new JLabel("입금금액(5만원, 10만원, 15만원, 20만원): ");
		JTextField moneyField = new JTextField();
		JLabel Checkgoal = new JLabel("기간(6개월 이상, 24개월 이하): ");
		JTextField GoalField = new JTextField();
		JButton yes2 = new JButton("확인");
		yes2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Account();
				String id = idField2.getText();
				String pwd = pwdField2.getText();
				String money = moneyField.getText();
				String goal = GoalField.getText();
				if(!CreateAccount2.IDChecking(id)) {
					JOptionPane.showMessageDialog(Account2_1, "현재 로그인 중인 아이디가 아닙니다");
					return;
				}
				if(!CreateAccount2.PWDChecking(pwd)) {
					JOptionPane.showMessageDialog(Account2_1, "비밀번호는 숫자 4자리로 입력해야합니다");
					return;
				}
				if(!CreateAccount2.MONEYChecking(money)) {
					JOptionPane.showMessageDialog(Account2_1, "입금금액을 확인해주세요. 숫자로만 입력 가능합니다");
					return;
				}
				if(!CreateAccount2.MAXchecking()) {
					JOptionPane.showMessageDialog(Account2_1, "계좌는 최대 5개까지 가질 수 있습니다");
					return;
				}
				if(!CreateAccount2.GOALChecking(goal)) {
					JOptionPane.showMessageDialog(Account2_1, "기간을 확인해주세요");
					return;
				}
				CreateAccount2 account = new CreateAccount2(idField2.getText(), pwdField2.getText(), moneyField.getText(), GoalField.getText());
				CreateAccount2.CreateAcount(account);
				JOptionPane.showMessageDialog(Account2_1, "적금 가입이 완료되었습니다");
				CreateAccount2.Createlog();
				createframe.setVisible(true);
				Account2_1.setVisible(false);
			}
		});
		JButton no2 = new JButton("취소");
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

		JButton Account2 = new JButton("적금");
		Account2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createframe.setVisible(false);
				Account2_1.setVisible(true);
			}
		});
		//--------------------------------------------------------------------------------------
		
		
		JButton Cancle = new JButton("취소");
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
