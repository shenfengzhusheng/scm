package org.xfs.scm.platform.config.fdfs;

import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class FdfsConfig {

    private static final Logger logger= LoggerFactory.getLogger(FdfsConfig.class);
//    private  TrackerClient trackerClient;
//    private  TrackerServer trackerServer;
//    private  StorageServer storageServer;
/*
    private  StorageClient storageClient;
*/

    @Value("${fdfs.tracker_server}")
    private String trackerserver;


    @Bean
    public StorageClient initStorageClient(){
        StorageClient storageClient= null;
        try{
            ClientGlobal.initByTrackers(this.trackerserver);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer=trackerClient.getStoreStorage(trackerServer);
            if(trackerServer == null){
                throw new IllegalStateException("getStoreStorage return null");
            }
            storageClient = new StorageClient(trackerServer, storageServer);
        }catch (Exception e){
            logger.error("init fdfs tracker error",e);
        }
        return storageClient;
    }




}
