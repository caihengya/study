package jedisCluster;

import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class jedisClusterTest {

   /* private static Jedis jedis;
    static {
        jedis = new Jedis(“ 192.168 .56 .180”,6379);
        jedis.auth(“123456”); // 之前我在redis配置中配置了权限密码，这里需要设置
    }*/
    @Test
    public void testKey() throws InterruptedException {
        System.out.println("清空数据：jedis.flushDB()");
        System.out.println("判断某个键是否存在：jedis.exists(key)");
        System.out.println("新增<K,V>的键值对：jedis.set(K, V)");
        System.out.println("是否存在:jedis.exists(K)");
        System.out.println("新增<K,V>的键值对：jedis.set(K, V)");

        System.out.println("系统中所有的键如下：keys --> Set keys = jedis.keys();");
        System.out.println("删除键K: jedis.del(K)");
        System.out.println("判断键K是否存在：jedis.exists(K)");
        System.out.println("设置键K的过期时间为5s: jedis.expire(K, 5)");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("keys：jedis.ttl(username)");
        System.out.println("移除键username的生存时间：jedis.persist(username)");
        System.out.println("keys：jedis.ttl(username)");
        System.out.println("查看键username所存储的值的类型：+jedis.type(username)");
    }
    @Test
    public void testString() throws InterruptedException {
        
    }
}
