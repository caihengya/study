package test;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @program: test.test
 * @description:
 * @author: Mr.ch
 * @create: 2020-05-13
 **/
public class test {


    public static void main(String[] args) {
      /*  BigDecimal max = new BigDecimal("1000.0");
        if (max.compareTo(new BigDecimal("1000.0")) <= 0) {
            System.out.println("dayudengyu0");
        } else {
            System.out.println("xiaoyu0");
        }*/
      /* String idCard="411481199512284819";
        String password = idCard.substring(idCard.length()-8);
        System.out.println(password);*/


        if (new BigDecimal("0.25").compareTo(new BigDecimal("0.5")) <= 0) {
            System.out.println("充足");
        }
       /* //分转元
        BigDecimal payAmount = new BigDecimal("0.00");
        *//*feeNo = feeNo.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        System.out.println(feeNo);
*//*
        //分转元
        if (payAmount.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("报错");
        }*/
    }


    public int f(int n, int m){
        return n == 1 ? n : (f(n - 1, m) + m - 1) % n + 1;
    }
    @Test
    public void test1(){
        int f = f(6, 3);
        System.out.println(f);
    }
}
