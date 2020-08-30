package com.socialNetworkAnalyzer;

import com.socialNetworkAnalyzer.login.PresenterLogin;
import com.socialNetworkAnalyzer.login.ViewControllerLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage stage) throws Exception {
        PresenterLogin presenterLogin = new PresenterLogin();
        Pane pane;

        ViewControllerLogin viewControllerLogin;

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.socialNetworkAnalyzer/Login.fxml"));
            pane = (Pane)loader.load();
            viewControllerLogin=(ViewControllerLogin)loader.getController();
            viewControllerLogin.setPresenterLogin(presenterLogin);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        Model model = new Model();
        presenterLogin.setViewControllerLogin(viewControllerLogin);
        presenterLogin.setModel(model);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
}
