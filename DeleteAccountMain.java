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
		
		JLabel DeleteHello = new JLabel("�����ϰ��� �ϴ� ������ ���¹�ȣ(NUMBER)�� �Է����ּ���");
		JTextField Number = new JTextField();
		
		JButton yes = new JButton("Ȯ��");
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String number = Number.getText();
				if(!DeleteAccount.ValidInput(number)) {
					JOptionPane.showMessageDialog(deleteframe, "��ȿ���� ���� ���¹�ȣ�Դϴ�");
					return;
				}
				DeleteAccount.DelAccount();
				JOptionPane.showMessageDialog(deleteframe, number + " ���°� �����Ǿ����ϴ�");
				DeleteAccount.Createlog(number);
				new Main();
				deleteframe.dispose();
			}
		});
		JButton no = new JButton("���");
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
