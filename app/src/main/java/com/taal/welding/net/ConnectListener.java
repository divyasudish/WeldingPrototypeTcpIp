package com.taal.welding.net;

import com.taal.welding.net.bean.ConnectConfiguration;

/**
 * Created by LiuJinqi on 2015-07-30.
 */
public interface ConnectListener {
    byte [] data = new byte[100000];
    /**
     * connect success
     */
    public void connectSuccess(ConnectConfiguration configuration);

    /**
     * connect error
     */
    public void connectBreak(ConnectConfiguration configuration);


    public void onReceviceData(ConnectConfiguration configurations, byte[] data);
}
