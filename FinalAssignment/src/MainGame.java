import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.*;
import java.util.*;
public class MainGame {
    /*
        Author: Joe Yuen, for CS3IOS Project 2021-2022
        block
            0 (townhall)     1(postOffice)      2(cavernseB)
            3(cavernsA)      4 (field)          5(n/a)
            6(field)         7 (exitGate)       8 (field)
    */
    /*
        solvePuzzle()                               :A function will be called when player uses the key at the exit gate.  The function returns true if player has solved the puzzle, false otherwise.
        block_0(String action, string noun)         :A function will be called when player enters the particular block and issue a command.  The function returns "undefined" if the action is invalid
        :                                               each function represents one block in the game world.  The arrangement is listed above
        block_8(String action, string noun)             block_0: means the block with ID 0
        getNextBlock(int currentBlock, String dir)  :get the ID of the block next to the current block in "dir" direction.  currentBlock=ID of currentBlock, dir={north|west|east|south}
        getBlockName(int i)                         :return the name of the block with give an ID (i)
        printBag(String bag[])                      :print out the content of player's bag
        printMap(int playerLocation)                :Given the player's location (in term of block ID), print the map around the player
        start()                                     :The main game loop.  The main component is a while loop that keep reading "action" and "noun" from user input until "exit" is entered
        main()                                      :The main method which create the MainGame object and call the method start to start the game.
    */
    ArrayList<String> bagContents = new ArrayList<String>();
    boolean puzzleSolved = false;
    boolean solvePuzzle(){
        int token=3;
        Scanner in = new Scanner(System.in);
        System.out.println("Congratulation, you reach the final challenge...");
        System.out.println("You have to solve 2 riddles && find the key to open the gate");
        System.out.println("By the way, if i feel any energy or power near here. I will gone");
        System.out.println("What is the thing that you can sit on but cannot be removed?");
        while (token>0){
            String ans = in.nextLine();
            if (ans.equals("chair")){
                System.out.println("What has to be broken before you can use it?");
                String ansAnotherRiddle = in.nextLine();
                if(ansAnotherRiddle.equals("egg")){
                    System.out.println("Now you may use the key to unlock the gate, if you have the key hahaha!");
                    return true;
                }else{
                    token-=1;
                    if(token>0){
                        System.out.println("Guess again, it is incorrect.");
                        System.out.println("You can try "+token+" more time");
                    }else{
                        return false;
                    }
                }
            }else{
                token-=1;
                System.out.println("Guess again, it is incorrect.");
                System.out.println("You can try "+token+" more time");
            }
        }
        return false;
    }

