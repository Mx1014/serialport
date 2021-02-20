package com.mx1014.serialport.utils;

import java.util.HashMap;

/**
 * @author Mx1014
 * @date 2021/2/19 22:14
 */
public class CH9329DictMap {
    /**
     * Kb_map 为键盘字符
     * Ctrl_map 为控制字符 默认为Def{0x00 0x00}  后字节必须为00  用途为组合按键，如Ctrl-A,则需要使用Ctrl_map[Ctrl]+Kb_map[A]
     */
    public final static HashMap<Object, String> Kb_map = new HashMap();
    //    public final static HashMap<Object, String>  Mod_map = new HashMap();
//    public final static HashMap<Object, String>  Fuc_map = new HashMap();
    public final static HashMap<Object, String> Ctrl_map = new HashMap();

    static {
        Kb_map.put("HEAD", "57AB");
        Kb_map.put("ADDR", "00");
        Kb_map.put("CMD", "02");
        Kb_map.put("LEN", "08");
        Kb_map.put("DATAEND", "0000000000");
        Kb_map.put('1', "1E");
        Kb_map.put('2', "1F");
        Kb_map.put('3', "20");
        Kb_map.put('4', "21");
        Kb_map.put('5', "22");
        Kb_map.put('6', "23");
        Kb_map.put('7', "24");
        Kb_map.put('8', "25");
        Kb_map.put('9', "26");
        Kb_map.put('0', "27");
        Kb_map.put('A', "04");
        Kb_map.put('B', "05");
        Kb_map.put('C', "06");
        Kb_map.put('D', "07");
        Kb_map.put('E', "08");
        Kb_map.put('F', "09");
        Kb_map.put('G', "0A");
        Kb_map.put('H', "0B");
        Kb_map.put('I', "0C");
        Kb_map.put('J', "0D");
        Kb_map.put('K', "0E");
        Kb_map.put('L', "0F");
        Kb_map.put('M', "10");
        Kb_map.put('N', "11");
        Kb_map.put('O', "12");
        Kb_map.put('P', "13");
        Kb_map.put('Q', "14");
        Kb_map.put('R', "15");
        Kb_map.put('S', "16");
        Kb_map.put('T', "17");
        Kb_map.put('U', "18");
        Kb_map.put('V', "19");
        Kb_map.put('W', "1A");
        Kb_map.put('X', "1B");
        Kb_map.put('Y', "1C");
        Kb_map.put('Z', "1D");
        Kb_map.put('a', "04");
        Kb_map.put('b', "05");
        Kb_map.put('c', "06");
        Kb_map.put('d', "07");
        Kb_map.put('e', "08");
        Kb_map.put('f', "09");
        Kb_map.put('g', "0A");
        Kb_map.put('h', "0B");
        Kb_map.put('i', "0C");
        Kb_map.put('j', "0D");
        Kb_map.put('k', "0E");
        Kb_map.put('l', "0F");
        Kb_map.put('m', "10");
        Kb_map.put('n', "11");
        Kb_map.put('o', "12");
        Kb_map.put('p', "13");
        Kb_map.put('q', "14");
        Kb_map.put('r', "15");
        Kb_map.put('s', "16");
        Kb_map.put('t', "17");
        Kb_map.put('u', "18");
        Kb_map.put('v', "19");
        Kb_map.put('w', "1A");
        Kb_map.put('x', "1B");
        Kb_map.put('y', "1C");
        Kb_map.put('z', "1D");
        Kb_map.put("F1", "3A");
        Kb_map.put("F2", "3B");
        Kb_map.put("F3", "3C");
        Kb_map.put("F4", "3D");
        Kb_map.put("F5", "3E");
        Kb_map.put("F6", "3F");
        Kb_map.put("F7", "40");
        Kb_map.put("F8", "41");
        Kb_map.put("F9", "42");
        Kb_map.put("F10", "43");
        Kb_map.put("F11", "44");
        Kb_map.put("F12", "45");
        Kb_map.put("Shift_L", "E1");
        Kb_map.put("Shift", "E1");
        Kb_map.put("Shift_R", "E5");
        Kb_map.put("Ctrl_L", "E0");
        Kb_map.put("Ctrl", "E0");
        Kb_map.put("Ctrl_R", "E4");
        Kb_map.put("Alt_L", "E2");
        Kb_map.put("Alt", "E2");
        Kb_map.put("Alt_R", "E6");
        Kb_map.put("Tab", "2B");
        Kb_map.put("ESC", "29");
        Kb_map.put("Enter", "58");
        Kb_map.put("Del", "4C");
        Kb_map.put("Insert", "49");
        Kb_map.put("Home", "4A");
        Kb_map.put("End", "4D");
        Kb_map.put("PgUp", "4B");
        Kb_map.put("PgDo", "4E");
        Kb_map.put("Num Lock", "53");
        Kb_map.put("Clear", "57AB00020800000000000000000C");
        Ctrl_map.put("Def", "0000");
        Ctrl_map.put("Ctrl", "0100");
        Ctrl_map.put("Shift", "0200");
        Ctrl_map.put("Alt", "0400");
        Ctrl_map.put("Win", "0800");
        Ctrl_map.put("Ctrl_L", "0100");
        Ctrl_map.put("Shift_L", "0200");
        Ctrl_map.put("Alt_L", "0400");
        Ctrl_map.put("Win_L", "0800");
        Ctrl_map.put("Ctrl_R", "1000");
        Ctrl_map.put("Shift_R", "2000");
        Ctrl_map.put("Alt_R", "4000");
        Ctrl_map.put("Win_R", "8000");

    }


}
