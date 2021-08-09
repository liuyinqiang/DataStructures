package com.lyq.linkedlist;

public class DoubleLinkedListDemo {


    public static void main(String[] args) {
        //测试
        //创建节点
        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");

        DoubleLinkedList list = new DoubleLinkedList();
//        list.add(hero1);
//        list.add(hero3);
//        list.add(hero2);
//        list.add(hero4);
        System.out.println("----------------");
        list.addByOrder(hero4);
        list.list();
        System.out.println("----------------");
        list.addByOrder(hero2);
        list.list();
        System.out.println("----------------");
        list.addByOrder(hero1);
        list.list();
        System.out.println("----------------");
        list.addByOrder(hero3);
        list.list();
        System.out.println("----------------");

//        System.out.println("----------------");
//        hero2 = new HeroNode2(2,"卢俊义qqqq","玉麒麟qqqq");
//        list.update(hero2);
//
//        list.list();
//
//
//        System.out.println("----------------");
//        list.delete(2);
//        list.list();
//        System.out.println("----------------");
//        list.delete(4);
//        list.list();

    }

}

class DoubleLinkedList{
    //先初始化一个头节点，头节点不要动，不存放具体数据
    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }


    //添加一个节点到双向链表，按照节点编码排序
    public void addByOrder(HeroNode2 heroNode){
        HeroNode2 tmp = head;
        boolean flag = false;
        while (true){
            if (tmp.next == null){
                break;
            }

            if (tmp.next.no > heroNode.no){
                break;
            } else if (tmp.next.no == heroNode.no){
                flag = false;
                break;
            }
            tmp = tmp.next;
        }
        if (flag){
            System.out.printf("要插入的%d编码的节点已存在：%d",heroNode.no,heroNode);
        } else {
            heroNode.next = tmp.next;
            heroNode.pre = tmp;
            tmp.next = heroNode;
        }

    }


    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode){
        //因为head节点不能动，因此需要一个辅助遍历 temp
        HeroNode2 temp = head;
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
        heroNode.pre = temp;
    }


    //对双向链表删除一个节点
    //1、找到待删除的节点
    //2、通过该节点，自我删除
    public void delete(int no){

        //判断当前链表是否为空，
        if(head.next == null){
            System.out.println("链表为空，无法删除。");
        }

        HeroNode2 temp = head.next; //辅助变量（指针）
        boolean flag = false; //是否找到待删除节点的
        while (true){
            if(temp == null){//已经到链表最后
                break;
            }
            if(temp.no == no){//找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; //后移、遍历当前链表
        }

        //判断flag
        if(flag){

            temp.pre.next = temp.next;
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("没有找到编号%d的英雄，不能删除\n",no);
        }
    }

    //双向链表修改与单向一致。
    public void update(HeroNode2 heroNode){
        //因为head节点不能动，因此需要一个辅助遍历 temp
        HeroNode2 temp = head.next;
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

    //显示链表,遍历
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空。");
            return;
        }
        //因为head节点不能动，因此需要一个辅助遍历 temp
        HeroNode2 temp = head.next;
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


class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;   //指向下一个节点
    public HeroNode2 pre;    //指向上一个节点

    public HeroNode2 (int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                //", next=" + next +
                '}';
    }
}
