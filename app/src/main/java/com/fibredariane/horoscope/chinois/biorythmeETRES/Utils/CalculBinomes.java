package com.fibredariane.horoscope.chinois.biorythmeETRES.Utils;

import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Binome;
import com.fibredariane.horoscope.chinois.biorythmeETRES.Models.Biorythme;

import java.time.*;

/**
 * Created by Carlotina on 12/04/2017.
 */

public class CalculBinomes {
    private static int mNbHour;
    private static int mNbMinute;
    private static int mMonth;



    public static String getStringBinome(int year, int month, int day, int hour, int minute) {
        String stringBinome = "";
        stringBinome = calcBinomeAnnee(year, month, day, hour, minute) + "." +
                calcBinomeMois(year, month, day, hour, minute) + "." +
                calcBinomeJour(year, month, day, hour, minute) + "." +
                calcBinomeHeure(year, month, day, hour, minute);
        return stringBinome;
    }


    public static int getDateAnneeNaissance(int year, int month, int day, int mHour, int mMinute) {
        int yearSwitch = year;
        int dayCalc = day;
        mMonth = month;

        if (month == 1 || month == 2)
            dayCalc = getEnergeticDay(year, month, day, mHour, mMinute);

        LocalDate dateNewYear = getAnneesLunaires(year);

        if ((dateNewYear.getMonthValue() > mMonth) || (dateNewYear.getMonthValue() == mMonth && dateNewYear.getDayOfMonth() > dayCalc))
            yearSwitch = yearSwitch - 1;

        return yearSwitch;
    }

    public static String calcBinomeAnnee(int year, int month, int day, int mHour, int mMinute) {
        String mStringBinome = "1";
        int yearSwitch = getDateAnneeNaissance(year, month, day, mHour, mMinute);

        switch (yearSwitch) {
            case 1924:
            case 1984:
                mStringBinome = "1";
                break;
            case 1925:
            case 1985:
                mStringBinome = "2";
                break;
            case 1926:
            case 1986:
                mStringBinome = "3";
                break;
            case 1927:
            case 1987:
                mStringBinome = "4";
                break;
            case 1928:
            case 1988:
                mStringBinome = "5";
                break;
            case 1929:
            case 1989:
                mStringBinome = "6";
                break;
            case 1930:
            case 1990:
                mStringBinome = "7";
                break;
            case 1931:
            case 1991:
                mStringBinome = "8";
                break;
            case 1932:
            case 1992:
                mStringBinome = "9";
                break;
            case 1933:
            case 1993:
                mStringBinome = "10";
                break;
            case 1934:
            case 1994:
                mStringBinome = "11";
                break;
            case 1935:
            case 1995:
                mStringBinome = "12";
                break;
            case 1936:
            case 1996:
                mStringBinome = "13";
                break;
            case 1937:
            case 1997:
                mStringBinome = "14";
                break;
            case 1938:
            case 1998:
                mStringBinome = "15";
                break;
            case 1939:
            case 1999:
                mStringBinome = "16";
                break;
            case 1940:
            case 2000:
                mStringBinome = "17";
                break;
            case 1941:
            case 2001:
                mStringBinome = "18";
                break;
            case 1942:
            case 2002:
                mStringBinome = "19";
                break;
            case 1943:
            case 2003:
                mStringBinome = "20";
                break;
            case 1944:
            case 2004:
                mStringBinome = "21";
                break;
            case 1945:
            case 2005:
                mStringBinome = "22";
                break;
            case 1946:
            case 2006:
                mStringBinome = "23";
                break;
            case 1947:
            case 2007:
                mStringBinome = "24";
                break;
            case 1948:
            case 2008:
                mStringBinome = "25";
                break;
            case 1949:
            case 2009:
                mStringBinome = "26";
                break;
            case 1950:
            case 2010:
                mStringBinome = "27";
                break;
            case 1951:
            case 2011:
                mStringBinome = "28";
                break;
            case 1952:
            case 2012:
                mStringBinome = "29";
                break;
            case 1953:
            case 2013:
                mStringBinome = "30";
                break;
            case 1954:
            case 2014:
                mStringBinome = "31";
                break;
            case 1955:
            case 2015:
                mStringBinome = "32";
                break;
            case 1956:
            case 2016:
                mStringBinome = "33";
                break;
            case 1957:
            case 2017:
                mStringBinome = "34";
                break;
            case 1958:
            case 2018:
                mStringBinome = "35";
                break;
            case 1959:
            case 2019:
                mStringBinome = "36";
                break;
            case 1960:
            case 2020:
                mStringBinome = "37";
                break;
            case 1961:
            case 2021:
                mStringBinome = "38";
                break;
            case 1962:
            case 2022:
                mStringBinome = "39";
                break;
            case 1963:
            case 2023:
                mStringBinome = "40";
                break;
            case 1964:
            case 2024:
                mStringBinome = "41";
                break;
            case 1965:
            case 2025:
                mStringBinome = "42";
                break;
            case 1966:
            case 2026:
                mStringBinome = "43";
                break;
            case 1967:
            case 2027:
                mStringBinome = "44";
                break;
            case 1968:
            case 2028:
                mStringBinome = "45";
                break;
            case 1969:
            case 2029:
                mStringBinome = "46";
                break;
            case 1970:
            case 2030:
                mStringBinome = "47";
                break;
            case 1971:
            case 2031:
                mStringBinome = "48";
                break;
            case 1972:
            case 2032:
                mStringBinome = "49";
                break;
            case 1973:
            case 2033:
                mStringBinome = "50";
                break;
            case 1974:
            case 2034:
                mStringBinome = "51";
                break;
            case 1975:
            case 2035:
                mStringBinome = "52";
                break;
            case 1976:
            case 2036:
                mStringBinome = "53";
                break;
            case 1977:
            case 2037:
                mStringBinome = "54";
                break;
            case 1978:
            case 2038:
                mStringBinome = "55";
                break;
            case 1979:
            case 2039:
                mStringBinome = "56";
                break;
            case 1980:
            case 2040:
                mStringBinome = "57";
                break;
            case 1981:
            case 2041:
                mStringBinome = "58";
                break;
            case 1982:
            case 2042:
                mStringBinome = "59";
                break;
            case 1983:
            case 2043:
                mStringBinome = "60";
                break;
        }

        return mStringBinome;
    }

