package src.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @program: test.test
 * @description: 支付相关的测试 金额相关的测试
 * @author: Mr.ch
 * @create: 2020-06-08
 **/
public class PayTest {

    /**
     * String类型的金额 四舍五入
     * 使用字符串格式化实现四舍五入(支持float和double类型)
     */
    @Test
    public void StringMoneyRound() {

        double data = 3.0255;
        //利用字符串格式化的方式实现四舍五入,保留1位小数
        String result = String.format("%.2f", data);
        //1代表小数点后面的位数, 不足补0。f代表数据是浮点类型。保留2位小数就是“%.2f”，依此累推。
        System.out.println(result);
    }

    /**
     * 使用BigDecimal实现四舍五入(支持float和double类型)
     * BigDecimal类型金额 四舍五入
     * RoundingMode.CEILING：取右边最近的整数
     * RoundingMode.DOWN：去掉小数部分取整，也就是正数取左边，负数取右边，相当于向原点靠近的方向取整
     * RoundingMode.FLOOR：取左边最近的正数
     * RoundingMode.HALF_DOWN:五舍六入，负数先取绝对值再五舍六入再负数
     * RoundingMode.HALF_UP:四舍五入，负数原理同上
     * RoundingMode.HALF_EVEN:这个比较绕，整数位若是奇数则四舍五入，若是偶数则五舍六入
     */
    @Test
    public void BigDecimalMoneyRound() {
        BigDecimal bigDecimal = new BigDecimal("20.22111").setScale(2, RoundingMode.HALF_UP);
        String s = bigDecimal.toString();
        System.out.println(s);
        System.out.println(bigDecimal);
        BigDecimal bigDecimal1 = new BigDecimal("20.22555").setScale(2, RoundingMode.HALF_UP);
        String s1 = bigDecimal1.toString();
        System.out.println(s1);
        System.out.println(bigDecimal1);

        double data = 3.02;
        //利用BigDecimal来实现四舍五入.保留一位小数
        double result = new BigDecimal(data).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        //1代表保留1位小数,保留两位小数就是2,依此累推
        //BigDecimal.ROUND_HALF_UP 代表使用四舍五入的方式
        System.out.println(result);//输出3.0
    }

    /**
     * 使用DecimalFormat实现四舍五入(仅支持float类型)
     */
    @Test
    public void DecimalFormatMoneyRound() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        //保留2位小数，.后面的#代表小数点后面的位数,保留3位小数就是#.###
        System.out.println(decimalFormat.format(3.065f));
        System.out.println(decimalFormat.format(3.065));
    }

    /**
     * 两个金额大小判断
     */
    @Test
    public void tt() {
        BigDecimal balance = new BigDecimal("4.11");

        //判断用户钱包是否够用
        if (balance.compareTo(new  BigDecimal("5.11")) > -1) {
            System.out.println("1111");
        }else {
            System.out.println("22222");
        }
    }

    /**
     * 判断某个金额是否为0
     */
    @Test
    public void zero(){
        if(new BigDecimal("0.00").compareTo(BigDecimal.ZERO)==0){
            System.out.println("0");
        }
    }

    /**
     * 判断某个金额是否为0
     */
    @Test
    public void moreZero(){

        /*Money totalAmount = Money.of(CurrencyUnit.of("CNY"), commodityTotalAmount);
        totalAmount.minus(Money.of(CurrencyUnit.of("CNY"), couponAmount));*/

        if ((new BigDecimal("30.00")).compareTo(BigDecimal.ZERO) > 0){
            System.out.println("大于0");
        }

    }

}
