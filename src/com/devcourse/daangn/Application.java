package com.devcourse.daangn;

import com.devcourse.daangn.dao.ProductRepository;
import com.devcourse.daangn.dao.ProductRepositoryMysql;
import com.devcourse.daangn.dao.UserRepository;
import com.devcourse.daangn.dao.UserRepositoryMysql;
import com.devcourse.daangn.entity.ProductDTO;
import com.devcourse.daangn.entity.UserDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Application {
    private static boolean isLogin = false; // 로그인 상태
    private static boolean isAppRunning = true; // 앱 실행 상태

    private static UserDTO userDTO; // 사용자 로그인 세션

    private static final UserRepository userRepository = UserRepositoryMysql.getInstance();
    private static final ProductRepository productRepository = ProductRepositoryMysql.getInstance();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (!isLogin && isAppRunning) {
            /* 회원가입 또는 로그인 */
            System.out.println("---------------------");
            System.out.println("\"당근\" 안내 화면");
            System.out.println("---------------------");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("-1. 종료");
            System.out.println("---------------------");
            System.out.print("선택 > ");
            int select = Integer.parseInt(br.readLine());

            String userName;
            String location;
            switch (select) {

                case 1: // 로그인
                    System.out.println("---------------------");
                    System.out.println("\"당근\" 로그인 화면");
                    System.out.println("---------------------");
                    System.out.printf("닉네임 >");
                    userName = br.readLine();
                    userDTO = userRepository.findUserByName(userName);
                    // userVo = 로그인 조회.
                    if (userDTO == null) {
                        // 로그인이 안된 상태.
                        System.out.println("[시스템] 로그인 할 수 없는 회원 정보입니다. 회원가입을 진행하세요.\n");
                        continue;
                    }

                    System.out.println("[시스템] " + userName + "님! 정상적으로 로그인 되었습니다.\n");
                    isLogin = true; // 로그인 성공
                    break;
                case 2: // 회원가입
                    System.out.println("---------------------");
                    System.out.println("\"당근\" 회원가입 화면");
                    System.out.println("---------------------");
                    System.out.print("닉네임 > ");
                    userName = br.readLine();
                    System.out.print("동네 > ");
                    location = br.readLine();

                    break;
                case -1:
                    System.out.println("[시스템] 당근을 종료합니다.\n");
                    isAppRunning = false;
                    break;
            }
        }

        while (isLogin && isAppRunning) { // 로그인 완료된 상태.
            System.out.println("---------------------");
            System.out.println("\"당근\" 메인 화면");
            System.out.println("---------------------");
            System.out.println("1. 홈 화면으로");
            System.out.println("2. 채팅(아직 미구현)");
            System.out.println("3. 나의 당근");
            System.out.println("-1. 종료");
            System.out.println("---------------------");
            System.out.print("선택 > ");

            int select = Integer.parseInt(br.readLine());
            
            switch (select) {
                case 1: // 홈화면으로.
                    System.out.println("---------------------");
                    System.out.println("\"당근\" 홈 화면");
                    System.out.println("---------------------");

                    List<ProductDTO> products  = productRepository.findAll();
                    for (ProductDTO product : products) {
                        /* TODO : 여기에서 모든 상품 리스트 먼저 보여주기 */
                    }
                    System.out.println("---------------------");
                    System.out.println("1. 상세 정보 조회하기 (입력 예시 > 1 상품번호");
                    System.out.println("2. 좋아요 등록하기 (입력 예시 > 2 상품번호");
                    System.out.println("3. 메인 화면으로");
                    System.out.println("-1. 종료");
                    System.out.println("---------------------");
                    System.out.print("선택 > ");

                    int select2 = Integer.parseInt(br.readLine());
                    switch (select2) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3: // 메인 화면으로.
                            break;

                    }

                    break;
                case 2: // 채팅
                    break;
                case 3: // 나의 당근 -> 내 프로필 정보
                    break;
                case -1:
                    System.out.println("[시스템] 당근을 종료합니다.\n");
                    isAppRunning = false;
                    break;
            }
            
            List<ProductDTO> products  = productRepository.findAll();


        }
    }
}