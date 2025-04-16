
package was.httpserver.servlet.annotation;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.servlet.PageNotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotationServletV3 implements HttpServlet {

    private final Map<String, ControllerMethod> pathMap;

    public AnnotationServletV3(List<Object> controllers) {
        this.pathMap = new HashMap<>();
        initializePathMap(controllers);
    }

    // 모든 컨트롤러를 찾아서 모든 메서드를 Map에 저장
    private void initializePathMap(List<Object> controllers) throws IllegalArgumentException {
        for (Object controller : controllers) {
            Method[] methods = controller.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Mapping.class)) {
                    String path = method.getAnnotation(Mapping.class).value();

                    // 중복 경로 체크 todo
                    if (pathMap.containsKey(path)) {
                        ControllerMethod controllerMethod = pathMap.get(path);

                        throw new IllegalStateException("경로 중복 등록, path = " + path +
                                ", method = " + method + ", 이미 등록된 메서드 = " + controllerMethod.method);
                    }
                    pathMap.put(path, new ControllerMethod(controller, method));
                }
            }
        }
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String path = request.getPath();
        ControllerMethod controllerMethod = pathMap.get(path);

        if (controllerMethod == null) {
            throw new PageNotFoundException("request = " + path);
        }

        controllerMethod.invoke(request, response);
    }

    // `ControllerMethod` : `@Mapping` 의 대상 메서드와 메서드가 있는 컨트롤러 객체를 캡슐화했다.
    // 이렇게 하면 `ControllerMethod` 객체를 사용해서 편리하게 실제 메서드를 호출할 수 있다.
    private static class ControllerMethod {

        private final Object controller;

        private final Method method;

        public ControllerMethod(Object controller, Method method) {
            this.controller = controller;
            this.method = method;
        }

        public void invoke(HttpRequest request, HttpResponse response) {
            Class<?>[] parameterTypes = method.getParameterTypes(); // 해당 메서드가 가진 파라미터 타입들
            Object[] args = new Object[parameterTypes.length];  // request, response / response

            // 불필요한 인자를 제외 후 필요한 인자만 args 배열에 담아서 해당 method를 invoke()
            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i] == HttpRequest.class) {
                    args[i] = request;
                } else if (parameterTypes[i] == HttpResponse.class) {
                    args[i] = response;
                } else {
                    throw new IllegalArgumentException("Unsupported parameter type: " + parameterTypes[i]);
                }
            }

            try {
                method.invoke(controller, args);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
