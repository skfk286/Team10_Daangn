package com.devcourse.daangn.service;

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

/**
 * 당근 메인 화면 서비스
 *
 * @author ycjung
 */
public class DaangnService {
    private DaangnService() {}

    private static DaangnService instance = new DaangnService();

    public static DaangnService getInstance() { return instance; }

    private static UserRepository userRepository = UserRepositoryMysql.getInstance();
    private static ProductRepository productRepository = ProductRepositoryMysql.getInstance();

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    UserDTO userDTO; // 로그인 세션 유지.

    /**
     * 회원가입 또는 로그인 선택 안내 화면
     * @return
     * @throws IOException
     */
    public int loginGuideForm() throws IOException {
        System.out.println("---------------------");
        System.out.println("\"당근\" 안내 화면");
        System.out.println("---------------------");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("-1. 종료");
        System.out.println("---------------------");
        System.out.print("선택 > ");

        return Integer.parseInt(br.readLine());
    }

    /**
     * 로그인 화면
     * @return
     * @throws IOException
     */
    public boolean loginForm() throws IOException {
        System.out.println("---------------------");
        System.out.println("\"당근\" 로그인 화면");
        System.out.println("---------------------");
        System.out.printf("닉네임 >");
        String userName = br.readLine();

        userDTO = userRepository.findUserByName(userName); // userVo = 로그인 조회.

        if (userDTO == null) {
            // 로그인이 안된 상태.
            System.out.println("[시스템] 로그인 할 수 없는 회원 정보입니다. 회원가입을 진행하세요.\n");
            return false;
        }

        System.out.println("[시스템] " + userName + "님! 정상적으로 로그인 되었습니다.\n");

        return true;
    }

    /**
     * 회원 가입 화면
     * @return
     * @throws IOException
     */
    public int joinForm() throws IOException {
        System.out.println("---------------------");
        System.out.println("\"당근\" 회원가입 화면");
        System.out.println("---------------------");
        System.out.print("닉네임 > ");
        String userName = br.readLine();
        System.out.print("동네 > ");
        String location = br.readLine();

        UserDTO userDTO = new UserDTO();

        int result = userRepository.saveUser(userDTO);

        if(result > 0)
            System.out.println("[시스템] 회원가입이 정상적으로 진행 되었습니다.");
        else {
            System.out.println("[시스템] 회원가입을 진행할 수 없습니다.");
        }

        return result;
    }

    /**
     * 메인 화면
     * @return
     * @throws IOException
     */
    public int mainForm() throws IOException {
        System.out.println("---------------------");
        System.out.println("\"당근\" 메인 화면");
        System.out.println("---------------------");
        System.out.println("1. 홈 화면으로");
        System.out.println("2. 채팅(아직 미구현)");
        System.out.println("3. 나의 당근");
        System.out.println("-1. 종료");
        System.out.println("---------------------");
        System.out.print("선택 > ");

        return Integer.parseInt(br.readLine());
    }

    /**
     * 홈 화면
     * @return
     * @throws IOException
     */
    public int homeForm() throws IOException {
        System.out.println("---------------------");
        System.out.println("\"당근\" 홈 화면");
        System.out.println("---------------------");

        List<ProductDTO> products  = productRepository.findByCity(userDTO.getLocation());
        for (ProductDTO product : products) {
            /* TODO : 여기에서 모든 상품 리스트 먼저 보여주기 */
        }
        System.out.println("---------------------");
        System.out.println("1. 홈 화면으로 가기");
        System.out.println("2. 상세 정보 조회하기 (입력 예시 > 1 상품번호)");
        System.out.println("3. 좋아요 등록하기 (입력 예시 > 2 상품번호)");
        System.out.println("4. 글쓰기");
        System.out.println("-1. 종료");
        System.out.println("---------------------");
        System.out.print("선택 > ");

        return Integer.parseInt(br.readLine());
    }

    /**
     * 시스템 종료 안내 화면
     * @return
     */
    public boolean terminateSystem() {
        System.out.println("[시스템] 당근을 종료합니다.\n");

        return false;
    }
}
