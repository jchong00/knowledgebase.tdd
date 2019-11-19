package org.platformparm.knowledgebase.tdd.jmock;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

public class SequenceTest {

    private final Mockery context = new JUnit4Mockery();

    /**
     * context.sequence() 함수로 Sequence 를 만들어 inSequence(...)로 순서를 지정하고
     * CallChainConsumer 에게 이를 호출하게 하여 지정된 순차대로 호출이 되는지 검증
     */
    @Test
    public void proofSequence() {
        final CallChain mock = context.mock(CallChain.class, "mockCipher");
        final Sequence seq = context.sequence("seq");
        context.checking(new Expectations() {
            {
                oneOf(mock).firstMethod(); inSequence(seq);
                oneOf(mock).secondMethod(); inSequence(seq);
                oneOf(mock).thirdMethod(); inSequence(seq);
            }
        });

        CallChainConsumer ccu = new CallChainConsumer();
        ccu.batchCall(mock);

    }

}
