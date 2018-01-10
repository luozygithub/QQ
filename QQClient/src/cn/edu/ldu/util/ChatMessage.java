/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu.util;

import java.net.InetAddress;

/**
 *
 * @author 罗中运
 */
public class ChatMessage {
     private InetAddress toAddr=null; //目标用户地址
    private int toPort; //目标用户端口
    private String userId=null; //用户id
    private String type=null; //消息类型：
    private String text=null; //消息体

    public InetAddress getToAddr() {
        return toAddr;
    }

    public void setToAddr(InetAddress toAddr) {
        this.toAddr = toAddr;
    }

    public int getToPort() {
        return toPort;
    }

    public void setToPort(int toPort) {
        this.toPort = toPort;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
