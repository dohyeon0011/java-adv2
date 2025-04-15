package reflection;

import reflection.data.User;

import java.lang.reflect.Field;

/**
 * **리플렉션과 주의사항**
 * 리플렉션을 활용하면 `private` 접근 제어자에도 직접 접근해서 값을 변경할 수 있다. 하지만 이는 객체 지향 프로그래밍의 원칙을 위반하는 행위로 간주될 수 있다.
 * `private` 접근 제어자는 클래스 내부에서만 데이터를 보호하고, 외부에서의 직접적인 접근을 방지하기 위해 사용된다.(캡슐화)
 * 리플렉션을 통해 이러한 접근 제한을 무시하는 것은 캡슐화 및 유지보수성에 악영향을 미칠 수 있다.
 * 예를 들어, 클래스의 내부 구조나 구현 세부 사항이 변경될 경우 리플렉션을 사용한 코드는
 * 쉽게 깨질 수 있으며, 이는 예상치 못한 버그를 초래할 수 있다.
 * 따라서 리플렉션을 사용할 때는 반드시 신중하게 접근해야 하며, 가능한 경우 접근 메서드(예: getter, setter)를 사용 하는 것이 바람직하다.
 * 리플렉션은 주로 테스트나 라이브러리 개발 같은 특별한 상황에서 유용하게 사용되지만,
 * 일반적인 애플리케이션 코드에서는 권장되지 않는다. 이를 무분별하게 사용하면 코드의 가독성과 안전성을 크게 저하시킬 수 있다.
 */
public class FieldV2 {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user = new User("id1", "userA", 20);
        System.out.println("기존 이름 = " + user.getName());

        Class<? extends User> aClass = user.getClass();
        Field nameField = aClass.getDeclaredField("name");  // "name" 필드 조회

        // private 필드에 접근 허용, private 메서드도 이렇게 호출 가능
        // 리플렉션은 `private` 필드에 접근할 수 있는 특별한 기능을 제공한다.
        // 참고로 `setAccessible(true)` 기능은 `Method`도 제공한다. 따라서 `private` 메서드를 호출할 수도 있다.
        nameField.setAccessible(true);
        nameField.set(user, "userB");
        System.out.println("변경된 이름 = " + user.getName());
    }
}
