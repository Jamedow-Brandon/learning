package com.jamedow.learning.utils.numUtils;

/**
 * Created by yoyo on 2017/2/19.
 */
public class SortNumber {


    /**
     * 正序希尔排序（不稳定），步数为dk的为一组，每个元素与自己组内的数据进行直接插入排序  
     * @param a  待排序数
     * @param n  a长度
     * @param dk 步数
     */
    static private void ShellInsertSortPos(int a[], int n, int dk)
    {
            for (int j = dk; j < n; j++)
                    if (a[j] < a[j - dk])
                    {
                        int temp = a[j];
                        int k = j - dk;
                        while (k >= 0 && a[k] > temp) {
                                a[k + dk] = a[k];
                                k -= dk;
                        }
                        a[k + dk] = temp;
                    }

    }


    /**
     * 倒序希尔排序
     * @param a
     * @param n
     * @param dk
     */
    static private void ShellInsertSortIn(int a[], int n, int dk)
    {
        for (int j = dk; j < n; j++)
            if (a[j] > a[j - dk])
            {
                int temp = a[j];
                int k = j - dk;
                while (k >= 0 && a[k] < temp) {
                    a[k + dk] = a[k];
                    k -= dk;
                }
                a[k + dk] = temp;
            }

    }


    static int[] BubblePos ( int r[],int n){
            int low = 0;
            int high= n -1; //设置变量的初始值  
            int tmp,j;
            while (low < high) {
                    for (j = low; j< high; ++j) //正向冒泡,找到最大者  
                        if (r[j] > r[j+1]) {
                                tmp = r[j];
                                r[j] = r[j+1];
                                r[j+1] = tmp;
                            }
                    --high;                     //修改high值, 前移一位  
                    for(j = high;j > low;--j)   //反向冒泡,找到最小者  
                        if (r[j] < r[j-1]){
                            tmp = r[j];
                            r[j] = r[j-1];
                            r[j-1] = tmp;
                            }
                    ++low;                      //修改low值,后移一位  
                }
        return r;
    }

    static int[] BubbleIn ( int r[],int n){
        int low = 0;
        int high= n -1;
        int tmp,j;
        while (low < high) {
            for (j = low; j< high; ++j)
                if (r[j] < r[j+1]) {
                    tmp = r[j];
                    r[j] = r[j+1];
                    r[j+1] = tmp;
                }
            --high;
            for(j = high;j > low;--j)
                if (r[j] > r[j-1]){
                    tmp = r[j];
                    r[j] = r[j-1];
                    r[j-1] = tmp;
                }
            ++low;
        }
        return r;
    }


    /**
     * 插入排序—希尔排序，时间复杂度:O（n^1.3）,空间复杂度O（1）
     * @param a  待排数
     * @param order true为正序，false为倒序
     * @return
     */
    static int[] shellSort(int a[],boolean order){

            int n=a.length;
            int dk = n/2;
            if(order!=false) {
                while (dk >= 1) {
                    ShellInsertSortPos(a, n, dk);
                    dk = dk / 2;
                }
            }else {
                while (dk >= 1) {
                    ShellInsertSortIn(a, n, dk);
                    dk = dk / 2;
                }
            }
    return a;
    }


    /**
     * 冒泡排序,时间复杂度:O（n^2）,空间复杂度O（1）
     * @param a 待排数
     * @param order true为正序，false为倒序
     * @return
     */
    static int[] BubbleSort(int a[],boolean order){


        if(order!=false) {

            BubblePos(a,a.length);

        }else {
             BubbleIn(a,a.length);
        }
        return a;
    }



    public static void main(String []args){
            int a[] = {3,1,5,7,2,4,8,9};

            int b[]=shellSort(a,true);

            for(int j= 0; j<8; j++){
                System.out.print(a[j]+" ");
            }

    }
}
