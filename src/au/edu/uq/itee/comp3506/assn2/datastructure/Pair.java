package au.edu.uq.itee.comp3506.assn2.datastructure;

public class Pair {
    public int key;
    public int val;

    public Pair(int key){
        this.key = key;
        this.val = 1;
    }
    
    public int get(int key) {
    	return val;
    }

    public void put(int key){
        if(this.key==key)
        {
        	this.val++;
        }
    }

    @Override
    public String toString(){
        return this.key+"-"+this.val;
    }
}