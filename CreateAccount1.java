package bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class CreateAccount1 {
	private String id;
	private String pwd;
	static String number;
	
	public CreateAccount1(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	
	private static String RandomNumber() { //계좌번호 9자리 랜덤생성
		Random random = new Random();
		int i = 899999999;
		int num;
		return Integer.toString(random.nextInt(i) + 100000000);
	}
	//--------------------------------------------------------------------------------------------------------------- id, pwd, 현재 만들어진 계좌 체크
	public static boolean IDChecking(String id) {
		if(id.isEmpty()) {
			return false;
		}
		if (!(id.equals(User.nowloginId))) {
			return false;
		}
		return true;
	}
	
	public static boolean PWDChecking(String pwd) {
		if(pwd.isEmpty()) {
			return false;
		}
		if(!pwd.matches("[0-9]*") || pwd.length() != 4) {
			return false;
		}
		return true;
	}
	
	public static boolean MAXchecking() {
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + User.nowloginId + "'s Accounts.txt"))){
			String line;
			int num = 0;
			while ((line = br.readLine()) != null) {
				if(line.contains("NUMBER: ")) {
					num++;
					if(num >= 5) {
						return false;
					}
				}
			}
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	//--------------------------------------------------------------------------------------------------------------- 계좌생성
	
	static void CreateAcount(CreateAccount1 account) {
		number = RandomNumber();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LoginMain.path + User.nowloginId + "'s Accounts.txt", true))) {
            bw.write(account.toString());
            bw.newLine();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LoginMain.path + User.nowloginId + "'s Accounts balance.txt", true))) {
            bw.write("0");
            bw.newLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	//---------------------------------------------------------------------------------------------------------------
	
	static void Createlog() {
		LocalDate nowdate = LocalDate.now();
		LocalTime nowtime = LocalTime.now();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(LoginMain.path + User.nowloginId +   " log.txt", true))) {
            bw.write(nowdate +" " + nowtime + " 보통예금생성");
            bw.newLine();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public String toString() {
		return "NUMBER: " + number + ", Account = 보통예금" + ", PASSWORD: " + pwd + " |||";
	}
}
