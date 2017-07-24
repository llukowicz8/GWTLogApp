package app.gwt.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LogServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see app.gwt.sample.client.LogService
     */
    void getLogs( AsyncCallback<java.util.List<app.gwt.sample.shared.Log>> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see app.gwt.sample.client.LogService
     */
    void getParsedLogById( int logId, AsyncCallback<java.lang.String> callback );


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static LogServiceAsync instance;

        public static final LogServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (LogServiceAsync) GWT.create( LogService.class );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instantiated
        }
    }
}
