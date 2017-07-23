package app.gwt.sample.server;


import app.gwt.sample.client.LogService;
import app.gwt.sample.shared.Log;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.List;



public class LogServiceImpl extends RemoteServiceServlet implements
        LogService {

    Repository repo = Repository.getInstance();
    Parser parser = new ParserImpl();

    @Override
    public List<Log> getLogs() {
        return repo.getElements();
    }

    @Override
    public String getParsedLogById(int logId) {
         Log log = repo.getElements().stream().filter(e->e.getId()==logId).
                 findFirst().get();
         return parser.parseLog(log);
    }
}
