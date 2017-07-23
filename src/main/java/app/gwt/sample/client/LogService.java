package app.gwt.sample.client;

import app.gwt.sample.shared.Log;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("logService")
public interface LogService extends RemoteService {

    List<Log> getLogs();
    String getLogById(int logId);
}
