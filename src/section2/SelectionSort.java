package section2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 选择排序
 * @Author lvzhichao
 * @Date 2021/7/13 17:18
 */
public class SelectionSort {
    public static void main(String[] args) {
        //创建一个乱序数组
        List<Integer> list = Stream.of(5, 66, 7, 63, 450, 65, 33, 4, 8, 102).collect(Collectors.toList());

        System.out.println(selectionSort(list));
    }

    //返回最小元素
    public static Integer finaSmallest(List<Integer> list){
        Integer minIndex = 0;
        Integer minValue = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            //只要有比我们当前轮询得到的最小值还小的值就赋给minValue,索引赋给minIndex
            if(list.get(i)<minValue){
                minIndex = i;
                minValue = list.get(i);
            }
        }
        return minIndex;
    }
    //对数组进行排序
    public static List<Integer> selectionSort(List<Integer> list){
        //创建一个空数组按顺序接收最小的数组元素
        List<Integer> integerList = new ArrayList<>();
        Integer minIndex;
        Integer size = list.size();
        //按元素个数为次数循环
        for (int i = 0; i < size; i++) {
            System.out.println("list:---"+list);
            //找到当前数组中最小的值的索引
            minIndex = finaSmallest(list);
            System.out.println("minIndex:---"+minIndex+":"+list.get(minIndex));
            //把最小值添加到新数组中
            integerList.add(list.get(minIndex));
            //把最小值在老数组中删除,下次遍历找到的最小值就是新的,remove方法是根据值删除元素而不是根据索引
            list.remove(list.get(minIndex));
        }

        return integerList;
    }
}
