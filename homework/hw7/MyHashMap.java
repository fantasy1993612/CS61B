import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyHashMap<T1, T2> implements Map61B<T1, T2> {

    private float loadFactor;
    private int fillCount;
    private Object[] table;
    private int initSize = 1;
    
    public MyHashMap(){
        table = new Object[1];
        this.loadFactor = 1.0f;
    }
    
    public MyHashMap(int initialSize){
        table = new Object[initialSize];
        this.loadFactor = 1.0f;
        this.initSize = initialSize;
    }
    
    public MyHashMap(int initialSize, float loadFactor){
        if(loadFactor <= 0.1f)
            throw new IllegalArgumentException("Load Factor must be greater than 0.1");
        
        table = new Object[initialSize];
        this.loadFactor = loadFactor;
        this.initSize = initialSize;
    }
    
    @Override
    public void clear() {
        // TODO Auto-generated method stub
        table = new Object[initSize];

    }

    @Override
    public boolean containsKey(T1 key) {
        
        return get(key) != null;
    }

    @Override
    public T2 get(T1 key) {     
        //choose bin 
        int index = getBucket(key, table);
        if(table[index] == null){   
            return null;
        } else {
            @SuppressWarnings("unchecked")
            ArrayList<Entry> list = ((ArrayList<Entry>) table[index]);
            int listIndex = list.indexOf(new Entry(key, null));
            if(listIndex == -1)
                return null; 
            
            Entry entry = list.get(listIndex);
            return entry.getValue();
        }       
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return fillCount;
    }

    @Override
    public void put(T1 key, T2 value) {
        //if load factor will be exceeded, resize table
        if(tooSmall())
            resize();
        
        //create Entry
        Entry entry = new Entry(key, value);
        
        //choose bin 
        int index = getBucket(key, table);
        
        addEntryToBucket(table, entry, index);
    }

    private int getBucket(T1 key, Object[] table) {
        int index = key.hashCode() % table.length;
        return index;
    }

    
    @SuppressWarnings("unchecked")
    private void addEntryToBucket(Object[] table, Entry entry, int index) {
        ArrayList<Entry> list;
        if(table[index] == null){
                        
            list = new ArrayList<Entry>();
            list.add(entry);
            fillCount += 1;
            table[index] = list;
        } else {
            list = ((ArrayList<Entry>) table[index]);
            if(!list.contains(entry)){
                            
                list.add(entry);
                fillCount += 1;
            }
            //this assignment is probably unnecessary... pass by reference
            //table[index] = list;
        }
    }

    private boolean tooSmall() {
        return ((float)(fillCount + 1)/table.length) > loadFactor;
    }

    @SuppressWarnings("unchecked")
    private void resize(){
        int newLen = (int) (((fillCount + 1) * 2) / loadFactor);
        fillCount = 0;
        //System.out.println("Resizing to size " + newLen);
        //iterate through all the keys and re-map them to the new array
        Object[] newTable = new Object[newLen];
        for(Object o : table){
            if(o != null){
                ArrayList<Entry> list = (ArrayList<Entry>)o;
                for(Entry entry : list){
                    int index = getBucket(entry.key, newTable);
                    addEntryToBucket(newTable, entry, index);
                }
            }   
        }
        table = newTable;
    }
    
    @Override
    public T2 remove(T1 key) {
        int index = getBucket(key, table);
        if(table[index] == null){   
            return null;
        } else {
            @SuppressWarnings("unchecked")
            ArrayList<Entry> list = ((ArrayList<Entry>) table[index]);
            int listIndex = list.indexOf(new Entry(key, null));
            if(listIndex == -1)
                return null; 
            
            //this is the only difference from "get"
            Entry entry = list.remove(listIndex);
            fillCount -= 1;
            return entry.getValue();
        }   
    }

    @Override
    public T2 remove(T1 key, T2 value) {
        int index = getBucket(key, table);
        if(table[index] == null){   
            return null;
        } else {
            @SuppressWarnings("unchecked")
            ArrayList<Entry> list = ((ArrayList<Entry>) table[index]);
            int listIndex = list.indexOf(new Entry(key, null));
            if(listIndex == -1)
                return null; 
            
            Entry entry = list.get(listIndex);
            if(entry.value == value){
                entry = list.remove(listIndex);
                fillCount -= 1;
                //if this is the last value, null the list
                if(list.size() == 0)
                    table[index] = null;
                
                return entry.getValue();
            }
            
            return null;
        }   
    }

    @Override
    public Set<T1> keySet() {
        Set<T1> output = new HashSet<T1>();
        for(int i = 0; i < table.length; i += 1){
            if(table[i] != null){
                @SuppressWarnings("unchecked")
                ArrayList<Entry> list = ((ArrayList<Entry>)table[i]);
                for(Entry entry : list){
                    output.add(entry.getKey());
                }
            }
        }
        return output.size() == 0 ? null : output;
    }
    
    @Override
    public String toString() {
        String output = "";
        for(int i = 0; i < table.length; i += 1){
            @SuppressWarnings("unchecked")
            String contents = table[i] == null ? "null" : ((ArrayList<Entry>)table[i]).toString();
            output += "[" + i + "] = " + contents + "\n";
        }
        return output;
    }
    
    private class Entry {
        T1 key;
        T2 value;
        
        public Entry(T1 key, T2 value){
            this.key = key;
            this.value = value;
        }
        
        public T1 getKey(){
            return key;
        }       
        
        public T2 getValue(){
            return value;
        }   
        
        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof MyHashMap.Entry) {
                @SuppressWarnings("unchecked")
                Entry other = (Entry) o;
                return this.getKey() == other.getKey();
            }
            return false;
        }
        
        @Override
        public String toString() {
            return "<" + key.toString() + ", " + value.toString() + ">";
        }
    }

}