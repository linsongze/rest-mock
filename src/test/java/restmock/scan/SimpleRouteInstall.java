package restmock.scan;

import restmock.request.AbstractRouteInstall;
import restmock.request.Install;
import restmock.request.RouteInstall;
import restmock.request.RouteMappingPrefix;

/**
 * Created by lsz on 2017/3/8.
 */
@Install
public class SimpleRouteInstall extends AbstractRouteInstall implements RouteInstall {
    @Override
    public void _install() {
        RouteMappingPrefix prefix = prefix("/x");
        prefix.whenGet("x2") .thenReturnText("hellow");
        prefix.whenGet("x3") .thenReturnText("hello3");
    }
}
