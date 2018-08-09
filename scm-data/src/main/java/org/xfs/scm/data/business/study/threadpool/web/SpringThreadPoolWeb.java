package org.xfs.scm.data.business.study.threadpool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.study.threadpool.model.QueryTicket;

@RestController
@RequestMapping("/task")
public class SpringThreadPoolWeb {
    @Autowired
    private ThreadPoolTaskExecutor executor;

    @RequestMapping("/loopQueryTicket.do")
    public Object loopQueryTicket(){
        QueryTicket queryTicket=new QueryTicket("zhagnsan", 3, 5);
        executor.execute(queryTicket);
        return JsonResponse.success("正在查询！");
    }
}
