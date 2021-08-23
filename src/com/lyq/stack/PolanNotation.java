package com.lyq.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PolanNotation {

    public static void main(String[] args) {

        //代码把中缀表达式转为后缀表达式
        String expr = "1 + ( ( 2 + 3 ) * 4 ) - 5"; //==> 入到栈中123+4*+5-


        List<String> expList = Arrays.asList(expr.split(" "));
        System.out.println(expr + "中缀表达式转换为后缀表达式是：" + getNiPolan(expList));

        System.out.println(expr + "计算的结果是：" + calculator(getNiPolan(expList)));

        System.out.println("-----------------------------------");

        //先定义逆波兰表达式
        //(3+4)x5-6  => 3 4 + 5 * 6 -
        //String expresstion = "3 4 + 5 * 6 -";
        String expresstion = "30 4 + 5 * 6 -";
        List<String> list = Arrays.asList(expresstion.split(" "));
        System.out.println("list => " + list);

        int rst = calculator(list);
        System.out.println("计算的结果是：" + rst);
    }

    public static List<String> getNiPolan(List<String> expList){

        List<String> list = new ArrayList<>();

        //1、初始化两个栈
        Stack<String> s1 = new Stack<String>();//存运算符
        //Stack<String> s2 = new Stack<String>();//存中间结果
        //由于 最终需要把 s2 逆序 得到最终的 后缀表达式，所有此处直接把s2定义为List,省去逆序的步骤
        List<String> s2 = new ArrayList<>();

        //2、从左到右扫描表达式

        String str = "";
        for (String item : expList){
            //3、遇到操作数时，将其压入到s2 //匹配到的是多位数
            if (item.matches("\\d+")){
                s2.add(item);
            }
            //4、遇到运算符时，比较其由于s1栈顶运算符的优先级：
            //4.1 如果s1为空，或栈顶运算符为左括号“(”,则直接将此运算符入栈到s1
            else if(item.equals("(") ){
                s1.push(item);
            }
            else if (item.equals(")")){
                //5、遇到括号时
                //5.1 如果是左括号“(”，直接压入s1
                //5.2 如果是右括号“)”,则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止，此时将一对括号丢弃
                while (!s1.isEmpty()){
                    str = s1.pop();
                    if(str.equals("(")){
                        break;
                    }
                    s2.add(str);
                }

            } else {
                //4.2 否则，若优先级比栈顶运算符高，也将运算符压入s1
                //4.3 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到4.1与s1中的新栈预算法比较
                while (!s1.isEmpty() && priority(item) <= priority(s1.peek())){ //TODO 此处未处理运算符是“(”和“)”,由于方法默认返回-1，赶巧忽略了括号
                    s2.add(s1.pop());
                }
                s1.push(item);

            }

        }

        //6、重复步骤2至5，直到表达式的最右边
        //7、将s1中剩余的预算法一次弹出并压入s2
        while (!s1.isEmpty()){
            s2.add(s1.pop());
        }
        //8、依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
        // (由于把s2定义为List, 8步骤省略了)

        return s2;
    }

    // 比较运算符优先级，优先级越高，返回值越大
    public static int priority(String oper) {
        if(oper.equals("*") || oper.equals("/")){
            return 1;
        } else if (oper.equals("+") || oper.equals("-")){
            return 0;
        } else {
            System.out.println("不存在该运算符："+oper);
            return  -1;  //默认只处理+-*/
        }
    }


    /**
     * 传入后缀表达式List
     * @param list
     * @return
     */
    public static int calculator(List<String> list){

        int res = 0;
        int num1,num2 = 0;

        Stack<String> stack = new Stack<String>();
        for (String item : list){

            if(item.matches("\\d+")){ //匹配的是多位数
                stack.push(item);
            } else {
                num2 = Integer.parseInt(stack.pop());
                num1 = Integer.parseInt(stack.pop());
                if (item.equals("+")){
                    res = num1 + num2;
                } else if (item.equals("-")){
                    res = num1 - num2;
                } else if (item.equals("*")){
                    res = num1 * num2;
                } else if (item.equals("/")){
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }

                stack.push(""+res);

            }

        }

        return Integer.parseInt(stack.pop());
    }
}
