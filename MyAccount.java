package bank;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class MyAccount {
	public MyAccount() {
		JFrame myframe = new JFrame("Bank");
		myframe.setSize(1200, 600);
		myframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridLayout(5, 1));
		
		JLabel list = new JLabel(User.nowloginId + "님의 계좌목록");
		JLabel noaccount = new JLabel("계좌가 존재하지 않습니다");
		//------------------------------------------------------------------------------------------------------------- 계좌찾기
		String a1 = null, a2 = null, a3 = null, a4 = null, a5 = null;
		String b1 = null, b2 = null, b3 = null, b4 = null, b5 = null;
		int num = 0;
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + User.nowloginId + "'s Accounts.txt"))){
			String line;
			num = 0;
			while ((line = br.readLine()) != null) {
					num++;
					if(num == 1) {
						a1 = line;
					}
					else if(num == 2) {
						a2 = line;
					}
					else if(num == 3) {
						a3 = line;
					}
					else if(num == 4) {
						a4 = line;
					}
					else if(num == 5) {
						a5 = line;
					}
			}
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
			e.printStackTrace();
		}
		//계좌별 금액
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + User.nowloginId + "'s Accounts balance.txt"))){
			String line;
			num = 0;
			while ((line = br.readLine()) != null) {
					num++;
					if(num == 1) {
						b1 = line;
					}
					else if(num == 2) {
						b2 = line;
					}
					else if(num == 3) {
						b3 = line;
					}
					else if(num == 4) {
						b4 = line;
					}
					else if(num == 5) {
						b5 = line;
					}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		JLabel account1 = new JLabel(a1);
		JLabel account2 = new JLabel(a2);
		JLabel account3 = new JLabel(a3);
		JLabel account4 = new JLabel(a4);
		JLabel account5 = new JLabel(a5);
		
		JLabel balance1 = new JLabel(b1);
		JLabel balance2 = new JLabel(b2);
		JLabel balance3 = new JLabel(b3);
		JLabel balance4 = new JLabel(b4);
		JLabel balance5 = new JLabel(b5);
		
		//-------------------------------------------------------------------------------------------------------------------
		File file = new File(LoginMain.path + User.nowloginId + "'s Accounts.txt");
		
		JButton Cancle = new JButton("취소");
		Cancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myframe.dispose();
				new Main();
			}
		});
		
		Font font1 = new Font("맑은 고딕", Font.PLAIN, 30);
		Font font2 = new Font("맑은 고딕", Font.PLAIN, 15);
		
		myframe.add(list, "North");
		list.setFont(font1);
		
		//계좌를 만든적이 없으면 계좌 안나오게
		if(file.exists()) {
			if(num != 0) { //파일은 있는데 계좌만 없는 경우도 생각하기
				panel.add(account1);
				panel.add(balance1);
				account1.setFont(font2);
				balance1.setFont(font2);
				panel.add(account2);
				panel.add(balance2);
				account2.setFont(font2);
				balance2.setFont(font2);
				panel.add(account3);
				panel.add(balance3);
				account3.setFont(font2);
				balance3.setFont(font2);
				panel.add(account4);
				panel.add(balance4);
				account4.setFont(font2);
				balance4.setFont(font2);
				panel.add(account5);
				panel.add(balance5);
				account5.setFont(font2);
				balance5.setFont(font2);
			}
			else {
				myframe.add(noaccount, "West");
				noaccount.setFont(font1);
			}
		}
		else {
			myframe.add(noaccount, "West");
			noaccount.setFont(font1);
		}
		
		myframe.add(Cancle, "South");
		myframe.add(panel);
		myframe.setVisible(true);
	}
}




