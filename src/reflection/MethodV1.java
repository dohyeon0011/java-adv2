package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Method;

public class MethodV1 {

    public static void main(String[] args) {
        Class<BasicData> helloClass = BasicData.class;

        System.out.println("===== methods() =====");
        Method[] methods = helloClass.getMethods(); // `getMethods()`: 해당 클래스와 상위 클래스에서 상속된 모든 public 메서드를 반환
        for (Method method : methods) {
            System.out.println("method = " + method);   // public만 반환
        }


        System.out.println("===== declaredMethods() =====");
        Method[] declaredMethods = helloClass.getDeclaredMethods(); // getDeclaredMethods()`: 해당 클래스에서 선언된 모든 메서드를 반환하며, 접근 제어자에 관계없이 반환. 상속된 메서드는 포함하지 않음
        for (Method declaredMethod : declaredMethods) {
            System.out.println("declaredMethod = " + declaredMethod);
        }
    }
}
