package com.lyq.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(4);
        String key = ""; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("show：显示栈数据");
            System.out.println("push：添加数据到栈（入栈）");
            System.out.println("pop：从栈取出数据（出栈）");
            System.out.println("exit：退出程序");
            System.out.printf("请输入你的选择：");

            key = scanner.next(); //接收一个字符
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出。");
    }
}


class ArrayStack{
    private int maxSize; //栈的大小
    private int[] stack; //数组，数组模拟栈，数据存放到数组中
    private int top = -1; //栈顶，初始化为-1

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // 判断栈是否已满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    // 判断栈是否为空
    public boolean isEmpty(){
        return top == -1;
    }

    // 入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈满，无法添加。");
            return;
        }
        top ++;
        stack[top] = value;
    }

    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空，无法取数\n");
        }
        int value = stack[top];
        top --;
        return value;
    }

    //遍历
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，无法取数");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

}