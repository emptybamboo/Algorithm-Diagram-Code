package section3;

/**
 * 递归
 * @Author lvzhichao
 * @Date 2021/7/14 11:28
 */
public class Recursive {
    public static void main(String[] args) {
        countDown(8);
    }

    public static void countDown(Integer num){

        if(num>=0){
            System.out.println(num);
            //递归条件:调用自己
            countDown(num-1);
        }
        //基线条件:停止调用自己
        else{
            return;
        }
    }

}
