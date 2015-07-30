import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class Creator{

    private String name;
    private int numArgs;
    private String info;
    private String opType;
    private String op;
    private boolean loopByUser;
    private int numRepeat;
    
    public void main(){
        Scanner in = new Scanner(System.in);
        name = getName(in);
        getOperationType(in);
        getOperationPart1(in);
        getNumArgs(in);
        info = getInfo(in);
    }
    
    private String getName(Scanner in){
        System.out.println("   What should the new operation be called? (i.e. Add or Subtract)");
        System.out.println("   WARNING! If you use the same name as an existing operation, it will be overwritten.");
        return in.nextLine();
    }
    
    private void getOperationType(Scanner in){
        System.out.println("   Now we'll define what this operation should do.");
        System.out.println("   It can repeat another operation a set ammount of times -- type 'for'");
        System.out.println("   or it can repeat another operation until a condition is met -- type 'while'");
        String opTypeInput = in.nextLine();
        if(opTypeInput.equals("for") || opTypeInput.equals("while")){opType = opTypeInput;}
        else{
            System.out.println("   Input not recognized. Let's try that again.");
            getOperationType(in);
        }
    }
    
    private void getOperationPart1(Scanner in){
        System.out.println("   How many times should the opeation be completed?");
        if(opType.equals("for")){
            System.out.println("   It can either be repeated a set ammount of times -- enter an integer value");
            System.out.println("   or it can be repeated according to one of the input numbers -- type 'input'");
            try{numRepeat = in.nextInt(); loopByUser = false;}
            catch(java.util.InputMismatchException e){
                if(in.nextLine().equals("input")){
                    System.out.println("   Ok. This operation will repeat itself according to the last number entered when it is run.");
                    loopByUser = true;
                }
                else{System.out.println("   Input not recognized. Let's try that again.");
                    getOperationPart1(in);}
            }
        }
        else if(opType.equals("while")){
            System.out.println("   After each run through, this operation will compare it's result to something and repeat until a condition is met.");
            System.out.println("   What should the result be compared too? Either enter an integer value, or type 'input' to have the result compared to a user-entered value");
            try{numRepeat = in.nextInt(); loopByUser = false;}
            catch(java.util.InputMismatchException e){
                if(in.nextLine().equals("input")){
                    System.out.println("   Ok. This operation will repeat itself according to the last number entered when it is run.");
                    loopByUser = true;
                }
                else{System.out.println("   Input not recognized. Let's try that again.");
                    getOperationPart1(in);}
            }
            System.out.println("   How should the result be compared?");
            System.out.println("   Until both numbers are 'equal'");
            System.out.println("   Until the result is 'greater than' the other number");
            System.out.println("   Until the result is 'less than' the other number");
            System.out.println("   Until the numbers are 'not equal'");
            String compType = in.nextLine();
            if(compTYpe.equals("equal")){comp = "=";}
            else if(compTYpe.equals("greater than")){comp = "<";}
            else if(compTYpe.equals("less than")){comp = ">";}
            else if(compTYpe.equals("not equal")){comp = "!=";}
            else{System.out.println("   Input not recognized. Let's go back.");
                getOperationPart1(in);}
        }
        else{
            System.out.println("   We've encountered an error. Let's back up.");
            getOperationType(in);
            getOperationPart1(in);
        }
        System.out.println("   What operation should be repeated? Select one from the list below.");
        List<String> opList = Main.getOpList();
        for(String op : opList){System.out.println(op);}
        getOperationPart2(in, opList);
    }
    
    private void getOperationPart2(Scanner in, List<String> opList){ //Split into two parts so that when given an invalid input, it can be restarted from here.
        String toDo = in.nextLine();
        if(!opList.contains(toDo)){
            System.out.println("   Oops... I can't do that. Try inputting an operation from the above list.");
            getOperationPart2(in, opList);
        }
        else{op = toDo;}
    }
    
    private void getNumArgs(){
        Class cls = Class.forName(op);
        operation = (Operation) cls.newInstance();
        numArgs = operation.getNumArgs();
        if(forByUser){numArgs += 1;}
    }
    
    private String getInfo(Scanner in){
        System.out.println("   What help message should be displayed to the user?");
        if(forByUser){System.out.println("   Remember that the last number input will be used to tell the operation how many times it should repeat.");}
        System.out.println("   (i.e. Enter two numbers to add, pressing enter after each.");
        System.out.println("   (i.e. Enter two numbers to subtract, pressing enter after each. The second number will be subtracted from the first number.");
        return in.nextLine();
    }
    
    private void buildOperation(Scanner in){ //Nothing is actually built until the end so that if it breaks half-way through, there are no incomplete remnants.
        //Write the name of the new operation in the operations file.
        try(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("opeations.txt"), "utf-8"))){
            writer.write("\n" + name);
        }
        catch(Exception e){
            System.out.println("   Error writing to operations file. Quitting.");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        
        //Write the new operation file
        try(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name + ".java"), "utf-8"))){
            writer.write("import java.util.List;\n\npublic class " + name + " implements Operation{\n\n\tpublic int getNumArgs(){return " + Integer.toString(numArgs) + ";}\n\n\tpublic void getInfo(){\n\t\tSystem.out.println(\"   " + info + "\");\n\t}\n\n\tpublic String execute(List<Float> args){\n\t\tfloat result = 0;\n\t\t");
            if(opType.equals("for")){
                writer.write("for(int i=0; i<");
                if(!loopByUser){writer.write(Integer.toString(numRepeat));}
                else{writer.write("args.get(getNumArgs()-1)");}
                writer.write("; i++){\n\t\t\t");
            }
            else if(opType.equals("while")){
                writer.write("while(result" + comp);
                if(!loopByUser){writer.write(Integer.toString(numRepeat));}
                else{writer.write("args.get(getNumArgs()-1");}
                writer.write("){\n\t\t\t");
            }
            else{
                System.out.println("   Internal error encountered. opType was recorded as: " + opType + ". Let's back up.");
                getOperationType(in);
                getOperationPart1(in);
            }
            //now execute
        }
        catch(Exception e){
            System.out.println("   Error writing file. Quitting");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        
    }
}