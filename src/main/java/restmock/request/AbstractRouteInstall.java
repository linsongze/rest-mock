package restmock.request;

import restmock.RestMock;
import restmock.RestMockResponse;

/**
 * Created by lsz on 2017/3/8.
 */
public abstract class AbstractRouteInstall implements  RouteInstall {
    @Override
    public void install() {
        _install();
    }
    public abstract void _install();

    public RestMockResponse whenGet(String uri) {
        return RestMock.whenGet(uri);
    }

    public RestMockResponse whenPost(String uri) {
        return RestMock.whenPost(uri);
    }
    public RouteMappingPrefix prefix(String mappingPrefix){
        return  new RouteMappingPrefix(mappingPrefix);
    }
}
