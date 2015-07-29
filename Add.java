import java.util.List;

public class Add implements Operation{

    public int getNumArgs(){return 2;}
    
    public void getInfo(){
        System.out.println("   Enter two numbers to add, pressing enter after each.");
    }
    
    public String execute(List<Float> args){
        return Float.toString(args.get(0) + args.get(1));
    }

}