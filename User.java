package bank;

import java.io.*;


public class User {
	private static String id;
	private static String pwd;
	static String nowloginId;
	static String nowloginPwd;
	static int datalog;
	
	public User(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	
	}
	
	//userdata에 id가 있는지 확인
	public static boolean IDcheck(String id) {
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + "userdata.txt"))){
			String line;
			while ((line = br.readLine()) != null) {
				if(line.contains("ID: " + id + ",")) {
					return true;
				}
			}
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//userdata에 저장되어있는 정보(id, pwd)확인 후 로그인
	public static boolean Login(String id, String pwd) {
		try(BufferedReader br = new BufferedReader(new FileReader(LoginMain.path + "userdata.txt"))){
			String line;
			int num = 0;
			while ((line = br.readLine()) != null) {
				if(line.contains("ID: " + id + ", PASSWORD: " + pwd  + "|||")) {
					datalog = num;
					return true;
				}
				num++;
			}
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//id, pwd란이 비워져있는지 확인 혹은 id가 영어나 숫자 이외의 것인지 확인
	public static boolean ValidInput(String id, String pwd) {
		if(id.isEmpty() || pwd.isEmpty()) {
			return false;
		}
		if(!id.matches("[a-zA-Z0-9]*")) {
			return false;
		}
		return true;
	}
	//길이체크, id 10문자 이하 pwd 8문자 이하
	public static boolean lengthcheck(String id, String pwd) {
		if(id.length() > 10 || pwd.length() > 8) {
			return false;
		}
		return true;
	}
	
	//회원가입
	static void MakeAccount(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LoginMain.path + "userdata.txt", true))) {
            bw.write(user.toString());
            bw.newLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	//회원탈퇴, datalog(탈퇴하고자 하는 회원정보가 있는 줄)까지는 save에 저장했다가 새로 파일을 만드는 개념
	public static void remove(String userdata) {
		String save = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(LoginMain.path + "userdata.txt")));	
			String line;
			for(int i = 0; i < datalog; i++) {
				line = br.readLine();
				save += (line + "\r\n");
			}
			String delData = br.readLine();
			while((line = br.readLine()) != null) {
				save += (line+ "\r\n");
			}
			FileWriter fw = new FileWriter(LoginMain.path + "userdata.txt");
			fw.write(save);
			fw.close();
			br.close();
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(Exception e) {
			e.printStackTrace();
		}
		File file = new File(LoginMain.path + User.nowloginId +  " log.txt");
		file.delete();
	}

	@Override
	public String toString() {
		return "ID: " + id + ", PASSWORD: " + pwd + "|||";
	}
}
