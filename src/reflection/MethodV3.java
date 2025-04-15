package reflection;

import reflection.data.Calculator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class MethodV3 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Scanner sc = new Scanner(System.in);
        System.out.print("호출 메서드: ");
        String methodName = sc.nextLine();

        System.out.print("숫자 1: ");
        int num1 = sc.nextInt();

        System.out.print("숫자 2: ");
        int num2 = sc.nextInt();

        Calculator calendar = new Calculator();
        
        // 호출할 메서드를 변수 이름을 이용해서 동적으로 선택
        Class<? extends Calculator> aClass = calendar.getClass();
        Method method = aClass.getMethod(methodName, int.class, int.class);

        Object returnValue = method.invoke(calendar, num1, num2);
        System.out.println("returnValue = " + returnValue);
    }
}
