package org.xfs.scm.platform.util.fastdfs;

import java.io.Serializable;

public interface FileManagerConfig extends Serializable {
    public static final String FILE_DEFAULT_AUTHOR = "Jeken.Liu";

    public static final String PROTOCOL = "http://";

    public static final String SEPARATOR = "/";

    public static final String TRACKER_NGNIX_ADDR = "121.42.205.204:81";

    public static final String TRACKER_NGNIX_PORT = "80";

    public static final String CLIENT_CONFIG_FILE = "fdfs_client.properties";
}
