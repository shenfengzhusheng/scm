package org.xfs.scm.data.business.study.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.study.test.model.User;
import org.xfs.scm.data.business.study.test.service.TestService;
import org.xfs.scm.platform.util.ReflectionUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 神风逐胜 on 2017/10/15 0015.16:16
 * version:1.0
 */
@Controller
@RequestMapping("/user")
public class TestWeb {

    @Resource
    TestService testService;


    @RequestMapping(value = "save",method = RequestMethod.GET)
    public @ResponseBody JsonResponse<List<User>> save(){
        JsonResponse<List<User>> json=new JsonResponse<List<User>>();
        List<User>list=new ArrayList<User>();
        for(int i=1;i<1000;i++){
            User user=new User();
            user.setAge(30);
            user.setUserCode("code"+i);
            user.setUserName("名称:"+i);
            user.setAddr("厦门同安");
            user.setEmail("www@qq.com");
            try {
                user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1985-02-08"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            list.add(this.testService.save(user));
        }
        json.setCode(100);
        json.setData(list);
        json.setMessage("保存成功！");
        return json;
    }

    @RequestMapping(value = "/query")
    public @ResponseBody JsonResponse<User> query(@RequestBody User user){
        JsonResponse<User>json=new JsonResponse<User>();
        String msg="未查询到信息！";
        try{
            User result=this.testService.findOne(ReflectionUtils.getQueryObj(user));
            if(result!=null){
                json.setCode(100);
                json.setData(result);
                msg="查询成功！";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

       // json.setData();
        json.setMessage(msg);
        return json;
    }
}
