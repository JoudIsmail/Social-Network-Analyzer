package com.socialNetworkAnalyzer.mainLogin;

import com.socialNetworkAnalyzer.ModelInsta;
import com.socialNetworkAnalyzer.login.PresenterLogin;
import com.socialNetworkAnalyzer.login.ViewControllerLogin;
import com.socialNetworkAnalyzer.twitter.ModelTwitter;
import com.socialNetworkAnalyzer.twitter.PresenterMainTwitter;
import com.socialNetworkAnalyzer.twitter.ViewControllerMainTwitter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewControllerMainLogin {


    @FXML
    private ImageView fbLoginbtn;

    @FXML
    private Button instaLoginbtn;

    @FXML
    private Button twitterLoginbtn;

    private PresenterMainLogin presenterMainLogin;

    public void setPresenterMainLogin(PresenterMainLogin presenterMainLogin) {
        this.presenterMainLogin = presenterMainLogin;
    }



    @FXML
    void instaLoginbtnHandler(ActionEvent event) {
        Pane pane = new Pane();
        PresenterLogin presenterLogin = new PresenterLogin();
        ViewControllerLogin viewControllerLogin = new ViewControllerLogin();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.socialNetworkAnalyzer/Login.fxml"));
            pane = (Pane)loader.load();
            viewControllerLogin= (ViewControllerLogin)loader.getController();
            viewControllerLogin.setPresenterLogin(presenterLogin);
        }catch(Exception e){
            Logger.getLogger(ViewControllerLogin.class.getName()).log(Level.SEVERE, null, e);
        }
        ModelInsta modelInsta = new ModelInsta();
        presenterLogin.setViewControllerLogin(viewControllerLogin);
        presenterLogin.setModel(modelInsta);

        Scene scene = new Scene(pane);


        Node node =(Node)event.getSource();
        Stage window = ((Stage)node.getScene().getWindow());
        window.setScene(scene);
        window.setTitle("JAW Instagram");
        window.show();
    }

    @FXML
    void twitterLoginbtnHandler(ActionEvent event) throws IOException {
        PresenterMainTwitter p = new PresenterMainTwitter();
        ViewControllerMainTwitter v = new ViewControllerMainTwitter(p);
        v.initView();

        ModelTwitter m = new ModelTwitter();
        p.setViewControllerMainTwitter(v);;
        p.setModelTwitter(m);
        p.init();

        Stage window = new Stage();
        Scene scene = new Scene(v.getUI());
        window.setScene(scene);
        window.setTitle("Login");
        window.setMaxHeight(1080);
        window.setMaxWidth(1920);
        window.setWidth(500);
        window.setHeight(500);
        window.setMinHeight(500);
        window.setMinWidth(500);
        v.getUI().prefHeightProperty().bind(window.heightProperty());
        window.show();

    }
}
