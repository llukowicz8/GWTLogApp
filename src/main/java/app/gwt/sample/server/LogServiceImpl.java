package app.gwt.sample.server;

import app.gwt.sample.client.GreetingService;
import app.gwt.sample.client.LogService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.List;


public class LogServiceImpl extends RemoteServiceServlet implements
        LogService {

    Repository repo = Repository.getInstance();

    @Override
    public List<String> getLogs() {
        return repo.getElements();
    }
}
