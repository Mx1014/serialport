package com.mx1014.serialport.mqtt;

import com.alibaba.fastjson.JSONObject;
import com.mx1014.serialport.utils.MyUtil;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * MQTT订阅发布工具类
 *
 * @author BoWenWang
 */
public class MqttUtil {

    private static MqttUtil instance = new MqttUtil();
    private static MqttClientPersistence persistence = new MemoryPersistence();

    /**
     * 如果mqtt服务配置了匿名访问，则不需要使用用户名和密码就可以实现消息的订阅和发布
     */
    private static MqttClient client;
    /**
     * 消息服务质量，一共有三个：
     * 0：尽力而为。消息可能会丢，但绝不会重复传输
     * 1：消息绝不会丢，但可能会重复传输
     * 2：恰好一次。每条消息肯定会被传输一次且仅传输一次
     */
    private int qos = 1;
    private String clientID;

    private MqttUtil() {

    }

    public static MqttUtil getInstance() {
        return instance;
    }

    public MqttClient getClient() {
        return client;
    }

    /**
     * 客户端初始化
     */
    public void init(String url, String ClientID, String userName, String passwd) {
        try {
            clientID = ClientID;
            System.out.println(url);
            client = new MqttClient(url, ClientID, persistence);
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setUserName(userName);
            connectOptions.setPassword(passwd.toCharArray());
            // 在重新启动和重新连接时记住状态
            connectOptions.setCleanSession(false);
            //发布者连接服务
            client.connect(connectOptions);
            System.out.println("init连接状态(" + clientID + ")： " + client.isConnected());

//            client = new MqttClient(serviceURI, clientID, persistence);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息发布
     *
     * @author wzq
     **/
    public void publish(String topic, String msg) {
        try {
            System.out.println("发布连接状态(" + clientID + ")： " + client.isConnected());
            MqttTopic mqttTopic = client.getTopic(topic);
            // 创建消息
            MqttMessage mqttMessage = new MqttMessage();
            // 设置消息的服务质量
            mqttMessage.setQos(qos);
            mqttMessage.setPayload(msg.getBytes());
            // 发布信息
            MqttDeliveryToken deliveryToken = mqttTopic.publish(mqttMessage);
            System.out.println("发布者(" + clientID + ")发布消息： " + msg);
            if (!deliveryToken.isComplete()) {
                System.out.println("【成功】发布消息： " + msg);
                deliveryToken.waitForCompletion();
            } else {
                System.out.println("【失败】发布消息： " + msg);
            }
            // 断开连接
            client.disconnect();
            // 关闭客户端
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息订阅
     *
     * @author wzq
     **/
    public void subscribe(String topic) {
        System.out.println("开始订阅："+topic);
        try {
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println(clientID + "：连接丢失...");
                    System.out.println(cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    try {
                        JSONObject jsonObject = JSONObject.parseObject(message.toString());
                        System.out.println("-------------begin-----------");
                        MyUtil.messageHandle(jsonObject);
                        System.out.println("--------------end------------");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                }
            });
            client.subscribe(topic, qos);
            System.out.println(clientID + "订阅者连接状态： " + client.isConnected());
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}
