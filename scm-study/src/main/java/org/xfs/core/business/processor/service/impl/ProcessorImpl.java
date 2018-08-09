package org.xfs.core.business.processor.service.impl;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.xfs.core.business.processor.model.ProcessorModel;
import org.xfs.core.business.processor.service.ProcessorI;
import org.xfs.core.platform.anntation.cache.RedisCache;

@Service
public class ProcessorImpl implements ProcessorI {



    @RedisCache(type = ProcessorModel.class, expire = 1200)
    public ProcessorModel test(ProcessorModel model) {
        model.setName("test:" + model.getName());
        model.setVersion("5.2");
        model.setCode("cc");

        System.out.println("not caced:model:" + model);
        return model;
    }

    public ProcessorModel processor(ProcessorModel model) {

        model.setName("real" + model.getName());
        model.setVersion("5.2");
        try {
            //
            AopContext.currentProxy();
            ProcessorI realProcessor = ((ProcessorI) AopContext.currentProxy());
            return realProcessor.test(model);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return this.test(model);
    }

}
