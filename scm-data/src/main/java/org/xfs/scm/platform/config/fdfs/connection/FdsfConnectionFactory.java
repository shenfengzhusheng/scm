package org.xfs.scm.platform.config.fdfs.connection;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.xfs.scm.platform.config.fdfs.factory.FastDFSTemplateFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * 使用连接池创建连接
 */
public class FdsfConnectionFactory extends BasePooledObjectFactory<StorageClient> {
    private FastDFSTemplateFactory factory;

    public FdsfConnectionFactory(FastDFSTemplateFactory templateFactory){
        this.factory=templateFactory;
    }

    @Override
    public StorageClient create() throws Exception {
        TrackerClient trackerClient = new TrackerClient(factory.getG_tracker_group());
        TrackerServer trackerServer = trackerClient.getConnection();
        return new StorageClient(trackerServer, null);    }

    @Override
    public PooledObject<StorageClient> wrap(StorageClient storageClient) {
        return new DefaultPooledObject<>(storageClient);
    }
    public PooledObject<StorageClient> makeObject() throws Exception {
        return wrap(create());
    }

    public void destroyObject(StorageClient obj) throws Exception {
    //    close(obj.getTrackerServer());
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignored) {
            }
        }
    }
}
