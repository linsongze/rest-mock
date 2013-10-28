package restmock.request;

import java.io.FileNotFoundException;

import restmock.RestMockResponse;
import restmock.response.Html;
import restmock.response.JSON;
import restmock.response.Response;
import restmock.response.TextPlain;
import restmock.response.XML;
import restmock.utils.Resource;

public class RouteRegister implements RestMockResponse {

	private final Route route;

	public RouteRegister(Route route) {
		this.route = route;
	}
	
	@Override
	public void thenReturnXML(Object object) {
		registerRoute(new XML(object));
	}

	@Override
	public void thenReturnXML(String value) {
		registerRoute(new XML(value));
	}

	@Override
	public void thenReturnHTML(String value) {
		registerRoute(new Html(value));
	}

	@Override
	public void thenReturnText(String value) {
		registerRoute(new TextPlain(value));
	}
	
	@Override
	public void thenReturnJSON(String value) {
		registerRoute(new JSON(value));
	}
	
	@Override
	public void thenReturnJSON(Object object) {
		try {
			registerRoute(new JSON(object));
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	
	@Override
	public void thenReturnErrorCodeWithMessage(int errorCode, String message) {
		Response response = new TextPlain(message);
		response.setResponseStatus(errorCode);
		
		registerRoute(response);
	}
	
	private void registerRoute(Response body) {
		RouteManager.getInstance().registerRoute(route, body);
	}

	@Override
	public void thenReturnJSONFromResource(String path) throws FileNotFoundException {
		thenReturnJSON(Resource.dataFromResource(path));
	}

	@Override
	public void thenReturnXMLFromResource(String path) throws FileNotFoundException {
		thenReturnXMLFromResource(Resource.dataFromResource(path));
	}

	@Override
	public void theReturnHTMLFromResource(String path) throws FileNotFoundException {
		thenReturnHTML(Resource.dataFromResource(path));
	}

	@Override
	public void thenReturnTextFromResource(String path) throws FileNotFoundException {
		thenReturnText(Resource.dataFromResource(path));
	}
	
}
