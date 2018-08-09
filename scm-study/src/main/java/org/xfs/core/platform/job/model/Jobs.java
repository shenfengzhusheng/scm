package org.xfs.core.platform.job.model;

import java.util.HashMap;
import java.util.Map;

public class Jobs {
    /**
     * DB 里的job
     */
    public static final Map<String, Job> jobs = new HashMap<String, Job>();

    /**
     * 运行中的job
     */
    public static final Map<String, Job> runJobs = new HashMap<String, Job>();

    /**
     * 暂停的job
     */
    public static final Map<String, Job> runJobsstopJobs = new HashMap<String, Job>();



}
