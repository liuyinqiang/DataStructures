package com.lyq.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "180+2*6-5/5";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //将每次扫描得到char保存到ch
        String keepNum = "";

        while (true){

            ch = expression.substring(index,index+1).charAt(0);

            if (operStack.isOper(ch)){ //如果是运算符

                //如果运算符栈非空 && 得到的运算符的优先级 小于等于 栈中的运算符的优先级
                if(!operStack.isEmpty() && (operStack.priority(ch) <= operStack.priority(operStack.peek()))){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();

                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);

                }

                operStack.push(ch);

            } else {

                //numStack.push(ch - 48); //得到的ch 是 '1' 而非 1 ，两者相差48

                keepNum += ch;
                if (index == expression.length() - 1 ){

                    numStack.push(Integer.parseInt(keepNum));

                } else {

                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){

                        numStack.push(Integer.parseInt(keepNum));

                        keepNum = "";
                    }
                }
            }

            index ++;

            if (index >= expression.length()){
                break;
            }

        }


        while (true){

            if(operStack.isEmpty()){
                break;
            }

            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();

            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }

        res = numStack.pop();
        System.out.printf("计算%s = %d", expression, res);

    }
}


class ArrayStack2{
    private int maxSize; //栈的大小
    private int[] stack; //数组，数组模拟栈，数据存放到数组中
    private int top = -1; //栈顶，初始化为-1

    public ArrayStack2(int maxSize){
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

    //查看栈顶的数据
    public int peek(){
        return stack[top];
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

    // 比较运算符优先级，优先级越高，返回值越大
    public int priority(int oper) {
        if(oper == '*' || oper == '/'){
            return 1;
        } else if (oper == '+' || oper == '-'){
            return 0;
        } else {
            return  -1;  //默认只处理+-*/
        }
    }

    // 是否是运算符
    public boolean isOper(char oper){
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    public int cal(int num1, int num2, int oper){
        int res = 0; //返回值
        switch (oper){

            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }

        return res;

    }





}