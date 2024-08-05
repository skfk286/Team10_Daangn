package com.devcourse.daangn;

import com.devcourse.daangn.dao.ProductRepository;
import com.devcourse.daangn.dao.ProductRepositoryMysql;
import com.devcourse.daangn.dao.UserRepository;
import com.devcourse.daangn.dao.UserRepositoryMysql;
import com.devcourse.daangn.entity.ProductDTO;
import com.devcourse.daangn.entity.UserDTO;
import com.devcourse.daangn.service.DaangnService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class Application {
    private static boolean isLogin = false; // 로그인 상태
    private static boolean isAppRunning = true; // 앱 실행 상태

    private static UserDTO userDTO; // 사용자 로그인 세션

    private static final DaangnService daangnService = DaangnService.getInstance();

    private static final UserRepository userRepository = UserRepositoryMysql.getInstance();
    private static final ProductRepository productRepository = ProductRepositoryMysql.getInstance();

    public static void main(String[] args) throws IOException, SQLException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while (!isLogin && isAppRunning) {

            int select = daangnService.loginGuideForm(); // 회원가입 또는 로그인 화면

            switch (select) {

                case 1: // 로그인
                    isLogin = daangnService.loginForm();
                    if (!isLogin) { // 로그인 실패
                        continue; // 다시 로그인 화면으로
                    }

                    break;
                case 2: // 회원가입
                    daangnService.joinForm();

                    break;
                case -1:
                    isAppRunning = daangnService.terminateSystem();
                    break;
            }
        }

        while (isLogin && isAppRunning) { // 로그인 완료된 상태.

            int select = daangnService.mainForm(); // 메인 화면
            
            switch (select) {
                case 1: // 홈화면으로.

                    int select2 = daangnService.homeForm();

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
                    isAppRunning = daangnService.terminateSystem();
                    break;
            }
            
        }
    }
}