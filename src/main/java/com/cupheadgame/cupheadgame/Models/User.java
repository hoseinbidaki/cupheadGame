package com.cupheadgame.cupheadgame.Models;

public class User implements Comparable<User>{
    public String username;
    public String password;
    public String avatar;
    public int score;

    public User(String username, String password, String avatar, int score) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(User o) {
        return -1*Integer.compare(this.score, o.getScore());
    }
}
