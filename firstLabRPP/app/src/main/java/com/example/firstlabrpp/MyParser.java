package com.example.firstlabrpp;

public class MyParser {
    public static String[] hundreds = {" ","сто " , "двести ", "триста ", "четыреста ", "пятьсот ", "шестьсот ", "семьсот ", "восемьсот ", "девятьсот "};
    public static String[] decades = {" ","десять" , "двадцать ", "тридцать ", "сорок ", "пятьдесят ", "шестьдесят ", "семьдесят ", "восемьдесят ", "девяносто "};
    public static String[] thousands = {" ","тысяча " , "две тысячи ", "три тысячи ", "четыре тысячи ", "пять тысячь ", "шесть тысячь ", "семь тысячь ", "восемь тысячь ", "девять тысячь "};
    public static String[] tenDecades = {" ","одиннадцать ", "двенадцать ","тринадцать ","четырнадцать ","пятнадцать ","шестнадцать ","семнадцать ","восемнадцать ","девятнадцать "};
    public static String[] numerals = {" "," один "," два "," три "," четыре "," пять "," шесть "," семь "," восемь ","девять "};

    public static int numLength;

    public static String getParsedString(int number){
        numLength= Integer.toString(number).length();

        if(number == 1000000) return "Миллион";

        int hundth = (number/100000)%10;
        int thDec = (number/10000)%10;
        int thousd = (number/1000)%10;
        int hundr = (number/100)%10;
        int dec = (number/10)%10;
        int num = (number%10);

        if ((dec == 1)&&(num !=0)) return hundreds[hundth] + decades[thDec] + thousands[thousd] + hundreds[hundr] + tenDecades[num];
        else return hundreds[hundth] + decades[thDec] + thousands[thousd] + hundreds[hundr] + decades[dec] + numerals[num];

    }
}
