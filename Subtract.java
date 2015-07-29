import java.util.List;

public class Subtract implements Operation{

    public int getNumArgs(){return 2;}
    
    public void getInfo(){
        System.out.println("   Enter two numbers to subtract, pressing enter after each. The second number will be subtracted from the first number.");
    }
    
    public String execute(List<Float> args){
        return Float.toString(args.get(0) - args.get(1));
    }

}