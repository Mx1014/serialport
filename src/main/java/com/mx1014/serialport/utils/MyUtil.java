package com.mx1014.serialport.utils;

import com.alibaba.fastjson.JSONObject;
import com.mx1014.serialport.exception.SendDataToSerialPortFailure;
import com.mx1014.serialport.exception.SerialPortOutputStreamCloseFailure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Mx1014
 * @date 2021/2/20 13:35
 */
public class MyUtil {
    //数据转换及串口发送
    public static void toSend(String ctrlCode, String var1) throws SerialPortOutputStreamCloseFailure, SendDataToSerialPortFailure {
        for (int j = 0; j < var1.length(); j++) {
            String hexStr = CH9329Util.toCH9329Code(ctrlCode, var1.charAt(j));
            byte[] bytes = BinaryUtil.HexString2Bytes(hexStr);
            System.out.println(BinaryUtil.Bytes2HexString(bytes));
            SerialTool.getInstance().getSerialPortVo().sendData(bytes);
            CH9329Util.clear();
            System.out.println("串口发送成功");
        }
    }

    public static  void messageHandle(JSONObject jsonObject) throws SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            String[] strArray = entry.getValue().toString().split("[^\\w\\d-]");
            List<String> list = new ArrayList<>();
            for (String str : strArray) {
                if (str != null && !str.isEmpty()) {
                    list.add(str);
                }
            }
            for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
                String value = it.next();
                if (value.matches("(.*)-(.*)")) {
                    String[] arr = value.split("-");
                    toSend(arr[0], arr[1]);
                } else {
                    toSend("Def", value);
                }
            }
        }
    }
}
