package com.socialNetworkAnalyzer.main;

import com.socialNetworkAnalyzer.Model;
import com.socialNetworkAnalyzer.SingletonSession;
import com.socialNetworkAnalyzer.login.PresenterLogin;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

public class Presenter {

    private ViewController viewController;
    private Model model;
    private PresenterLogin presenterLogin = new PresenterLogin();
    private String username;
    private InstagramUser user;

    public Presenter(){}

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    public void setModel(Model model) {
        this.model = model;
    }


    public int getFollowers(){
        int followers;
        model = new Model();
        followers= model.getFollowersCount();
        return followers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public InstagramUser getUser() {
        return user;
    }

    public void setUser(InstagramUser user) {
        this.user = user;
    }

    public static void setInstagram4jInstance(Instagram4j instagram4j) {
        Model.setInstagram4jInstance(instagram4j);
    }
}
