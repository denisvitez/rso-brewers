package si.fri.rso.sn.brewers.api.v1;

import com.kumuluz.ee.discovery.annotations.RegisterService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@RegisterService
@ApplicationPath("/v1")
public class BrewerApplication extends Application {
}
