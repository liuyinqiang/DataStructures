package com.lyq.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {

        CircleArray queue = new CircleArray(4);//因为预留1，所以队列有效长度为3
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头数据");

            key = scanner.next().charAt(0); //接收一个字符
            switch (key) {
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
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
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
 * 使用数组模拟环形队列--编写一个CircleArray类
 *
 * 循环使用的队列
 */
class CircleArray {
    private int maxSize;    //表示数组的最大容量
    private int front;      //队列头
    private int rear;       //队列尾
    private int[] arr;      //该数组用于存放数据，模拟队列

    //1、front变量的含义调整：front就指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素
    //  front初始值=0
    //2、rear变量的含义调整：rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定
    //  rear的初始值=0
    //3、当队列满时的条件是：(rear+1)%maxSize==front
    //4、当队列为空的条件是：rear==front
    //5、队列中的有效的数据个数是：(rear+maxSize-front)%maxSize

    //创建队列的构造器
    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
     }

    //判断队列是否满
    //队列预留1个位置
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
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
        //直接将数据加入
        arr[rear] = num;
        //将rear后移，必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否为空
        if (isEmpty()){
            throw new RuntimeException("队列为空，不能取数据。");
        }

        //这里需要分析出front是指向队列的第一个元素
        //1、先把front对应的值保留到一个临时变量
        //2、将frant后移，考虑取模
        //3、将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;

    }

    //显示队列的所有数据
    public void showQueue(){
        //判断队列是否为空
        if (isEmpty()){
            System.out.println("队列为空，没有数据。");
            return;
        }

        //思路：从front开始遍历，遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    // 求出当前队列有效数据的个数
    public int size(){
        return (rear + maxSize -front) % maxSize;
    }

    //显示队列的头数据， 注意不是取出数据
    public int headQueue(){
        //判断队列是否为空
        if (isEmpty()){
            throw new RuntimeException("队列为空，不能取数据。");
        }
        return arr[front];
    }

}


