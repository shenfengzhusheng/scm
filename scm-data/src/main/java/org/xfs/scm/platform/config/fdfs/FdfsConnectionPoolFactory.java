package org.xfs.scm.platform.config.fdfs;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.csource.fastdfs.StorageClient;
import org.xfs.scm.platform.config.fdfs.factory.FastDFSTemplateFactory;

public class FdfsConnectionPoolFactory {
    private GenericObjectPool<StorageClient> pool;

    public FdfsConnectionPoolFactory(FastDFSTemplateFactory fastDFSTemplateFactory){
       // this.pool=new GenericObjectPool<StorageClient>(new ConnectionPoolFactory(fastDFSTemplateFactory));
    }
}
