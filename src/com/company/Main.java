package com.company;

import com.company.domain.Character;
import com.company.domain.Location;
import com.company.domain.Player;
import com.company.domain.impl.ItemImpl;
import com.company.domain.impl.LocationImpl;
import com.company.domain.impl.CharacterImpl;
import com.company.ui.GameUI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Location startingLocation = createGame();
        Player player = createPlayer();
        GameUI gameUI = new GameUI();
        gameUI.play(startingLocation, player);
    }

    private static Location createGame() {

        /*
            Create starting location
            -> Hallway
         */
        Location hallway = new LocationImpl("Ground hallway");
        Location kitchen = new LocationImpl("Kitchen");
        Location livingRoom = new LocationImpl("Living room");
        Location upstairs = new LocationImpl("1st floor hallway");
        Location bathroom_1 = new LocationImpl("Bathroom");

        /*
            Available directions
            -> Kitchen
            -> Living room
            -> 1st floor hallway
         */
        hallway.addDirection("Go to the kitchen", kitchen);
        hallway.addDirection("Go to the living room", livingRoom);
        hallway.addDirection("Go upstairs", upstairs);
        hallway.setSafety(true);
//        hallway.addOption("Open lock on doors to the outside", 5);

        /*
            Available directions
            -> ground hallway
         */
        kitchen.addDirection("Go to the hallway", hallway);
        kitchen.addItem("Knife", 10, 5, 2);
        kitchen.addItem("Door", 100, 50, 40);

        /*
            Available directions
            -> ground hallway
         */
        livingRoom.addDirection("Go to the hallway", hallway);
        livingRoom.addItem("Key", 0, 0, 1);

        /*
            Available directions
            -> ground hallway
            -> Bathroom
         */
        upstairs.addDirection("Go downstairs", hallway);
        upstairs.addDirection("Go to the Bathroom", bathroom_1);

        /*
            Available directions
         */
        bathroom_1.addDirection("Go to the hallway", upstairs);

        return hallway;
    }

    private static Player createPlayer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your name:");

        Player player = new CharacterImpl(sc.nextLine());
        player.printCharacterInfo();
        player.addOption("Do nothing and rest", 0);
        player.addOption("Suicide", 66);
        player.addOption("Look into inventory", 2);
        player.setWeapon(new ItemImpl("Sword", 10, 10, 3));
        player.addItem(player.getWeapon());
//        player.addOption("Drop " + player.getWeapon().getName(), 4, player.getWeapon());

        return player;
    }
}
