package restmock.request;

import restmock.RestMock;
import restmock.RestMockResponse;

/**
 * Created by lsz on 2017/3/8.
 */
public class RouteMappingPrefix {
    private String prefix = "";
    public RouteMappingPrefix(){

    }
    public RouteMappingPrefix(String prefix){
       this.prefix = prefix;
    }
    public RestMockResponse whenGet(String uri) {
        uri = contact(uri);
        return RestMock.whenGet(uri);
    }

    public RestMockResponse whenPost(String uri) {
        uri = contact(uri);
        return RestMock.whenPost(uri);
    }

    private String contact(String mapping){
        String m = "/"+prefix+"/"+mapping;
        m = m.replace("//","/");
        return m;
    }
}
