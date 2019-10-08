package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

public class Controller {

    @FXML private GridPane rootPane;
    @FXML private TextField urlTextField;
    @FXML private WebView webView;
    private WebEngine webEngine;
    @FXML private Label loadingLable;

    @FXML void initialize(){
        webEngine = webView.getEngine();
        loadingLable.setText(" ");
        webView.prefWidthProperty().bind(rootPane.widthProperty());
        webView.prefHeightProperty().bind(rootPane.heightProperty());
    }

    @FXML void urlLabelPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER))
        {
            getSite();
        }
    }

    private String validUrl(String url){
        String http = url.substring(0, 7);
        if(!(http.equals("http://"))){
            url = "http://" + url;
        }
        return url;
    }

    @FXML
    void GoSite(ActionEvent event) {
        getSite();
    }

    private void getSite(){
        String validUrl = validUrl(urlTextField.getText());
        webEngine.load(validUrl);
        urlTextField.setText(validUrl);
        loadingLable.setText("Loading ...");
        webEngine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
            @Override
            public void handle(WebEvent<String> event) {
                loadingLable.setText(" ");
            }
        });
    }

}