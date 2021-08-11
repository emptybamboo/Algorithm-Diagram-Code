package section6;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 广度优先搜索
 * 下面的小图说明了我早晨起床后要做的事情。
 * 该图指出，我不能没刷牙就吃早餐，因此“吃早餐”依赖于“刷牙”。
 * 另一方面，洗澡不依赖于刷牙，因为我可以先洗澡再刷牙。根据这个图，可创建一个列表，
 * 指出我需要按什么顺序完成早晨起床后要做的事情：
 * (1) 起床
 * (2) 洗澡
 * (3) 刷牙
 * (4) 吃早餐
 * 请注意，“洗澡”可随便移动，因此下面的列表也可行：
 * (1) 起床
 * (2) 刷牙
 * (3) 洗澡
 * (4) 吃早餐
 * 6.3 请问下面的三个列表哪些可行、哪些不可行？
 * 答,B可行,AC不可行
 * 6.4 下面是一个更大的图，请根据它创建一个可行的列表。
 * 起床-刷牙-吃早饭-锻炼-洗澡-穿衣服-打包午餐
 * 从某种程度上说，这种列表是有序的。如果任务A依赖于任务B，在列表中任务A就必须在任
 * 务B后面。这被称为拓扑排序，使用它可根据图创建一个有序列表。假设你正在规划一场婚
 * 礼，并有一个很大的图，其中充斥着需要做的事情，但却不知道要从哪里开始。这时就可使
 * 用拓扑排序来创建一个有序的任务列表。
 * 假设你有一个家谱
 * 这是一个图，因为它由节点（人）和边组成。其中的边从一个节点指向其父母，但所有的边
 * 都往下指。在家谱中，往上指的边不合情理！因为你父亲不可能是你祖父的父亲！
 * 这种图被称为树。树是一种特殊的图，其中没有往后指的边。
 * 6.5 请问下面哪个图也是树？
 * A,C都是树
 * @Author lvzhichao
 * @Date 2021/7/22 10:04
 */
public class BreadthFirstSearch {

    public static void main(String[] args) {
        Hashtable<String, List<String>> hashtable = new Hashtable<>();
        hashtable.put("埼玉", Stream.of("杰诺斯","吹雪","邦古").collect(Collectors.toList()));
        hashtable.put("杰诺斯", Stream.of("库赛诺","机械大猩猩").collect(Collectors.toList()));
        hashtable.put("吹雪", Stream.of("龙卷","睫毛","邦普").collect(Collectors.toList()));
        hashtable.put("邦古", Stream.of("饿狼","茶兰子","邦普").collect(Collectors.toList()));
        hashtable.put("库赛诺", new ArrayList<>());
        hashtable.put("机械大猩猩", new ArrayList<>());
        hashtable.put("龙卷", new ArrayList<>());
        hashtable.put("睫毛", new ArrayList<>());
        hashtable.put("邦普", new ArrayList<>());
        hashtable.put("饿狼", new ArrayList<>());
        hashtable.put("茶兰子", new ArrayList<>());

        search("埼玉",hashtable);
    }

    public static boolean search(String name,Hashtable<String, List<String>> hashtable){
        //空队列
        Queue<String> queue = new LinkedList<>();
        //从Hashtable中取到对应的List加入到队列中,不能直接把LIST加入,应该吧List的所有元素按个加入
        for (String s : hashtable.get(name)) {
            queue.add(s);
        }
        //已检查过
        ArrayList<Object> already = new ArrayList<>();
        //只要队列中的元素没被取光
        while (queue.size()>0) {
            System.out.println(already);
            //出队
            String remove = queue.remove();
            System.out.println(remove);
            //只要不是已经检查过的就继续检查
            if(!already.contains(remove)){
                //如果是卖家,就返回true
                if(isSeller(remove)){
                    System.out.println(name+"的关系网里有卖家,是"+remove);
                    return true;
                }else{
                    //如果不是卖家就把这个人的朋友添加到队列,把这个人记录为已检查
                    for (String s : hashtable.get(remove)) {
                        queue.add(s);
                    }
                    already.add(remove);
                }
            }
        }


        return false;
    }

    public static boolean isSeller(String name){
        return name.contains("邦");
    }
}
