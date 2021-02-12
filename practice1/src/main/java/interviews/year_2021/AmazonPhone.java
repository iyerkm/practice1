package main.java.interviews.year_2021;

import java.util.*;

public class AmazonPhone {
    public static void main(String[] args) {
        List<Offer> input = new ArrayList<Offer>();
        input.add(new Offer("seller1",1000,5,5));
        input.add(new Offer("seller2", 998,5,4));
        input.add(new Offer("seller3",1000,3,5));
        input.add(new Offer("seller4",1000,5,3));
        input.add(new Offer("seller5",997 ,5,4));
        input.add(new Offer("seller6",998 ,9,3));

        List<Offer> result = offers(input, new OfferChainedComparator(new RatingComparator(),
                new CostComparator(),new ShippingCostComparator()));
        for(Offer offer: result){
            System.out.println(offer.toString());
        }

    }
    public static List<Offer> offers(List<Offer> offers, Comparator comparator){
        Collections.sort(offers, comparator);
        return offers;
    }
}

class Offer{
    String sellerName;
    double cost;
    double shippingCost;
    double rating;
    public Offer(String sellerName,double cost, double shippingCost,double rating){
        this.rating = rating;
        this.cost = cost;
        this.sellerName = sellerName;
        this.shippingCost = shippingCost;
    }

    @Override
    public String toString() {
        return "sellerName:" + sellerName + " cost:" + cost + " shippingCost:" + shippingCost + " rating:" + rating;
    }
}

class RatingComparator implements Comparator<Offer> {
    public int compare(Offer o1, Offer o2) {
        return (int)( o2.rating - o1.rating);
    }
}

class CostComparator implements Comparator<Offer> {
    public int compare(Offer o1, Offer o2) {
        return (int)( o1.cost - o2.cost);
    }
}

class ShippingCostComparator implements Comparator<Offer> {
    public int compare(Offer o1, Offer o2) {
        return (int)( (o1.cost+o1.shippingCost) - (o2.cost + o2.shippingCost));
    }
}

class OfferChainedComparator implements Comparator<Offer> {

    private List<Comparator<Offer>> listComparators;

    public OfferChainedComparator(Comparator<Offer>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }

    @Override
    public int compare(Offer o1, Offer o2) {
        for (Comparator<Offer> comparator : listComparators) {
            int result = comparator.compare(o1, o2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}