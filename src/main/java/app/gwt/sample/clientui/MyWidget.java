package app.gwt.sample.clientui;



import app.gwt.sample.client.LogService;
import app.gwt.sample.client.LogServiceAsync;
import app.gwt.sample.client.Messages;
import app.gwt.sample.shared.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.uibinder.client.*;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.CellPreviewEvent;

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
    Messages BUNDLE;
    @UiField
    FormPanel form;
    @UiField
    Button uploadButton;
    @UiField
    Button parseButton;
    @UiField
    CellTable<Log> table;
    @UiField
    DialogBox dialogBox;


    @UiConstructor
    public MyWidget() {
        initWidget(uiBinder.createAndBindUi(this));
        addColumn();
        form.setAction(UPLOAD_ACTION_URL);
        form.setEncoding(FormPanel.ENCODING_MULTIPART);
        form.setMethod(FormPanel.METHOD_POST);
        dialogBox.setText(BUNDLE.popUpHeader());
        parseButton.setEnabled(false);
        table.setVisible(false);

    }


    @UiHandler("uploadButton")
    void onUploadClick(ClickEvent event) {
        form.submit();
    }

    @UiHandler("parseButton")
    void onParseClick(ClickEvent event) {
        logService.getParsedLogById(CHOSEN_LOG_ID,new LogByIdCallback());
        parseButton.setEnabled(false);
    }



    @UiHandler("form")
    void onUploadFormSubmitComplete(FormPanel.SubmitCompleteEvent event) {
        logService.getLogs(new AllLogsCallback());

    }

    @UiHandler("table")
    void onCellTableCellRowClick(CellPreviewEvent<Log> event) {
        CHOSEN_LOG_ID = event.getValue().getId();
        parseButton.setEnabled(true);
    }

    private void addColumn() {
        TextColumn<Log> nameColumn = new TextColumn<Log>() {
            @Override
            public String getValue(Log object) {
                return object.getContent();
            }
        };
        table.addColumn(nameColumn,BUNDLE.contentHeader());

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

            Label content = new Label(result);
            dialogBox.center();
            dialogBox.setWidget(content);

        }

    }






}
