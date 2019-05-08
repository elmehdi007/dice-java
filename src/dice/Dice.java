package dice;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author EL MEHDI GUETTAR
 */

public class Dice {

    Scanner sc = new Scanner(System.in);
    int numberOfThrows = 0;
    private ArrayList autorizedDiceFaces = new ArrayList<String>();
    String throwsFaces = null;

    public Dice() {
        autorizedDiceFaces.add("1");
        autorizedDiceFaces.add("2");
        autorizedDiceFaces.add("3");
        autorizedDiceFaces.add("4");
        autorizedDiceFaces.add("5");
        autorizedDiceFaces.add("6");
    }

    int readNumberOfThrows() {
        boolean firstTimeGetInput = false;
        String txtAsk = "Please enter N ( 1 <= N <=1000000 ): ";
        try {
            do {
                System.out.print(txtAsk);
                String numberOfThrowsStr = this.sc.nextLine();
                numberOfThrows = Integer.parseInt(numberOfThrowsStr);
                if((numberOfThrows < 0 || numberOfThrows > 1000000)){
                        txtAsk = "Please enter another N ( 1 <= N <=1000000 ): ";
                }

            } while (numberOfThrows < 0 || numberOfThrows > 1000000);

        } catch (Exception e) {
            System.out.print(e);
        }

        return numberOfThrows;
    }

    private boolean checkThrowValueLength(String ThrowValue) {
        boolean checkThrowValue = true;
        if (ThrowValue.length() != this.numberOfThrows) {
            checkThrowValue = false;
        }
        return checkThrowValue;
    }

    private boolean checkThrowValue(String ThrowValue) {
        boolean checkThrowValue = true;

        String slitedThrowValue[] = ThrowValue.split("");
        for (int i = 0; i < slitedThrowValue.length; i++) {
            if (!autorizedDiceFaces.contains(slitedThrowValue[i])) {
                checkThrowValue = false;
            }
        }
        throwsFaces = ThrowValue;
        return checkThrowValue;
    }

    void readThrowsResultValues() {
        String txtAsk = "Please enter the throws result values: ";
        boolean etatCheckThrowValue;
        boolean etatCheckThrowValueLength ;
        
        try {
            do {
                System.out.print(txtAsk);
                this.throwsFaces = this.sc.nextLine();
                
                 etatCheckThrowValue = checkThrowValue(this.throwsFaces);
                 etatCheckThrowValueLength = checkThrowValueLength(this.throwsFaces);
                 if(etatCheckThrowValue == false){
                     txtAsk = "the throws result values incorrect: ";
                 }
                 else if(etatCheckThrowValueLength == false){
                       txtAsk = "Please check the throws result values length: ";
                 }
            } while (etatCheckThrowValue == false || etatCheckThrowValueLength == false);

        } catch (Exception e) {
            System.out.print(e);
        }

    }

//     for excercice 1
    int getCount6rolled() {
        int count6rolled = 0;

        Pattern pattern;
        Matcher matcher;

        String patternTxt = "(^[6]{2}[1-5]*[1-6]*$) | ((^[1-5])*[6]{2}[1-5]*[1-6]{1}) | ((^[1-5])*[6]{2}$) | ((^[6]{2})([1-5]*)([1-6]{1})([1-5]*)([6]{2}$)) | ((^[6]{2}) (([6]{2})*) ([6]{2}$))";
        pattern = Pattern.compile(patternTxt.replace(" ", ""));
        matcher = pattern.matcher(this.throwsFaces);

        while (matcher.find()) {
            count6rolled++;

        }

        return count6rolled;
    }

//    for excerice tow
    int longest_subsequence() {
        int longest_subsequence = 0;
        String splite[] = this.throwsFaces.split("6");

        for (int i = 0; i < splite.length; i++) {
            longest_subsequence = (longest_subsequence < splite[i].length()) ? splite[i].length() : longest_subsequence;

        }

        return longest_subsequence;
    }

    public static void main(String[] args) {
        Dice dice = new Dice();
        dice.readNumberOfThrows();
        dice.readThrowsResultValues();

        // output exercice 1
        System.out.println(dice.getCount6rolled());
        // end exercice 1

        // output exercice 2
        System.out.println(dice.longest_subsequence());
        // end exercice 2

    }

}