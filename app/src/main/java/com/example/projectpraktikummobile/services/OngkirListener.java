package com.example.projectpraktikummobile.services;

public interface OngkirListener<T> {
    void onResponse(T items);
    void onFailure(String message);
}