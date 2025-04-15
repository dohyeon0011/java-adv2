package reflection.data;


/**
 * 클래스가 제공하는 다양한 정보를 동적으로 분석하고 사용하는 기능을 **리플렉션(Reflection)** 이라 한다.
 * 리플렉션을 통해 프로그램 실행 중에 클래스, 메서드, 필드 등에 대한 정보를 얻거나, 새로운 객체를 생성하고 메서드를 호출하며, 필드의 값을 읽고 쓸 수 있다.
 * 리플렉션을 통해 얻을 수 있는 정보는 다음과 같다.
 *  **클래스의 메타데이터**: 클래스 이름, 접근 제어자, 부모 클래스, 구현된 인터페이스 등.
 *  **필드 정보**: 필드의 이름, 타입, 접근 제어자를 확인하고, 해당 필드의 값을 읽거나 수정할 수 있다.
 *  **메서드 정보**: 메서드 이름, 반환 타입, 매개변수 정보를 확인하고, 실행 중에 동적으로 메서드를 호출할 수 있다.
 *  **생성자 정보**: 생성자의 매개변수 타입과 개수를 확인하고, 동적으로 객체를 생성할 수 있다.
 */
public class BasicData {

    public String publicField;
    private int privateField;

    public BasicData() {
        System.out.println("BasicData.BasicData");
    }

    private BasicData(String data) {
        System.out.println("BasicData.BasicData: " + data);
    }

    public void call() {
        System.out.println("BasicData.call");
    }

    public String hello(String str) {
        System.out.println("BasicData.hello");
        return str + " hello";
    }

    private void privateMethod() {
        System.out.println("BasicData. privateMethod");
    }

    void defaultMethod() {
        System.out.println("BasicData.defaultMethod");
    }

    protected void protectedMethod() {
        System.out.println("BasicData.protectedMethod");
    }
}
