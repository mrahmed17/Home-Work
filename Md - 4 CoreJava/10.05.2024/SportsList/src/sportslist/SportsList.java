package sportslist;

import sports.Basketball;
import sports.Football;
import sports.Rugby;
import sports.Sports;

public class SportsList {

    public static void main(String[] args) {

        Sports sports = new Sports();

        Football football = new Football();

        Basketball basketball = new Basketball();

        Rugby rugby = new Rugby();

        sports.play();
        football.play();
        basketball.play();
        rugby.play();
    }

}
