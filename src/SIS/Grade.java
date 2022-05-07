package SIS;

import java.util.*;

//TODO: fix this mess
public class Grade {

    private char letterCode;
    private double value;

    Grade() {
    }

    Grade(double _g) {
        value = _g;
        computeGrade();
    }

    // getters:
    public char getLetterCode() {
        return letterCode;
    }
    public double getValue() {
        return value;
    }

    // setters:
    public void setValue(double _v) {
        value = _v;
    }
    public void computeGrade() {
        String _val = Double.toString(value);
        StringTokenizer st = new StringTokenizer(_val, ".");
        st.nextToken();
        int dec = Integer.parseInt(st.nextToken());
        double scaled_val = value;
        //loop to count number of digits after decimal point:
        int num_Size = 1;
        int temp = dec;
        while (temp > 9) {
            num_Size++;
            temp /= 10;
        }
        //now we divide the number after the decimal point by the number of digits
        //in order to find out if the number is <=0.5 or more so we can round:
        if (dec * Math.pow(10, -1 * num_Size) >= 0.5)
            scaled_val = Math.ceil(value);
        else
            scaled_val = Math.floor(value);
        if (scaled_val >= 90 && scaled_val <= 100)
            letterCode = 'A';
        else if (scaled_val >= 80 && scaled_val < 90)
            letterCode = 'B';
        else if (scaled_val >= 70 && scaled_val < 80)
            letterCode = 'C';
        else if (scaled_val >= 60 && scaled_val < 70)
            letterCode = 'D';
        else if (scaled_val >= 50 && scaled_val < 60)
            letterCode = 'F';
        //set the actual value to the rounded value;
        value = scaled_val;
    }

    public String toString() {
        return ("Letter Grade: " + letterCode + " Numeric value: " + value);
    }

}


