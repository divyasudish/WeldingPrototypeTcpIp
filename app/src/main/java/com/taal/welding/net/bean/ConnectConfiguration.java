package com.taal.welding.net.bean;

/**
 * Created by Administrator on 2015-07-31.
 */
public class ConnectConfiguration {
    private String host;
    private int port ;
    public static byte[] x = new byte[10000];

    public ConnectConfiguration(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public ConnectConfiguration(String host, int port, byte[] y) {
        this.host = host;
        this.port = port;
        this.x = y;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    @Override
    public String toString() {
        return "ConnectConfiguration---------------->Host:"+host+" port:"+port;
    }
}
