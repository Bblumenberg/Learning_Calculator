import java.util.List;

public interface Operation{
    
    int getNumArgs();
    void getInfo();
    String execute(List<Float> args);
    
}