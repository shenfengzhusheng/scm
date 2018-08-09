package org.xfs.netty.server.advance;

import org.xfs.netty.server.advance.start.ServerManager;

public class TestServer {

    public static void main(String[] args)throws Exception {
        ServerManager manager = new ServerManager();
        //manager.startServer(args[0]);
        manager.startServer();
    }
}
