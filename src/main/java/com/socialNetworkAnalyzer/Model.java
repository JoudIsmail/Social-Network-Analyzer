package com.socialNetworkAnalyzer;



import javafx.scene.image.ImageView;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowingRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private static Instagram4j instagram = null;

    private List <InstagramUserSummary>users = new ArrayList<>();

    private int nRows = 3;

    private  int nCols;

    private String username;
    private int gainedFollowers;
    private int lostFollowers;
    private String followers;
    private String following;

    private static String currentUsername;
    private static InstagramUser currentUser;

    public Instagram4j getInstagram() {
        return instagram;
    }

    public void setInstagram(Instagram4j instagram) {
        this.instagram = instagram;
    }

    public Model(){}


    public int getGainedFollowers() {
        return gainedFollowers;
    }

    public int getLostFollowers() {
        return lostFollowers;
    }

    public Instagram4j login(String username, String password) {
        instagram = Instagram4j.builder().username(username).password(password).build();
        instagram.setup();
        try {
            instagram.login();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return instagram;
    }

    public Instagram4j getInstagramInstance() {
        return instagram;
    }

    //I won't implement this method in this Version, perhaps in future Versions

    public List<InstagramUserSummary> getFollowersList(Instagram4j instagram, InstagramUser user) {
        String nextMaxId = null;
        List<InstagramUserSummary> users = new ArrayList<InstagramUserSummary>();

        while (true) {
            InstagramGetUserFollowersResult followersResult = null;
            try {
                followersResult = instagram.sendRequest(new InstagramGetUserFollowersRequest(user.getPk(), nextMaxId));
            } catch (IOException e) {
                e.printStackTrace();
            }
            users.addAll(followersResult.getUsers());
            nextMaxId = followersResult.getNext_max_id();

            if (nextMaxId == null) {
                break;
            }
        }

        return users;
    }

    //comes in future Version

    public List<InstagramUserSummary> getFollowingList(Instagram4j instagram, InstagramUser user) {
        String nextMaxId = null;
        List<InstagramUserSummary> users = new ArrayList<InstagramUserSummary>();

        while (true) {
            InstagramGetUserFollowersResult followersResult = null;
            try {
                followersResult = instagram.sendRequest(new InstagramGetUserFollowingRequest(user.getPk(), nextMaxId));
            } catch (IOException e) {
                e.printStackTrace();
            }
            users.addAll(followersResult.getUsers());
            nextMaxId = followersResult.getNext_max_id();

            if (nextMaxId == null) {
                break;
            }
        }

        return users;
    }

    public InstagramUser getConnectedProfile(Instagram4j instagram) {
        return getProfile(instagram, instagram.getUsername());
    }

    public InstagramUser getProfile(Instagram4j instagram, String username) {
        InstagramSearchUsernameResult userResult = null;
        try {
            userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(username));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userResult.getUser();
    }

    public static void updateProfile(InstagramUser user){
        currentUser = user;
        currentUsername = user.getUsername();
        SingletonSession.Instance().setUsername(currentUsername);
    }

    public static ImageView getUserProfilePhoto(String imgUrl) throws IOException {
        ImageView imageIcon = new ImageView("");

        return imageIcon;
    }

    public static void setInstagram4jInstance(Instagram4j instagram4j) {
        instagram = instagram4j;
    }

    public static Instagram4j getInstagram4jInstance(){
        return  instagram;
    }

    public int getFollowersCount(){
        Instagram4j instagram4j = getInstagram4jInstance();
        InstagramUser user = getConnectedProfile(instagram4j);
        return user.getFollower_count();
    }

    public void followersTracking(){
        if (getFollowersCount()<Integer.valueOf(followers)){
            lostFollowers = getFollowersCount() - Integer.valueOf(followers);
            updateFile();
        }
        if (getFollowersCount()>Integer.valueOf(followers)){
            gainedFollowers = getFollowersCount() - Integer.valueOf(followers);
            updateFile();
        }else{
            lostFollowers = 0;
            gainedFollowers = 0;
        }
    }

    public void createFile(){
        try{
            String path = "src/main/resources/com.socialNetworkAnalyzer/tracks.txt";
            String followersnum = String.valueOf(getFollowersCount());
            File file = new File(path);
            if (!file.exists()){
                file.createNewFile();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(path), "utf-8"));
                bufferedWriter.write("Followers: "+followersnum);
                bufferedWriter.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void updateFile(){
        String path =  "src/main/resources/com.socialNetworkAnalyzer/tracks.txt";
        String followersnum = String.valueOf(getFollowersCount());
       try{
        FileWriter fileWriter = new FileWriter(path, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
           bufferedWriter.write("Followers: "+followersnum);
           fileWriter.close();
           bufferedWriter.close();
       }catch(IOException e){
           e.printStackTrace();
       }
    }

    public void readFollowersFile() {
        String path = "src/main/resources/com.socialNetworkAnalyzer/tracks.txt";
        String line;

        File file = new File(path);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((line = bufferedReader.readLine()) != null){
                if (line.contains("Followers")){
                    followers = line.split(":")[1].trim();
                    break;
                }
            }
            System.out.println(followers);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //TODO
}
