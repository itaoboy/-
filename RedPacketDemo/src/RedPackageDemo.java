import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RedPackageDemo {

    private static List<Integer> divideRedPackage(Integer totalAmount,Integer totalPeopleNum){
        List<Integer> amountList = new ArrayList<>();
        Integer restAmount = totalAmount;
        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();
        for (int i=0;i<totalPeopleNum-1;i++){
            // 随机范围:[1,剩余人均金额*2 - 1]分
            int amount = random.nextInt(restAmount/restPeopleNum * 2 -1)+1;
            restAmount -= amount;
            restPeopleNum --;
            amountList.add(amount);
        }
        amountList.add(restAmount);
        return amountList;
    }

    public static void main(String[] args) {
        int[] amountArr = new int[]{0,0,0,0,0};
        int num = 20;
        for (int i=0;i<num;i++){
            List<Integer> amountList = divideRedPackage(100,5);
            amountArr[0] += amountList.get(0);
            amountArr[1] += amountList.get(1);
            amountArr[2] += amountList.get(2);
            amountArr[3] += amountList.get(3);
            amountArr[4] += amountList.get(4);
            System.out.println(Arrays.toString(amountList.toArray()));
        }
        for (int i=0;i<amountArr.length;i++){
            System.out.println("第"+(i+1)+"人平均"+":"+(amountArr[i]/num));
        }
    }
}
