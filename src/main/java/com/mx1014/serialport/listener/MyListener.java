package com.mx1014.serialport.listener;

import com.mx1014.serialport.utils.BinaryUtil;

/**
 * @author Mx1014
 * @date 2021/2/18 0:25
 */
public class MyListener extends PortListener {
    @Override
    public void onReceive(byte[] data) {
        if ("57AB0082010085".equalsIgnoreCase(BinaryUtil.Bytes2HexString(data))){
            System.out.println("串口处理成功");
        }
        System.out.println(BinaryUtil.Bytes2HexString(data));
//        String dataStr = new String(data).trim();
//        System.out.println("串口接收到数据: " + dataStr);
    }

    @Override
    public void onReadException(Exception e) {
        System.out.println("发生异常: " + e.getMessage());
    }
}
