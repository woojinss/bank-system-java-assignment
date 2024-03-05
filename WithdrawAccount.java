package bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;

public class WithdrawAccount {
	private String number;
	private String pwd;
	private int money;
	static int numberline = 0;
	
	public WithdrawAccount(String number, String pwd, int money) {
		this.number = number;
		this.pwd = pwd;
		this.money = money;
	}
	//----------------------------------------------------------------------------------------------------------���¹�ȣ, ��й�ȣ, ���̳ʽ� ���� Ȯ��
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
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
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
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean MONEYChecking(int money) {
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + User.nowloginId + "'s Accounts balance.txt"))){
			String line;
			int num = 0;
			while ((line = br.readLine()) != null) {
				if(num == numberline) {
					if(Integer.parseInt(line) == 0 || money == 0) {
						return false;
					}
					if(money < 0) {
						return false;
					}
					else if(Integer.parseInt(line) - money < 0) {
						return false;
					}
					else {
						return true;
					}
				}
				num++;
			}
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	//---------------------------------------------------------------------------------------------------------- �������� Ȯ��
	public static boolean Account2Check() {
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + User.nowloginId + "'s Accounts.txt"))){
			String line;
			int num = 0;
			while ((line = br.readLine()) != null) {
				if(num == numberline && line.contains("Account = ����")) {
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
	//---------------------------------------------------------------------------------------------------------- ���
	
	public static void withdraw(int money) {
		String save = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(LoginMain.path + User.nowloginId + "'s Accounts balance.txt")));	
			String line;
			for(int i = 0; i < numberline; i++) {
				line = br.readLine();
				save += (line + "\r\n");
			}
			save += (Integer.toString(Integer.parseInt(br.readLine()) - money) + "\r\n");
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
	//----------------------------------------------------------------------------------------------------------//���� -> ���뿹��
	public static void AccountChange(String number, String pwd) {
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
			savemoney = br.readLine(); //�����ؾ��ϴ� ��
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
		//���� ���� ���·� ��ȯ
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(LoginMain.path + User.nowloginId + "'s Accounts.txt", true))) {
            bw.write("NUMBER: " + number + ", Account = ���뿹��" + ", PASSWORD: " + pwd + " |||");
            bw.newLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LoginMain.path + User.nowloginId + "'s Accounts balance.txt", true))) {
            bw.write(savemoney);
            bw.newLine();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	//----------------------------------------------------------------------------------------------------------
	
	static void WithdrawCreatelog(String number, int money) {
		LocalDate nowdate = LocalDate.now();
		LocalTime nowtime = LocalTime.now();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(LoginMain.path +  User.nowloginId + " log.txt", true))) {
            bw.write(nowdate + " " + nowtime + " " + number + " " + Integer.toString(money) + " " + "�� ���");
            bw.newLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	static void WithdrawAccountFailCreatelog(String number) {
		LocalDate nowdate = LocalDate.now();
		LocalTime nowtime = LocalTime.now();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(LoginMain.path + User.nowloginId +  " log.txt", true))) {
            bw.write(nowdate + " " + nowtime + " " + number + " " + " ��������");
            bw.newLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
	}
}
