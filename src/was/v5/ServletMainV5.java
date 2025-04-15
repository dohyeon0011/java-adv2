package was.v5;

import was.httpserver.HttpServer;
import was.httpserver.servlet.DiscardServlet;
import was.httpserver.ServletManager;
import was.v5.servlet.HomeServlet;
import was.v5.servlet.SearchServlet;
import was.v5.servlet.Site1Servlet;
import was.v5.servlet.Site2Servlet;

import java.io.IOException;

/**
 * HTTP 서버와 관련된 부분 - `was.httpserver` 패키지
 *      `HttpServer` , `HttpRequestHandler` , `HttpRequest` , `HttpResponse`, `HttpServlet` , `HttpServletManager`
 *      `was.httpserver.servlet` 패키지
 *      `InternalErrorServlet` , `NotFoundServlet` , `DiscardServlet`
 *
 * 서비스 개발을 위한 로직 - `v5.servlet` 패키지
 *      `HomeServlet`
 *      `Site1Servlet`
 *      `Site2Servlet`
 *      `SearchServlet`
 */
public class ServletMainV5 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        ServletManager servletManager = new ServletManager();
        servletManager.add("/", new HomeServlet());
        servletManager.add("/site1", new Site1Servlet());
        servletManager.add("/site2", new Site2Servlet());
        servletManager.add("/search", new SearchServlet());
        servletManager.add("/favicon.ico", new DiscardServlet());

        HttpServer server = new HttpServer(PORT, servletManager);
        server.start();
    }
}
