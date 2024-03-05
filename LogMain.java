package bank;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class LogMain extends JFrame{
	public LogMain() {
		JFrame logframe = new JFrame("Bank");
		logframe.setSize(1200, 600);
		logframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridLayout(5, 3));
		
		JLabel LogHello = new JLabel(User.nowloginId + " 님의 사용기록");
		JLabel nolog = new JLabel("내역이 존재하지 않습니다");
		
		Queue<String> queue = new LinkedList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + User.nowloginId + " log.txt"))){
			String line;
			while ((line = br.readLine()) != null) {
				queue.add(line);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
		File file = new File(LoginMain.path + User.nowloginId + " log.txt");
		JButton Cancle = new JButton("취소");
		Cancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logframe.dispose();
				new Main();
			}
		});

		Font font1 = new Font("맑은 고딕", Font.PLAIN, 15);
		Font font2 = new Font("맑은 고딕", Font.PLAIN, 30);
		LogHello.setFont(font1);
		logframe.add(LogHello, "North");
		if(queue.size() == 0) {
			logframe.add(nolog, "West");
			nolog.setFont(font2);
		}
		else {
			if(file.exists()) {
				Log.CleanLog();
					try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + User.nowloginId + " log.txt"))){
						String line;
						while ((line = br.readLine()) != null) {
							JLabel a = new JLabel(line);
							panel.add(a);
							a.setFont(font1);
						}
					} catch(IOException e) {
						e.printStackTrace();
				}
			}
			else {
				logframe.add(nolog, "West");
				nolog.setFont(font2);
			}
		}

		logframe.add(Cancle, "South");
		logframe.add(panel);
		logframe.setVisible(true);
	}
}
