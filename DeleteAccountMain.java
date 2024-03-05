package bank;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DeleteAccountMain extends Frame {
	public DeleteAccountMain() {
		JFrame deleteframe = new JFrame("Bank");
		deleteframe.setSize(400, 300);
		deleteframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridLayout(4, 3));
		
		JLabel DeleteHello = new JLabel("삭제하고자 하는 계좌의 계좌번호(NUMBER)를 입력해주세요");
		JTextField Number = new JTextField();
		
		JButton yes = new JButton("확인");
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String number = Number.getText();
				if(!DeleteAccount.ValidInput(number)) {
					JOptionPane.showMessageDialog(deleteframe, "유효하지 않은 계좌번호입니다");
					return;
				}
				DeleteAccount.DelAccount();
				JOptionPane.showMessageDialog(deleteframe, number + " 계좌가 해지되었습니다");
				DeleteAccount.Createlog(number);
				new Main();
				deleteframe.dispose();
			}
		});
		JButton no = new JButton("취소");
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Main();
				deleteframe.dispose();
			}
		});
		
		panel.add(DeleteHello);
		panel.add(Number);
		panel.add(yes);
		panel.add(no);
		deleteframe.add(panel);
		deleteframe.setVisible(true);
	}
}
