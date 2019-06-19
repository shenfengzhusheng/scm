package org.xfs.scm.data.business.study.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qht.tms.car.api.CarApi;
import com.qht.tms.car.entity.Car;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xfs.scm.common.utils.date.DateUtil;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping(path = "/callback/")
public class CallbackWeb {

    @Reference(cache = "lru")
    private CarApi carApi;

    @RequestMapping("test")
    public Object test(String name){
        if(name==null){
            name="hello world!";
        }
        List<Car> result=this.carApi.find(1L);
        return result;
    }
    @RequestMapping("/")
    public Callable<String> index(){
        System.out.println("in callback!");
        return new Callable<String>(){

            public String call() throws Exception {

                // Do some work..
                Thread.sleep(10000L);
                return DateUtil.getNowTime();

            }
        };
    }

    @RequestMapping("/query")
    public Callable<Car> query(){
        System.out.println("in callback!");
        return new Callable<Car>(){

            public Car call() throws Exception {

                // Do some work..
                Thread.sleep(2*1000L);
                List<Car> result=carApi.find(1L);
                if(result!=null){
                    return result.get(0);
                }
                return null;
            }
        };
    }

}
