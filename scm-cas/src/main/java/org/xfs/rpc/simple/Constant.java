package org.xfs.rpc.simple;

import java.util.List;

import org.xfs.rpc.simple.model.Item;

public interface Constant {
	int ZK_SESSION_TIMEOUT = 5000;
	 
    String ZK_REGISTRY_PATH = "/registry";
    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/data";
    
}
