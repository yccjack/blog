package com.mystic.ycc.blog.test;

import com.sun.jna.Library;
import com.sun.jna.Native;
public class TestDll {
    public interface DLibrary extends Library {
        //此处我的jdk版本为64位,故加载64位的Dll
        DLibrary INSTANCE = Native.loadLibrary("D:\\project\\dll\\Dll_db\\x64\\Debug\\Dll_db.dll",DLibrary.class);
        //Dll2x64中定义的函数
        int add(int a,int b);
        int substract(int a, int b);
    }
    public static void main(String[] args) {
        int add = DLibrary.INSTANCE.add(3,4);
        int substract = DLibrary.INSTANCE.substract(3,4);
        System.out.println("a+b="+add);
        System.out.println("a-b="+substract);
    }
}
