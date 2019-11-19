package org.platformparm.knowledgebase.tdd.jmock;

public class CallChainConsumer {

    public void batchCall(CallChain cc) {

        cc.firstMethod();
        cc.secondMethod();
        cc.thirdMethod();

    }

}
