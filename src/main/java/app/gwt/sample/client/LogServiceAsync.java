package app.gwt.sample.client;


import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface LogServiceAsync {
    void getLogs(AsyncCallback<List<String>> callback);
}
