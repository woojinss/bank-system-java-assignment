package bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;

public class DeleteAccount {
	private String number;
	static int datalog;
	
	public DeleteAccount(String number) {
		this.number = number;
	}
	//-------------------------------------------------------------------------------------------------------------------------- 입력오류 검사
	public static boolean ValidInput(String number) {
		if(number.isEmpty()) {
			return false;
		}
		if(number.length() != 9) {
			return false;
		}
		if(!CheckNumber(number)) {
			return false;
		}
		return true;
	}
	
	private static boolean CheckNumber(String number) {
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + User.nowloginId + "'s Accounts.txt"))){
			String line;
			int num = 0;
			while ((line = br.readLine()) != null) {
				if(line.contains(number)) {
					datalog = num;
					return true;
				}
				num++;
			}
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	//--------------------------------------------------------------------------------------------------------------------------
	
	
	
	//-------------------------------------------------------------------------------------------------------------------------- 계좌삭제
	public static void DelAccount() {
		String save = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(LoginMain.path + User.nowloginId + "'s Accounts.txt")));	
			String line;
			for(int i = 0; i < datalog; i++) {
				line = br.readLine();
				save += (line + "\r\n");
			}
			String delData = br.readLine();
			while((line = br.readLine()) != null) {
				save += (line+ "\r\n");
			}
			FileWriter fw = new FileWriter(LoginMain.path + User.nowloginId + "'s Accounts.txt");
			fw.write(save);
			fw.close();
			br.close();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(Exception e) {
			e.printStackTrace();
		}
		save = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(LoginMain.path + User.nowloginId + "'s Accounts balance.txt")));	
			String line;
			for(int i = 0; i < datalog; i++) {
				line = br.readLine();
				save += (line + "\r\n");
			}
			String delData = br.readLine();
			while((line = br.readLine()) != null) {
				save += (line+ "\r\n");
			}
			FileWriter fw = new FileWriter(LoginMain.path + User.nowloginId + "'s Accounts balance.txt");
			fw.write(save);
			fw.close();
			br.close();
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(Exception e) {
			e.printStackTrace();
		}
	}
	//--------------------------------------------------------------------------------------------------------------------------
	
	static void Createlog(String number) {
		LocalDate nowdate = LocalDate.now();
		LocalTime nowtime = LocalTime.now();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(LoginMain.path + User.nowloginId +  " log.txt", true))) {
            bw.write(nowdate +" " + nowtime + " " + number + " " + " 계좌해지");
            bw.newLine();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
