package io.member.impl;

import io.member.Member;
import io.member.MemberRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectMemberRepository implements MemberRepository {

    private static final String FILE_PATH = "temp/members-obj.dat";

    /**
     * **직렬화**
     *  `ObjectOutputStream` 를 사용하면 객체 인스턴스를 직렬화해서 byte로 변경할 수 있다.
     *  우리는 회원 객체 하나가 아니라 회원 목록 전체를 파일에 저장해야 하므로 `members` 컬렉션을 직렬화 해야한다.
     *  `oos.writeObject(members)` 를 호출하면 `members` 컬렉션과 그 안에 포함된 `Member` 를 모두 직렬화해서 byte로 변경한다.
     *  그리고 `oos` 와 연결되어 있는 `FileOutputStream` 에 결과를 출력한다.
     */
    @Override
    public void add(Member member) {
        List<Member> members = findAll();
        members.add(member);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(members);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * **역직렬화**
     * `ObjectInputStream` 을 사용하면 byte를 역직렬화 해서 객체 인스턴스로 만들어 수 있다.
     * `Object findObject = ois.readObject()` 를 사용하면 역직렬화가 된다.
     *  이때 반환 타입이 `Object` 이므로 캐스팅해서 사용해야 한다.
     */
    @Override
    public List<Member> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object findObject = ois.readObject();

            return (List<Member>) findObject;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
