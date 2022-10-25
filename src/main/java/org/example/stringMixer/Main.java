package org.example.stringMixer;

import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
//        char[] test_val = {'a', 'b', 'c', 'd'};
        char[] test_val = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
                'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        //System.out.println(Arrays.toString(brute_force_prime_number(25)));

        //System.out.println(dec_to_sys(50, 3));

        generate_strings(1,4,10000, test_val);
    }

    /**
     * returns a to the power of b on integers
     * @param a - base
     * @param b - exponent
     * @return a to the power of b
     */
    public static long long_pow(long a, long b){
        long pow = a;

        for(long i=0; i<b; i++){
            pow = pow * a;
        }

        return pow;
    }

    /**
     * returns maximum combination of given number of characters in given range
     * @param char_length - length of user defined char array
     * @param min_k - start of the range
     * @param max_k - end of the range
     * @return maximum combination
     */
    public static long get_max_possible_combination(int char_length, int min_k, int max_k){
        long result = 0;

        for(int i=min_k; i<max_k; i++){
            result += long_pow(char_length, i);
        }

        return result;
    }

    /**
     * Check if given number is a prime or not
     * @param number - number to check
     * @return true if number is a prime one, false otherwise
     */
    public static boolean isPrimeBruteForce(long number) {
        for (long i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static long long_modulo(long a, long b){
        long reminder = 0;

        while(a >= 0){
            if(a < b){
                reminder = a;
            }
            a -= b;
        }

        return reminder;
    }

    /**
     * Translates decimal number to string of given characters
     * @param decimal - number to translate
     * @param addition - how many zeros before number
     * @param chars - array of available characters
     * @return translated string
     */
    public static String dec_to_sys_translate(long decimal, int addition, char[] chars){
        String result_str = "";
        long system = chars.length;

        if(decimal == 0){
            result_str += chars[0];
        }else{
            while(decimal > 0){
                int reminder = (int) long_modulo(decimal, system);
                //int reminder = decimal%system;

                result_str = chars[reminder] + result_str;
                decimal = decimal / system;
            }
        }

        for(int i=0; i<addition; i++){
            if(result_str.length() < addition+1){
                result_str = chars[0] + result_str;
            }
        }
        return result_str;
    }

    /**
     * Shuffle table of chars
     * @param tab - array of chars
     */
    public static void shuffle_table(char[] tab){
        for(int i=tab.length-1; i>0; i--){
            int random_index = (int) Math.random() * (i+1);

            char tmp = tab[random_index];
            tab[random_index] = tab[i];
            tab[i] = tmp;
        }
    }

    /**
     * Shuffle table of ints
     * @param tab - table of ints
     */
    public static void shuffle_table(int[] tab){
        for(int i=tab.length-1; i>0; i--){
            int random_index = (int) Math.random() * (i+1);

            int tmp = tab[random_index];
            tab[random_index] = tab[i];
            tab[i] = tmp;
        }
    }

    /**
     * Sum given array of ints
     * @param tab - array of ints
     * @return sum of array
     */
    public static int sum_array(int[] tab){
        int sum = 0;
        for(int i=0; i<tab.length; i++){
            sum += tab[i];
        }

        return sum;
    }

    /**
     * Returns array of possibilities of strings. From shortest to longests
     * @param min - minimal string
     * @param max - maximal string
     * @param quantity - number of lines to generate
     * @return array of randomly distributed lengths
     */
    public static int[] randomize_output_quantities(int min, int max, int quantity){
        int possibilities = (max - min + 1);
        int[] result = new int[possibilities];


        while(sum_array(result) != quantity){
            int control = quantity;

            for(int i=possibilities-1; i>=0; i--){
                control = (int) Math.floor(Math.random() * control);
                result[i] = control;
            }
        }

        shuffle_table(result);

        return result;
    }


    public static void validate_generator(int min, int max, int quantity, char[] char_tab) throws Exception {
        if (min < 1 || max < 1 || quantity < 1) {
            throw new Exception("min_length, max_length and quantity cannot be less than 0");
        }else if (min > max) {
            throw new Exception("min_length cannot be greater than max_length");
        }else if(quantity > get_max_possible_combination(char_tab.length, min, max)) {
            throw new Exception("min_length, max_length has to little possibilities for given quantity");
        }

    }

    public static void generate_strings(int min, int max, int quantity, char[] char_tab){
        try{
            validate_generator(min, max, quantity, char_tab);

            int[] possibilities = randomize_output_quantities(min, max, quantity);
            FileHandler str_file = new FileHandler(System.getProperty("user.home") + "\\Desktop");


            //System.out.println("Possibilities: " + Arrays.toString(possibilities));
            for(int i=0; i<possibilities.length; i++){
                //shuffle chars array
                shuffle_table(char_tab);
                //how many combination is possible in given case
                long max_possible = (long) Math.pow(char_tab.length, min+i);
                //System.out.println("Case: " + possibilities[i] + " Max possible: " + max_possible);

                long number = 0;
                long prime = 2;
                for(int j = possibilities[i]; j>0; j--){
                    if(number == -1 ||number >= max_possible){
                        prime++;
                        while(!isPrimeBruteForce(prime)){
                            prime++;
                        }
                        number = prime;
                    }
                    if(number == 0) {
                        //System.out.println("Max possible: " + max_possible + " Pos: " + i + " NUMBER: " + number + " " + dec_to_sys_translate(0, i, char_tab));
                        str_file.write_to_file(dec_to_sys_translate(0, i, char_tab));
                        number++;
                    } else if(number == 1) {
                        //System.out.println("Max possible: " + max_possible + " Pos: " + i + " NUMBER: " + number + " " + dec_to_sys_translate(1, i, char_tab));
                        str_file.write_to_file(dec_to_sys_translate(1, i, char_tab));
                        number++;
                    } else{
                        //System.out.println("Max possible: " + max_possible + " Pos: " + i + " NUMBER: " + number + " " + dec_to_sys_translate(number, i, char_tab));
                        str_file.write_to_file(dec_to_sys_translate(number, i, char_tab));
                        if(long_pow(number, prime) < Long.MAX_VALUE && long_pow(number, prime) > Long.MIN_VALUE){
                            number = long_pow(number, prime);
                        } else{
                            number = -1;
                        }

                    }
                }
            }

            MySQLCon.insert_job(str_file.get_file_path());
        } catch(Exception e){
            System.out.println(e);
        }

    }

}