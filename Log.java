package bank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.processing.FilerException;

public class Log {
	int datalog = 0;
	//내역의 개수 계산하는 함수
	static int LogCount() {
		int num = 0;
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path +  User.nowloginId + " log.txt"))){
			String line;
			while ((line = br.readLine()) != null) {
				num++;
			}
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
			e.printStackTrace();
		}
		return num;
	}
	//내역을 일정하게 유지하는 함수
	static void CleanLog() {
		String save = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(LoginMain.path + User.nowloginId +  " log.txt")));	
			String line;
			int n = 0;
			if(LogCount() >= 6) {
				n = LogCount() - 5;
			}
			for(int i = 0; i < n; i++) {
				line = br.readLine();
				String delData = line;
			}
			while((line = br.readLine()) != null) {
				save += (line+ "\r\n");
			}
			FileWriter fw = new FileWriter(LoginMain.path + User.nowloginId + " log.txt");
			fw.write(save);
			fw.close();
			br.close();
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(Exception e) {
			e.printStackTrace();
		}
	}
}