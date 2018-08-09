package org.xfs.scm.platform.config.quartz.service;

import com.github.pagehelper.PageInfo;
import org.xfs.scm.platform.config.quartz.entity.JobAndTrigger;

public interface JobAndTriggerServiceI {
    PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);

}
