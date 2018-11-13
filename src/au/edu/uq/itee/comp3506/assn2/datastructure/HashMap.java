package au.edu.uq.itee.comp3506.assn2.datastructure;
//reference  http://blog.csdn.net/linxiang0591/article/details/39452043 View on 2017/10/22
public class HashMap {
    //set initial size as 2^5=32
    private int size=32;
    private Map table[];
    HashMap(){
        table=new Map[size];
    }
    /*
    Map Operation: Put->
    Associate the specified value with the specified key in this map.
     */
    public void put(String k,String v){
       //generate hashValue
        int hash=k.hashCode() % size;
        Map m=table[hash];
        if(m==null){
            Map mapIntoNewBucket=new Map(k,v);
            table[hash]=mapIntoNewBucket;

        }else{
            //handle the collision case
            //there is one key exist in this bucket
            if(m.key.equals(k)){
                // if the key is duplicated
                // overwrite the exist key-value pair
                m.setValue(v);
            }else{  //non-duplicated key
                //insert new key into end of list
                // in same bucket
                while(m.next!=null){
                    m=m.next;
                }
                Map mapIntoSameBucket=new Map(k,v);
                m.next=mapIntoSameBucket;
            }
        }
    }
    /*
    Map Operation: get ->
    Return the specific value which is associate with specific key
    in HashMap,.
    Return null if HashMap not contain the key
     */
    public String get(String k){
        int hash=k.hashCode() % size;
        Map m=table[hash];
        //if the bucket is found, then traverse the linkedlist
        //to see if the value associate key is present?
        while(m!=null){
            //find mapping key
            if(m.key.equals(k)){
                return m.getValue();
            }
            m=m.next;
        }
        return null;
    }
    public static void main(String[] args) {
        HashMap map = new HashMap();

        map.put("John", "1");
        map.put("Dan", "2");
        map.put("Chris", "3");
        map.put("Hello", "4");

        String value = map.get("Chris");
        System.out.println("test_1: "+value);
        map.put("Chris", "5");
        String value2 = map.get("Chris");
        System.out.println("test_2: "+value2);
    }
}

class Map{
    /*
    Define a simple Map data Structure
     with key and value
     */
    String key;
    String value;
    Map next;

    Map(String k,String v){
        this.key=k;
        this.value=v;
    }
    //Map basic operation that setValue, getValue, getKey,
    public String getValue(){
        return value;
    }
    public void setValue(String v){
        this.value=v;
    }
    public String getKey(){
        return key;
    }
}