package network.tcp.autocloseable;

public class ResourceCloseMainV2 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;

        try {
            resource1 = new ResourceV1("resource1");
            resource2 = new ResourceV1("resource2");

            resource1.call();
            resource1.callEx(); // CallException 발생해서 finally 블럭으로 이동
        } catch (CallException e) {
            System.out.println("ex: " + e);
            throw e;
        } finally {
            if (resource2 != null) {
                resource2.closeEx();    // CloseException도 발생(메인의 try-catch에서 맨 처음에 발생한 resource1의 CallException 에러가 씹히고 이거만 에러가 잡힘)
            }
            if (resource1 != null) {
                resource1.closeEx();
            }
            System.out.println("자원 정리");
            resource2.closeEx();    // 여기서 먼저 예외 터져서
            resource1.closeEx();    // 이 코드 호출 안됨
        }
    }
}