    public static String calcBinomeMois(int year, int month, int day, int mHour, int mMinute) {
        String mStringBinome = "1";
        int monthSwitch = getMoisSolaire(year, month, day, mHour, mMinute);
        //    int yearCalc = year;
        int yearCalc = getDateAnneeNaissance(year, month, day, mHour, mMinute);
        if (month == 1 && monthSwitch == 12) {
            yearCalc = year - 1;
        }
        int lastDigitYear = yearCalc % 10;

        switch (monthSwitch) {
            case 1:
                if (lastDigitYear == 4 || lastDigitYear == 9)
                    mStringBinome = "14";
                if (lastDigitYear == 5 || lastDigitYear == 0)
                    mStringBinome = "26";
                if (lastDigitYear == 6 || lastDigitYear == 1)
                    mStringBinome = "38";
                if (lastDigitYear == 7 || lastDigitYear == 2)
                    mStringBinome = "50";
                if (lastDigitYear == 8 || lastDigitYear == 3)
                    mStringBinome = "2";
                break;
            case 2:
                if (lastDigitYear == 4 || lastDigitYear == 9)
                    mStringBinome = "3";
                if (lastDigitYear == 5 || lastDigitYear == 0)
                    mStringBinome = "15";
                if (lastDigitYear == 6 || lastDigitYear == 1)
                    mStringBinome = "27";
                if (lastDigitYear == 7 || lastDigitYear == 2)
                    mStringBinome = "39";
                if (lastDigitYear == 8 || lastDigitYear == 3)
                    mStringBinome = "51";
                break;
            case 3:
                if (lastDigitYear == 4 || lastDigitYear == 9)
                    mStringBinome = "4";
                if (lastDigitYear == 5 || lastDigitYear == 0)
                    mStringBinome = "16";
                if (lastDigitYear == 6 || lastDigitYear == 1)
                    mStringBinome = "28";
                if (lastDigitYear == 7 || lastDigitYear == 2)
                    mStringBinome = "40";
                if (lastDigitYear == 8 || lastDigitYear == 3)
                    mStringBinome = "52";
                break;
            case 4:
                if (lastDigitYear == 4 || lastDigitYear == 9)
                    mStringBinome = "5";
                if (lastDigitYear == 5 || lastDigitYear == 0)
                    mStringBinome = "17";
                if (lastDigitYear == 6 || lastDigitYear == 1)
                    mStringBinome = "29";
                if (lastDigitYear == 7 || lastDigitYear == 2)
                    mStringBinome = "41";
                if (lastDigitYear == 8 || lastDigitYear == 3)
                    mStringBinome = "53";
                break;
            case 5:
                if (lastDigitYear == 4 || lastDigitYear == 9)
                    mStringBinome = "6";
                if (lastDigitYear == 5 || lastDigitYear == 0)
                    mStringBinome = "18";
                if (lastDigitYear == 6 || lastDigitYear == 1)
                    mStringBinome = "30";
                if (lastDigitYear == 7 || lastDigitYear == 2)
                    mStringBinome = "42";
                if (lastDigitYear == 8 || lastDigitYear == 3)
                    mStringBinome = "54";
                break;
            case 6:
                if (lastDigitYear == 4 || lastDigitYear == 9)
                    mStringBinome = "7";
                if (lastDigitYear == 5 || lastDigitYear == 0)
                    mStringBinome = "19";
                if (lastDigitYear == 6 || lastDigitYear == 1)
                    mStringBinome = "31";
                if (lastDigitYear == 7 || lastDigitYear == 2)
                    mStringBinome = "43";
                if (lastDigitYear == 8 || lastDigitYear == 3)
                    mStringBinome = "55";
                break;
            case 7:
                if (lastDigitYear == 4 || lastDigitYear == 9)
                    mStringBinome = "8";
                if (lastDigitYear == 5 || lastDigitYear == 0)
                    mStringBinome = "20";
                if (lastDigitYear == 6 || lastDigitYear == 1)
                    mStringBinome = "32";
                if (lastDigitYear == 7 || lastDigitYear == 2)
                    mStringBinome = "44";
                if (lastDigitYear == 8 || lastDigitYear == 3)
                    mStringBinome = "56";
                break;
            case 8:
                if (lastDigitYear == 4 || lastDigitYear == 9)
                    mStringBinome = "9";
                if (lastDigitYear == 5 || lastDigitYear == 0)
                    mStringBinome = "21";
                if (lastDigitYear == 6 || lastDigitYear == 1)
                    mStringBinome = "33";
                if (lastDigitYear == 7 || lastDigitYear == 2)
                    mStringBinome = "45";
                if (lastDigitYear == 8 || lastDigitYear == 3)
                    mStringBinome = "57";
                break;
            case 9:
                if (lastDigitYear == 4 || lastDigitYear == 9)
                    mStringBinome = "10";
                if (lastDigitYear == 5 || lastDigitYear == 0)
                    mStringBinome = "22";
                if (lastDigitYear == 6 || lastDigitYear == 1)
                    mStringBinome = "34";
                if (lastDigitYear == 7 || lastDigitYear == 2)
                    mStringBinome = "46";
                if (lastDigitYear == 8 || lastDigitYear == 3)
                    mStringBinome = "58";
                break;
            case 10:
                if (lastDigitYear == 4 || lastDigitYear == 9)
                    mStringBinome = "11";
                if (lastDigitYear == 5 || lastDigitYear == 0)
                    mStringBinome = "23";
                if (lastDigitYear == 6 || lastDigitYear == 1)
                    mStringBinome = "35";
                if (lastDigitYear == 7 || lastDigitYear == 2)
                    mStringBinome = "47";
                if (lastDigitYear == 8 || lastDigitYear == 3)
                    mStringBinome = "59";
                break;
            case 11:
                if (lastDigitYear == 4 || lastDigitYear == 9)
                    mStringBinome = "12";
                if (lastDigitYear == 5 || lastDigitYear == 0)
                    mStringBinome = "24";
                if (lastDigitYear == 6 || lastDigitYear == 1)
                    mStringBinome = "36";
                if (lastDigitYear == 7 || lastDigitYear == 2)
                    mStringBinome = "48";
                if (lastDigitYear == 8 || lastDigitYear == 3)
                    mStringBinome = "60";
                break;
            case 12:
                if (lastDigitYear == 4 || lastDigitYear == 9)
                    mStringBinome = "13";
                if (lastDigitYear == 5 || lastDigitYear == 0)
                    mStringBinome = "25";
                if (lastDigitYear == 6 || lastDigitYear == 1)
                    mStringBinome = "37";
                if (lastDigitYear == 7 || lastDigitYear == 2)
                    mStringBinome = "49";
                if (lastDigitYear == 8 || lastDigitYear == 3)
                    mStringBinome = "1";
                break;

        }
        return mStringBinome;
    }

