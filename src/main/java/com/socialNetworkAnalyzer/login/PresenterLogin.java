package com.socialNetworkAnalyzer.login;

import com.socialNetworkAnalyzer.Model;
import com.socialNetworkAnalyzer.SingletonSession;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

public class PresenterLogin {

    private ViewControllerLogin viewControllerLogin;
    private Model model;


    public PresenterLogin(){}

    public void setViewControllerLogin(ViewControllerLogin viewControllerLogin) {
        this.viewControllerLogin = viewControllerLogin;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Instagram4j login(String username, String password){
        Instagram4j instagram4j = null;
        model = new Model();
        instagram4j = model.login(username, password);
        return instagram4j;
    }

    public void storeUser(String username, String password){
        SingletonSession.Instance().setUsername(username);
        SingletonSession.Instance().setPassword(password);
    }

    public InstagramUser getProfile(Instagram4j instagram4j){
        InstagramUser instagramUser;
        model = new Model();
        instagramUser = model.getConnectedProfile(instagram4j);
        return instagramUser;
    }

    public static void updateProfile(InstagramUser user){
        Model.updateProfile(user);
    }

    public static void setInstagram4jInstance(Instagram4j instagram4j) {
        Model.setInstagram4jInstance(instagram4j);
    }
}
