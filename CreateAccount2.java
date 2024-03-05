package bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreateAccount2 {
	private String id;
	private String pwd;
	private String money; //적금 목표금액
	private String goal; //기간
	static String number;
	
	public CreateAccount2(String id, String pwd, String money, String goal) {
		this.id = id;
		this.pwd = pwd;
		this.money = money;
		this.goal = goal;
	}
	
	private static String RandomNumber() { //계좌번호 9자리 랜덤생성
		Random random = new Random();
		int i = 899999999;
		int num;
		return Integer.toString(random.nextInt(i) + 100000000);
	}
	
	//--------------------------------------------------------------------------------------------------------------- id, pwd, 목표금액, 현재 만들어진 계좌 체크
	public static boolean IDChecking(String id) {
		if (id.isEmpty()) {
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
	
	public static boolean MONEYChecking(String money) {
		if(money.isEmpty()) {
			return false;
		}
		try {
			if(Integer.parseInt(money) % 50000 != 0 || (Integer.parseInt(money) < 50000 || Integer.parseInt(money) > 200000)) {
				return false;
			}
		} catch(NumberFormatException n) {
			return false;
		}
		return true;
	}
	
	public static boolean GOALChecking(String goal) {
		if(goal.isEmpty()) {
			return false;
		}
		try {
			if(Integer.parseInt(goal) < 6  || Integer.parseInt(goal) > 24) {
				return false;
			}
		} catch(NumberFormatException n) {
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
	
	static void CreateAcount(CreateAccount2 account){
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
	    }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	//---------------------------------------------------------------------------------------------------------------
	
	static void Createlog() {
		LocalDate nowdate = LocalDate.now();
		LocalTime nowtime = LocalTime.now();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(LoginMain.path + User.nowloginId + " log.txt", true))) {
            bw.write(nowdate +" " + nowtime + " 적금생성");
            bw.newLine();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public String toString() {
		return "NUMBER: " + RandomNumber() + ", Account = 적금" + ", PASSWORD: " + pwd +  ", MONEY: " + money + ", GOAL: " + goal +" |||";
	}
}
