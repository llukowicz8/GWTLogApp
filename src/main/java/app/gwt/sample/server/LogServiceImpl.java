package app.gwt.sample.server;

import app.gwt.sample.client.GreetingService;
import app.gwt.sample.client.LogService;
import app.gwt.sample.shared.Log;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.List;
import java.util.stream.Collectors;


public class LogServiceImpl extends RemoteServiceServlet implements
        LogService {

    Repository repo = Repository.getInstance();
    Parser parser = new ParserImpl();

    @Override
    public List<Log> getLogs() {
        return repo.getElements();
    }

    @Override
    public String getLogById(final int logId) {
         Log pobrany = repo.getElements().stream().filter(e->e.getId()==logId).
                 findFirst().get();

         String parsed = parser.parseLog(pobrany);


       return parsed;
    }
}
