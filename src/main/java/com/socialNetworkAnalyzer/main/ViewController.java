package com.socialNetworkAnalyzer.main;

import com.socialNetworkAnalyzer.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

import java.io.IOException;


public class ViewController {

    @FXML
    private Label followerslbl, followinglbl, followersgainedlbl, followerslostlbl;

    @FXML
    private Button followGbtn;

    @FXML
    private Button followLbtn;

    @FXML
    private Button mlikesbtn;

    @FXML
    private Button lLikesbtn;

    @FXML
    private Button mCommentsbtn;

    @FXML
    private Button lCommentsbtn;

    @FXML
    private ImageView profpic;

    @FXML
    private Label usernamelbl, numofposts;


    private Presenter presenter;


    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }


    public void initialize(){
        Model model = new Model();
        Instagram4j instagram4j = Model.getInstagram4jInstance();
        InstagramUser user = model.getConnectedProfile(instagram4j);
        followerslbl.setText("Followers: "+ String.valueOf(user.getFollower_count()));
        followinglbl.setText("Following: "+ String.valueOf(user.getFollowing_count()));
        numofposts.setText("Number Of Posts: "+ String.valueOf(user.getMedia_count()));
        usernamelbl.setText(user.getFull_name());
        model.createFile();
        model.readFollowersFile();
        model.followersTracking();
        followersgainedlbl.setText("Followers Gained: "+String.valueOf(model.getGainedFollowers()));
        followerslostlbl.setText("Followers Lost: "+ String.valueOf(model.getLostFollowers()));
        Image image = new Image(user.getProfile_pic_url());
        profpic.setImage(image);
    }
}
