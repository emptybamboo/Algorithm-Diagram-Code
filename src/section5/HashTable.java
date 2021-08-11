package section5;

import java.util.HashSet;

/**
 * 散列表
 * 练习
 * 散列函数的结果必须是均匀分布的，这很重要。它们的映射范围必须尽可能大。最糟糕的散
 * 列函数莫过于将所有输入都映射到散列表的同一个位置。
 * 假设你有四个处理字符串的散列函数。
 * A. 不管输入是什么，都返回1。
 * B. 将字符串的长度用作索引。
 * C. 将字符串的第一个字符用作索引。即将所有以a打头的字符串都映射到散列表的同一个位
 * 置，以此类推。
 * D. 将每个字符都映射到一个素数：a = 2，b = 3，c = 5，d = 7，e = 11，等等。对于给定的字
 * 符串，这个散列函数将其中每个字符对应的素数相加，再计算结果除以散列表长度的余数。
 * 例如，如果散列表的长度为10，字符串为bag，则索引为(3 + 2 + 17) % 10 = 22 % 10 = 2。
 * 在下面的每个示例中，上述哪个散列函数可实现均匀分布？假设散列表的长度为10。
 * 5.5 将姓名和电话号码分别作为键和值的电话簿，其中联系人姓名为Esther、Ben、Bob和
 * Dan。答:D
 * 5.6 电池尺寸到功率的映射，其中电池尺寸为A、AA、AAA和AAAA。答:B D
 * 5.7 书名到作者的映射，其中书名分别为Maus、Fun Home和Watchmen。答:C B D
 * @Author lvzhichao
 * @Date 2021/7/19 16:20
 */
public class HashTable {
    static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) {
        vote("张三");
        vote("李四");
        vote("王老五");
        vote("张三");
        vote("王老五");
    }

    public static void vote(String name){
        if(set.contains(name)){
            System.out.println("已经投过票了");
        }else{
            System.out.println("恭喜你投票成功");
            set.add(name);
        }
    }
}
