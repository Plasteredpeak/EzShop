package Attributes;

import java.io.Serializable;

public class Date implements Serializable {
    private int day,month,year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
    public void display(){
        System.out.printf("%d/%d/%d\n",day,month,year);
    }
}

