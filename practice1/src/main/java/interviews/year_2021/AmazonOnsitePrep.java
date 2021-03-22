package main.java.interviews.year_2021;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main.java.common.Util.print;

public class AmazonOnsitePrep {
    public static void main(String[] args){
        /*
        2020-10-18 product1 200
        2020-10-30 product1 100 -> 12 days at 200
        2020-11-04 product1 120 -> 5 days at 100
        2020-11-09 product1 150 -> 5 days at 120
        2020-11-15 product1 100 -> 6 days at 150
        2020-11-25 product1 110 -> 10 days at 100
         */
        List<Log> logList = new ArrayList<>();
        logList.add(new Log(LocalDate.parse("2020-10-18"), "product1" , 200));
        logList.add(new Log(LocalDate.parse("2020-10-30"), "product1" , 100));
        logList.add(new Log(LocalDate.parse("2020-11-04"), "product1" , 120));
        logList.add(new Log(LocalDate.parse("2020-11-09"), "product1" , 150));
        logList.add(new Log(LocalDate.parse("2020-11-15"), "product1" , 100));
        logList.add(new Log(LocalDate.parse("2020-11-25"), "product1" , 110));

        print(findLongestStandingPrice(logList));
    }

    public static int findLongestStandingPrice(List<Log> logs){
        Map<Integer,Long> priceDaysMap = new HashMap<>();
        long longest= Long.MIN_VALUE;
        int price=0;
        int lastPrice = 0;
        LocalDate lastProcessedDate=null;
        for(Log log:logs){
            long diffInDays = lastProcessedDate==null ? 0:ChronoUnit.DAYS.between(lastProcessedDate,log.date);

            priceDaysMap.put(lastPrice,priceDaysMap.getOrDefault(lastPrice,0L)+diffInDays);
            lastProcessedDate = log.date;
            if(longest < priceDaysMap.get(lastPrice)){
                price = lastPrice;
                longest=priceDaysMap.get(lastPrice);
            }
            lastPrice = log.price;
        }
        print(priceDaysMap);
        return price;
    }
}
class Log{
    public LocalDate date;
    public String productId;
    public int price;
    public Log(LocalDate date,String productId, int price){
        this.date=date;
        this.productId=productId;
        this.price=price;
    }
}