    public static String calcBinomeJour(int year, int month, int day, int mHour, int mMinute) {

        int dayBinome = getEnergeticDay(year, month, day, mHour, mMinute) + getIntDay(year / 10, year % 10) + getIntMonthYear(month, year);
        if (dayBinome != 60) {
            dayBinome = dayBinome % 60;
        }
        return String.valueOf(dayBinome);

    }

    public static String calcBinomeHeure(int year, int month, int day, int mHour, int mMinute) {
        String mStringBinome = "1";

        int dayBinome = getEnergeticDay(year, month, day, mHour, mMinute) + getIntDay(year / 10, year % 10) + getIntMonthYear(month, year);
        if (dayBinome != 60) {
            dayBinome = dayBinome % 60;
        }
        int dayBinomeModulo = dayBinome % 10;
        mHour = mHour - 1;
        switch (dayBinomeModulo) {
            case 1:
            case 6:
                if (mHour >= 23 || mHour == 0)
                    mStringBinome = "1";
                if (mHour >= 1 && mHour < 3)
                    mStringBinome = "2";
                if (mHour >= 3 && mHour < 5)
                    mStringBinome = "3";
                if (mHour >= 5 && mHour < 7)
                    mStringBinome = "4";
                if (mHour >= 7 && mHour < 9)
                    mStringBinome = "5";
                if (mHour >= 9 && mHour < 11)
                    mStringBinome = "6";
                if (mHour >= 11 && mHour < 13)
                    mStringBinome = "7";
                if (mHour >= 13 && mHour < 15)
                    mStringBinome = "8";
                if (mHour >= 15 && mHour < 17)
                    mStringBinome = "9";
                if (mHour >= 17 && mHour < 19)
                    mStringBinome = "10";
                if (mHour >= 19 && mHour < 21)
                    mStringBinome = "11";
                if (mHour >= 21 && mHour < 23)
                    mStringBinome = "12";
                break;
            case 2:
            case 7:
                if (mHour >= 23 || mHour == 0)
                    mStringBinome = "13";
                if (mHour >= 1 && mHour < 3)
                    mStringBinome = "14";
                if (mHour >= 3 && mHour < 5)
                    mStringBinome = "15";
                if (mHour >= 5 && mHour < 7)
                    mStringBinome = "16";
                if (mHour >= 7 && mHour < 9)
                    mStringBinome = "17";
                if (mHour >= 9 && mHour < 11)
                    mStringBinome = "18";
                if (mHour >= 11 && mHour < 13)
                    mStringBinome = "19";
                if (mHour >= 13 && mHour < 15)
                    mStringBinome = "20";
                if (mHour >= 15 && mHour < 17)
                    mStringBinome = "21";
                if (mHour >= 17 && mHour < 19)
                    mStringBinome = "22";
                if (mHour >= 19 && mHour < 21)
                    mStringBinome = "23";
                if (mHour >= 21 && mHour < 23)
                    mStringBinome = "24";
                break;
            case 3:
            case 8:
                if (mHour >= 23 || mHour == 0)
                    mStringBinome = "25";
                if (mHour >= 1 && mHour < 3)
                    mStringBinome = "26";
                if (mHour >= 3 && mHour < 5)
                    mStringBinome = "27";
                if (mHour >= 5 && mHour < 7)
                    mStringBinome = "28";
                if (mHour >= 7 && mHour < 9)
                    mStringBinome = "29";
                if (mHour >= 9 && mHour < 11)
                    mStringBinome = "30";
                if (mHour >= 11 && mHour < 13)
                    mStringBinome = "31";
                if (mHour >= 13 && mHour < 15)
                    mStringBinome = "32";
                if (mHour >= 15 && mHour < 17)
                    mStringBinome = "33";
                if (mHour >= 17 && mHour < 19)
                    mStringBinome = "34";
                if (mHour >= 19 && mHour < 21)
                    mStringBinome = "35";
                if (mHour >= 21 && mHour < 23)
                    mStringBinome = "36";
                break;
            case 4:
            case 9:
                if (mHour >= 23 || mHour == 0)
                    mStringBinome = "37";
                if (mHour >= 1 && mHour < 3)
                    mStringBinome = "38";
                if (mHour >= 3 && mHour < 5)
                    mStringBinome = "39";
                if (mHour >= 5 && mHour < 7)
                    mStringBinome = "40";
                if (mHour >= 7 && mHour < 9)
                    mStringBinome = "41";
                if (mHour >= 9 && mHour < 11)
                    mStringBinome = "42";
                if (mHour >= 11 && mHour < 13)
                    mStringBinome = "43";
                if (mHour >= 13 && mHour < 15)
                    mStringBinome = "44";
                if (mHour >= 15 && mHour < 17)
                    mStringBinome = "45";
                if (mHour >= 17 && mHour < 19)
                    mStringBinome = "46";
                if (mHour >= 19 && mHour < 21)
                    mStringBinome = "47";
                if (mHour >= 21 && mHour < 23)
                    mStringBinome = "48";
                break;
            case 5:
            case 0:
                if (mHour >= 23 || mHour == 0)
                    mStringBinome = "49";
                if (mHour >= 1 && mHour < 3)
                    mStringBinome = "50";
                if (mHour >= 3 && mHour < 5)
                    mStringBinome = "51";
                if (mHour >= 5 && mHour < 7)
                    mStringBinome = "52";
                if (mHour >= 7 && mHour < 9)
                    mStringBinome = "53";
                if (mHour >= 9 && mHour < 11)
                    mStringBinome = "54";
                if (mHour >= 11 && mHour < 13)
                    mStringBinome = "55";
                if (mHour >= 13 && mHour < 15)
                    mStringBinome = "56";
                if (mHour >= 15 && mHour < 17)
                    mStringBinome = "57";
                if (mHour >= 17 && mHour < 19)
                    mStringBinome = "58";
                if (mHour >= 19 && mHour < 21)
                    mStringBinome = "59";
                if (mHour >= 21 && mHour < 23)
                    mStringBinome = "60";
                break;

        }
        return mStringBinome;
    }

