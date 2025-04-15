package reflection;

import reflection.data.BasicData;

public class BasicV1 {

    public static void main(String[] args) throws ClassNotFoundException {
        // 클래스 메타 데이터 조회 방법 3가지

        // 1. 클래스에서 찾기
        Class<BasicData> basicDataClass1 = BasicData.class;
        System.out.println("basicDataClass1 = " + basicDataClass1);

        // 2. 인스턴스에서 찾기(반환 타입을 보면 `Class<? extends BasicData>`로 표현되는데, 실제 인스턴스가 BasicData(부모) 타입일 수도 있지만, 그 자식 타입일 수도 있기 때문이다.)
        BasicData basicInstance = new BasicData();
        Class<? extends BasicData> basicDataClass2 = basicInstance.getClass();
        System.out.println("basicDataClass2 = " + basicDataClass2);

        // 3. 문자로 찾기
        String className = "reflection.data.BasicData"; // 패키지명 정확히 입력해야함
        Class<?> basicDataClass3 = Class.forName(className);
        System.out.println("basicDataClass3 = " + basicDataClass3);
    }
}
