package com.lyq.sparsearray;

/**
 * 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {

        //1、构建二维数组
        int dim2Array[][] = new int[6][7];
        dim2Array[0][3] = 22;
        dim2Array[0][6] = 15;
        dim2Array[1][1] = 11;
        dim2Array[1][5] = 17;
        dim2Array[2][3] = -6;
        dim2Array[3][5] = 39;
        dim2Array[4][0] = 91;
        dim2Array[5][2] = 28;

        //打印二维数组
        for(int[] row : dim2Array){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //2、二维数组转为稀疏数组
        //2.1、获取二维数组的有效数值个数
        int valueNum = 0;
        for(int i = 0; i< dim2Array.length; i++){
            for (int j = 0; j< dim2Array[0].length; j++){
                if (0 != dim2Array[i][j]){
                    valueNum ++;
                }
            }
        }
        System.out.println("二维数组有效数值个数："+valueNum);

        //2.2、创建稀疏数组
        int sparseArray[][] = new int[valueNum+1][3];
        //2.3、给稀疏数组赋值
        //给稀疏数组第一行赋值
        sparseArray[0][0] = dim2Array.length;
        sparseArray[0][1] = dim2Array[0].length;
        sparseArray[0][2] = valueNum;

        //遍历赋值
        int count = 0;
        for(int i = 0; i< dim2Array.length; i++){
            for (int j = 0; j< dim2Array[0].length; j++){
                if (0 != dim2Array[i][j]){
                    count ++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = dim2Array[i][j];
                }
            }
        }
        //打印得到的稀疏数组
        System.out.println("打印得到的稀疏数组：");
        for (int i=0;i<sparseArray.length;i++){
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }

        //3、稀疏数组转化为正常二维数组
        //3.1、根据稀疏数组的第一行创建普通二维数组
        int simpleDim2Array[][]  = new int[sparseArray[0][0]][sparseArray[0][1]];
        //3.2、遍历给二维数组赋值
        for (int i = 1;i<sparseArray.length;i++) {
            simpleDim2Array[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        //打印恢复后的二维数组
        System.out.println("打印恢复后的二维数组：");
        for(int[] row : simpleDim2Array){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

    }
}
