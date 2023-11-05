package view;

import dao.NonuserDao;
import dao.UserDao;
import model.Nonuser;
import model.User;

public class UserView implements CommonView {

	private UserDao udao = new UserDao(); // UserDao 객체를 생성하여 데이터베이스 작업에 사용
	private NonuserDao nudao = new NonuserDao(); // NonuserDao 객체를 생성하여 데이터베이스 작업에 사용

	// 로그인 메서드
	public String Login() {
		try {
			// 로그인 창 출력
			System.out.println("-----------------------------------------------------");
			System.out.println();
			System.out.println("                     [ 로 그 인 ]");
			System.out.println();
			System.out.println("-----------------------------------------------------");
			System.out.println();
			System.out.print(" I      D : ");
			String userid = scan.nextLine(); // 사용자로부터 아이디 입력
			System.out.println();
			System.out.print(" Password : ");
			String userpwd = scan.nextLine(); // 사용자로부터 비밀번호 입력
			System.out.println();

			User user = udao.selectById(userid); // 아이디로 사용자 정보를 데이터베이스에서 가져옴

			if (userpwd.equals(user.getUserPwd())) // 입력된 비밀번호가 사용자의 비밀번호와 일치하는지 확인
				return userid; // 로그인 성공 시 아이디를 반환

		} catch (Exception e) {
			// 에러가 발생한 경우 예외 처리
			// e.printStackTrace();
		}
		return "fail"; // 로그인 실패 시 "fail"을 반환
	}

	// 비회원 로그인 메서드
	public String Non_userlogin() {
		try {
			Nonuser nuser = new Nonuser();

			System.out.println("-----------------------------------------------------");
			System.out.println();
			System.out.println("                   [ 비회원 로그인 ]");
			System.out.println();
			System.out.println("-----------------------------------------------------");
			System.out.println();
			System.out.print(" 이   름\t: ");
			String username = scan.nextLine(); // 비회원의 이름 입력
			System.out.println();
			System.out.print(" 전화 번호\t: ");
			String usercp = scan.nextLine(); // 비회원의 전화 번호 입력
			System.out.println();
			System.out.print(" 주   소\t: ");
			String useraddr = scan.nextLine(); // 비회원의 주소 입력
			System.out.println();
			System.out.print(" 상세 주소\t: ");
			String userDaddr = scan.nextLine(); // 비회원의 상세 주소 입력
			System.out.println();

			nuser.setNonuserName(username); // Nonuser 객체에 이름 설정
			nuser.setNonuserCp(usercp); // Nonuser 객체에 전화 번호 설정
			nuser.setNonuserAddr(useraddr); // Nonuser 객체에 주소 설정
			nuser.setNonuserDetailAddr(userDaddr); // Nonuser 객체에 상세 주소 설정

			if (nudao.create(nuser) == true) // 비회원 정보를 데이터베이스에 저장
				return usercp; // 로그인 성공 시 전화 번호를 반환

		} catch (Exception e) {
			e.printStackTrace(); // 에러가 발생한 경우 예외 처리
		}
		return "fail"; // 로그인 실패 시 "fail"을 반환
	}

