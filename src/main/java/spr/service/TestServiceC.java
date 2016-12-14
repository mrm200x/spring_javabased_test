package spr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/12/14.
 */
@Service
public class TestServiceC {

    @Autowired
    TestServiceA testServiceA;

    @Autowired
    TestServiceB testServiceB;


    @Transactional(transactionManager = "transactionManagerA", rollbackFor = RuntimeException.class)
    public void testRollBackInsert(boolean flag){

        testServiceA.insertTest1();
        testServiceB.insertTest2();
        if(flag){
            throw new RuntimeException("rollbk");
        }
    }
}
