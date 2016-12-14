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
public class TestService {

    @Autowired
    Test1Dao test1Dao;

    public Test1Entity doService(){
        Test1Entity test1Entity =test1Dao.selectByPrimaryKey(1);
        return test1Entity;
    }

    @Transactional
    public int insertTest1(){
        Test1Entity test1Entity = new Test1Entity();
        test1Entity.setName1("name1");
        return test1Dao.insert(test1Entity);

    }

    @Transactional(transactionManager = "transactionManager", rollbackFor = RuntimeException.class)
    public void insertTest1(boolean flag){
        Test1Entity test1Entity = new Test1Entity();
        test1Entity.setName1("name1");
         test1Dao.insert(test1Entity);
        if(flag){
            throw  new RuntimeException();
        }

    }
}