	// 회원 가입 메서드
	public String JoinUser() {
		String userid;
		String userpw;
		String username;
		String useraddr;
		String userDaddr;
		String usercp;

		try {
			System.out.println();
			System.out.println("-----------------------------------------------------");
			System.out.println();
			System.out.println("                  ○  회  원  가  입  ○");
			System.out.println();

			// 아이디 입력
			while (true) {
				System.out.println("-----------------------------------------------------");
				System.out.println();
				System.out.println("ID를 입력해 주십시오 (15글자 제한)");
				System.out.println();
				System.out.printf("    %-10s\t: ", "I      D");
				userid = scan.nextLine(); // 사용자로부터 아이디 입력

				User vo = udao.selectById(userid); // 입력한 아이디로 데이터베이스에서 사용자 정보를 가져옴

				// 빈칸일시
				if (userid.equals("")) {
					System.out.println();
					System.out.println("-----------------------------------------------------");
					System.out.println();
					System.out.println("                아이디는 필수 입력 사항입니다.");
					System.out.println("                  다시 시도하여 주십시오.");
					System.out.println();
					continue;
				} else if (vo == null) {
					break;
				} else {
					System.out.println();
					System.out.println("-----------------------------------------------------");
					System.out.println();
					System.out.println("                 중복된 아이디가 존재합니다.");
					System.out.println("                  다시 시도하여 주십시오.");
					System.out.println();
					continue;
				}
			}
			System.out.println();

			// 비밀번호 입력
			while (true) {
				System.out.println("-----------------------------------------------------");
				System.out.println();
				System.out.println("Password를 입력해 주십시오.");
				System.out.println();
				System.out.printf("    %-10s\t: ", "Password");
				userpw = scan.nextLine(); // 사용자로부터 비밀번호 입력

				if (!userpw.equals("")) {
					break;
				} else {
					System.out.println();
					System.out.println("-----------------------------------------------------");
					System.out.println();
					System.out.println("              비밀 번호는 필수 입력 사항입니다.");
					System.out.println("                 다시 시도하여 주십시오.");
					System.out.println();
					continue;
				}
			}
			System.out.println();

			// 이름 입력
			while (true) {
				System.out.println("-----------------------------------------------------");
				System.out.println();
				System.out.println("이름을 입력해 주십시오.");
				System.out.println();
				System.out.printf("    %-9s\t: ", "이     름");
				username = scan.nextLine(); // 사용자로부터 이름 입력

				if (!username.equals("")) {
					break;
				} else {
					System.out.println();
					System.out.println("-----------------------------------------------------");
					System.out.println();
					System.out.println("                이름은 필수 입력 사항입니다.");
					System.out.println("                 다시 시도하여 주십시오.");
					System.out.println();
					continue;
				}
			}
			System.out.println();

			// 전화번호 입력
			while (true) {
				System.out.println("-----------------------------------------------------");
				System.out.println();
				System.out.println("전화번호를 입력해 주십시오. 숫자만 입력해 주십시오.");
				System.out.println();
				System.out.printf("    %-8s\t: ", "전 화 번 호");
				usercp = scan.nextLine(); // 사용자로부터 전화 번호 입력

				if (!usercp.equals("")) {
					break;
				} else {
					System.out.println();
					System.out.println("-----------------------------------------------------");
					System.out.println();
					System.out.println("               전화번호는 필수 입력 사항입니다.");
					System.out.println("                  다시 시도하여 주십시오.");
					System.out.println();
					continue;
				}
			}
			System.out.println();

			// 주소 입력
			while (true) {
				System.out.println("-----------------------------------------------------");
				System.out.println();
				System.out.println("주소를 입력해 주십시오.");
				System.out.println();
				System.out.printf("    %-9s\t: ", "주     소");
				useraddr = scan.nextLine(); // 사용자로부터 주소 입력

				if (!useraddr.equals("")) {
					break;
				} else {
					System.out.println();
					System.out.println("-----------------------------------------------------");
					System.out.println();
					System.out.println("                주소는 필수 입력 사항입니다.");
					System.out.println("                  다시 시도하여 주십시오.");
					System.out.println();
					continue;
				}
			}

			// 상세 주소 입력
			while (true) {
				System.out.println("-----------------------------------------------------");
				System.out.println();
				System.out.println("상세 주소를 입력해 주십시오.");
				System.out.println();
				System.out.printf("    %-9s\t: ", "상 세  주 소");
				userDaddr = scan.nextLine(); // 사용자로부터 상세 주소 입력

				if (!userDaddr.equals("")) {
					break;
				} else {
					System.out.println();
					System.out.println("-----------------------------------------------------");
					System.out.println();
					System.out.println("                상세 주소는 필수 입력 사항입니다.");
					System.out.println("                  다시 시도하여 주십시오.");
					System.out.println();
					continue;
				}
			}

			System.out.println();

			User user = new User();

			// 회원 객체에 사용자 정보 설정
			user.setUserId(userid);
			user.setUserPwd(userpw);
			user.setUserName(username);
			user.setUserCp(usercp);
			user.setUserAddr(useraddr);
			user.setUserDetailAddr(userDaddr);

			if (udao.create(user) == true) // 회원 정보를 데이터베이스에 저장
				return "success"; // 가입 성공 시 "success"를 반환

		} catch (Exception e) {
			e.printStackTrace(); // 에러가 발생한 경우 예외 처리
		}
		return "fail"; // 가입 실패 시 "fail"을 반환
	}
}
