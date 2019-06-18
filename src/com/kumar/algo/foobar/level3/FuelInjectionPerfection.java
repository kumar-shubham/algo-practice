import java.math.BigInteger;

public class FuelInjectionPerfection {

    public static void main(String[] args) {

        int result = solution("15");
        System.out.println(result);
    }

    public static int solution(String x) {

        BigInteger number = new BigInteger(x);
        BigInteger num = new BigInteger(x);
        int count = 0;
        while(num.compareTo(BigInteger.ONE) > 0){
            num = num.divide(BigInteger.TWO);
            count++;
        }

//        System.out.println(count);

        if(BigInteger.TWO.pow(count).compareTo(number) == 0){
            return count;
        }
        BigInteger bigger = BigInteger.TWO.pow(++count);
        BigInteger smaller = bigger.divide(BigInteger.TWO);

//        System.out.println(bigger);
//        System.out.println(smaller);

        BigInteger diff = bigger.subtract(number);
        if(diff.compareTo(number.subtract(smaller)) > 0){
            diff = number.subtract(smaller);
            count--;
        }

        int result = diff.intValue()+count;


        return result;
    }
}