    private static int getMoisSolaire(int year, int month, int day, int mHour, int mMinute) {
        int returnMonth = month;
        int dayCalc = day;
        if (day > 3 || day < 9) {
            dayCalc = getEnergeticDay(year, month, day, mHour, mMinute);
        }
        switch (month) {
            case 1:
                if (dayCalc < 6)
                    returnMonth = 12;
                break;
            case 2:
                if (dayCalc < 4)
                    returnMonth = 1;
                break;
            case 3:
                if (dayCalc < 5)
                    returnMonth = 2;
                break;
            case 4:
                if (dayCalc < 5)
                    returnMonth = 3;
                break;
            case 5:
                if (dayCalc < 5)
                    returnMonth = 4;
                break;
            case 6:
                if (dayCalc < 6)
                    returnMonth = 5;
                break;
            case 7:
                if (dayCalc < 7)
                    returnMonth = 6;
                break;
            case 8:
                if (dayCalc < 8)
                    returnMonth = 7;
                break;
            case 9:
                if (dayCalc < 8)
                    returnMonth = 8;
                break;
            case 10:
                if (dayCalc < 8)
                    returnMonth = 9;
                break;
            case 11:
                if (dayCalc < 7)
                    returnMonth = 10;
                break;
            case 12:
                if (dayCalc < 7)
                    returnMonth = 11;
                break;

        }
        return returnMonth;
    }


