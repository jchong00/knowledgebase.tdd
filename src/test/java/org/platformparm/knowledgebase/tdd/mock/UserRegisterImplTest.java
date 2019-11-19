package org.platformparm.knowledgebase.tdd.mock;

import static org.junit.Assert.*;
import org.junit.Test;

public class UserRegisterImplTest {

    @Test
    public void testSetPassword() {
        UserRegister testTarget = new UserRegisterImpl(); // 1) 테스트 대상 실 객체 생성
        Cipher cipher = new Cipher() {                    // 2) 협력 class 의 mock 생성
            @Override
            public String decrypt(String source) {
                return "potato"; // 무조건 이 값을 반환한다.
            }

            @Override
            public String encrypt(String source) {
                return "8ee2027983915ec78acc45027d874316"; // 무조건 이 값을 반환한다.
            }
        };

        String encrypted = cipher.encrypt("potato"); // 4) 가짜 암호화 호출을 한다.
        testTarget.setPassword("potato", encrypted);    // 5) 실 객체 호출, 궁극의 테스트 Call
        assertEquals("potato", cipher.decrypt(encrypted)); // 6) 사실을 단정의 대상이 아니지만 잘 암호화 했는지 확인
        assertEquals(testTarget.getPassword(), encrypted); // 7) 테스트 대상체에 대한 단정
    }

}
