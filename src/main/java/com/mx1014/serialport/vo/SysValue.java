package com.mx1014.serialport.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mx1014
 * @date 2021/2/18 13:27
 */
@Configuration
public class SysValue {

    @Value("${mqtt.url}")
    private String url;


    @Value("${mqtt.clientID}")
    private String clientID;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.passwd}")
    private String passwd;

    @Value("${serial.baudrate}")
    private int baudrate;


    @Value("${mqtt.topic}")
    private String topic;


    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getBaudrate() {
        return baudrate;
    }

    public void setBaudrate(int baudrate) {
        this.baudrate = baudrate;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


}
