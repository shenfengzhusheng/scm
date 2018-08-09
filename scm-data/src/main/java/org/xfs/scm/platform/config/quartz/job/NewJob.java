package org.xfs.scm.platform.config.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xfs.scm.common.utils.date.DateUtil;

public class NewJob implements BaseJob {
    private static Logger logger = LoggerFactory.getLogger(NewJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.error("New Job执行时间: " + DateUtil.getNowDayChs());
    }
}
