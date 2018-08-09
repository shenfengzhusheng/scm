package org.xfs.scm.platform.config.quartz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xfs.scm.platform.config.quartz.entity.JobAndTrigger;
import org.xfs.scm.platform.config.quartz.mapper.JobAndTriggerMapper;
import org.xfs.scm.platform.config.quartz.service.JobAndTriggerServiceI;

import java.util.List;

@Service
public class JobAndTriggerServiceImpl implements JobAndTriggerServiceI {

    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;
    @Override
    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobAndTrigger> list = this.jobAndTriggerMapper.getJobAndTriggerDetails();
        PageInfo<JobAndTrigger> page = new PageInfo<JobAndTrigger>(list);
        return page;
    }
}
