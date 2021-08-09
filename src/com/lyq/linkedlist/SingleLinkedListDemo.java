package com.lyq.linkedlist;

import java.util.Stack;

/**
 * 单链表的增（含排序）、删、改、查
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {

        //测试
        //创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        SingleLinkedList list = new SingleLinkedList();
        list.add(hero1);
        list.add(hero2);
        list.add(hero4);
        list.add(hero3);

//        list.addByOrder(hero1);
//        list.addByOrder(hero3);
//        list.addByOrder(hero4);
//        list.addByOrder(hero2);

        list.list();

        hero2 = new HeroNode(2,"无敌","玉麒麟~~");
        list.update(hero2);
        System.out.println("修改后的列表：");
        list.list();

//        System.out.println("删除编码1英雄：");
//        list.delete(1);
//        list.list();
//        System.out.println("删除编码4英雄：");
//        list.delete(4);
//        list.list();

        System.out.printf("获取到链表的实际有效节点个数是：%d",getLength(list.getHead()));

        System.out.println();
        HeroNode res = findLastIndexNode(list.getHead(),2);
        System.out.println("获取到的倒数第2个节点是：\n"+res);


        reversetList(list.getHead());
        System.out.println("反转后的链表：");
        list.list();

        System.out.println("原链表结构顺序：");
        list.list();
        System.out.println("逆序打印单链表，不改变原链表结构：");
        reversePrint2(list.getHead());

        System.out.println("方式1，逆序打印：");
        reversePrint1(list.getHead());

        System.out.println("----------------------------------");
        mergeList(); //笨方法

        System.out.println("----------------------------------");
        testMergeTwoList();


    }


    public static void testMergeTwoList(){
        System.out.println("----------------------------------");
        HeroNode head1 = new HeroNode(0,"","");
        HeroNode node1 = new HeroNode(1,"node1","node1");
        HeroNode node3 = new HeroNode(3,"node3","node3");
        HeroNode node5 = new HeroNode(5,"node5","node5");
        HeroNode node7 = new HeroNode(7,"node7","node7");
        node5.next = node7;
        node3.next = node5;
        node1.next = node3;
        head1.next = node1;

        System.out.println("head1:----------------");
        HeroNode temp1 = head1.next;
        while (temp1 != null){
            System.out.println(temp1);
            temp1 = temp1.next;
        }

        HeroNode head2 = new HeroNode(0,"","");
        HeroNode node2 = new HeroNode(2,"node2","node2");
        HeroNode node4 = new HeroNode(4,"node4","node4");
        HeroNode node6 = new HeroNode(6,"node6","node6");
        HeroNode node8 = new HeroNode(8,"node8","node8");
        node6.next = node8;
        node4.next = node6;
        node2.next = node4;
        head2.next = node2;

        System.out.println("head2:----------------");
        HeroNode temp2 = head2.next;
        while (temp2 != null){
            System.out.println(temp2);
            temp2 = temp2.next;
        }

        System.out.println("head1,head2 合并:----------------");
        //HeroNode temp = mergeTwoList(head1,head2).next;

        HeroNode temp = mergeTwoList22(head1,head2).next;

        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //递归方法 合并两个单链表
    public static HeroNode mergeTwoList(HeroNode head1,HeroNode head2){
        if(head1 == null && head2 == null){
            return null;
        }
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }

        HeroNode head = null;

        if(head1.no > head2.no){
            head = head2; //将节点编码较小的，指定给头节点
            //继续递归head2
            head.next = mergeTwoList(head1,head2.next);
        } else {
            head = head1;
            //继续递归head1
            head.next = mergeTwoList(head1.next, head2);
        }
        return head;

    }


    //非递归方法 合并两个单链表
    public static HeroNode mergeTwoList22(HeroNode head1,HeroNode head2){
        if(head1 == null || head2 == null){
            return head1 != null ? head1 : head2;
        }
        //合并后的链表头 , 把节点no小的给到链表头
        HeroNode head = head1.no < head2.no ? head1 : head2;

        HeroNode cur1 = head == head1 ? head1 : head2; //?? 代表合并后的链表头
        HeroNode cur2 = head == head1 ? head2 : head1; //?? 代表另一链表

        HeroNode pre = null; //cur1的前一个节点
        HeroNode next = null; //cur2的后一个节点

        while (cur1 != null && cur2 != null){

            if (cur1.no <= cur2.no){
                pre = cur1;
                cur1 = cur1.next;
            } else {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }
        pre.next = cur1 == null? cur2 : cur1;
        return head;
    }



    //合并两个有向单向链表。。复制、新建
    public static void mergeList(){

        HeroNode head1 = new HeroNode(0,"","");
        HeroNode node1 = new HeroNode(1,"node1","node1");
        HeroNode node3 = new HeroNode(3,"node3","node3");
        HeroNode node5 = new HeroNode(5,"node5","node5");
        HeroNode node7 = new HeroNode(7,"node7","node7");
        node5.next = node7;
        node3.next = node5;
        node1.next = node3;
        head1.next = node1;

        System.out.println("head1:----------------");
        HeroNode temp1 = head1.next;
        while (temp1 != null){
            System.out.println(temp1);
            temp1 = temp1.next;
        }

        HeroNode head2 = new HeroNode(0,"","");
        HeroNode node2 = new HeroNode(2,"node2","node2");
        HeroNode node4 = new HeroNode(4,"node4","node4");
        HeroNode node6 = new HeroNode(6,"node6","node6");
        HeroNode node8 = new HeroNode(8,"node8","node8");
        node6.next = node8;
        node4.next = node6;
        node2.next = node4;
        head2.next = node2;

        System.out.println("head2:----------------");
        HeroNode temp2 = head2.next;
        while (temp2 != null){
            System.out.println(temp2);
            temp2 = temp2.next;
        }

        System.out.println("head1、head2整合:----------------");

        HeroNode head3 = new HeroNode(0,"","");
        HeroNode tmp = null;
        temp1 = head1.next;
        while (temp1 != null){
            System.out.println("head1-->temp1--->"+temp1);
            tmp = new HeroNode(0,"","");
            tmp.no = temp1.no;
            tmp.name = temp1.name;
            tmp.nickname = temp1.nickname;
            addByOrder2222(head3, tmp);
            temp1 = temp1.next;
        }

        //head1、head2整合
        temp2 = head2.next;
        while (temp2 != null){
            System.out.println("head2-->temp2--->"+temp2);
            tmp = new HeroNode(0,"","");
            tmp.no = temp2.no;
            tmp.name = temp2.name;
            tmp.nickname = temp2.nickname;
            addByOrder2222(head3, tmp);
            temp2 = temp2.next;
        }


        System.out.println("head1、head2整合后:----------------");
        HeroNode temp3 = head3.next;
        while (temp3 != null){
            System.out.println(temp3);
            temp3 = temp3.next;
        }



    }

    //合并两个有序单向链表
    public static void addByOrder2222(HeroNode head,HeroNode heroNode){
        //因为head节点不能动，因此需要一个辅助遍历 temp
        HeroNode temp = head;

        boolean flag = false;
        while (true){
            if(temp.next == null){//说明temp已经是链表的最后
                break;
            }
            if(temp.next.no > heroNode.no){//位置找到，就在temp的后面插入
                break;
            } else if(temp.next.no == heroNode.no){//说明希望插入的node的编码已存在
                flag = true;
                break;
            }
            temp = temp.next; //后移、遍历当前链表
        }

        //判断flag
        if(flag){
            System.out.printf("准备插入的英雄编号%d已经存在，不能加入\n",heroNode.no);
        } else {
            //插入到链表temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }


    //从尾到头打印单链表数据：方式1：反向遍历；；方式2：Stack栈
    //方式1：先获取一个反转的链表，然后打印获取的反转链表
    public static void reversePrint1(HeroNode head){
        //如果当前链表为空或者只有一个节点，无需反转，直接返回
        if(head.next == null || head.next.next == null){
            return;
        }
        //定义一个辅助指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前节点cur 的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");

        //遍历原理的链表，每遍历一个节点，取出添加到新的reverseHead的最前端
        while (cur != null){
            next = cur.next;//先暂时保存当前节点的下一个节点，最后要用
            cur.next = reverseHead.next; //将cur的下一节点指向当前反转链表的最前端
            reverseHead.next = cur; //将cur连接到反转链表上
            cur = next; //将cur后移
        }

        //遍历打印
        cur = reverseHead.next;
        while (cur != null){
            System.out.println(cur);
            cur = cur.next;
        }
    }


    //方式2：利用栈数据结构，将各节点压入栈中，利用栈的先进后出特点，实现逆序打印的效果
    public static void reversePrint2(HeroNode head){
        if (head.next == null){
            return; //空链表，不能打印
        }
        //创建一个栈，将各节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表所有节点压入栈
        while (cur != null){
            stack.push(cur);
            cur = cur.next; //cur后移， 这样就可以压入下一个节点
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size()>0){
            System.out.println(stack.pop());//stack的特点先进后出
        }
    }


    //链表反转
    public static void reversetList(HeroNode head){
        //如果当前链表为空或者只有一个节点，无需反转，直接返回
        if(head.next == null || head.next.next == null){
            return;
        }
        //定义一个辅助指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前节点cur 的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");

        //遍历原理的链表，每遍历一个节点，取出添加到新的reverseHead的最前端
        while (cur != null){
            next = cur.next;//先暂时保存当前节点的下一个节点，最后要用
            cur.next = reverseHead.next; //将cur的下一节点指向当前反转链表的最前端
            reverseHead.next = cur; //将cur连接到反转链表上
            cur = next; //将cur后移
        }

        //将head.next 指向reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }

    // 获取到单链表的节点个数（不统计头节点）

    /**
     *
     * @param head
     * @return 返回链表有效节点个数
     */
    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0;
        }
        int lenght = 0;
        //定义一个辅助变量
        HeroNode cur = head.next;
        while (cur != null){
            lenght++;
            cur = cur.next;
        }
        return lenght;
    }


    // 查找链表的倒数第N个节点

    /**
     *
     * @param head
     * @param index 表示链表的倒数第index个节点
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index){
        //判断如果链表为空，返回null
        if (head.next == null){
            return null;
        }

        //第一次遍历找到链表的长度
        int size = getLength(head);
        //index校验
        if(index <=0 || index > size){
            return null;
        }
        //定义一个辅助变量，for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0;i<size-index;i++){
            cur = cur.next;
        }
        return cur;
    }
}



/**
 * 定义SingleLinkedList  管理单链表
 */