    public static int calcIdBinomeAnnee(int year, int month, int day, int mHour, int mMinute) {
        Integer mIdBinome = 1;
        int yearSwitch = getDateAnneeNaissance(year, month, day, mHour, mMinute);

        switch (yearSwitch) {
            case 1924:
            case 1984:
                mIdBinome = 1;
                break;
            case 1925:
            case 1985:
                mIdBinome = 2;
                break;
            case 1926:
            case 1986:
                mIdBinome = 3;
                break;
            case 1927:
            case 1987:
                mIdBinome = 4;
                break;
            case 1928:
            case 1988:
                mIdBinome = 5;
                break;
            case 1929:
            case 1989:
                mIdBinome = 6;
                break;
            case 1930:
            case 1990:
                mIdBinome = 7;
                break;
            case 1931:
            case 1991:
                mIdBinome = 8;
                break;
            case 1932:
            case 1992:
                mIdBinome = 9;
                break;
            case 1933:
            case 1993:
                mIdBinome = 10;
                break;
            case 1934:
            case 1994:
                mIdBinome = 11;
                break;
            case 1935:
            case 1995:
                mIdBinome = 12;
                break;
            case 1936:
            case 1996:
                mIdBinome = 13;
                break;
            case 1937:
            case 1997:
                mIdBinome = 14;
                break;
            case 1938:
            case 1998:
                mIdBinome = 15;
                break;
            case 1939:
            case 1999:
                mIdBinome = 16;
                break;
            case 1940:
            case 2000:
                mIdBinome = 17;
                break;
            case 1941:
            case 2001:
                mIdBinome = 18;
                break;
            case 1942:
            case 2002:
                mIdBinome = 19;
                break;
            case 1943:
            case 2003:
                mIdBinome = 20;
                break;
            case 1944:
            case 2004:
                mIdBinome = 21;
                break;
            case 1945:
            case 2005:
                mIdBinome = 22;
                break;
            case 1946:
            case 2006:
                mIdBinome = 23;
                break;
            case 1947:
            case 2007:
                mIdBinome = 24;
                break;
            case 1948:
            case 2008:
                mIdBinome = 25;
                break;
            case 1949:
            case 2009:
                mIdBinome = 26;
                break;
            case 1950:
            case 2010:
                mIdBinome = 27;
                break;
            case 1951:
            case 2011:
                mIdBinome = 28;
                break;
            case 1952:
            case 2012:
                mIdBinome = 29;
                break;
            case 1953:
            case 2013:
                mIdBinome = 30;
                break;
            case 1954:
            case 2014:
                mIdBinome = 31;
                break;
            case 1955:
            case 2015:
                mIdBinome = 32;
                break;
            case 1956:
            case 2016:
                mIdBinome = 33;
                break;
            case 1957:
            case 2017:
                mIdBinome = 34;
                break;
            case 1958:
            case 2018:
                mIdBinome = 35;
                break;
            case 1959:
            case 2019:
                mIdBinome = 36;
                break;
            case 1960:
            case 2020:
                mIdBinome = 37;
                break;
            case 1961:
            case 2021:
                mIdBinome = 38;
                break;
            case 1962:
            case 2022:
                mIdBinome = 39;
                break;
            case 1963:
            case 2023:
                mIdBinome = 40;
                break;
            case 1964:
            case 2024:
                mIdBinome = 41;
                break;
            case 1965:
            case 2025:
                mIdBinome = 42;
                break;
            case 1966:
            case 2026:
                mIdBinome = 43;
                break;
            case 1967:
            case 2027:
                mIdBinome = 44;
                break;
            case 1968:
            case 2028:
                mIdBinome = 45;
                break;
            case 1969:
            case 2029:
                mIdBinome = 46;
                break;
            case 1970:
            case 2030:
                mIdBinome = 47;
                break;
            case 1971:
            case 2031:
                mIdBinome = 48;
                break;
            case 1972:
            case 2032:
                mIdBinome = 49;
                break;
            case 1973:
            case 2033:
                mIdBinome = 50;
                break;
            case 1974:
            case 2034:
                mIdBinome = 51;
                break;
            case 1975:
            case 2035:
                mIdBinome = 52;
                break;
            case 1976:
            case 2036:
                mIdBinome = 53;
                break;
            case 1977:
            case 2037:
                mIdBinome = 54;
                break;
            case 1978:
            case 2038:
                mIdBinome = 55;
                break;
            case 1979:
            case 2039:
                mIdBinome = 56;
                break;
            case 1980:
            case 2040:
                mIdBinome = 57;
                break;
            case 1981:
            case 2041:
                mIdBinome = 58;
                break;
            case 1982:
            case 2042:
                mIdBinome = 59;
                break;
            case 1983:
            case 2043:
                mIdBinome = 59;
                break;
        }
        return mIdBinome;
    }


