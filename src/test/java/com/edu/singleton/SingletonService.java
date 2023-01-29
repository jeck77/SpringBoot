package com.edu.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    // 싱글톤 패턴으로 생성해도 임의로 생성 할 수 있으니 private로 생성자를 만들어서
    // 다른곳에서 생성 할 수 없도록 방지한다.
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
