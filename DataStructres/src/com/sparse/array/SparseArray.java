package com.sparse.array;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;

/**
 * @Author Liuyunda
 * @Date 2020/6/3 14:46
 * @Email man021436@163.com
 * @Description: DOTO
 */
public class SparseArray {
    public static void main(String[] args) throws IOException {
        // 先创建一个原始的二维数组11*11
        // 0：表示没有棋子，1：表示黑子，2：表示白子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][3] = 2;
        // 输出原始的二维数组
        System.out.println("----原始的二维数组----");
        for (int[] row : chessArr1){
            for (int data: row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        // 将二维数组转稀疏数组
        // 1. 先遍历二维数组得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if (chessArr1[i][j] != 0 ){
                    sum++;
                }
            }
        }
        // System.out.println("sum="+sum);

        // 2. 创建对应的稀疏数组
        int sparesArray[][] = new int[sum+1][3];
        // 给稀疏数组赋值
        sparesArray[0][0] = 11;
        sparesArray[0][1] = 11;
        sparesArray[0][2] = sum;

        // 遍历二维数组，将非零的值存放到稀疏数组中
        // count用来记录是第几个非零数据
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if (chessArr1[i][j] != 0 ){
                    count++;
                    sparesArray[count][0] = i;
                    sparesArray[count][1] = j;
                    sparesArray[count][2] = chessArr1[i][j];
                }
            }
        }
        // 保存稀疏数组到map.data中
        File file = new File("C:\\LYD\\IdeaProjects\\Java\\DataStructres\\HomeWork\\map.data");
        FileOutputStream f = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(f,"utf-8");


        // 输出稀疏数组
        System.out.println("-------------------------------------");
        System.out.println("-------------稀疏数组---------------");
        for (int i = 0; i < sparesArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparesArray[i][0],sparesArray[i][1],sparesArray[i][2]);
            // 写入
            if (i == sparesArray.length - 1) {
                writer.append(sparesArray[i][0] + "," + sparesArray[i][1] + "," + sparesArray[i][2]);
            } else {
                writer.append(sparesArray[i][0] + "," + sparesArray[i][1] + "," + sparesArray[i][2] + ",");
            }
        }
        // 关闭流
        writer.close();
        f.close();
        Desktop.getDesktop().open(file);

        // 读取map.data
        FileInputStream in = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(in,"utf-8");
        StringBuffer s = new StringBuffer();
        while (reader.ready()){
            s.append((char) reader.read());
        }
        System.out.println(s.toString());
        // 关闭流
        reader.close();
        in.close();
        // 拆分string
        String [] str = s.toString().split(",");
        // 初始化稀疏数组
        int sparesArr2[][] = new int[str.length/3][3];
        // 给稀疏数组赋值
        int x = 0;
        for(String s1 :str){
            sparesArr2[x/3][x%3] = Integer.parseInt(s1);
            x++;
        }
        System.out.println("稀疏数组赋值完毕");

        // 将稀疏数组恢复成原始的二维数组
        // 1.先读取稀疏数组的第一行，并创建原始的二维数组
        // int chessArr2[][] = new int[sparesArray[0][0]][sparesArray[0][1]];
        int chessArr2[][] = new int[sparesArr2[0][0]][sparesArr2[0][1]];
        // 2.给原始数组赋值
        for (int i = 1; i < sparesArray.length; i++) {
            chessArr2[sparesArr2[i][0]][sparesArr2[i][1]] = sparesArr2[i][2];
        }
        // 输出恢复后的二维数组
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr2){
            for (int data: row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
