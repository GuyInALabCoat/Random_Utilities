package com.company;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        System.out.print("Type a hex or decimal number: ");

        String input = StringReader();

        if (isNumeric(input)){          // checks if the input is a number i.e., a decimal number
            decimalToHex(Integer.parseInt(input));      // parses the number from the string and passes the value to the function
        } else {
            hexToDecimal(input);        // else the number has at least 1 non-numeric character and therefore may be a valid hex number
        }

    }

    private static void hexToDecimal(String str){

        int sum = 0;
        int number;
        str = new StringBuilder(str).reverse().toString();      // creates a new StringBuilder object with value of str, str is reversed and assigned to the variable of str with type String
        int length = str.length();      // calculates the length of the string

        for (int i = 0; i < length; i++) {      // from i = 0 to the length of the string - 1
            String a_character = Character.toString(str.charAt(i));     // find the character at position i, and assign it as a String to a_character
            number = regexString(a_character);      // call regexString which returns the decimal number of any hex letter, i.e., returns a 10 if a_character is "a".
            sum += (number * (int)Math.pow(16, i));     // starting from i = 0, add 16^i multiplied by number, where number is the coefficient of multiplication for that hex number
        }
        System.out.println(sum);        // print out the decimal total for the hex number
    }

    private static int regexString(String input){

        if (input.matches("\\d+")){     // if the input is an int, simply return that int
            return Integer.parseInt(input);
        } else if (input.matches("a")){     // else the input is a character from a - f, so return the decimal value of that character
            return 10;
        } else if (input.matches("b")){
            return 11;
        } else if (input.matches("c")){
            return 12;
        } else if (input.matches("d")){
            return 13;
        } else if (input.matches("e")){
            return 14;
        } else if (input.matches("f")){
            return 15;
        } else {
            return 0;
        }
    }

    private static void decimalToHex(int number){
        ArrayList<Character> hexnumber = new ArrayList<>();     // creates the arrayList where the hex number will be built-up character by character

            // pass the input number and the empty arrayList to the function powersOf16
            powersOf16(number, hexnumber);
    }


    private static String StringReader(){
        Scanner reader = new Scanner(System.in);

        return reader.nextLine();
    }

    private static int IntReader(){
        Scanner reader = new Scanner(System.in);

            return Integer.parseInt(reader.nextLine());
    }

    private static boolean isNumeric(String str){
        try{
            int i = Integer.parseInt(str);
        } catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

    private static void powersOf16(int number, ArrayList<Character> hexnumber){

        if (number <= 15){
            hexnumber.add(intToHex(number));        // add the hex representation of any number <= 15 to the arrayList

            System.out.println(getStringRepresentation(hexnumber));     // transform the arrayList to a String and print it to the screen. Voila.
        } else {

            int i = 1;
            int remainder;

            while (number > (int) Math.pow(16, i)) {        // while the number is greater than 16^i, increment i to find the power of 16 that is greater than the number
                i++;
            }
            i--;        // decrement i to find the last power than is less than the number

            remainder = (number / ((int) Math.pow(16, i)));     // find the remainder of dividing the number by the greatest power of 16 < number
            int subtractor = remainder * (int) Math.pow(16, i);     // find the greatest multiple of the power of 16 < number

            hexnumber.add(intToHex(remainder));     // add the remainder as a hex character to the arrayList

            powersOf16(number - subtractor, hexnumber);     // recurse on the difference between the number and the greatest multiple of 16 < number


            // For example, on number = 1000, i would be equal to 2
            // 16^2 is 256 which is the power of 16 < number
            // (int)1000 / 256 = 3 which is the remainder
            // 256 * 3 = 768 which is the subtractor
            // add 3 as a character to the arrayList
            // recurse on (1000 - 768) or 232

        }
    }

    private static Character intToHex(int remainder){

        if (remainder > 9){
            if (remainder == 10){       // if the number is > 9, return the character that corresponds to that decimal value
                return 'A';
            } else if (remainder == 11){
                return 'B';
            } else if (remainder == 12){
                return 'C';
            } else if (remainder == 13){
                return 'D';
            } else if (remainder == 14){
                return 'E';
            } else if (remainder == 15){
                return 'F';
            } else {
                System.out.println("Major ERROR!");
            }
        }
        return (Character.forDigit(remainder, 10));     // if the number is less than 10, return that number but as a character

    }

    private static String getStringRepresentation(ArrayList<Character> list){       // convert arrayList -> String

        StringBuilder builder = new StringBuilder(list.size()); // create a new StringBuilder object of the size of the arrayList
        list.forEach(builder::append);      // append each character in the arrayList to the builder object
        return builder.toString();      // return the StringBuilder object as a String
    }

}
