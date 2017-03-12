package com.jamedow.learning.text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yoyo on 2017/3/10.
 */
public class SudokuTest2 {



    public static void main(String[] args) {
        //数组
        int[][] soduku = new int[9][9];
        //生成数据
        getData(soduku);
//     print(soduku);
        //修改数据
        changeData(soduku);
        //打印
        print(soduku);

//       System.out.println(getValueList());

    }


    private static int currentCount; //当前循环次数

    /**
     *          * 修改内容
     *          * @param soduku
     *          
     */

    private static void changeData(int[][] soduku) {
        //获得一个数字
        for (int m = 0; m < soduku.length; m++) {
            lineNumList = getValueList(); //随机生成数据
            //获得一行数据
            currentCount = 0;
            for (int i = 0; i < lineNumList.size(); i++) {
                int num = lineNumList.get(i);
                //查询是否已经使用，当前之前
                if (findHasValue(soduku, m, i, num)) {
                    //将当前添加到最后
                    lineNumList.add(lineNumList.remove(i));
                    i--;
                    if (currentCount > lineNumList.size()) { //数据不符合
                        m--; //重新来
                        break;
                    }
                    currentCount++;
                    continue;
                }

                //交换与之后的
                changeValue(soduku, m, i, num);
            }
//                        System.out.println(m + " ## " + lineNumList);
//                        print(soduku);
        }

    }


    private static void changeValue(int[][] soduku, int currentRow, int currentCols, int num) {
        int endRow = (currentRow / 3 + 1) * 3;
        int endCols = (currentCols / 3 + 1) * 3;
        for (int i = currentRow; i < endRow; i++) {
            int startCols = currentCols / 3 * 3;
            if (i == currentRow) {
                startCols = currentCols;
            }
            for (int j = startCols; j < endCols; j++) {
                int temp = soduku[i][j];
                if (temp == num) {
                    soduku[i][j] = soduku[currentRow][currentCols];
                    soduku[currentRow][currentCols] = temp;
                }
            }
        }
    }

    /**
     *          * 查询当前的之前是否使用过num
     *          * @param soduku
     *          * @param currentRow
     *          * @param currentCols
     *          * @param num
     *          * @return
     *          
     */


    private static boolean findHasValue(int[][] soduku, int currentRow, int currentCols, int num) {
        return findAreaValue(soduku, currentRow, currentCols, num)
                || findRowValue(soduku, currentRow, currentCols, num)
                || findColsValue(soduku, currentRow, currentCols, num);
    }

    /**
     *          * 当前行查询
     *          * @param soduku
     *          * @param currentRow
     *          * @param currentCols
     *          * @param num
     *          * @return
     *          
     */


    private static boolean findRowValue(int[][] soduku, int currentRow, int currentCols, int num) {
        for (int i = 0; i < currentCols; i++) {
            int exists = soduku[currentRow][i];
            if (exists == num) {
                return true;
            }
        }
        return false;
    }


    /**
     *          * 当前列查询
     *          * @param soduku
     *          * @param currentRow
     *          * @param currentCols
     *          * @param num
     *          * @return
     *          
     */


    private static boolean findColsValue(int[][] soduku, int currentRow, int currentCols, int num) {
        for (int i = 0; i < currentRow; i++) {
            int exists = soduku[i][currentCols];
            if (exists == num) {
                return true;
            }
        }
        return false;
    }


    /**
     *          * 当前区域查询
     *          * @param soduku
     *          * @param currentRow
     *          * @param currentCols
     *          * @param num
     *          * @return
     *          
     */


    private static boolean findAreaValue(int[][] soduku, int currentRow, int currentCols, int num) {
        //currentRow 1  currentCols 5
        int startRow = currentRow / 3 * 3;  //3
        int startCols = currentCols / 3 * 3;
        for (int i = startRow; i <= currentRow; i++) {  // 345
            int cols = startCols + 3;
            if (i == currentRow) {
                cols = currentCols;
            }
            for (int j = startCols; j < cols; j++) {
                int exists = soduku[i][j];
                if (exists == num) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     *          * 生成数据
     *          * @param soduku
     *          
     */


    private static void getData(int[][] soduku) {
        for (int m = 0; m < 9; m++) {
            for (int n = 0; n < 9; n++) {
                soduku[m][n] = (m % 3) * 3 + (n % 3) + 1;
            }
        }
        for (int m = 0; m < 9; m++) {
            for (int n = 0; n < 9; n++) {
                System.out.print(soduku[m][n]);
            }
            System.out.println("");
        }
    }


    private static List<Integer> lineNumList;


    private static List<Integer> getValueList() {
        Random random = new Random();
        List<Integer> list = new ArrayList<Integer>();
        while (list.size() < 9) {
            //保证行数据唯一
            int num = random.nextInt(10);
            if (!list.contains(num) && num > 0) {
                list.add(num);
            }
        }

        System.out.println(list);
        return list;
    }

    /**
     *          * 输出
     *          * @param soduku
     *          
     */


    private static void print(int[][] soduku) {
        for (int m = 0; m < 9; m++) {
            for (int n = 0; n < 9; n++) {
                System.out.print(soduku[m][n]);
                if (((n + 1) % 3 == 0) && n < 8) {
                    System.out.print(" | ");
                }
            }
            if (((m + 1) % 3 == 0) && m < 8) {
                System.out.println();
                for (int i = 0; i < 15; i++) {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
        System.out.println("#############");
    }

}
