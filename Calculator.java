import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;

public class Calculator{
    private static Scanner opListFile;
    
    private static void playIntro(){
        System.out.println("   Here is what I can do:");
        try{
            opListFile = new Scanner(new FileInputStream("operations.txt"));
        }
        catch(FileNotFoundException e){
            System.out.println("Internal error. Cannot find operations file.");
        }
        List<String> opList = new ArrayList<String>(0);
        while(opListFile.hasNextLine()){opList.add(opListFile.nextLine());}
        for(String op : opList){System.out.println(op);}
        System.out.println("   What should we do?");
        getOperationFromUser(opList);
    }
    
    private static void getOperationFromUser(List<String> opList){
        Scanner in = new Scanner(System.in);
        String toDo = in.nextLine();
        if(!opList.contains(toDo)){
            System.out.println("   Oops... I can't do that. Try inputting an operation from the above list");
            getOperationFromUser(opList);
        }
        else{
            execute(toDo);
        }
    }
    
    private static void execute(String toDo){
        int numArgs = 0;
        Operation operation = null;
        try{
            Class cls = Class.forName(toDo);
            operation = (Operation) cls.newInstance();
            operation.getInfo();
            numArgs = operation.getNumArgs();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        List<Float> argList = new ArrayList<Float>(0);
        for(int i=0; i<numArgs; i++){
            Scanner in = new Scanner(System.in);
            argList.add(Float.valueOf(in.nextLine()));
        }
        System.out.println("Result: " + operation.execute(argList));
        menuChoice();
    }
    
    private static void menuChoice(){
        System.out.println("   type 'top' to restart the calculator");
        System.out.println("   type 'calc' to return to the operation selection menu");
        System.out.println("   type 'quit' or press ctrl-C to quit");
        Scanner in = new Scanner(System.in);
        String toDo = in.nextLine();
        if(toDo.equals("top")){Main.main(new String[0]);}
        else if(toDo.equals("calc")){main();}
        else if(toDo.equals("quit")){System.exit(0);}
        else{System.out.println("   invalid option; qutting"); System.exit(0);}
    }
    
    public static void main(){
        playIntro();
    }

}