class SingleLinkedList{
    // 先初始化一个头节点
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路：当不考虑编号顺序时
    //1、找到当前链表的最后节点
    //2、将最后这个节点的next 指向新的节点
    public void add(HeroNode heroNode){
        //因为head节点不能动，因此需要一个辅助遍历 temp
        HeroNode temp = head;
        // 遍历链表，找到最后
        while (true){
            //找到链表的最后
            if(temp.next == null){
                break;
            }
            //如果没有找到，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }


    //
    public void addByOrder(HeroNode heroNode){
        //因为head节点不能动，因此需要一个辅助遍历 temp
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){//说明temp已经是链表的最后
                break;
            }
            if(temp.next.no > heroNode.no){//位置找到，就在temp的后面插入
                break;
            } else if(temp.next.no == heroNode.no){//说明希望插入的node的编码已存在
                flag = true;
                break;
            }
            temp = temp.next; //后移、遍历当前链表
        }

        //判断flag
        if(flag){
            System.out.printf("准备插入的英雄编号%d已经存在，不能加入\n",heroNode.no);
        } else {
            //插入到链表temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //
    public void update(HeroNode heroNode){
        //因为head节点不能动，因此需要一个辅助遍历 temp
        HeroNode temp = head.next;
        boolean flag = false; //是否找到待修改节点
        while (true){
            if(temp == null){//已遍历完链表
                break;
            }
            if(temp.no > heroNode.no){//找到待修改的节点
                flag = true;
                break;
            }
            temp = temp.next; //后移、遍历当前链表
        }

        //判断flag
        if(flag){
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            System.out.printf("没有找到编号%d的英雄，不能修改\n",heroNode.no);
        }
    }


    //删除节点，思路：
    //1、head不能懂，所有要找一个temp辅助找到待删除节点的前一个节点
    //2、比较时是temp.next.no 和 需要 删除的节点no比较
    //
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false; //是否找到待删除节点的
        while (true){
            if(temp.next == null){//已经到链表最后
                break;
            }
            if(temp.next.no == no){//找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; //后移、遍历当前链表
        }

        //判断flag
        if(flag){
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到编号%d的英雄，不能删除\n",no);
        }
    }


    //显示链表,遍历
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空。");
            return;
        }
        //因为head节点不能动，因此需要一个辅助遍历 temp
        HeroNode temp = head.next;
        while (true){
            //判断是否到链表最后
            if(temp == null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //链表后移，即temp后移
            temp = temp.next;
        }

    }


}



/**
 * 定义HeroNode，每个HeroNode对象就是一个节点
 */
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;   //指向下一个节点

    public HeroNode (int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                //", next=" + next +
                '}';
    }
}
