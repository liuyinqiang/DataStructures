package com.lyq.recursion;

public class MiGong {
    public static void main(String[] args) {

        // map代表地图  8行7列
        int[][] map = new int[8][7];

        // 先设置围墙
        // 第1行、第8行 都是围墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 第1列、第7列 都是围墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 第4行、第2、3列 也是围墙
        map[3][1] = 1;
        map[3][2] = 1;


        //打印地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }

        setway(map,1,1);

        //打印地图
        System.out.println("招路后，地图：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }

    }


    // 使用递归回溯来给小球找路
    // 说明：
    // 1、map表示地图
    // 2、i,j 表示从地图的哪个位置开始出发(1,1)
    // 3、如果小球能到map[6][5]位置，则说明通路找到
    // 4、约定，当map[i][j]为0表示该点没有走过，1表示墙，2表示可以走，3表示已经走过，但是走不通
    // 5、走迷宫时，需要确定一个策略（方法）下->右->上->左，如果该点走不通，再回溯

    /**
     *
     *
     * @param map 表示地图
     * @param i 从哪个位置开始找
     * @param j
     * @return 如果找到通过，返回true，否则返回false
     */
    public static boolean setway(int[][] map,int i, int j){
        if (map[6][5] == 2){
            return true;
        }else {
            if(map[i][j] == 0) {
                map[i][j] = 2;

                if(setway(map,i+1,j)){
                    return true;
                } else if(setway(map,i,j+1)){
                    return true;
                } else if(setway(map,i-1,j)){
                    return true;
                } else if(setway(map,i,j-1)){
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }

        }
    }
}
