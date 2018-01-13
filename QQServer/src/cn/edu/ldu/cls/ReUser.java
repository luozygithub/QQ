/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu.cls;

/**
 *
 * @author 罗中运
 */
public class ReUser {
    String id;
    String password;
    String Tel;
    String Nickname;

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String Nickname) {
        this.Nickname = Nickname;
    }

    public ReUser(String id, String password, String Tel, String Nickname) {
        this.id = id;
        this.password = password;
        this.Tel = Tel;
        this.Nickname = Nickname;
    }

    public ReUser() {
    }

    public ReUser(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
