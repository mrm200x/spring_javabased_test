package spr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spr.bean.Test1Entity;
import spr.bean.Test2Entity;
import spr.dao.Test1Dao;
import spr.dao2.Test2Dao;

/**
 * Created by Administrator on 2016/12/14.
 */
@Service
public class TestService2 {

    @Autowired
    Test2Dao test2Dao;

    public Test2Entity doService(){
        Test2Entity test2Entity =test2Dao.selectByPrimaryKey(1);
        return test2Entity;
    }

    @Transactional
    public int insertTest2(){
        Test2Entity test2Entity = new Test2Entity();
        test2Entity.setName2("name222");
        return test2Dao.insert(test2Entity);

    }
}
