package spr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spr.bean.Test1Entity;
import spr.bean.Test2Entity;
import spr.service.TestService;
import spr.service.TestService2;
import spr.service.TestService3;
import spr.service.TestServiceC;

/**
 * Created by Administrator on 2016/12/14.
 */
@Controller
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private TestService2 testService2;

    @Autowired
    private TestService3 testService3;
    @Autowired
    private TestServiceC testServiceC;
    @RequestMapping(value = "/testPath")
    @ResponseBody
    public String test(){
        Test1Entity test1Entity =testService.doService();
        Test2Entity Test2Entity =testService2.doService();
        return "okok";
    }

    @RequestMapping(value = "/testInsert1")
    @ResponseBody
    public String testInsert1(boolean flag){
        testService.insertTest1(flag);
        return "insertokok";
    }

    @RequestMapping(value = "/testInsert3")
    @ResponseBody
    public String testInsert3(boolean flag){
        testService3.testRollBackInsert(flag);
        return "insertokok";
    }

    @RequestMapping(value = "/testInsertc")
    @ResponseBody
    public String testInsertc(boolean flag){
        testServiceC.testRollBackInsert(flag);
        return "insertokok";
    }
}