    String block0(String action, String noun){
        if (action.equals("check") && noun.equals("block")){
            return "townHall";
        }else if (action.equals("examine")) {
            //return "ok" if the (1)examining item is valid (2) the command is "examine all".  Otherwise, return "undefined"
            if (noun.equals("all")) {
                System.out.println("1. photo");
                System.out.println("2. paper");
                System.out.println("3. toolboxes");
                System.out.println("4. map");
                System.out.println("5. key");
                System.out.println("6. battery");
            } else if (noun.equals("photo")) {
                System.out.println("Photo: A photo of a caverns");
            } else if (noun.equals("paper")) {
                System.out.println("Paper: A notice posted on a message board");
            } else if (noun.equals("map")) {
                System.out.println("A local map, with a mark on cavernsB");
            } else if (noun.equals("key")) {
                System.out.println("A key with number IOS");
            } else if (noun.equals("battery")) {
                System.out.println("An AA sized battery");
            }else{
                System.out.println("Item not found");
                return "Item not found";
            }
            return "ok";
        }else if (action.equals("take")){//bagContents
            if(bagContents.size()>4) {
                System.out.println("Bag is full now!");
            } else {
                if (noun.equals("photo")) {//photo.paper.toolboxes.map.key.battery
                    return "photo";
                } else if (noun.equals("paper")) {
                    return "paper";
                } else if (noun.equals("toolboxes")) {
                    return "toolboxes";
                } else if (noun.equals("map")) {
                    return "map";
                } else if (noun.equals("key")) {
                    return "key";
                } else if (noun.equals("battery")) {
                    return "battery";
                } else {
                    return "undefined";
                }
            }
        }
        return "undefined";
    }
    String block1(String action, String noun){
        if (action.equals("check") && noun.equals("block")){
            return "postOffice";
        }
        return "undefined";
    }
    String block2(String action, String noun){
        if (action.equals("check") && noun.equals("block")){
            return "cavernsB";
        }
        return "undefined";
    }
    String block3(String action, String noun){
        if (action.equals("check") && noun.equals("block")){
            return "cavernsA";
        }
        return "undefined";
    }
    String block4(String action, String noun){
        if (action.equals("check") && noun.equals("block")){
            return "field";
        }
        return "undefined";
    }
    String block5(String action, String noun){
        if (action.equals("check") && noun.equals("block")){
            return "n/a";
        }
        return "undefined";
    }
    String block6(String action, String noun){
        if (action.equals("check") && noun.equals("block")){
            return "field";
        }
        return "field";
    }
    String block7(String action, String noun){
        if (action.equals("check") && noun.equals("block")){
            return "exitGate";
        }else if (action.equals("examine")) {
            if (noun.equals("all")) {
                System.out.println("1. gate");
                System.out.println("2. giant");
            } else if (noun.equals("gate")) {
                System.out.println("The gate is locked");
            } else if (noun.equals("giant")) {
                if (puzzleSolved){
                    System.out.println("You already solved the puzzle");
                }else if (solvePuzzle()){
                    puzzleSolved=true;
                }else{
                    System.out.println("Please try again if you know the answer");
                }
            }else{
                System.out.println("Item not found");
            }
        }else if (action.equals("take")){
            return "undefined";
        }else if (action.equals("use")){
            //The start method should check if the player has the item to be used first
            if (noun.equals("key")){
                if(!bagContents.contains("key")){//?????????????????????key???????????????
                    System.out.println("You do not have key!!");
                }else {
                    if (puzzleSolved){
                        return "GameCompleted";
                    }else{
                        System.out.println("Talk to the giant first");
                        return "invalid";
                    }
                }
            }else{
                System.out.println(noun+" cannot be used here");
            }
        }
        return "undefined";
    }
    String block8(String action, String noun){
        if (action.equals("check") && noun.equals("block")){
            return "field";
        }
        return "undefined";
    }

