package org.xfs.scm.data.business.study.hello.web;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.study.hello.model.Interview;
import org.xfs.scm.data.business.study.hello.model.Person;
import org.xfs.scm.data.business.study.hello.service.HelloWorldService;
import org.xfs.scm.data.business.study.hello.service.InterviewService;
import org.xfs.scm.platform.base.model.Page;
import org.xfs.scm.platform.config.exception.BusinessException;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by 神风逐胜 on 2017/10/7 0007.20:52
 * version:1.0
 */
@Controller
public class WelcomeWeb {


    private final Logger logger = LoggerFactory.getLogger(WelcomeWeb.class);
    private final HelloWorldService helloWorldService;
    @Autowired
    MongoTemplate mongoTemplate;

    @Resource
    InterviewService interviewService;
    @Autowired
    public WelcomeWeb(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        logger.debug("index() is executed!");

        model.put("title", helloWorldService.getTitle(""));
        model.put("msg", helloWorldService.getDesc());

        return "index";
    }

    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {

        logger.debug("hello() is executed - $name {}", name);

        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        model.addObject("title", helloWorldService.getTitle(name));
        model.addObject("msg", helloWorldService.getDesc());
        return model;
    }
    @SuppressWarnings("rawtypes")
	@ResponseBody
    @RequestMapping(path = "/ajax/{name}",method = RequestMethod.GET)
    public JsonResponse ajaxTest(@PathVariable("name") String name){
        if(logger.isInfoEnabled()){
            logger.info("mongoTemplate:"+this.mongoTemplate);
        }
        JsonResponse<String> json=new JsonResponse<String>();
        if(logger.isInfoEnabled()){
            logger.info("result:"+"name".equals("exception"));
        }

        if(name==null || "".equals(name)||name.equals("exception")){
            //String text="lambda";
            throw new BusinessException("测试业务异常！");
        }
        DBCollection dbCollection=this.mongoTemplate.getCollection("runob");

        BasicDBObject queryObj = new BasicDBObject();   //构建查询条件
        queryObj.put("_id",1999);
        DBCursor cursor =dbCollection.find(queryObj);
        json.setCode(100);
        json.setData("输入参数为："+cursor);
        json.setMessage("ajax request success");
        return json;
    }

    @RequestMapping(value = "/validate",method =RequestMethod.GET)
    public ModelAndView validate(@Valid Person person) throws Exception {
        // logger.info("------------index-------------------->");
        ModelAndView model = new ModelAndView();
        model.addObject("greeting", "测试");

        if(logger.isDebugEnabled()){
            logger.debug("--------------validate------------------>"+person);

        }

        model.setViewName("welcome");
        return model;
    }

    @RequestMapping(value = "/api",method =RequestMethod.POST)
    public @ResponseBody  Person api(@Valid @RequestBody  Person person) {
        // logger.info("------------index-------------------->");

        if(logger.isDebugEnabled()) {
            logger.debug("--------------validate------------------>" + person);
        }
        if(person==null){
            person=new Person();
        }
        person.setEmail("333@333.com");

        return person;
    }
    @RequestMapping("/save")
    public @ResponseBody JsonResponse<String> save(){
        JsonResponse<String> json=new JsonResponse<String>();
        int size=500;
        for(int i=1;i<=size;i++){
            Date now=new Date();
            Interview interview=new Interview();
            interview.set_createtime(now.getTime());
            interview.set_processed(i);
            interview.setDesc("changelog.txt ");
            interview.setId(UUID.randomUUID().toString());
            interview.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(now));
            interview.setPuid(i);
            interview.set_createtime(now.getTime());
            interview.setTemplateType("json");
            this.mongoTemplate.save(interview,"test");
        }
        json.setCode(100);
        json.setData("输入参数为：");
        json.setMessage("ajax request success");
        return json;
    }

    @RequestMapping("/query")
    public @ResponseBody JsonResponse<Page<Interview>> query(@Valid Integer currentPage,@Valid Integer rows){
        JsonResponse< Page<Interview>> json=new JsonResponse< Page<Interview>>();
       // int size=500;
        Query query=new Query();
        Criteria criteria = where("puid").gt(300);    // 大于

        query.addCriteria(criteria);
       // List<Interview> list=this.mongoTemplate.find(query,Interview.class);
        Page<Interview> page=new Page<Interview>();
        if(currentPage==null){
            currentPage=1;
        }
        if(rows==null){
            rows=50;
        }
        page.setPageSize(rows);
        page.setCurrentPage(currentPage);
      //  page.setPageSize();
        this.interviewService.findPage(page,query);
        json.setCode(100);
        json.setData(page);
        json.setMessage("ajax request success");
        return json;
    }

    @RequestMapping("application")
    public String applicationPage(){
        return "/study/application";
    }

    @RequestMapping("session")
    public String sessionPage(){
        return "/study/session";
    }
}
