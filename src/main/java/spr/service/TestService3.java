package spr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spr.bean.Test1Entity;
import spr.dao.Test1Dao;

/**
 * Created by Administrator on 2016/12/14.
 */
@Service
public class TestService3 {

    @Autowired
    TestService testService;

    @Autowired
    TestService2 testService2;


    @Transactional
    public void testRollBackInsert(boolean flag){

        testService.insertTest1();
        testService2.insertTest2();
        if(flag){
            throw new RuntimeException("rollbk");
        }
    }
}
