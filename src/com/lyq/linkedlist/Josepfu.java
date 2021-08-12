package com.lyq.linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();

        list.addBoy(5);
        list.showBoy();

        list.countBoy(1,2,5);//2->4->1->5->3
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


    /**
     *
     * @param startNo 从第几个人开始报数
     * @param countNum 数几下
     * @param nums 有几个人
     */
    public void countBoy(int startNo, int countNum, int nums){
        //先对数据进行校验
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建要给辅助指针，帮助完成小孩出圈
        Boy helper = first;
        //1、需求创建一个辅助指针（变量）helper,实先应该指向环形链表的最后节点
        while (true){
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }


        //2、小孩报数前，让first和helper指针移动k-1次 //k表示从第k个人开始报数
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //3、当小孩报数时，让first和helper指针同时移动m-1次，然后出圈 //m表示数m次
        //这里是个循环操作，直到圈中只有一个节点
        while (true){
            if (helper == first) {//说明圈中只有一个节点
                break;
            }
            //让first和helper 指针同时移动countNum=1
            for (int j = 0; j < countNum-1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这是first指向的节点，就是要除出圈的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时将first指向的小孩出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n",helper.getNo());

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
