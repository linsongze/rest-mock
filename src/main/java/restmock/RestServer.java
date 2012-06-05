package restmock;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import restmock.request.FrontController;
import restmock.request.HttpMethod;
import restmock.request.Route;
import restmock.request.RouteManager;

public class RestServer {

	private Server server;

	public RestServer(int port) {
		server = new Server(port);
	}

	public RestMockResponse whenGet(String uri) {
		return new HttpResponse(RouteManager.getInstance(), new Route(HttpMethod.GET, uri));
	}

	public void start() {
		initContext();
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				try {
					server.start();
					server.join();
				} catch (Exception e) {
					throw new RuntimeException("Could not start the server!", e);
				}
			}
		};

		Thread thread = new Thread(runnable);
		thread.setDaemon(true);
		thread.start();
	}

	private void initContext() {
		ServletContextHandler context = new ServletContextHandler();
		
		context.setContextPath("/");
		context.setResourceBase(".");
		context.setClassLoader(Thread.currentThread().getContextClassLoader());
		context.addServlet(FrontController.class, "/");
		
		server.setHandler(context);
	}

	public void stop() {
		try {
			server.stop();
			RouteManager.getInstance().clean();
		} catch (Exception e) {
			throw new RuntimeException("Could not stop the server!", e);
		}
	}

}