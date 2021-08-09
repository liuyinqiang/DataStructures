package com.lyq.linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();

        list.addBoy(25);
        list.showBoy();
    }
}

class CircleSingleLinkedList{
    //创建一个first节点
    private Boy first = null;

    public void addBoy(int nums){
        if(nums < 1){
            System.out.printf("nums的值不正确：%d",nums);
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;

            }
        }

    }

    public void showBoy(){
        if(first == null){
            System.out.println("没有任何小孩~~~");
        }
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩编号%d \n",curBoy.getNo());
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext(); //后移
        }
    }

}

class Boy{

    private int no;//编号
    private Boy next;//指向下一个小孩，默认null

    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
