/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu;

import java.net.SocketException;

/**
 *
 * @author 罗中运
 */
public class test1 {
    public static void main(String[] args) throws SocketException {
        Thread listThread=new ListServer();
           listThread.start();
        Thread s=new tset();
            s.start();
    }
}
