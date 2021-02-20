package com.mx1014.serialport.utils;

import com.mx1014.serialport.exception.SendDataToSerialPortFailure;
import com.mx1014.serialport.exception.SerialPortOutputStreamCloseFailure;

/**
 * @author Mx1014
 * @date 2021/2/19 22:45
 */
public class CH9329Util {

    /**
     * 根据控制码、数据码拼接CH9329串口接收数据格式，并对数据计算累计和，返回完整的16进制字符串
     *
     * @param ctrlCode 控制码
     * @param value    数据码
     * @return
     */
    public static String toCH9329Code(String ctrlCode, Object value) {
        String key = "";
        key = key + CH9329DictMap.Kb_map.get(value);
        key = CH9329DictMap.Kb_map.get("HEAD") + CH9329DictMap.Kb_map.get("ADDR") + CH9329DictMap.Kb_map.get("CMD") + CH9329DictMap.Kb_map.get("LEN") + CH9329DictMap.Ctrl_map.get(ctrlCode) + key + CH9329DictMap.Kb_map.get("DATAEND");
        key = key + BinaryUtil.makeChecksum(key);
        return key;
    }

    /**
     * 按键释放操作，每次按键操作后后都需要调用
     *
     * @throws SerialPortOutputStreamCloseFailure
     * @throws SendDataToSerialPortFailure
     */
    public static void clear() throws SerialPortOutputStreamCloseFailure, SendDataToSerialPortFailure {
        String clear = CH9329DictMap.Kb_map.get("Clear");
        byte[] bytes = BinaryUtil.HexString2Bytes(clear);
        System.out.println(BinaryUtil.Bytes2HexString(bytes));
        SerialTool.getInstance().getSerialPortVo().sendData(bytes);
    }


}
