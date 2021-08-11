package section1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 二分查找
 * 练习
 * 1.1 假设有一个包含128个名字的有序列表，你要使用二分查找在其中查找一个名字，请
 * 问最多需要几步才能找到？
 * 答:7步
 * 1.2 上面列表的长度翻倍后，最多需要几步？
 * 答:8步
 * @Author lvzhichao
 * @Date 2021/7/13 9:55
 */
public class BinarySearch {
    public static void main(String[] args) {


        List<Integer> list = Stream
                .of(1,3,5,7,9,11,13,15,17,19)
                .collect(Collectors.toList());

        Integer guess = 7;

        System.out.println(getNum(list, guess));

    }

    public static Integer getNum(List<Integer> list, Integer item) {
        Integer min = 0;
        Integer max = list.size() - 1;
        //中间索引
        Integer mid;
        //猜测的数字
        Integer guess;
        //猜测次数
        Integer guessCount = 0;
        while (min <= max) {
            guessCount++;
            mid = (min + max) / 2;
            //获取中间索引对应的元素作为我们猜测的数字
            guess = list.get(mid);
            //如果猜测数字等于方法参数传来的数字,就返回mid索引
            if (item.equals(guess)) {
                System.out.println("一共猜测了"+guessCount+"次");
                return mid;
            }
            //如果猜测数字小于传来的数字,那就增大最小数字为猜测数字,缩小范围
            if (guess < item) {
                min = mid + 1;
            }
            //如果猜测数字大于传来的数字,那就缩小最大数字为猜测数字,缩小范围
            else {
                max = mid - 1;
            }

        }
        return null;
    }
}
