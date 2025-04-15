package reflection;

import reflection.data.BasicData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodV2 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 정적 메서드 호출 - 일반적인 메서드 호출
        BasicData helloInstance = new BasicData();
        helloInstance.call();   // 이 부분은 코드를 변경하지 않는 이상 정적이다.(항상 무조건 helloInstance 인스턴스의 call()이 호출되니까)

        // 동적 메서드 호출 - 리플렉션 사용
        Class<? extends BasicData> helloClass = helloInstance.getClass();
        String methodName = "hello";

        // 메서드 이름을 변수로 변경할 수 있다.
        Method method1 = helloClass.getDeclaredMethod(methodName, String.class);    // hello 메서드는 찾았지만, 어느 인스턴스에 있는 메서드인지는 모름.
        System.out.println("method1 = " + method1);
        Object returnValue = method1.invoke(helloInstance, "hi");   // helloInstance에 있는 hello 메서드를 찾아서 "hi" 인자를 넘기고 실행함.
        System.out.println("returnValue = " + returnValue);
    }
}
