package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConstructV2 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> aClass = Class.forName("reflection.data.BasicData");

        Constructor<?> constructor = aClass.getDeclaredConstructor(String.class);   // 매개변수로 String을 사용하는 생성자 조회
        constructor.setAccessible(true);
        Object instance = constructor.newInstance("hello"); // 찾은 생성자에 "hello" 인자 넘기기
        System.out.println("instance = " + instance);

        Method method1 = aClass.getDeclaredMethod("call");  // 처음에 생성한 인스턴스에 "call" 이라는 이름의 메서드를 동적으로 찾아서 호출.
        method1.invoke(instance);
    }
}
