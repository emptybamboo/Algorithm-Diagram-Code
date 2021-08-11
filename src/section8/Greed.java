package section8;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 贪婪算法
 * @Author lvzhichao
 * @Date 2021/8/5 15:08
 */
public class Greed {
    public static void main(String[] args) {
        //创建包含所有的set
        Set<String> areaSet = Stream.of("江北", "渝北", "沙坪坝", "南岸", "渝中", "九龙坡", "大渡口").collect(Collectors.toSet());
        //包含所有频道的散列表
        HashMap<String, Set> channelMap = new HashMap<>();
        channelMap.put("0001",Stream.of("江北", "渝北", "沙坪坝").collect(Collectors.toSet()));
        channelMap.put("0002",Stream.of("沙坪坝", "南岸", "渝中").collect(Collectors.toSet()));
        channelMap.put("0003",Stream.of("渝中", "九龙坡", "大渡口").collect(Collectors.toSet()));
        channelMap.put("0004",Stream.of("沙坪坝", "南岸").collect(Collectors.toSet()));
        channelMap.put("0005",Stream.of("九龙坡", "大渡口").collect(Collectors.toSet()));


        System.out.println(getChannel(channelMap,areaSet));
    }

    public static Set<String> getChannel(HashMap<String, Set> channelMap,Set<String> areaSet){
        //最终选定的频道
        Set<String> final_stations = new HashSet<>();
        while(areaSet.size()>0){
            String best_station = null;
            HashSet<String> states_covered = new HashSet<>();
            HashSet<String> cover = new HashSet<>();


            for (String station : channelMap.keySet()) {
                //清空中间Set拿来求当前遍历频道地区和所有地区的交集
                cover.clear();
                cover.addAll(areaSet);
                cover.retainAll(channelMap.get(station));

                if(cover.size()>states_covered.size()){
                    best_station = station;
                    //不能这样直接=赋予,这样等于是把地址值赋给states_covered
                    // 让这两个对象states_covered,cover指向了内存中同一个位置,会一起变动
                    //states_covered = cover;
                    //这样克隆后再赋值才可以
                    states_covered = (HashSet<String>) cover.clone();
                }
            }
            //只有在最后才减集,这样是以一次while循环减,不然一次for循环全减完了,还玩个屁
            areaSet.removeAll(states_covered);

            final_stations.add(best_station);
        }


        return final_stations;
    }
}
