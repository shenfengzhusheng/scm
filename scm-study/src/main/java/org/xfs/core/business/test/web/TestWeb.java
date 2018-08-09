package org.xfs.core.business.test.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.core.business.test.model.TestVo;
import org.xfs.core.business.test.service.CacheService;
import org.xfs.core.business.test.service.TestService;
import org.xfs.core.platform.base.web.BaseWeb;
import org.xfs.core.util.http.RequestInfoUtil;
import org.xfs.core.util.json.JsonFormatUtil;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/test")
public class TestWeb extends BaseWeb {
    private static Logger logger = LoggerFactory.getLogger(TestWeb.class);
    @Resource
    TestService testService;

    @Resource
    JedisCluster jedisCluster;
    //
    // @Resource
    // RedisCacheUtil redisCacheUtil;

    @Resource
    CacheService cacheService;

    @RequestMapping("/test")
    @ResponseBody
    public Object test(TestVo vo) {
        // System.out.println(super.getRequest().getContentType().toString()+"value:" + vo.getValue());
        System.out.println(RequestInfoUtil.getRequestInfo(super.getRequest()));
        // String path = this.getClass().getResource("/").getPath();
        // // String path = request.getRealPath("/");
        // path = path + File.separator + "static" + File.separator + "test.html";
        // System.out.println("path:" + path);
        // Writer out = null;
        // try {
        // out = new FileWriter(new File(path));
        // out.write("测试时代复分有挂 地的士速递硒鼓");
        // } catch (Exception e) {
        // logger.error("网页输出异常", e);
        // } finally {
        // if (out != null) {
        // try {
        // out.close();
        // } catch (IOException e) {
        // logger.error("关闭输出流异常", e);
        //
        // }
        // }
        // }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1");
        map.put("success", true);
        Object obj = null;
        // new JsonFormatUtil().outFile("test", "ff");
        try {
            // System.out.println("----------------------------------->" + this.jedisCluster.set("88gun", "germany flak 88 gun"));
            System.out.println("----------------------------------->" + this.jedisCluster.get("88gun"));
            // System.out.println("----------------------cached--------->"+this.redisCacheUtil.set("oper", "中国", 8000));
            // System.out.println("----------------------cached--------->" + this.redisCacheUtil.get("oper"));
            obj = this.cacheService.getPerson("key");
            System.out.println("----------------------cached--------->" + obj);
            // System.out.println("----------------------cached--------->" + this.testService.cache("key"));
            // TestPo t=new TestPo();
            // t.setCode("1");
            // List<TestPo>list2=this.testService.list(t, 6, 8);
            // PageInfo<TestPo>pages=new PageInfo(list2);
            // System.out.println("=====>size:"+pages);
            // for(TestPo tp:pages.getList()){
            // System.out.println(tp);
            // }
            // // obj= this.cacheService.setPerson("test").get(456);
            // // System.out.println("----------------------cached--------->" +obj);
            // // Thread.sleep(new Random().nextInt(3) * 1000);
            // List<Object> list = new ArrayList<Object>();
            // for (int i = 0; i < 1000; i++) {
            // list.add(i);
            // System.out.println(Thread.currentThread().getName() + "now is :" + i);
            Thread.sleep(300);
            // }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            logger.error("test error:" + e);
        }
        // System.out.println("key:"+vo.getKey());
        map.put("data", obj);
        map.put("msg", "成功！");
        return map;// a920aac5-a1cc-4747-905c-299076e410e8
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(TestVo vo) {
        new JsonFormatUtil().outFile("test", "ff");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1");
        map.put("success", true);
        map.put("data", this.testService.modifyCacheData(vo.getKey()));
        map.put("msg", "成功！");
        return map;// a920aac5-a1cc-4747-905c-299076e410e8
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(TestVo vo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1");
        map.put("success", true);
        this.testService.deleteCache(vo.getKey());
        map.put("data", "dddd");
        map.put("msg", "成功！");
        return map;// a920aac5-a1cc-4747-905c-299076e410e8
    }

    @RequestMapping("/put")
    @ResponseBody
    public Object put(TestVo vo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1");
        map.put("success", true);
        map.put("data", "put" + this.testService.putCache(vo.getKey()));
        map.put("msg", "成功！");
        return map;// a920aac5-a1cc-4747-905c-299076e410e8
    }
}