    int getNextBlock(int currentBlock,String dir) {
        int nextBlock=-1;
        if (dir.equals( "north")) {
            nextBlock=currentBlock -3;
            if (nextBlock<0)
                nextBlock=-1;
        } else if (dir.equals("east")) {
            nextBlock=currentBlock+ 1;
            if (nextBlock==3||nextBlock==6||nextBlock==9)
                nextBlock=-1;
        } else if (dir.equals("south")) {
            nextBlock=currentBlock+ 3;
            if (nextBlock>8)
                nextBlock=-1;
        } else if (dir.equals("west")) {
            nextBlock=currentBlock - 1;
            if (nextBlock==2 || nextBlock==5)//??????????????????block??????????????????-1???-1??????????????????block2???5???????????????????????????????????????9?????????????????????(?????????????????????)
                nextBlock=-1;
        }

        if (nextBlock!=-1){
            if (nextBlock==0 && block0("check","block")!="n/a")
                return nextBlock;
            if (nextBlock==1 && block1("check","block")!="n/a")
                return nextBlock;
            if (nextBlock==2 && block2("check","block")!="n/a")
                return nextBlock;
            if (nextBlock==3 && block3("check","block")!="n/a")
                return nextBlock;
            if (nextBlock==4 && block4("check","block")!="n/a")
                return nextBlock;
            if (nextBlock==5 && block5("check","block")!="n/a")
                return nextBlock;
            if (nextBlock==6 && block6("check","block")!="n/a")
                return nextBlock;
            if (nextBlock==7 && block7("check","block")!="n/a")
                return nextBlock;
            if (nextBlock==8 && block8("check","block")!="n/a")
                return nextBlock;
            nextBlock=-1;
        }
        return nextBlock;
    }
    String getBlockName(int id){
        String name="Error";
        if (id==0){
            name= block0("check","block");
        }else if (id==1){
            name= block1("check","block");
        }else if (id==2){
            name= block2("check","block");
        }else if (id==3){
            name= block3("check","block");
        }else if (id==4){
            name= block4("check","block");
        }else if (id==5){
            name= block5("check","block");
        }else if (id==6){
            name= block6("check","block");
        }else if (id==7){
            name= block7("check","block");
        }else if (id==8) {
            name = block8("check", "block");
        }
        return name;
    }
    //void printBag(String bag[]){//******************
    void printBag(ArrayList<String> bag){//******************
        for(int i=0;i< bag.size();i++){
            if(bag.get(i) != null){
                System.out.println(bag.get(i));
            }
        }
    }
    void printMap(int playerLocation){
        if (playerLocation==4){//?????????
            System.out.println("                       |North: Town Hall| ");
            System.out.println("                                  ^");
            System.out.println("                                  |");
            System.out.println("|West: Post Office| <- | Current: Entrance | -> |East: N/A|");
            System.out.println("                                  |");
            System.out.println("                                  v");
            System.out.println("                       |South: Field|");
        }
        if (playerLocation==0){//?????????
            System.out.println("                    |North: N/A| ");
            System.out.println("                          ^");
            System.out.println("                          |");
            System.out.println("|West: N/A| <- | Current: Town Hall | -> |East: Post Office|");
            System.out.println("                          |");
            System.out.println("                          v");
            System.out.println("                   |South: CavernsA|");
        }
        if (playerLocation==1){//?????????
            System.out.println("                       |North: N/A| ");
            System.out.println("                              ^");
            System.out.println("                              |");
            System.out.println("|West: Town Hall| <- | Current: Post Office | -> |East: CavernsB|");
            System.out.println("                              |");
            System.out.println("                              v");
            System.out.println("                       |South: Field|");
        }
        if (playerLocation==2){//?????????
            System.out.println("                           |North: N/A| ");
            System.out.println("                                  ^");
            System.out.println("                                  |");
            System.out.println("|West: Post Office| <- | Current: CavernsB | -> |East: N/A|");
            System.out.println("                                  |");
            System.out.println("                                  v");
            System.out.println("                           |South: N/A|");
        }
        if (playerLocation==3){//?????????
            System.out.println("                  |North: Town Hall| ");
            System.out.println("                          ^");
            System.out.println("                          |");
            System.out.println("|West: N/A| <- | Current: CavernsA | -> |East: Field|");
            System.out.println("                          |");
            System.out.println("                          v");
            System.out.println("                  |South: Field|");
        }
        if (playerLocation==5){//??????????????????
        }
        if (playerLocation==6){
            System.out.println("                  |North: CavernsA| ");
            System.out.println("                          ^");
            System.out.println("                          |");
            System.out.println("|West: N/A| <- | Current: Field | -> |East: ExitGate|");
            System.out.println("                          |");
            System.out.println("                          v");
            System.out.println("                  |South: N/A|");
        }
        if (playerLocation==7){//?????????
            System.out.println("                    |North: Field| ");
            System.out.println("                            ^");
            System.out.println("                            |");
            System.out.println("|West: Field| <- | Current: ExitGate | -> |East: Field|");
            System.out.println("                            |");
            System.out.println("                            v");
            System.out.println("                    |South: N/A|");
        }
        if (playerLocation==8){//?????????
            System.out.println("                       |North: N/A| ");
            System.out.println("                                  ^");
            System.out.println("                                  |");
            System.out.println("|West: ExitGate| <- | Current: Field | -> |East: N/A|");
            System.out.println("                                  |");
            System.out.println("                                  v");
            System.out.println("                       |South: N/A|");
        }
    }
    void start(){
        Scanner in = new Scanner(System.in);
        String action="";
        String noun="";
        int currentBlock=4;
        System.out.println("==================================== \n Welcome to your CS3IOS Coursework (Java version)! \n | Game started, type 'exit' to quit. | \n==================================== \n");
        while (!action.equals("exit")) {
            String[] words=in.nextLine().split(" ");
            //action = in.next();
            action = words[0];
            if (words.length>1)
                noun=words[1];
            if (!action.equals("go") && !action.equals("examine") && !action.equals("take") && !action.equals("drop") && !action.equals("use") && !action.equals("exit")&& !action.equals("show")&& !action.equals("check")){
                System.out.println("Invalid command, please use go|examine|take|drop|use|show|check|exit");
            }else if (words.length>2) {
                System.out.println("Invalid command, please use \"action noun\"");
            }else{//????????????input????????????????????????s
                if (action.equals("exit")) {
                    System.out.println("You have quit the game successfully");
                    System.exit(0);
                }
                if (action.equals("go")) {
                    //check whether the destination is a  valid move
                    int n;
                    if (noun.equals("north") || noun.equals("east") || noun.equals("south") || noun.equals("west")) {
                        n = getNextBlock(currentBlock, noun);
                        if (n != -1) {
                            currentBlock = n;
                            System.out.println("Player moves to " + getBlockName(currentBlock));
                          //  printMap(currentBlock);
                        } else {
                            System.out.println("You can't move to there");
                        }
                    } else {
                        System.out.println("Invalid direction, please use north|east|south|west");
                    }
                } else if (action.equals("drop")) {
                    //action = drop, noun is stored in variable "noun"
                    //System.out.println("Not implemented yet");
                    for(int i=0;i< bagContents.size();i++){//************???????????????????????????????????????????????????????????????**************************
                        if(noun.equals(bagContents.get(i))){
                            bagContents.remove(bagContents.get(i));
                        }
                    }
                } else if (action.equals("show")) {
                    //action = show, noun is stored in the variable "noun", which should be map
                    if (noun.equals("map")) {
                        printMap(currentBlock);
                    } else if (noun.equals("bag")){//**********
                        printBag(bagContents);
                    } else {
                        System.out.println("Invalid noun, please use \"show map\"");
                    }
                } else if (action.equals("check")) {
                    //action = check, noun is stored in the variable "noun", which should be "block"
                    if (noun.equals("block")) {
                        System.out.println(getBlockName(currentBlock));
                    } else {
                        System.out.println("Invalid noun, please use \"check block\"");
                    }
                } else {
                    // Let the current block handle the command first
                    // The result of a given action will be stored in "result"
                    String result = "";
                    if (currentBlock == 0) {
                        result = block0(action, noun);
                        if(result=="photo" || result=="paper" || result=="toolboxes"||result=="map"||result=="key"||result=="battery"){
                            bagContents.add(result);
                        }
                    } else if (currentBlock == 1) {
                        result = block1(action, noun);
                    } else if (currentBlock == 2) {
                        result = block2(action, noun);
                    } else if (currentBlock == 3) {
                        result = block3(action, noun);
                    } else if (currentBlock == 4) {
                        result = block4(action, noun);
                    } else if (currentBlock == 5) {
                        result = block5(action, noun);
                    } else if (currentBlock == 6) {
                        result = block6(action, noun);
                    } else if (currentBlock == 7) {
                        result = block7(action, noun);
                    } else if (currentBlock == 8) {
                        result = block8(action, noun);
                    }
                    //Handle the result
                    //
                    if (action.equals("use") && noun.equals("key")&& result.equals("GameCompleted")){
                        System.out.println("Congratulation! You have finished this game!");
                        break;
                    }
                }
            }
        }//end while
    }

    public static void main(String[] args) {
        MainGame m=new MainGame();
        m.start();
    }
}

