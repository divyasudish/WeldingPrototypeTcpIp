package com.taal.welding.net;

import com.taal.welding.net.bean.ConnectConfiguration;

/**
 * Created by liu on 15/8/2.
 */
public class BaseConnectAdapter implements ConnectListener {
    @Override
    public void connectSuccess(ConnectConfiguration configuration) {

    }

    @Override
    public void connectBreak(ConnectConfiguration configuration) {

    }

    @Override
    public void onReceviceData(ConnectConfiguration configurations, byte[] data) {

    }
}
