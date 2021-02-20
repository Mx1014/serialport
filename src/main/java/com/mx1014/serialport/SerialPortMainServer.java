package com.mx1014.serialport;

import com.mx1014.serialport.listener.MyListener;
import com.mx1014.serialport.mqtt.MqttUtil;
import com.mx1014.serialport.utils.SerialTool;
import com.mx1014.serialport.vo.SerialPortVo;
import com.mx1014.serialport.vo.SysValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author Mx1014
 * @date 2021/2/18 13:03
 */
@Component
@Order(0)
public class SerialPortMainServer implements CommandLineRunner {

    @Autowired
    private SysValue sysValue;

    @Override
    public void run(String... args) throws Exception {
        MqttUtil.getInstance().init(sysValue.getUrl(), sysValue.getClientID(), sysValue.getUsername(), sysValue.getPasswd());
        MqttUtil.getInstance().subscribe(sysValue.getTopic()+"/"+sysValue.getClientID()+"/sub");
        ArrayList<String> ports = SerialTool.getInstance().findPort();
        System.out.println("已找到串口数量："+ports.stream().count());

        SerialPortVo serialPortVo = SerialTool.getInstance().openPort("Uart", ports.get(0), sysValue.getBaudrate());
        serialPortVo.bindListener(new MyListener(), 500);
    }
}
