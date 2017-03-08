package restmock;

import org.reflections.Reflections;
import restmock.request.*;
import restmock.utils.CollectionsUtils;

import java.util.Set;

public class RestMock {
	
	public static final int DEFAULT_PORT = 9080;
	
	private static final RestMockServer server = new RestMockServer();

	public static RestMockResponse whenGet(String uri) {
		return registerRoute(HttpMethod.GET, uri);
	}

	public static RestMockResponse whenPost(String uri) {
		return registerRoute(HttpMethod.POST, uri);
	}
	
	private static RestMockResponse registerRoute(HttpMethod method, String uri) {
		return new RouteRegister(new Route(method, uri));
	}

	public static void startServer() {
		startServer(DEFAULT_PORT);
	}

	public static void startServer(int port) {
		server.start(port);
	}

	public static void stopServer() {
		server.stop();
	}

	public static void clean() {
		server.clean();
	}

	public static RouteMappingPrefix prefix(String mappingPrefix){
		return  new RouteMappingPrefix(mappingPrefix);
	}

	/**
	 * scan package to regist the route
	 * @param scanPackage
	 */
	public static void scanInstall(String scanPackage) throws IllegalAccessException, InstantiationException {
		Reflections reflections = new Reflections(scanPackage);
		Set<Class<?>> routeInstalls =  reflections.getTypesAnnotatedWith(Install.class);
		if(CollectionsUtils.isNotEmpty(routeInstalls)){
			for (Class clazz:routeInstalls){
				RouteInstall routeInstall = (RouteInstall) clazz.newInstance();
				routeInstall.install();
			}
		}
	}
}
