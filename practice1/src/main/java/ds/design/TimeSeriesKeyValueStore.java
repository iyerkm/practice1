package main.java.ds.design;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeSeriesKeyValueStore {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        KeyValueStore KV = new KeyValueStore();
        long t1=KV.set("foo","bar1");
        Thread.sleep(50);
        long t2=KV.set("foo","bar2");
        Thread.sleep(50);
        long t3=KV.set("foo","bar3");
        Thread.sleep(50);
        long t4=KV.set("foo","bar4");

        //System.out.println(KV.get("foo",));
        System.out.println(KV.get("foo",t1)); //bar1
        System.out.println(KV.get("foo",t2)); //bar2
        System.out.println(KV.get("foo",t3)); //bar3
        System.out.println(KV.get("foo",t3 + 120)); //bar4
        System.out.println(KV.get("foo",t4)); //bar4
        System.out.println(KV.get("foo")); //bar4

        //invalid tests
        System.out.println(KV.get("foo1",t1)); //""
        System.out.println(KV.get("foo",234234L)); //bar
    }
}

/*
class TimeSeriesKV{
    Map<Long,String> map;
    long latestTimeStamp;
}

*/
class KeyValueStore{
    Map<String,TreeMap<Long,String>> map;

    public KeyValueStore(){
        this.map = new HashMap<>();
    }


    public long set(String key, String value){
        Long currentTime = new Date().getTime();
        TreeMap<Long,String> values=this.map.getOrDefault(key,new TreeMap<>());
        System.out.println("key: " + key + " value: " + value + " time is: " + currentTime);
        values.put(currentTime,value);
        this.map.put(key,values);
        return currentTime;
    }

    public String get(String key,Long timeStamp) throws Exception{
        if(!map.containsKey(key)){
            return "";
        }
        TreeMap<Long,String> values = this.map.get(key);
        Map.Entry<Long,String> entry = values.floorEntry(timeStamp);
        if(null==entry){return "";}
        return entry.getValue();
    }

    public String get(String key) throws Exception{
        if(!map.containsKey(key)){
            return "";
        }
        Long currentTime = new Date().getTime();
        return this.get(key,currentTime);
    }
}
/*
class TimeMap {
    Map<String, List<TimeValue>> map;
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key,new ArrayList<>());
        map.get(key).add(new TimeValue(timestamp,value));
    }

    public String get(String key, int timestamp) {
        List<TimeValue> list = map.get(key);
        if(list==null || timestamp < list.get(0).timestamp){
            return "";
        }
        int start=0;
        int end = list.size()-1;
        int mid;
        while(start < end){
            mid = (start + end + 1) / 2;
            if(list.get(mid).timestamp > timestamp){
                end = mid-1;
            } else {
                start = mid;
            }
        }
        return list.get(end).value;
    }
}

class TimeValue{
    int timestamp;
    String value;
    public TimeValue(int timestamp, String value){
        this.timestamp = timestamp;
        this.value = value;
    }
}

 */