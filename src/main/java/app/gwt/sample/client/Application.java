package app.gwt.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {

  private static final String UPLOAD_ACTION_URL = GWT.getModuleBaseURL() + "upload";

  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  //private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
  //private final LogServiceAsync logService = GWT.create(LogService.class);
  private final Messages messages = GWT.create(Messages.class);



  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
//    final FormPanel form = new FormPanel();
//
//    form.setAction(UPLOAD_ACTION_URL);
//
//    // Because we're going to add a FileUpload widget, we'll need to set the
//    // form to use the POST method, and multipart MIME encoding.
//    form.setEncoding(FormPanel.ENCODING_MULTIPART);
//    form.setMethod(FormPanel.METHOD_POST);
//
//    // Create a panel to hold all of the form widgets.
//    VerticalPanel panel = new VerticalPanel();
//    form.setWidget(panel);
//
//    // Create a TextBox, giving it a name so that it will be submitted.
//    final TextBox tb = new TextBox();
//    CellTable<String> table = new CellTable<String>();
//    TextColumn<String> nameColumn = new TextColumn<String>() {
//      @Override
//      public String getValue(String element) {
//        return element;
//      }
//    };
//    table.addColumn(nameColumn, "trace");
//    ListDataProvider<String> dataProvider = new ListDataProvider<String>();
//
//    // Connect the table to the data provider.
//    dataProvider.addDataDisplay(table);
//
//    // Add the data to the data provider, which automatically pushes it to the
//    // widget.
//    final List<String> listProv = dataProvider.getList();
//
//
//
//
//
//    tb.setName("textBoxFormElement");
//    panel.add(tb);
//
//    // Create a ListBox, giving it a name and some values to be associated
//    // with its options.
//    ListBox lb = new ListBox();
//    lb.setName("listBoxFormElement");
//    lb.addItem("foo", "fooValue");
//    lb.addItem("bar", "barValue");
//    lb.addItem("baz", "bazValue");
//    panel.add(lb);
//
//    // Create a FileUpload widget.
//    FileUpload upload = new FileUpload();
//    upload.setName("uploadFormElement");
//    panel.add(upload);
//
//    // Add a 'submit' button.
//    panel.add(new Button("Submit", new ClickHandler() {
//      public void onClick(ClickEvent event) {
//        form.submit();
//      }
//    }));
//    panel.add(table);
//
//    // Add an event handler to the form.
//    form.addSubmitHandler(new FormPanel.SubmitHandler() {
//      public void onSubmit(FormPanel.SubmitEvent event) {
//        // This event is fired just before the form is submitted. We can
//        // take this opportunity to perform validation.
//        if (tb.getText().length() == 0) {
//          Window.alert("The text box must not be empty");
//          event.cancel();
//        }
//      }
//    });
//
//    form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
//      public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
//        // When the form submission is successfully completed, this
//        // event is fired. Assuming the service returned a response of type
//        // text/html, we can get the result text here (see the FormPanel
//        // documentation for further explanation).
//
//        GWT.log(event.getResults());
//        Window.alert(event.getResults());
//        logService.getLogs(new AsyncCallback<List<String>>() {
//          @Override
//          public void onFailure(Throwable throwable) {
//            Window.alert("Unable to obtain server response: "
//                    + throwable.getMessage());
//          }
//
//          @Override
//          public void onSuccess(List<String> result) {
//            Window.alert("wow dostałem odpowiedz "+result.size());
//            for (String e : result) {
//              listProv.add(e);
//            }
//          }
//        });
//      }
//    });

  //  RootPanel.get().add(form);
    MyWidget helloWorld =
            new MyWidget("able", "baker", "charlie");
    RootPanel.get().add(helloWorld);
    //RootPanel.get().add(new MyHTMLTable());
  }}