package section7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @Author lvzhichao
 * @Date 2021/8/2 14:51
 */
public class Dickstra {
    //所有节点和权重关系散列表
    final static HashMap<String,HashMap<String,Integer>> graph = new HashMap<>();
    //开销散列表,由起点初始,实时更新
    final static HashMap<String,Integer> costs = new HashMap<>();
    //父节点标注散列表,实时更新
    final static HashMap<String,String> parents = new HashMap<>();
    //更优的开销
    static Integer new_cost;
    //当前开销
    static Integer cost;
    //当前节点所有邻居
    static HashMap<String,Integer> neighbors;
    //已处理节点
    static List<String> processed = new ArrayList<>();

    public static void main(String[] args) {

        HashMap<String,Integer> graph1 = new HashMap<>();
        graph1.put("a",6);
        graph1.put("b",2);

        HashMap<String,Integer> graph2 = new HashMap<>();
        graph2.put("fin",1);

        HashMap<String,Integer> graph3 = new HashMap<>();
        graph3.put("a",3);
        graph3.put("fin",5);

        graph.put("start",graph1);
        graph.put("a",graph2);
        graph.put("b",graph3);
        graph.put("fin",null);

        costs.put("a",6);
        costs.put("b",2);
        costs.put("fin", (int) Double.POSITIVE_INFINITY);

        parents.put("a","start");
        parents.put("b","start");
        parents.put("fin",null);


//        System.out.println(graph.keySet());
//        Set<String> strings = graph.keySet();
        //只要当前节点不是null就循环
        System.out.println(costs);
        getBest();
        System.out.println(costs);
    }

    public static void getBest(){
        //costs是起点开始到所有点的开销,自然也包含了每个节点,从全部节点中选出还未处理的节点中最低开销的那个
        String node = find_lowest_cost_node(costs);
        //如果挑选出的节点不为null,就一直循环,何时为null?自然是走完所有的节点到终点的时候为null
        while(null != node){
            //从开销列表获取当前挑选节点的开销
            cost = costs.get(node);
            //获取当前挑选节点的邻居
            neighbors = graph.get(node);
            //如果邻居不为空就循环遍历邻居,也是当邻居为终点时不进行
            if(null!=neighbors){
                for (String s : neighbors.keySet()) {
                    //当前这个节点的开销
                    // 加上
                    // 当前这个节点到邻居节点的开销
                    new_cost = cost + neighbors.get(s);
                    //如果当前节点到达该邻居更近,就更新开销,更新邻居父节点,并把当前节点加入已处理
                    //costs.get(s)是原本起点到达这个邻居节点的最优开销,而new_cost是从挑选的节点到达这个邻居节点的开销
                    //如果从当前挑选节点到邻居节点比之前从起点到邻居节点的最优选择开销更小更优则替换邻居节点的父节点和开销
                    //等于是优化了到达这个邻居节点的线路
                    if(costs.get(s)>new_cost){
                        costs.put(s,new_cost);
                        parents.put(s, node);
                    }
                }
            }
            //一波邻居的遍历后,等于检查完当前挑选节点的所有邻居,就把挑选节点加入已检查List
            processed.add(node);
            //再次从没检查过的所有节点中找到到达终点开销最小的那个,开始循环
            node = find_lowest_cost_node(costs);
        }

        printWay(parents);
        System.out.println("父节点散列表:"+parents);
    }

    public static String find_lowest_cost_node(HashMap<String, Integer> nodes){
        //设置正无穷大值为初始最低消耗,这样第一个挑选节点进来后可以保证开销一定小于这个值
        Integer lowest_cost = (int)Double.POSITIVE_INFINITY;
        //设置一个最低消耗节点为null
        String lowest_cost_node = null;
        for (String s : nodes.keySet()) {
            //获取开销散列表中当前轮询的节点的开销值
            cost = costs.get(s);
            //如果当前节点的开销小于最低开销并且当前节点不在已检查节点中
            //就把最低开销更新,最低节点更新为当前轮询节点
            //如果轮训完都没有一个符合,那就说明到终点了,最低开销节点就依旧是null,会被方法return回去
            //在getBest方法中的while循环中就不会开始循环,结束算法
            if(cost<lowest_cost && !processed.contains(s)){
                lowest_cost = cost;
                lowest_cost_node = s;
            }
        }
        return lowest_cost_node;
    }

    public static void printWay(HashMap<String,String> parents){
        String thisNode = parents.get("fin");
        List<String> printList = new ArrayList<>();
        printList.add("fin");
        printList.add(thisNode);
        while (printList.size()!=parents.size()+1){
            for (String s : parents.keySet()) {
                System.out.println(thisNode);
                if(null!=thisNode){
                    thisNode = parents.get(thisNode);
                    if(null!=thisNode){
                        printList.add(thisNode);
                    }
                }
            }
        }
        Collections.reverse(printList);
        StringBuilder builder = new StringBuilder("最短路径为:");
        printList.forEach(v->{
            builder.append(v).append("->");
        });
        System.out.println(builder);
    }
}
