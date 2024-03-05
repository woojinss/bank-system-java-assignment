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

public class DepositAccount {
	private String number;
	private String pwd;
	private int money;
	static String Nmoney = "";
	
	//----------------------------------------------------------------적금판별
	static String goalmoney = ""; //달마다 넣어야 하는 금액
	static String nowmoney = ""; //현재 계좌에 들어있는 돈
	static String goalmonth = ""; //적금기간
	//----------------------------------------------------------------
	static int numberline = 0;
	
	
	public DepositAccount(String number, String pwd, int money) {
		this.number = number;
		this.pwd = pwd;
		this.money = money;
	}
	//----------------------------------------------------------------------------------------------------------계좌번호, 비밀번호 확인
	public static boolean NUMBERChecking(String number) {
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + User.nowloginId + "'s Accounts.txt"))){
			String line;
			numberline = 0;
			while ((line = br.readLine()) != null) {
				if(line.contains(number)) {
					return true;
				}
				numberline++;
			}
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean pwdChecking(String pwd) {
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + User.nowloginId + "'s Accounts.txt"))){
			String line;
			int num = 0;
			while ((line = br.readLine()) != null) {
				if(num == numberline && line.contains(pwd)) {
					return true;
				}
				num++;
			}
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean MAXmoneyChecking(int money) {
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + User.nowloginId + "'s Accounts balance.txt"))){
			String line;
			int num = 0;
			while ((line = br.readLine()) != null) {
				if(num == numberline) {
					Nmoney = line;
				}
				num++;
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		if(Integer.parseInt(Nmoney) + money > 1000000000) {
			return false;
		}
		if(money < 0) {
			return false;
		}
		else {
			return true;
		}
	}
	//---------------------------------------------------------------------------------------------------------------------적금확인
	public static boolean Account2Check() {
		String a1 = "50000", a2 = "100000", a3 = "150000", a4 = "200000";
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + User.nowloginId + "'s Accounts.txt"))){
			String line;
			int num = 0;
			while ((line = br.readLine()) != null) {
				if(num == numberline && line.contains("MONEY: " + a1)) {
					goalmoney = a1;
				}
				else if(num == numberline && line.contains("MONEY: " + a2)) {
					goalmoney = a2;
				}
				else if(num == numberline && line.contains("MONEY: " + a3)) {
					goalmoney = a3;
				}
				else if(num == numberline && line.contains("MONEY: " + a4)) {
					goalmoney = a4;
				}
				num++;
			}
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
			e.printStackTrace();
		}
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + User.nowloginId + "'s Accounts.txt"))){
			String line;
			int num = 0;
			while ((line = br.readLine()) != null) {
				if(num == numberline && line.contains("Account = 적금")) {
					return true;
				}
				num++;
			}
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean CanAccountChange(String money) {
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + User.nowloginId + "'s Accounts balance.txt"))){
			String line;
			int num = 0;
			while ((line = br.readLine()) != null) {
				if(num == numberline) {
					nowmoney = line;
				}
				num++;
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + User.nowloginId + "'s Accounts.txt"))){
			String line;
			int num = 0, i = 6;
			while ((line = br.readLine()) != null) {
				i = 0;
				if(num == numberline) {
					while(i <= 24) {
						if(line.contains("GOAL: " + Integer.toString(i) + " |||")) {
							goalmonth = Integer.toString(i);
							break;
						}
						i++;
					}
				}
				num++;
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		if(Integer.parseInt(nowmoney) + Integer.parseInt(money) >= Integer.parseInt(goalmoney) * Integer.parseInt(goalmonth)) {
			return true; //적금 목표금액 달성
		}
		else {
			return false;
		}
	}
	
	public static boolean AccountMONEYlimit(String money) { //입금하려는 금액이 설정금액인지
		if(Integer.parseInt(money) != Integer.parseInt(goalmoney)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	//----------------------------------------------------------------------------------------------------------
	
	public static void deposit(int money) {
		String save = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(LoginMain.path + User.nowloginId + "'s Accounts balance.txt")));	
			String line;
			for(int i = 0; i < numberline; i++) {
				line = br.readLine();
				save += (line + "\r\n");
			}
			save += (Integer.toString(Integer.parseInt(br.readLine()) + money) + "\r\n");
			while((line = br.readLine()) != null) {
				save += (line+ "\r\n");
			}
			FileWriter fw = new FileWriter(LoginMain.path + User.nowloginId + "'s Accounts balance.txt");
			fw.write(save);
			fw.close();
			br.close();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(Exception e) {
			e.printStackTrace();
		}
	}
	//---------------------------------------------------------------------------------------------------------- 적금 -> 보통예금
	public static void ChangeAccount(String number, String pwd) {
		String savemoney = "";
		
		String save = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(LoginMain.path + User.nowloginId + "'s Accounts.txt")));	
			String line;
			for(int i = 0; i < numberline; i++) {
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
			for(int i = 0; i < numberline; i++) {
				line = br.readLine();
				save += (line + "\r\n");
			}
			savemoney = br.readLine(); //저장해야하는 돈
			while((line = br.readLine()) != null) {
				save += (line+ "\r\n");
			}
			FileWriter fw = new FileWriter(LoginMain.path + User.nowloginId + "'s Accounts balance.txt");
			fw.write(save);
			fw.close();
			br.close();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(Exception e) {
			e.printStackTrace();
		}
		
		//보통 예금 계좌로 전환
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(LoginMain.path + User.nowloginId + "'s Accounts.txt", true))) {
            bw.write("NUMBER: " + number + ", Account = 보통예금" + ", PASSWORD: " + pwd + " |||");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LoginMain.path + User.nowloginId + "'s Accounts balance.txt", true))) {
            bw.write(Integer.toString((int)(Integer.parseInt(savemoney) + (Integer.parseInt(savemoney) * 0.1))));
            bw.newLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
	}
	//----------------------------------------------------------------------------------------------------------
	static void DepositCreatelog(String number, int money) {
		LocalDate nowdate = LocalDate.now();
		LocalTime nowtime = LocalTime.now();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(LoginMain.path + User.nowloginId +  " log.txt", true))) {
            bw.write(nowdate + " " + nowtime + " " + number + " " + Integer.toString(money) + " " + "원 입금");
            bw.newLine();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	static void DepositAccountGoalCreatelog(String number) {
		LocalDate nowdate = LocalDate.now();
		LocalTime nowtime = LocalTime.now();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(LoginMain.path + User.nowloginId +  " log.txt", true))) {
            bw.write(nowdate + " " + nowtime + " " + number + " " + " 적금완료");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
