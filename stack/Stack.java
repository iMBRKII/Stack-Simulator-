package stack;



import java.util.ArrayList;

/**
 *
 * @author MK
 */
public class Stack {

    private final ArrayList<String> array = new ArrayList<>();
    private int maxDiv;
    private String lastElement = null;

    public int getMaxDiv(){
        return maxDiv;
    }
    
    public String getLastElement(){
        return lastElement;
    }
    
    public int getSize(){
        return array.size();
    }
    
    public String getElementAt(int i){
        String x = array.get(i);
        return x;
    }
    
    public void setMaxDiv(int maxDiv){
        this.maxDiv = maxDiv;
    }
    
    public void setLastElement(String str){
        lastElement = str;
    }
    
    public void clear(){
        array.clear();
    }
    
    public void push(String value) {
        array.add(value);
        lastElement = value;
    }
    
    public String pop(){
        String temp = array.remove(array.size() - 1);
        lastElement = array.isEmpty() ? null : array.get(array.size() - 1);
        
        return temp;
    }
    
    public boolean isEmpty(){
        return array.isEmpty();
    }
    
    public boolean isFull(){
        return maxDiv == array.size();
    }
    
    public String peek(){
        return lastElement != null? lastElement : "null";
    }
    
    public boolean contain(String target){
        return linearSearch(target);
    }
    
    public String display(){
        return array.toString();
    }
    
    private boolean linearSearch(String key){
        for (int i = 0; i < array.size(); i++){
            if(array.get(i).equals(key)){
                return true;
            }
        }
        
        return false;
    }
}
