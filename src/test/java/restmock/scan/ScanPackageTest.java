package restmock.scan;

import org.eclipse.jetty.client.ContentExchange;
import org.junit.Test;
import restmock.RestMock;
import restmock.integration.IntegrationTestBase;
import restmock.request.HttpMethod;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by lsz on 2017/3/8.
 */
public class ScanPackageTest extends IntegrationTestBase {

    @Test
    public void test() throws IOException, InterruptedException, InstantiationException, IllegalAccessException {

        RestMock.scanInstall("restmock.scan");


        ContentExchange exchange = sendRequestAndWaitForDone(baseUrl + "/x/x2", HttpMethod.GET);
        assertEquals(HttpServletResponse.SC_OK, exchange.getResponseStatus());
        System.out.println(exchange.getResponseContent());
    }
}
