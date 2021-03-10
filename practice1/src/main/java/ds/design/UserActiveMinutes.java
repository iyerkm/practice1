package main.java.ds.design;

import java.util.*;
/*
At Twitter, we measure several metrics to assess the health of our user base and how much users enjoy using our product. One of the common metrics we measure is the time that users spend in the Twitter app. More precisely, we measure a metric called "User Active Minutes" (UAM), defined as follows.
Given each day is divided into 24 hours, and each hour into 60 minutes, this means each day consists of 1440 minutes. User active minutes for a given user is defined as the count of the number of "distinct" minutes out of those 1440 in which a given user takes some action on Twitter (e.g. click or read a tweet or tweet or hover). Multiple actions in the same minute are only counted as one minute.
Different users will have different numbers of UAMs in a 30-day period. For example, one user might spend 10K minutes on Twitter and another might spend only 5 minutes in a whole month. We would ideally like a binned histogram of the number of users who spend X minutes, for different values of X.

E.g.:
5M users spend between 0-100 minutes in the trailing 30 day period
6.3M users spend between 100-200 min in the trailing 30 day period
...
<etc>
Assume you are given 30 days of raw logs that have the format:
<user_id (64-bit long), unix_timestamp (milliseconds)>
Question: Given a bin size in minutes (integer), write some code to compute the histogram of UAMs across our user base.
UserID,Timestamp
123,12345 <- like a Tweet
123,12346 + 1mins <- open a video
HashMap<Long,Set<Long>> (userID, Set of minutes represetning the timsteamps)
Granular 100mins
int multiplier = 0
key (150) --> 0 --> map.size() - 1
0



Assumptions:
1. Granularity is of 1 minute
*/
public class UserActiveMinutes {
    Map<Long,Set<Integer>> userActivityMap = new HashMap<>();
    private static int MONTHLY_MINUTES=10;
    private static int binSize=2;
    public int[] result = new int[MONTHLY_MINUTES/binSize+1];

    public void generateUAMHistogram(List<UserActivity> input) {
        if(null==input || input.size()==0){
            throw new IllegalArgumentException("Input is empty");
        }

        for(UserActivity activity: input){
            long userId = activity.userId;
            long timeStamp = activity.timeStamp;
            int timeStampMin = millisToMinutes(timeStamp);

            userActivityMap.putIfAbsent(userId,new HashSet());
            Set<Integer> value = userActivityMap.get(userId);
            value.add(timeStampMin);
            userActivityMap.put(userId,value);
        }

        for(Map.Entry<Long,Set<Integer>> entry: userActivityMap.entrySet()){
            result[entry.getValue().size()/binSize]++;
        }
        //return result;
    }
    static Integer millisToMinutes(long millis){
        return (int) ((millis/1000)/60);
    }
    public static void main(String[] args) {
        UserActiveMinutes userActiveMinutes = new UserActiveMinutes();
        List<UserActivity> userActivityList = new ArrayList<>();
        userActivityList.add(new UserActivity(123,System.currentTimeMillis()));
        userActivityList.add(new UserActivity(123,System.currentTimeMillis()));
        userActivityList.add(new UserActivity(356,System.currentTimeMillis()+1000*60*4));
        userActivityList.add(new UserActivity(356,System.currentTimeMillis()+1000*60*6));
        userActivityList.add(new UserActivity(356,System.currentTimeMillis()+1000*60*8));
        userActivityList.add(new UserActivity(356,System.currentTimeMillis()+1000*60*10));
        userActivityList.add(new UserActivity(356,System.currentTimeMillis()+1000*60*12));

        userActivityList.add(new UserActivity(567,System.currentTimeMillis()+1000*60*4));
        userActivityList.add(new UserActivity(567,System.currentTimeMillis()+1000*60*6));
        userActivityList.add(new UserActivity(567,System.currentTimeMillis()+1000*60*8));
        userActivityList.add(new UserActivity(567,System.currentTimeMillis()+1000*60*10));
        userActivityList.add(new UserActivity(567,System.currentTimeMillis()+1000*60*12));
        userActiveMinutes.generateUAMHistogram(userActivityList);
        System.out.println(Arrays.toString(userActiveMinutes.result));
    }
}

class UserActivity{
    public long userId;
    public long timeStamp;
    public UserActivity(long userId, long timeStamp){
        this.userId=userId;
        this.timeStamp=timeStamp;
    }
}