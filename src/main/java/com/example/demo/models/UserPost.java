package com.example.demo.models;

public enum UserPost {
    DIRECTOR(1),
    CLEANER(2),
    PLAYER(3),
    MANAGER(4);


    private final int id;

    public int getId() {
        return id;
    }


    UserPost(Integer id){
        this.id = id;
    }
}
