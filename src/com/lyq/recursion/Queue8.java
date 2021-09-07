package com.lyq.recursion;

public class Queue8 {

    int max = 8; //定义一个max表示共有多少个皇后
    //定义数组，保存皇后放置位子的结果，比如arr = {0,4,7,5,2,6,1,3}
    //arr下标表示第几行，即第几个皇后，，arr[i]=val,val表示第i+1个皇后，放在第i+1行的val+1列。

    int[] array = new int[max];
    static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d种解法！",count);
    }


    //放置第n个皇后
    //特别注意：check是每一次递归时，进入到check中都有for (int i = 0; i < max; i++) ，因此会有回溯
    private void check(int n){
        if (n == max){
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n，放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if(judge(n)){
                //不冲突
                check(n+1);
            }
            //如果冲突，就继续执行：array[n] = i ; 即将第n个皇后，放置在本行的下一列位置。
        }
    }

    // n 表示第n个皇后
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {

            //1、array[i] == array[n] ：表示判断第n个皇后是否和前面的n-1个皇后在同一列
            //2.Math.abs(n-1) == Math.abs(array[n]-array[i]) ：表示判断第n个皇后是否和第i个皇后在同一斜线

            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])) {
                return false;
            }
        }
        return true;
    }


    //打印皇后摆放的位子
    public void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }



}
