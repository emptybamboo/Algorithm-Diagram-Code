package section4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 快速排序
 * 练习
 * 使用大O表示法时，下面各种操作都需要多长时间？
 * 4.5 打印数组中每个元素的值。 答:O(n)
 * 4.6 将数组中每个元素的值都乘以2。 答:O(n)
 * 4.7 只将数组中第一个元素的值乘以2。 答:O(1)
 * 4.8 根据数组包含的元素创建一个乘法表，即如果数组为[2, 3, 7, 8, 10]，首先将每个元素 答:O(n²)
 * 都乘以2，再将每个元素都乘以3，然后将每个元素都乘以7，以此类推。
 * @Author lvzhichao
 * @Date 2021/7/15 11:36
 */
public class QuickSort {
    public static void main(String[] args) {
        List<Integer> list = Stream.of(2, 66, 9, 13, 7, 67, 103, 256, 42).collect(Collectors.toList());
        //subList方法是左闭右开,会截取左边的元素不会截取右边的
        System.out.println(qsort(list));
//        for (int i = 0; i < 30; i++) {
//            System.out.println(random(10,0));
//        }
//        System.out.println(list.subList(0,3));
//        System.out.println(list);
//        System.out.println(Stream
//                .of(list.subList(0,0)
//                        ,Stream.of(1).collect(Collectors.toList())
//                        ,Stream.of(1,2).collect(Collectors.toList()))
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList()));
    }

    //快排
    public static List<Integer> qsort(List<Integer> list){
        Random rand = new Random();
        Integer pivot;
        List<Integer> startList = new ArrayList<>();
        List<Integer> midList;
        List<Integer> endList = new ArrayList<>();
        //基线条件
        if(list.size()<2){
            return list;
        }
        //递归条件
        else{
            //随机取基准值
            pivot = rand.nextInt(list.size());

            for (Integer integer : list) {
                //比基准值大的都归到右边的数组
                if(integer>list.get(pivot)){
                    endList.add(integer);
                }
                //比基准值小的都归到左边的数组
                if(integer<list.get(pivot)){
                    startList.add(integer);
                }
            }
            //将基准值封装为List
            midList = Stream.of(list.get(pivot)).collect(Collectors.toList());

            //将前中后三个数组合并返回,这里前后数组递归调用彻底快排
            return Stream
                    .of(qsort(startList),midList,qsort(endList))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());

        }
    }

}