    public static LocalDate getAnneesLunaires(int year) {
        int month = 0;
        int dayOfMonth = 0;
        switch (year) {
            case 1972:
                month = 1;
                dayOfMonth = 16;
                break;
            case 1966:
                month = 1;
                dayOfMonth = 21;
                break;
            case 1947:
            case 2004:
                month = 1;
                dayOfMonth = 22;
                break;
            case 1974:
            case 1993:
            case 2012:
            case 2023:
                month = 1;
                dayOfMonth = 23;
                break;
            case 1955:
            case 2001:
            case 1936:
                month = 1;
                dayOfMonth = 24;
                break;
            case 1925:
            case 1944:
            case 1963:
            case 1982:
            case 2020:
                month = 1;
                dayOfMonth = 25;
                break;
            case 2009:
            case 1933:
                month = 1;
                dayOfMonth = 26;
                break;
            case 1941:
            case 1952:
            case 1971:
            case 1990:
            case 1979:
                month = 1;
                dayOfMonth = 27;
                break;
            case 1960:
            case 1998:
            case 2017:
                month = 1;
                dayOfMonth = 28;
                break;
            case 1949:
            case 1987:
            case 2006:
                month = 1;
                dayOfMonth = 29;
                break;
            case 1930:
            case 1968:
                month = 1;
                dayOfMonth = 30;
                break;
            case 1938:
            case 1957:
            case 1976:
            case 1995:
            case 2014:
                month = 1;
                dayOfMonth = 31;
                break;
            case 2003:
            case 2022:
                month = 2;
                dayOfMonth = 1;
                break;
            case 1927:
            case 1946:
            case 1965:
            case 1984:
                month = 2;
                dayOfMonth = 2;
                break;
            case 1954:
            case 1973:
            case 2011:
                month = 2;
                dayOfMonth = 3;
                break;
            case 1935:
            case 1992:
                month = 2;
                dayOfMonth = 4;
                break;
            case 1924:
            case 1943:
            case 1962:
            case 1981:
            case 2000:
            case 2019:
                month = 2;
                dayOfMonth = 5;
                break;
            case 1932:
            case 1951:
            case 1970:
            case 1989:
                month = 2;
                dayOfMonth = 6;
                break;
            case 1978:
            case 1997:
            case 2008:
                month = 2;
                dayOfMonth = 7;
                break;
            case 1940:
            case 1959:
            case 2016:
                month = 2;
                dayOfMonth = 8;
                break;
            case 1967:
            case 1986:
            case 2005:
                month = 2;
                dayOfMonth = 9;
                break;
            case 1929:
            case 1948:
            case 1994:
            case 2013:
                month = 2;
                dayOfMonth = 10;
                break;
            case 1937:
            case 1975:
                month = 2;
                dayOfMonth = 11;
                break;
            case 1956:
            case 2002:
            case 2021:
                month = 2;
                dayOfMonth = 12;
                break;
            case 1926:
            case 1945:
            case 1964:
            case 1983:
                month = 2;
                dayOfMonth = 13;
                break;
            case 1934:
            case 1953:
            case 2010:
                month = 2;
                dayOfMonth = 14;
                break;
            case 1942:
            case 1961:
            case 1991:
                month = 2;
                dayOfMonth = 15;
                break;
            case 1980:
            case 1999:
            case 2018:
                month = 2;
                dayOfMonth = 16;
                break;
            case 1931:
            case 1950:
            case 1969:
            case 1988:
                month = 2;
                dayOfMonth = 17;
                break;
            case 1958:
            case 1977:
            case 2007:
                month = 2;
                dayOfMonth = 18;
                break;
            case 1939:
            case 1996:
            case 2015:
                month = 2;
                dayOfMonth = 19;
                break;
            case 1985:
                month = 2;
                dayOfMonth = 20;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + year);
        }

