import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @program: test.test
 * @description: 集合容器大小验证
 * @author: Mr.ch
 * @create: 2020-06-22
 **/
public class ListContainerSize {
    public static void main(String[] args) {
        capacity();
    }

    /**
     * size 为集合内元素的个数 不是容器大小
     * 验证map集合容器大小capacity 必须是2的幂次方
     * 传入值 会用位运算( n >>>) 计算与他最近的2的幂次方数子 返回
     * 当传入134时 计算得到255+1 (return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;)
     */
    private static void capacity() {
        try {
            HashMap<Object, Object> objectObjectHashMap = new HashMap<>(8);
            objectObjectHashMap.put(1, 1);
            objectObjectHashMap.put(2, 2);
            objectObjectHashMap.put(3,2);
            objectObjectHashMap.put(4,2);
            Class<?> mapType = objectObjectHashMap.getClass();
            Method capacity = mapType.getDeclaredMethod("capacity");
            capacity.setAccessible(true);
            System.out.println("capacity : " + capacity.invoke(objectObjectHashMap) + "    size : " + objectObjectHashMap.size());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
