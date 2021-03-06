package interviews.year_2021;
import static main.java.common.Util.print;

public class FacebookPhone2021 {
    public static void main(String[] args){
        String input1="1.23";
        String input2="-1.23";
        String input3="-";
        String input4="-.";
        String input5="-123.";
        String input6="-.123";
        print(isValidNumber(input1));
        print(isValidNumber(input2));
        print(isValidNumber(input3));
        print(isValidNumber(input4));
        print(isValidNumber(input5));
        print(isValidNumber(input6));
    }

    private static boolean isValidNumber(String input) {
        if(null==input || input.length()==0){
            return false;
        }
        boolean seenNumber = false;
        boolean seenDot = false;

        for(int i=0;i<input.length();i++){
            char c=input.charAt(i);
            if(c>='0' && c<='9'){
                seenNumber=true;
            } else if(c=='.'){
                if(!seenNumber || seenDot || i==input.length()-1){
                    return false;
                }
            } else if (c == '-') {
                if(i!=0) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return seenNumber;
    }
}
