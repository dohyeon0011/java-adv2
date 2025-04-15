package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class BasicV2 {

    public static void main(String[] args) {
        Class<BasicData> basicData = BasicData.class;

        System.out.println("basicData.getName() = " + basicData.getName()); // 패키지 경로 + 클래스 이름 반환
        System.out.println("basicData.getSimpleName() = " + basicData.getSimpleName()); // 클래스 이름만 반환
        System.out.println("basicData.getPackage() = " + basicData.getPackage());   // 패지키 경로 반환
        System.out.println();

        System.out.println("basicData.getSuperclass() = " + basicData.getSuperclass());
        System.out.println("basicData.getInterfaces() = " + Arrays.toString(basicData.getInterfaces()));
        System.out.println();

        System.out.println("basicData.isInterface() = " + basicData.isInterface());
        System.out.println("basicData.isEnum() = " + basicData.isEnum());
        System.out.println("basicData.isAnnotation() = " + basicData.isAnnotation());
        System.out.println();

        int modifiers = basicData.getModifiers();   // 수정자 정보
        System.out.println("basicData.modifiers() = " + modifiers); // 1
        System.out.println("Modifier.isPublic(modifiers) = " + Modifier.isPublic(modifiers));   // true
        System.out.println("Modifier.toString(modifiers) = " + Modifier.toString(modifiers));   // public
    }
}
