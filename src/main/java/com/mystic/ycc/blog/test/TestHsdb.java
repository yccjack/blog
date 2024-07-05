package com.mystic.ycc.blog.test;

/**
 * -Xmx20m
 */
public class TestHsdb {

    public static class Test {
        /**
         * 随着Test的类型信息存放在方法区
         */
        public static HsDb staticHsDb = new HsDb();

        /**
         * 随着Test的类型信息存放在方法区
         */
        public HsDb instanceHsDb = new HsDb();

        void foo() {
            //存放在foo()方法栈帧的局部变量表中
            HsDb stackHsDb = new HsDb();
            //断点处
            System.out.println("finish");
        }
    }

    public static class HsDb {
        public int i;
        public String y;
        public final static String staticStr = "123";

        public static String hs = "321";

    }

    public static void main(String[] args) {
        Test test = new Test();
        test.foo();
    }
}
