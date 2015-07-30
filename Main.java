import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class Main{
    
    public static void playIntro(){
        System.out.println("   Welcome to the Calculator!");
        System.out.println("   While you're using this calculator, input commands by typing the command then pressing enter. Make sure you type the command *exactly* as it is shown");
        System.out.println("   Would you like to:");
        System.out.println("   run the calculator -- type 'run'");
        System.out.println("   add a function -- type 'add'");
    }
    
    public static void getUserInput(){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine(); //gets user input, either 'run' or 'add'
        if(s.equals("run")){
            System.out.println("   You are now running the Calculator");
            Calculator.main();
        }
        else if(s.equals("add")){
            System.out.println("   Let's walk through adding a new function to the Calculator");
            System.out.println("   For the first three steps, examples will be given showing what input would recreate the existing Add and Subtract operations.");
            Creator creator = new Creator();
            creator.main();
        }
        else{System.out.println("   Invalid option. Please choose one of the listed options"); getUserInput();}
    }
    
    public static List<String> getOpList(){
        Scanner opListFile = null;
        try{
            opListFile = new Scanner(new FileInputStream("operations.txt"));
        }
        catch(FileNotFoundException e){
            System.out.println("Internal error. Cannot find operations file. Quitting.");
            System.exit(0);
        }
        List<String> opList = new ArrayList<String>(0);
        while(opListFile.hasNextLine()){opList.add(opListFile.nextLine());}
        return opList;
    }
    
    public static void main(String[] args){
        playIntro(); //plays the welcome message
        getUserInput();
    }
    
}