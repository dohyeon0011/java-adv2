package io.file;

import java.io.File;
import java.io.IOException;

public class OldFilePath {

    public static void main(String[] args) throws IOException {
        File file = new File("temp/.."); // temp 디렉터리의 상위 폴더(java-adv2)
        System.out.println("file.getPath = " + file.getPath());

        // 절대 경로(절대 경로는 경로의 처음부터 내가 입력한 모든 경로를 다 표현한다.)
        System.out.println("Absolute Path = " + file.getAbsolutePath());

        /**
         * # 정규 경로
         *  경로의 계산이 모두 끝난 경로이다. 정규 경로는 하나만 존재한다.
         *  예제에서 `..` 은 바로 위의 상위 디렉토리를 뜻한다. 이런 경로의 계산을 모두 처리하면 하나의 경로만 남는다.
         *  예를 들어 절대 경로는 다음 2가지 경로가 모두 가능하지만
         *  `/Users/yh/study/inflearn/java/java-adv2`
         *  `/Users/yh/study/inflearn/java/java-adv2/temp/..`
         *  정규 경로는 다음 하나만 가능하다.
         *  `/Users/yh/study/inflearn/java/java-adv2`
         */
        System.out.println("Canonical path = " + file.getCanonicalPath());

        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println((f.isFile() ? "F" : "D") + " | " + f.getName());
        }

    }
}
