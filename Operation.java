import java.util.List;

public interface Operation{
    
    int getNumArgs();
    void getInfo();
    String execute(List<Float> args); //String to allow for broader response range
    
}