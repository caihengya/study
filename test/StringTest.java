package test;

import org.junit.Test;

/**
 * @program: test.test
 * @description: 字符串测试类
 * @author: Mr.ch
 * @create: 2020-06-22
 **/
public class StringTest {

    @Test
    public  void subTest(){
        String str = "enterVideoList?result={jsonString}";

        //截取?之前字符串
        String str1 = str.substring(0, str.indexOf("?"));
        System.out.println(str1);
        int i = str.lastIndexOf("?");
        //截取?之后字符串
        String str2 = str.substring(str1.length() + 1, str.length());
        System.out.println(str2);
        String str3 = str.substring(str.lastIndexOf("?") + 1);
        System.out.println(str3);
        String plugNum = "enterVideoList?result={jsonString}";
        String substring = plugNum.substring(plugNum.lastIndexOf("?") + 1);
        System.out.println(substring);
    }
}
