package com.jamedow.learning.utils.numUtils;

/**
 * Created by yoyo on 2017/2/19.
 */
public class SortNumber {


    /**
     * 正序希尔排序（不稳定），步数为dk的为一组，每个元素与自己组内的数据进行直接插入排序  
     *
     * @param a  待排序数
     * @param n  a长度
     * @param dk 步数
     */
    private void ShellInsertSortPos(int a[], int n, int dk) {
        for (int j = dk; j < n; j++)
            if (a[j] < a[j - dk]) {
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
     *
     * @param a
     * @param n
     * @param dk
     */
    private void ShellInsertSortIn(int a[], int n, int dk) {
        for (int j = dk; j < n; j++)
            if (a[j] > a[j - dk]) {
                int temp = a[j];
                int k = j - dk;
                while (k >= 0 && a[k] < temp) {
                    a[k + dk] = a[k];
                    k -= dk;
                }
                a[k + dk] = temp;
            }

    }


    int[] BubblePos(int r[], int n) {
        int low = 0;
        int high = n - 1; //设置变量的初始值  
        int tmp, j;
        while (low < high) {
            for (j = low; j < high; ++j) //正向冒泡,找到最大者  
                if (r[j] > r[j + 1]) {
                    tmp = r[j];
                    r[j] = r[j + 1];
                    r[j + 1] = tmp;
                }
            --high;                     //修改high值, 前移一位  
            for (j = high; j > low; --j)   //反向冒泡,找到最小者  
                if (r[j] < r[j - 1]) {
                    tmp = r[j];
                    r[j] = r[j - 1];
                    r[j - 1] = tmp;
                }
            ++low;                      //修改low值,后移一位  
        }
        return r;
    }

    int[] BubbleIn(int r[], int n) {
        int low = 0;
        int high = n - 1;
        int tmp, j;
        while (low < high) {
            for (j = low; j < high; ++j)
                if (r[j] < r[j + 1]) {
                    tmp = r[j];
                    r[j] = r[j + 1];
                    r[j + 1] = tmp;
                }
            --high;
            for (j = high; j > low; --j)
                if (r[j] > r[j - 1]) {
                    tmp = r[j];
                    r[j] = r[j - 1];
                    r[j - 1] = tmp;
                }
            ++low;
        }
        return r;
    }

    /**
     * 正序快速排序
     *
     * @param a
     * @param l
     * @param r
     */
    private void quickPos(int a[], int l, int r) {
        if (l < r) {
            int i = l, j = r, temp = a[l];
            while (i < j) {
                while (i < j && a[j] >= temp)
                    j--;

                if (i < j)
                    a[i++] = a[j];

                while (i < j && a[i] < temp)
                    i++;

                if (i < j)
                    a[j--] = a[i];
            }
            a[i] = temp;
            quickPos(a, l, i - 1);
            quickPos(a, i + 1, r);
        }
    }

    private void quickIn(int a[], int l, int r) {
        if (l < r) {
            int i = l, j = r, temp = a[l];
            while (i < j) {
                while (i < j && a[j] <= temp)
                    j--;

                if (i < j)
                    a[i++] = a[j];

                while (i < j && a[i] > temp)
                    i++;

                if (i < j)
                    a[j--] = a[i];
            }
            a[i] = temp;
            quickIn(a, l, i - 1);
            quickIn(a, i + 1, r);
        }
    }


    /**
     * 插入排序—希尔排序，时间复杂度:O（n^1.3）,空间复杂度O（1），不稳定
     *
     * @param a     待排数
     * @param order true为正序，false为倒序
     * @return
     */
    int[] shellSort(int a[], boolean order) {

        int n = a.length;
        int dk = n / 2;
        if (order != false) {
            while (dk >= 1) {
                ShellInsertSortPos(a, n, dk);
                dk = dk / 2;
            }
        } else {
            while (dk >= 1) {
                ShellInsertSortIn(a, n, dk);
                dk = dk / 2;
            }
        }
        return a;
    }


    /**
     * 冒泡排序,时间复杂度:O（n^2）,空间复杂度O（1）,稳定
     *
     * @param a     待排数
     * @param order true为正序，false为倒序
     * @return
     */
    int[] bubbleSort(int a[], boolean order) {


        if (order != false) {

            BubblePos(a, a.length);

        } else {
            BubbleIn(a, a.length);
        }
        return a;
    }



    /**
     * 快速排序，时间复杂度:O（nlog2（n））,空间复杂度O（nlog2（n）），不稳定
     * @param a 待排数
     * @param order true为正序，false为倒序
     * @return
     */
    int[] quickSort(int a[],boolean order){

        if(order!=false){

            quickPos(a,0,a.length-1);

        }else{

            quickIn(a,0,a.length-1);
        }
        return a;
    }




}
