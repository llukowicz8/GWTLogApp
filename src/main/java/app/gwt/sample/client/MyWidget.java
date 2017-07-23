package app.gwt.sample.client;



import app.gwt.sample.shared.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.CellPreviewEvent;

import javax.annotation.PostConstruct;
import java.util.List;

public class MyWidget extends Composite {
    // Annotation not needed as we use the default but this allows to change the path
    @UiTemplate("MyWidget.ui.xml")
    interface MyUiBinder extends UiBinder<Widget, MyWidget> {
    }
    private static int CHOSEN_LOG_ID ;
    private static final String UPLOAD_ACTION_URL = GWT.getModuleBaseURL() + "upload";
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    private final LogServiceAsync logService = GWT.create(LogService.class);

    @UiField
    FormPanel form;

    @UiField
    Button uploadButton;

    @UiField
    Button parseButton;


    @UiField
    CellTable<Log> table;



    @UiField
    DialogBox wishlistDialogBox;


   //@PostConstruct \robic

    private void addColumns() {
        TextColumn<Log> nameColumn = new TextColumn<Log>() {
            @Override
            public String getValue(Log object) {
                return object.getContent();
            }
        };
        // Add column to table
        table.addColumn(nameColumn);

    }


    public MyWidget(String... names) {
        // sets listBox
        initWidget(uiBinder.createAndBindUi(this));
        addColumns();
        form.setAction(UPLOAD_ACTION_URL);
        form.setEncoding(FormPanel.ENCODING_MULTIPART);
        form.setMethod(FormPanel.METHOD_POST);
        parseButton.setEnabled(false);
        table.setVisible(false);





    }


    @UiHandler("uploadButton")
    void onUploadClick(ClickEvent event) {
        form.submit();
    }

    @UiHandler("parseButton")
    void onParseClick(ClickEvent event) {
        logService.getLogById(CHOSEN_LOG_ID,new LogByIdCallback());
        parseButton.setEnabled(false);
    }



    @UiHandler("form")
    void onUploadFormSubmitComplete(FormPanel.SubmitCompleteEvent event) {
        GWT.log("wowo");
        logService.getLogs(new AllLogsCallback());

    }

    @UiHandler("table")
    void onCellTableCellRowClick(CellPreviewEvent<Log> event) {

       // GWT.log(String.valueOf(event.getValue().getId()));
        CHOSEN_LOG_ID = event.getValue().getId();
        parseButton.setEnabled(true);

    }





    private class AllLogsCallback implements AsyncCallback<List<Log>> {


        public void onFailure(Throwable caught) {
            Window.alert(caught.getMessage());
        }

        public void onSuccess(List<Log> result) {

          GWT.log(String.valueOf(result));
            table.setVisible(true);
            table.setRowData(result);
        }

    }

    private class LogByIdCallback implements AsyncCallback<String> {


        public void onFailure(Throwable caught) {
            Window.alert(caught.getMessage());
        }

        public void onSuccess(String result) {

            //GWT.log(String.valueOf(result));
            GWT.log("dostanie przeparsowany "+result);
            wishlistDialogBox.center();
            //wishlistDialogBox.setVisible(true);
            wishlistDialogBox.setText(result);
        }

    }




}
