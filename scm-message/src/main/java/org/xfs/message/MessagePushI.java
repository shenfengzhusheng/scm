package org.xfs.message;

import org.xfs.message.model.PushMessage;



public interface MessagePushI {

    /**
     *
     * @return
     */
    boolean pushNotification(PushMessage message);


  //  boolean broadcastAll();
}
