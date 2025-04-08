package io.streams;

import java.io.*;

public class DataStreamEtcMain {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/data.txt");
        DataOutputStream dos = new DataOutputStream(fos);

        /**
         * 저장한 `data.dat` 파일을 직접 열어보면 제대로 보이지 않는다.
         * 왜냐하면 `writeUTF()` 의 경우 UTF-8 형식으로 저장하지만, 나머지의 경우 문자가 아니라 각 타입에 맞는 byte 단위로 저장하기 때문이다.
         * 예를 들어서 자바에서 int는 4byte를 묶어서 사용한다. 해당 byte가 그대로 저장되는 것이다.
         * 텍스트 편집기는 자신의 문자 집합을 사용해서 byte를 문자로 표현하려고 시도하지만 문자 집합에 없는 단어이거나
         * 또는 전혀 예상하지 않은 문자로 디코딩 될 것이다.
         */
        dos.writeUTF("회원 A");
        dos.writeInt(20);
        dos.writeDouble(10.5);
        dos.writeBoolean(true);

        dos.close();

        FileInputStream fis = new FileInputStream("temp/data.txt");
        DataInputStream dis = new DataInputStream(fis);

        System.out.println(dis.readUTF()); // 반드시 읽을 때는 저장한 순서대로 읽어야만 올바른 데이터가 조회됨.
        System.out.println(dis.readInt());
        System.out.println(dis.readDouble());
        System.out.println(dis.readBoolean());

        dis.close();
    }
}
