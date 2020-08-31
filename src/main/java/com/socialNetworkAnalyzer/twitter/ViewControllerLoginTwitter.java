package com.socialNetworkAnalyzer.twitter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class ViewControllerLoginTwitter {

    @FXML
    private WebView webViewScreen;

    private PresenterLoginTwitter presenterLoginTwitter;

    public void setPresenterLoginTwitter(PresenterLoginTwitter presenterLoginTwitter) {
        this.presenterLoginTwitter = presenterLoginTwitter;
    }

    @FXML
    void loginbtnHandler(ActionEvent event) {

    }
}
