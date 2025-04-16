package was.v8;

import was.httpserver.HttpServer;
import was.httpserver.ServletManager;
import was.httpserver.servlet.DiscardServlet;
import was.httpserver.servlet.annotation.AnnotationServletV2;
import was.httpserver.servlet.annotation.AnnotationServletV3;

import java.io.IOException;
import java.util.List;

public class ServerMainV8 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        List<Object> controllers = List.of(new SiteControllerV8(), new SearchControllerV8());
//        AnnotationServletV2 annotationServlet = new AnnotationServletV2(controllers);
        AnnotationServletV3 annotationServlet = new AnnotationServletV3(controllers);   // `AnnotationServletV3` 을 생성하는 시점에 `@Mapping` 을 사용하는 컨트롤러의 메서드를 모두 찾아서 `pathMap` 에 보관한다.

        ServletManager servletManager = new ServletManager();
        servletManager.setDefaultServlet(annotationServlet);
        servletManager.add("/favicon.ico", new DiscardServlet());

        HttpServer server = new HttpServer(PORT, servletManager);
        server.start();
    }
}
