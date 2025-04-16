package reflection;

import java.lang.reflect.Constructor;

public class ConstructV1 {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("reflection.data.BasicData");   // 클래스 정보를 동적 조회

        System.out.println("===== constructors() =====");
        Constructor<?>[] constructors = aClass.getConstructors();   // 자신에게 있는 모든 public 생성자만 반환
        for (Constructor<?> constructor : constructors) {
            System.out.println("constructor = " + constructor);
        }

        System.out.println("===== declaredConstructors() =====");
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();   // 자신에게 있는 모든 private 생성자까지 모두 반환
        for (Constructor<?> constructor : declaredConstructors) {
            System.out.println("constructor = " + constructor);
        }
    }
}
