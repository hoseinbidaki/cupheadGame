package com.cupheadgame.cupheadgame.Models;

import com.cupheadgame.cupheadgame.Main;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Database {
    private static Database database = new Database();
    public static Database getInstance() {
        return database;
    }

    public Database() {
        loggedInUser = null;
        loadData();
        loadGameData();
    }

    private ArrayList<User> users;
    private ArrayList<Game> games;
    private User loggedInUser;

    public void loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("users.json"));
            users = new Gson().fromJson(br, new TypeToken<ArrayList<User>>(){}.getType());
            br.close();
        }
        catch (Exception ex) {
            users = new ArrayList<>();
            saveData();
        }
    }

    public void loadGameData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("games.json"));
            games = new Gson().fromJson(br, new TypeToken<ArrayList<Game>>(){}.getType());
            br.close();
        }
        catch (Exception ex) {
            games = new ArrayList<>();
            saveGameData();
        }
    }

    public void saveGameData() {
        try {
            FileWriter writer = new FileWriter("games.json");
            writer.write(new Gson().toJson(games));
            writer.close();
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public void saveData() {
        try {
            FileWriter writer = new FileWriter("users.json");
            writer.write(new Gson().toJson(users));
            writer.close();
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUser(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return users.get(i);
            }
        }
        return null;
    }


    public ArrayList<Game> getGames() {
        return games;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void Logout() {
        setLoggedInUser(null);
    }

    public void Login(User user) {
        setLoggedInUser(user);
    }

    public void setScore(int score) {
        this.loggedInUser.score = Math.max(
                this.loggedInUser.score, score
        );
    }
}