        return LocalDate.of(year, month, dayOfMonth);
    }

    private static void getFuseauHoraire() {
        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime dateRef = LocalDateTime.now(ZoneId.of("+08:00"));
        mNbHour = dateRef.getHour() - dateNow.getHour();
        if (mNbHour < 0)
            mNbHour = 24 + mNbHour;
        mNbMinute = dateRef.getMinute() - dateNow.getMinute();
        if (mNbMinute < 0)
            mNbMinute = 60 + mNbMinute;
    }

    private static int getEnergeticDay(int year, int month, int day, int mHour, int mMinute) {
        getFuseauHoraire();
        int minuteCalc = mMinute + mNbMinute;
        int hourCalc;
        int dayCalc = day;
        if (minuteCalc >= 60) {
            hourCalc = mHour + mNbHour + 1;
        } else {
            hourCalc = mHour + mNbHour;
        }
        // appartient au jour énergétique précédent
        if (hourCalc >= -1 && hourCalc < 0) {
            dayCalc = getPreviousDay(day, month, year);
        }
        // appartient au jour énergétique suivant
        if (hourCalc >= 23) {
            dayCalc = getNextDay(day, month, year);
        }
        return dayCalc;
    }

    private static int getIntDay(int numberOfDozen, int numberOfUnit) {
        int intDay = 0;
        switch (numberOfUnit) {
            case 0:
                switch (numberOfDozen) {
                    case 190:
                        intDay = 10;
                        break;
                    case 191:
                    case 199:
                        intDay = 2;
                        break;
                    case 192:
                    case 200:
                        intDay = 54;
                        break;
                    case 193:
                    case 201:
                        intDay = 47;
                        break;
                    case 194:
                    case 202:
                        intDay = 39;
                        break;
                    case 195:
                    case 203:
                        intDay = 32;
                        break;
                    case 196:
                    case 204:
                        intDay = 24;
                        break;
                    case 197:
                    case 205:
                        intDay = 17;
                        break;
                    case 198:
                    case 206:
                        intDay = 9;
                        break;
                }
                ;
                break;

            case 1:
                switch (numberOfDozen) {
                    case 190:
                    case 198:
                        intDay = 15;
                        break;
                    case 191:
                    case 199:
                        intDay = 7;
                        break;
                    case 192:
                    case 200:
                        intDay = 0;
                        break;
                    case 193:
                    case 201:
                        intDay = 52;
                        break;
                    case 194:
                    case 202:
                        intDay = 45;
                        break;
                    case 195:
                    case 203:
                        intDay = 37;
                        break;
                    case 196:
                    case 204:
                        intDay = 30;
                        break;
                    case 197:
                    case 205:
                        intDay = 22;
                        break;
                }
                ;
                break;
            case 2:
                switch (numberOfDozen) {
                    case 190:
                    case 198:
                        intDay = 20;
                        break;
                    case 191:
                    case 199:
                        intDay = 12;
                        break;
                    case 192:
                    case 200:
                        intDay = 5;
                        break;
                    case 193:
                    case 201:
                        intDay = 57;
                        break;
                    case 194:
                    case 202:
                        intDay = 50;
                        break;
                    case 195:
                    case 203:
                        intDay = 42;
                        break;
                    case 196:
                    case 204:
                        intDay = 35;
                        break;
                    case 197:
                    case 205:
                        intDay = 27;
                        break;
                }
                ;
                break;
            case 3:
                switch (numberOfDozen) {
                    case 190:
                    case 198:
                        intDay = 25;
                        break;
                    case 191:
                    case 199:
                        intDay = 18;
                        break;
                    case 192:
                    case 200:
                        intDay = 10;
                        break;
                    case 193:
                    case 201:
                        intDay = 3;
                        break;
                    case 194:
                    case 202:
                        intDay = 55;
                        break;
                    case 195:
                    case 203:
                        intDay = 48;
                        break;
                    case 196:
                    case 204:
                        intDay = 40;
                        break;
                    case 197:
                    case 205:
                        intDay = 33;
                        break;
                }
                ;
                break;
            case 4:
                switch (numberOfDozen) {
                    case 190:
                    case 198:
                        intDay = 30;
                        break;
                    case 191:
                    case 199:
                        intDay = 23;
                        break;
                    case 192:
                    case 200:
                        intDay = 15;
                        break;
                    case 193:
                    case 201:
                        intDay = 8;
                        break;
                    case 194:
                    case 202:
                        intDay = 0;
                        break;
                    case 195:
                    case 203:
                        intDay = 53;
                        break;
                    case 196:
                    case 204:
                        intDay = 45;
                        break;
                    case 197:
                    case 205:
                        intDay = 38;
                        break;
                }
                ;
                break;
            case 5:
                switch (numberOfDozen) {
                    case 190:
                    case 198:
                        intDay = 36;
                        break;
                    case 191:
                    case 199:
                        intDay = 28;
                        break;
                    case 192:
                    case 200:
                        intDay = 21;
                        break;
                    case 193:
                    case 201:
                        intDay = 13;
                        break;
                    case 194:
                    case 202:
                        intDay = 6;
                        break;
                    case 195:
                    case 203:
                        intDay = 58;
                        break;
                    case 196:
                    case 204:
                        intDay = 51;
                        break;
                    case 197:
                    case 205:
                        intDay = 43;
                        break;
                }
                ;
                break;
            case 6:
                switch (numberOfDozen) {
                    case 190:
                    case 198:
                        intDay = 41;
                        break;
                    case 191:
                    case 199:
                        intDay = 33;
                        break;
                    case 192:
                    case 200:
                        intDay = 26;
                        break;
                    case 193:
                    case 201:
                        intDay = 18;
                        break;
                    case 194:
                    case 202:
                        intDay = 11;
                        break;
                    case 195:
                    case 203:
                        intDay = 3;
                        break;
                    case 196:
                    case 204:
                        intDay = 56;
                        break;
                    case 197:
                    case 205:
                        intDay = 48;
                        break;
                }
                ;
                break;
            case 7:
                switch (numberOfDozen) {
                    case 190:
                    case 198:
                        intDay = 46;
                        break;
                    case 191:
                    case 199:
                        intDay = 39;
                        break;
                    case 192:
                    case 200:
                        intDay = 31;
                        break;
                    case 193:
                    case 201:
                        intDay = 24;
                        break;
                    case 194:
                    case 202:
                        intDay = 16;
                        break;
                    case 195:
                    case 203:
                        intDay = 9;
                        break;
                    case 196:
                    case 204:
                        intDay = 1;
                        break;
                    case 197:
                    case 205:
                        intDay = 54;
                        break;
                }
                ;
                break;
            case 8:
                switch (numberOfDozen) {
                    case 190:
                    case 198:
                        intDay = 51;
                        break;
                    case 191:
                    case 199:
                        intDay = 44;
                        break;
                    case 192:
                    case 200:
                        intDay = 36;
                        break;
                    case 193:
                    case 201:
                        intDay = 29;
                        break;
                    case 194:
                    case 202:
                        intDay = 21;
                        break;
                    case 195:
                    case 203:
                        intDay = 14;
                        break;
                    case 196:
                    case 204:
                        intDay = 6;
                        break;
                    case 197:
                    case 205:
                        intDay = 59;
                        break;
                }
                ;
                break;
            case 9:
                switch (numberOfDozen) {
                    case 190:
                    case 198:
                        intDay = 57;
                        break;
                    case 191:
                    case 199:
                        intDay = 49;
                        break;
                    case 192:
                    case 200:
                        intDay = 42;
                        break;
                    case 193:
                    case 201:
                        intDay = 34;
                        break;
                    case 194:
                    case 202:
                        intDay = 27;
                        break;
                    case 195:
                    case 203:
                        intDay = 19;
                        break;
                    case 196:
                    case 204:
                        intDay = 12;
                        break;
                    case 197:
                    case 205:
                        intDay = 4;
                        break;
                }
                ;
                break;
        }
        return intDay;
    }

    private static int getIntMonthYear(int month, int year) {
        int intCalc = 0;
        if (LocalDate.of(year, month, 1).isLeapYear()) { // annee bissextile = true
            switch (month) {
                case 1:
                    intCalc = 0;
                    break;
                case 2:
                    intCalc = 31;
                    break;
                case 3:
                    intCalc = 0;
                    break;
                case 4:
                    intCalc = 31;
                    break;
                case 5:
                    intCalc = 1;
                    break;
                case 6:
                    intCalc = 32;
                    break;
                case 7:
                    intCalc = 2;
                    break;
                case 8:
                    intCalc = 33;
                    break;
                case 9:
                    intCalc = 4;
                    break;
                case 10:
                    intCalc = 34;
                    break;
                case 11:
                    intCalc = 5;
                    break;
                case 12:
                    intCalc = 35;
                    break;
            }
        } else {
            switch (month) {
                case 1:
                    intCalc = 0;
                    break;
                case 2:
                    intCalc = 31;
                    break;
                case 3:
                    intCalc = 59;
                    break;
                case 4:
                    intCalc = 30;
                    break;
                case 5:
                    intCalc = 0;
                    break;
                case 6:
                    intCalc = 31;
                    break;
                case 7:
                    intCalc = 1;
                    break;
                case 8:
                    intCalc = 32;
                    break;
                case 9:
                    intCalc = 3;
                    break;
                case 10:
                    intCalc = 33;
                    break;
                case 11:
                    intCalc = 4;
                    break;
                case 12:
                    intCalc = 34;
                    break;
            }
        }
        return intCalc;
    }

    private static int getNextDay(int day, int month, int year) {
        int dayReturn = day + 1;

        if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day == 31) {
            dayReturn = 1;
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 30) {
            dayReturn = 1;
        }
        if (month == 2 && day == 29) {
            dayReturn = 1;
        }
        if (month == 2 && day == 28) {
            if (!LocalDate.of(year, month, day).isLeapYear()) { // annee bissextile = true
                dayReturn = 1;
            }
        }
        if (dayReturn == 1) {
            if (month == 12)
                mMonth = 1;
            else
                mMonth = month + 1;
        }

        return dayReturn;
    }

    private static int getPreviousDay(int day, int month, int year) {
        int dayReturn = day - 1;
        if (day == 1) {
            if (month == 1 || month == 2 || month == 4 || month == 6 || month == 8 || month == 9 || month == 11) {
                dayReturn = 31;
                if (month == 1)
                    mMonth = 12;
                else
                    mMonth = month - 1;
            }
            if (month == 5 || month == 7 || month == 10 || month == 12) {
                dayReturn = 30;
                mMonth = month - 1;
            }
            if (month == 3) {
                if (LocalDate.of(year, month, day).isLeapYear()) { // annee bissextile = true) {
                    dayReturn = 29;
                    mMonth = month - 1;
                } else {
                    dayReturn = 28;
                    mMonth = month - 1;
                }
            }
        }
        return dayReturn;
    }
}
