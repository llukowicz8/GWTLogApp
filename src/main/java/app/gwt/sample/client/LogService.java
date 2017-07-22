package app.gwt.sample.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("logService")
public interface LogService extends RemoteService {

    List<String> getLogs();
}
