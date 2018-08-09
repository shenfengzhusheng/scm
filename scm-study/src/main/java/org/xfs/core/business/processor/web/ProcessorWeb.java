package org.xfs.core.business.processor.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xfs.core.business.processor.model.ProcessorModel;
import org.xfs.core.business.processor.service.ProcessorI;
import org.xfs.core.platform.anntation.cache.RedisCache;

@RestController
@RequestMapping("/processor/")
public class ProcessorWeb {

    @Resource
    ProcessorI processor;

    @RequestMapping(value = "test")
    public ProcessorModel process(ProcessorModel vo) {
        System.out.println("vo:" + vo);
        // System.out.println("vo:" + vo);
        // return this.test(vo);
        ProcessorModel result = this.processor.processor(vo);
        return result;
    }

    @RedisCache(type = ProcessorModel.class, expire = 1200)
    private ProcessorModel test(ProcessorModel vo) {
        System.out.println("no cache");
        vo.setVersion("45");
        return vo;
    }
}
