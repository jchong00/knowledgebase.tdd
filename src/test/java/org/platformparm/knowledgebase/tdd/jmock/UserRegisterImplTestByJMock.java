package org.platformparm.knowledgebase.tdd.jmock;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.platformparm.knowledgebase.tdd.mock.Cipher;
import org.platformparm.knowledgebase.tdd.mock.UserRegister;
import org.platformparm.knowledgebase.tdd.mock.UserRegisterImpl;

public class UserRegisterImplTestByJMock {

    final Mockery context = new JUnit4Mockery();

    @Test
    public void testSetPassword() {
        UserRegister testTarget = new UserRegisterImpl(); // 1) 테스트 대상 실 객체 생성
        final Cipher mockCipher = context.mock(Cipher.class, "mockCipher");

        context.checking(new Expectations() {
            {
                /* =================================================================================
                 * 호출횟수에 대한 정리
                 * =================================================================================
                 * 테스트를 실행하는 도중에 예상하는 호출 횟수를 기술하는 데 필요하다.
                 * 예상 구문은 호출 횟수로 시작한다.
                 *
                 * exactly(n).of([mockObject]): 호출이 정확히 n번 일어날 것을 예상한다.
                 *
                 * oneOf([mockObject]): 호출이 정확히 한 번 일어날 것을 예상한다.
                 *  exactly(1).of([mockObject]) 과 동일하다.
                 *
                 * atLeast(n).of([mockObject]): 호출이 최소 n번은(n번 이상) 일어날 것을 예상한다.
                 *
                 * atMost(n).of([mockObject]): 호출이 최대 n번은(n번 이하) 일어날 것을 예상한다.
                 *
                 * between(min, max).of([mockObject]):호출이 최소 min번, 최대 max번 일어날것을 예상한다.
                 *
                 * allowing, ignoring([mockObject]): 호출이 수번 일어나던 한 번도 없던 상관하지 않는다.
                 *  atLeast(0).of([mockObject]) 와 동일하다.
                 *
                 * never([mockObject]): 호출이 한 번도 일어나지 말아야 한다.
                 * 예상 구문을 지정하지 안은 경우와 동일하다.
                 *
                 */

                /*
                * ==================================================================================
                * 예상 메서드에 대한 정리
                * ==================================================================================
                * 테스트 횟수를 정의하고 다음에 위치하는 내용이 예상 메서드이다.
                *
                * oneOf([mockObject]).realMethod(값1, 값2);
                * 또는 with()로 감싼 matcher 인자를 사용할 수도 있다.
                * oneOf([mockObject]).realMethod(with(...), with(...)); 값이랑 matcher 랑 섞으면 에러
                * 예>>>> oneOf(mockObject).realMethod(with(lessThen(10)), with(lessThen(110)));
                */

                oneOf(mockCipher).encrypt("potato");
                will(returnValue("8ee2027983915ec78acc45027d874316"));
                oneOf(mockCipher).decrypt("8ee2027983915ec78acc45027d874316");
                will(returnValue("potato"));

                /*
                * ==================================================================================
                * 동작(will)에 대한 정리
                * ==================================================================================
                * 예상한 구문이 호출 되었을 때 실행 해야 할 동작을 정의한다. 동작이라고 했지만 거의
                * 반환(return)이다.
                *
                * 위 예제 코드와 같이 예상 하는 동작 후에 will이 온다. will 내부에 들어 갈 수 있는
                * 내용은 다음과 같다.
                *
                * returnValue(v): v를 호출자에게 반환한다.
                *
                * returnIterator(c): c 컬랙션에 대한 반복자를 호출자에게 반환한다.
                *
                * returnIterator(v1, v2, [...], vn): v1 ~ vn 까지 값에 대한 반복자를 반환한다.
                *
                * throwException(e): 예외 객체 e를 반환한다.
                *
                * doAll(a1, a2, [...], a3): a1 ~ an 까지의 모든 동작을 수행한다. (?????)
                *
                */

                oneOf(mockCipher).encrypt("sweet_potato");
                will(returnValue("8ee2027983915ec78acc45027d874317"));
                oneOf(mockCipher).decrypt("8ee2027983915ec78acc45027d874317");
                will(returnValue("sweet_potato"));
            }
        });

        String encrypted = mockCipher.encrypt("potato"); // 4) 가짜 암호화 호출을 한다.
        testTarget.setPassword("potato", encrypted);   // 5) 실 객체 호출, 궁극의 테스트 Call
        assertEquals("potato", mockCipher.decrypt(encrypted)); // 6) 사실을 단정의 대상이 아니지만 잘 암호화 했는지 확인
        assertEquals(testTarget.getPassword(), encrypted); // 7) 테스트 대상체에 대한 단정


        encrypted = mockCipher.encrypt("sweet_potato"); // 4) 가짜 암호화 호출을 한다.
        testTarget.setPassword("sweet_potato", encrypted);   // 5) 실 객체 호출, 궁극의 테스트 Call
        assertEquals("sweet_potato", mockCipher.decrypt(encrypted)); // 6) 사실을 단정의 대상이 아니지만 잘 암호화 했는지 확인
        assertEquals(testTarget.getPassword(), encrypted); // 7) 테스트 대상체에 대한 단정
    }
}
