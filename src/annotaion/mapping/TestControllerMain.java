package annotaion.mapping;

import java.lang.reflect.Method;

public class TestControllerMain {

    public static void main(String[] args) {
        TestController testController = new TestController();

        Class<? extends TestController> aClass = testController.getClass();
        for (Method method : aClass.getDeclaredMethods()) { // testController에 있는 모든 메서드 반환 받고, 모든 메서드를 반복문 돌려서
            System.out.println("method = " + method);
            SimpleMapping simpleMapping = method.getAnnotation(SimpleMapping.class);    // 해당 메서드에 붙어있는 어노테이션 value 값 추출
            if (simpleMapping != null) {
                System.out.println("[" + simpleMapping.value() + "] -> " + method);
            }
        }
    }
}
