package section4;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 分而治之
 * 4.1 请编写前述sum函数的代码。
 * 4.2 编写一个递归函数来计算列表包含的元素数。
 * 4.3 找出列表中最大的数字。
 * 4.4 还记得第1章介绍的二分查找吗？它也是一种分而治之算法。你能
 * 找出二分查找算法的基线条件和递归条件吗？
 * @Author lvzhichao
 * @Date 2021/7/14 15:36
 */
public class DC {
    public static void main(String[] args) {
        List<Integer> list = Stream.of(1,6,8,20).collect(Collectors.toList());
//        System.out.println(getSum(list,0));
//        System.out.println(getTotal(list));
//        System.out.println(getMax(list,0,0));
        System.out.println(binarySearch(list,8,0,list.size()));
    }
//4.1 请编写前述sum函数的代码。
//    public static Integer getSum(List<Integer> list){
//        Integer sum = 0;
//        //备份数组长度
//        Integer size = list.size();
//        //按照初始数组元素数量循环
//        for (Integer i = 0; i < size; i++) {
//            //如果数组size不为0则递归,并且从数组中删除第一个元素
//            if(size>0){
//                sum += list.get(0);
//                list.remove(list.get(0));
//            }else{
//                //不然的话return
//                return sum;
//            }
//        }
//        return sum;
//    }
//    public static Integer getSum(List<Integer> list){
//        Integer sum = 0;
//
//
//        //基线条件
//        if(list.size()>0){
//           sum += list.get(0);
//           list.remove(list.get(0));
////           sum += getSum(list);
//            return sum + getSum(list);
//        }else {
//            return 0;
//        }
//
//    }
    //4.1 请编写前述sum函数的代码。
    public static Integer getSum(List<Integer> list,Integer index){

        //基线条件
        if(list.size()>index){
            return list.get(index) + getSum(list,++index);
        }else {
            return 0;
        }
    }

    //4.2 编写一个递归函数来计算列表包含的元素数。
    public static Integer getTotal(List<Integer> list){
        //基线条件
        if(list.size()==0){
            return 0;
        }
        //递归条件
        else {
            list.remove(0);
            return 1 + getTotal(list);
        }
    }

    //4.3 找出列表中最大的数字。
    public static Integer getMax(List<Integer> list,Integer index,Integer max){
        //基线条件
        if(list.size()==index+1){
            return max;
        }
        //递归条件
        else{
            return getMax(list,index+1,list.get(index)>list.get(index+1)?list.get(index):list.get(index+1));
        }
    }
    //4.4 还记得第1章介绍的二分查找吗？它也是一种分而治之算法。你能
    //找出二分查找算法的基线条件和递归条件吗？
    //基线条件就是猜测的数字等于给出的数字,递归条件就是
    public static Integer binarySearch(List<Integer> list, Integer item,Integer start,Integer end){
        if(list.size()==1){
            return list.get(0).equals(item)?list.get(0):null;
        }
        else if(item.equals(list.get((start+end)/2))){
            return list.get((start+end)/2);
        }
        else{
            start = list.get((start+end)/2)<item?start:((start+end)/2+1);
            end = list.get((start+end)/2)<item?((start+end)/2-1):end;
            return binarySearch(list.subList(start,end),item,start,end);
        }
    }
}
