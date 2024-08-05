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
    private static boolean isHome = false; // 홈 화면 페이징 컨트롤

    private static final DaangnService daangnService = DaangnService.getInstance();

    private static final UserRepository userRepository = UserRepositoryMysql.getInstance();
    private static final ProductRepository productRepository = ProductRepositoryMysql.getInstance();


    public static void main(String[] args) throws IOException, SQLException {

        while (!isLogin && isAppRunning) {

            int select = daangnService.loginGuideForm(); // 회원가입 또는 로그인 화면

            switch (select) {

                case 1 -> { // 로그인
                    isLogin = daangnService.loginForm();
                    if (!isLogin) { // 로그인 실패
                        continue; // 다시 로그인 화면으로
                    }
                }
                case 2 -> { // 회원가입
                    daangnService.joinForm();
                }
                case -1 -> {
                    isAppRunning = daangnService.terminateSystem();
                }
            }
        }

        while (isLogin && isAppRunning) { // 로그인 완료된 상태.

            int select = daangnService.mainForm(); // 메인 화면

            switch (select) {
                case 1 -> { // 홈화면으로.
                    isHome = true;
                    while(isHome) {
                        int[] select2 = daangnService.homeForm();

                        switch (select2[0]) {
                            case 1 -> { // 메인 화면으로 가기
                                isHome = false;
                            }
                            case 2 -> { // 상세 정보 조회하기
                                int result = daangnService.detailProductForm(select2[1]);

                                if (result == 1) { isHome = false; } // 메인 화면으로 가기
                                else if(result == 2) {} // 홈 화면으로 가기
                                else if(result == 3) { // 좋아요 등록하기
                                    daangnService.likeSuccessForm();
                                }
                                else if(result == 4) {} // 채팅하기
                                else { // 종료하기
                                    isHome = false;
                                    isAppRunning = false;
                                }
                            }
                            case 3 -> { // 좋아요 등록하기

                            }
                            case 4 -> { // 글쓰기
                                daangnService.writingForm();
                            }
                        }
                    }
                }
                case 2 -> { // 채팅

                }
                case 3 -> { // 나의 당근 -> 내 프로필 정보

                }
                case -1 -> {
                    isAppRunning = daangnService.terminateSystem();
                }
            }


        }
    }
}