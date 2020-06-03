package com.queue;

import java.util.Scanner;

/**
 * @Author Liuyunda
 * @Date 2020/6/3 16:48
 * @Email man021436@163.com
 * @Description: DOTO
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        // 创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int val = scanner.nextInt();
                    arrayQueue.addQueue(val);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.println("取出的数据是："+res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.println("队列头数据是："+res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }
        System.out.println("程序退出");
    }
}

/**
 * 使用数组模拟队列，编写ArrayQueue类
 */
class ArrayQueue{
    //数组最大容量
    private int maxSize;
    //队列头
    private int front;
    // 队列尾
    private int rear;
    // 该数据用于存放数据，模拟队列
    private int[] arr;
    public ArrayQueue(int arrMaxSize){
        this.maxSize = arrMaxSize;
        this.arr = new int[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    /**
     * 判断队列是否满
     * @return
     */
    public boolean isFull(){
        return rear == maxSize-1;
    }

    /**
     * 判断队列是否空
     * @return
     */
    public boolean isEmpty(){
        return rear == front;
    }

    /**
     * 添加数据到队列
     * @param n
     */
    public void addQueue(int n){
        // 判断队列是否满
        if(isFull()){
            System.out.println("队列已满不能添加数据");
            return;
        }
        // 让rear后移
        rear++;
        arr[rear] = n;
    }

    /**
     * 获取队列的数据，出队列
     * @return
     */
    public int getQueue(){
        // 判断队列是否空
        if (isEmpty()){
            // 通过抛异常处理
            throw new RuntimeException("队列为空不能取数据");
        }
        front++;
        return arr[front];
    }

    /**
     * 显示队列数据
     */
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    /**
     * 显示队列头数据
     * @return
     */
    public int headQueue(){
        if (isEmpty()){
            // 通过抛异常处理
            throw new RuntimeException("队列为空");
        }
        return arr[front+1];
    }
}
