package com.mystic.ycc.blog.test;

import com.mystic.ycc.blog.service.UserService;

public class Test<Integer> {

    public Integer t;

    public int i=5;
    public long y;

    public static final UserService lock=new UserService();

    public static String s="1123123123333333333333333333333123123122222222";
    public  String s1="1123123123333333333333333333333123123122222222";

    public  synchronized void  t() {
        synchronized (lock){
            int i=this.i;
            long y=this.y;

            System.out.println(i+y);
        }

    }

    public static void main(String[] args) {
        Test<Object> test = new Test<>();
       new Thread(()->{

            while (true){
                test.t();
                test.t=123;
            }
        }).start();

        new Thread(()->{
            while (true){
                test.i+=1;
                test.y+= 1L;
            }
        }).start();
        while (true){

        }
    }

}
