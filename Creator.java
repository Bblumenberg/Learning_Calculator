import java.util.Scanner;

public class Creator{

    private String name;
    private int numArgs;
    private String info;
    private String opType;
    
    public void main(){
        Scanner in = new Scanner(System.in);
        name = getName(in);
        getNumArgs(in);
        info = getInfo(in);
        getOperation(in);
        
    }
    
    public String getName(Scanner in){
        System.out.println("   What should the new operation be called? (i.e. Add or Subtract)");
        return in.nextLine();
    }
    
    public void getNumArgs(Scanner in){
        System.out.println("   How many numbers should the user input for this operation? Please enter an integer. (i.e. 2)");
        String numArgsString = in.nextLine();
        try{
            numArgs = Integer.valueOf(numArgsString);
        }
        catch(NumberFormatException e){
            System.out.println("   That's not an integer...");
            getNumArgs(in);
        }
    }
    
    public String getInfo(Scanner in){
        System.out.println("   What help message should be displayed to the user?");
        System.out.println("   (i.e. Enter two numbers to add, pressing enter after each.");
        System.out.println("   (i.e. Enter two numbers to subtract, pressing enter after each. The second number will be subtracted from the first number.");
        return in.nextLine();
    }
    
    public void getOperation(Scanner in){
        System.out.println("   Now we'll define what this operation should do.");
        System.out.println("   It can repeat an operation a set ammount of times -- type 'for'");
        System.out.println("   or it can repeat an operation until a condition is met -- type 'while'");
        String opTypeInput = in.nextLine();
        if(opTypeInput.equals("for") || opTypeInput.equals("while")){opType = opTypeInput;}
        else{
            System.out.println("   Input not recognized. Let's try that again.");
            getOperation(in);
        }
    }
}