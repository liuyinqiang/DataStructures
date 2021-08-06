package com.lyq.queue;

import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头数据");

            key = scanner.next().charAt(0); //接收一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入要添加到队列的数：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
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

/**
 * 使用数组模拟队列--编写一个ArrayQueue类
 *
 * 只能使用一次的队列
 */
class ArrayQueue {
    private int maxSize;    //表示数组的最大容量
    private int front;      //队列头
    private int rear;       //队列尾
    private int[] arr;      //该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; //指向队列头部，分析出front是指向队列头的前一个位置。
        rear = -1;  //指向队列尾部，指向队列尾的数据（队列最后一个数据）
    }

    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int num){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列满，不能加入数据。");
            return;
        }
        rear++; //让rear 后移
        arr[rear] = num;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否为空
        if (isEmpty()){
            throw new RuntimeException("队列为空，不能取数据。");
        }
        front++; //front后移
        return arr[front];
    }
    
    //显示队列的所有数据
    public void showQueue(){
        //判断队列是否为空
        if (isEmpty()){
            System.out.println("队列为空，没有数据。");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //显示队列的头数据， 注意不是取出数据
    public int headQueue(){
        //判断队列是否为空
        if (isEmpty()){
            throw new RuntimeException("队列为空，不能取数据。");
        }
        return arr[front + 1];
    }

}
