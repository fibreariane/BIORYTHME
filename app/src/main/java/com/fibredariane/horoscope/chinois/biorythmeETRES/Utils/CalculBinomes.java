package com.fibredariane.horoscope.chinois.biorythmeETRES.Utils;

import android.util.Log;

import java.time.*;

/**
 * Created by Carlotina on 12/04/2017.
 */

public class CalculBinomes {

    public static String getStringBinome(int year, int month, int day, int hour, int minute) {
        return getStringBinome(year, month, day, hour, minute, false);
    }

    public static String getStringBinome(int year, int month, int day, int hour, int minute, boolean modeCalcul) {
        String stringBinome = "";
        LocalDateTime jourSaisi = LocalDateTime.of(year, month, day, hour, minute);
        int decalageGMT = getDecalageGMT(jourSaisi);
        LocalDateTime jourEnergetiquePekin = getEnergeticDay(jourSaisi, decalageGMT, 8);
        LocalDateTime jourEnergetique = getEnergeticDay(jourSaisi, decalageGMT, 0);
        int anneeLunaire = getAnneeNaissanceLunaire(jourEnergetiquePekin);
        int anneeSolaire = jourEnergetiquePekin.getYear();
        Log.d("CALCULBIORYTHME", "Date :" + jourSaisi.toString());
        Log.d("CALCULBIORYTHME", "  GMT :" + decalageGMT);
        Log.d("CALCULBIORYTHME", "  Jour énergétique + 8 :" + jourEnergetiquePekin.toString());
        Log.d("CALCULBIORYTHME", "  Jour énergétique :" + jourEnergetique.toString());
        Log.d("CALCULBIORYTHME", "  Année lunaire :" + anneeLunaire);
        Log.d("CALCULBIORYTHME", "  Année lunaire :" + anneeSolaire);
        String binomeJour = getBinomeJour(jourEnergetiquePekin, anneeLunaire, anneeSolaire);
        stringBinome = getBinomeAnnee(anneeLunaire) + "." +
                getBinomeMois(jourEnergetiquePekin, anneeSolaire) + "." +
                getBinomeJour(jourEnergetiquePekin, anneeLunaire, anneeSolaire) + "." +
                getBinomeHeure(jourEnergetique, binomeJour);

        Log.d("CALCULBIORYTHMECOMPLET", "Biorythme :" + stringBinome);
        Log.d("CALCULBIORYTHME", "------------------------");
        if (modeCalcul) {
            String calcul = "";
            calcul = getBinomeAnnee(anneeLunaire, true);
            calcul += "\n" + getBinomeMois(jourEnergetiquePekin, anneeSolaire, true);
            calcul += "\n" + getBinomeJour(jourEnergetiquePekin, anneeLunaire, anneeSolaire, true);
            calcul += "\n" + getBinomeHeure(jourEnergetique, binomeJour, true);
            return calcul;
        } else
            return stringBinome;
    }

    /**
     * Récupération du décalage GMT en prenant en compte l'heure d'été
     *
     * @param dateSaisie
     * @return
     */
    public static int getDecalageGMT(LocalDateTime dateSaisie) {
        int decalage = -1;
        LocalDate date = LocalDate.of(dateSaisie.getYear(), dateSaisie.getMonthValue(), dateSaisie.getDayOfMonth());
        int year = dateSaisie.getYear();
        switch (year) {
            case 1941:
                if (date.isAfter(LocalDate.of(year, 5, 3))
                        && date.isBefore(LocalDate.of(year, 10, 7))) {
                    decalage = -2;
                }
                break;
            case 1942:
                if (date.isAfter(LocalDate.of(year, 3, 7))
                        && date.isBefore(LocalDate.of(year, 11, 3))) {
                    decalage = -2;
                }
                break;
            case 1943:
                if (date.isAfter(LocalDate.of(year, 3, 28))
                        && date.isBefore(LocalDate.of(year, 10, 5))) {
                    decalage = -2;
                }
                break;
            case 1944:
                if (date.isAfter(LocalDate.of(year, 4, 2))
                        && date.isBefore(LocalDate.of(year, 10, 9))) {
                    decalage = -2;
                }
                break;
            case 1945:
                if (date.isAfter(LocalDate.of(year, 4, 1))
                        && date.isBefore(LocalDate.of(year, 9, 17))) {
                    decalage = -2;
                }
                break;
            case 1976:
            case 1982:
            case 1993:
                if (date.isAfter(LocalDate.of(year, 3, 27))
                        && date.isBefore(LocalDate.of(year, 9, 27))) {
                    decalage = -2;
                }
                break;
            case 1977:
                if (date.isAfter(LocalDate.of(year, 4, 2))
                        && date.isBefore(LocalDate.of(year, 9, 26))) {
                    decalage = -2;
                }
                break;
            case 1978:
                if (date.isAfter(LocalDate.of(year, 4, 1))
                        && date.isBefore(LocalDate.of(year, 10, 2))) {
                    decalage = -2;
                }
                break;
            case 1979:
                if (date.isAfter(LocalDate.of(year, 3, 31))
                        && date.isBefore(LocalDate.of(year, 10, 1))) {
                    decalage = -2;
                }
                break;
            case 1980:
                if (date.isAfter(LocalDate.of(year, 4, 5))
                        && date.isBefore(LocalDate.of(year, 9, 29))) {
                    decalage = -2;
                }
                break;
            case 1981:
            case 1987:
            case 1992:
                if (date.isAfter(LocalDate.of(year, 3, 28))
                        && date.isBefore(LocalDate.of(year, 9, 28))) {
                    decalage = -2;
                }
                break;
            case 1983:
            case 1988:
            case 1994:
                if (date.isAfter(LocalDate.of(year, 3, 26))
                        && date.isBefore(LocalDate.of(year, 9, 26))) {
                    decalage = -2;
                }
                break;
            case 1984:
            case 1990:
                if (date.isAfter(LocalDate.of(year, 3, 24))
                        && date.isBefore(LocalDate.of(year, 10, 1))) {
                    decalage = -2;
                }
                break;
            case 1985:
            case 1991:
                if (date.isAfter(LocalDate.of(year, 3, 30))
                        && date.isBefore(LocalDate.of(year, 9, 30))) {
                    decalage = -2;
                }
                break;
            case 1986:
                if (date.isAfter(LocalDate.of(year, 3, 29))
                        && date.isBefore(LocalDate.of(year, 9, 29))) {
                    decalage = -2;
                }
                break;
            case 1989:
            case 1995:
                if (date.isAfter(LocalDate.of(year, 3, 25))
                        && date.isBefore(LocalDate.of(year, 9, 25))) {
                    decalage = -2;
                }
                break;
            case 1996:
            case 2002:
            case 2013:
            case 2019:
                if (date.isAfter(LocalDate.of(year, 3, 30))
                        && date.isBefore(LocalDate.of(year, 10, 28))) {
                    decalage = -2;
                }
                break;
            case 1997:
            case 2003:
            case 2008:
            case 2014:
                if (date.isAfter(LocalDate.of(year, 3, 29))
                        && date.isBefore(LocalDate.of(year, 10, 27))) {
                    decalage = -2;
                }
                break;
            case 1998:
            case 2009:
            case 2015:
            case 2020:
                if (date.isAfter(LocalDate.of(year, 3, 28))
                        && date.isBefore(LocalDate.of(year, 10, 26))) {
                    decalage = -2;
                }
                break;
            case 1999:
            case 2004:
            case 2021:
                if (date.isAfter(LocalDate.of(year, 3, 27))
                        && date.isBefore(LocalDate.of(year, 11, 1))) {
                    decalage = -2;
                }
                break;
            case 2000:
            case 2006:
            case 2017:
                if (date.isAfter(LocalDate.of(year, 3, 25))
                        && date.isBefore(LocalDate.of(year, 10, 30))) {
                    decalage = -2;
                }
                break;
            case 2001:
            case 2007:
            case 2012:
            case 2018:
                if (date.isAfter(LocalDate.of(year, 3, 24))
                        && date.isBefore(LocalDate.of(year, 10, 29))) {
                    decalage = -2;
                }
                break;
            case 2005:
            case 2011:
            case 2016:
                if (date.isAfter(LocalDate.of(year, 3, 26))
                        && date.isBefore(LocalDate.of(year, 11, 1))) {
                    decalage = -2;
                }
                break;
            case 2010:
                if (date.isAfter(LocalDate.of(year, 3, 27))
                        && date.isBefore(LocalDate.of(year, 10, 25))) {
                    decalage = -2;
                }
                break;
        }

        return decalage;
    }

    /**
     * Récupération du jour énergétique avec décalage GMT et décalage Heure de Pékin si nécessaire
     *
     * @param dateSaisie
     * @param decalageGMT
     * @param decalagePekin
     * @return
     */
    private static LocalDateTime getEnergeticDay(LocalDateTime dateSaisie, int decalageGMT, int decalagePekin) {
        LocalDateTime dateTime = dateSaisie.plusHours(decalageGMT + decalagePekin);
        return dateTime;
    }

    /**
     * Récupérer l'année de naissance selon le calendrier lunaire
     *
     * @param dateEnergetique
     * @return
     */
    public static int getAnneeNaissanceLunaire(LocalDateTime dateEnergetique) {
        int yearSwitch = dateEnergetique.getYear();
        LocalDate dateDebAnnee = getAnneesLunaires(dateEnergetique.getYear());
        if ((dateDebAnnee.getMonthValue() > dateEnergetique.getMonthValue())
                || (dateDebAnnee.getMonthValue() == dateEnergetique.getMonthValue()
                && dateDebAnnee.getDayOfMonth() > dateEnergetique.getDayOfMonth()))
            yearSwitch = yearSwitch - 1;

        return yearSwitch;
    }

    /**
     * Récupération du mois selon la date énergétique
     *
     * @param dateEnergetique
     * @return
     */
    private static int getMoisSolaire(LocalDateTime dateEnergetique) {
        int returnMonth = dateEnergetique.getMonthValue();
        int dayCalc = dateEnergetique.getDayOfMonth();

        switch (dateEnergetique.getMonthValue()) {
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

    /**
     * Récupérer la correspondance avec l'année lunaire
     *
     * @param year
     * @return
     */
    public static int getCorrespondanceNumAnneeLunaire(int year) {
        Integer numAnneeLunaire = 1;

        switch (year) {
            case 1967:
                numAnneeLunaire = 1;
                break;
            case 1990:
                numAnneeLunaire = 2;
                break;
            case 1933:
            case 1956:
            case 2013:
                numAnneeLunaire = 3;
                break;
            case 1979:
                numAnneeLunaire = 4;
                break;
            case 2002:
                numAnneeLunaire = 5;
                break;
            case 1945:
            case 1968:
            case 2025:
                numAnneeLunaire = 6;
                break;
            case 1991:
                numAnneeLunaire = 7;
                break;
            case 1934:
            case 2014:
                numAnneeLunaire = 8;
                break;
            case 1957:
            case 1980:
                numAnneeLunaire = 9;
                break;
            case 2003:
                numAnneeLunaire = 10;
                break;
            case 1946:
            case 2026:
                numAnneeLunaire = 11;
                break;
            case 1969:
            case 1992:
                numAnneeLunaire = 12;
                break;
            case 1935:
            case 2015:
                numAnneeLunaire = 13;
                break;
            case 1958:
                numAnneeLunaire = 14;
                break;
            case 1924:
            case 1981:
            case 2004:
                numAnneeLunaire = 15;
                break;
            case 1947:
            case 2027:
                numAnneeLunaire = 16;
                break;
            case 1970:
                numAnneeLunaire = 17;
                break;
            case 1936:
            case 1993:
            case 2016:
                numAnneeLunaire = 18;
                break;
            case 1959:
                numAnneeLunaire = 19;
                break;
            case 1982:
                numAnneeLunaire = 20;
                break;
            case 1925:
            case 2005:
            case 2028:
                numAnneeLunaire = 21;
                break;
            case 1971:
                numAnneeLunaire = 22;
                break;
            case 1994:
                numAnneeLunaire = 23;
                break;
            case 1937:
            case 1960:
            case 2017:
                numAnneeLunaire = 24;
                break;
            case 1983:
                numAnneeLunaire = 25;
                break;
            case 1926:
            case 2006:
                numAnneeLunaire = 26;
                break;
            case 1949:
            case 1972:
            case 2029:
                numAnneeLunaire = 27;
                break;
            case 1995:
                numAnneeLunaire = 28;
                break;
            case 1938:
            case 2018:
                numAnneeLunaire = 29;
                break;
            case 1961:
            case 1984:
                numAnneeLunaire = 30;
                break;
            case 1927:
            case 2007:
                numAnneeLunaire = 31;
                break;
            case 1950:
            case 2030:
                numAnneeLunaire = 32;
                break;
            case 1973:
            case 1996:
                numAnneeLunaire = 33;
                break;
            case 1939:
            case 2019:
                numAnneeLunaire = 34;
                break;
            case 1962:
                numAnneeLunaire = 35;
                break;
            case 1928:
            case 1985:
            case 2008:
                numAnneeLunaire = 36;
                break;
            case 1951:
                numAnneeLunaire = 37;
                break;
            case 1974:
                numAnneeLunaire = 38;
                break;
            case 1940:
            case 1997:
            case 2020:
                numAnneeLunaire = 39;
                break;
            case 1963:
                numAnneeLunaire = 40;
                break;
            case 1986:
                numAnneeLunaire = 41;
                break;
            case 1929:
            case 1952:
            case 2009:
            case 2032:
                numAnneeLunaire = 42;
                break;
            case 1975:
                numAnneeLunaire = 43;
                break;
            case 1998:
                numAnneeLunaire = 44;
                break;
            case 1964:
            case 2021:
                numAnneeLunaire = 45;
                break;
            case 1987:
                numAnneeLunaire = 46;
                break;
            case 1930:
            case 2010:
                numAnneeLunaire = 47;
                break;
            case 1953:
            case 1976:
            case 2033:
                numAnneeLunaire = 48;
                break;
            case 1999:
                numAnneeLunaire = 49;
                break;
            case 1942:
            case 2022:
                numAnneeLunaire = 50;
                break;
            case 1965:
            case 1988:
                numAnneeLunaire = 51;
                break;
            case 1931:
            case 2011:
                numAnneeLunaire = 52;
                break;
            case 1954:
                numAnneeLunaire = 53;
                break;
            case 1977:
            case 2000:
                numAnneeLunaire = 54;
                break;
            case 1943:
            case 2023:
                numAnneeLunaire = 55;
                break;
            case 1966:
                numAnneeLunaire = 56;
                break;
            case 1932:
            case 1989:
            case 2012:
                numAnneeLunaire = 57;
                break;
            case 1955:
                numAnneeLunaire = 58;
                break;
            case 1978:
                numAnneeLunaire = 59;
                break;
            case 1944:
            case 2001:
            case 2024:
                numAnneeLunaire = 60;
                break;
        }
        return numAnneeLunaire;
    }

    /**
     * Récupération du binome de l'année
     *
     * @param year
     * @return
     */
    public static String getBinomeAnnee(int year) {
        return getBinomeAnnee(year, false);
    }

    public static String getBinomeAnnee(int year, boolean modeCalcul) {
        String mStringBinome = "1";

        switch (year) {
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

        if (modeCalcul) {
            String calcul = "Binome Année \n  Année Lunaire : " + year;
            calcul += "\n  Binome :" + mStringBinome;
            return calcul;
        } else
            return mStringBinome;
    }

    /**
     * Récupération du binome du mois
     *
     * @param dateEnergetique
     * @param anneeSolaire
     * @return
     */
    public static String getBinomeMois(LocalDateTime dateEnergetique, int anneeSolaire) {
        return getBinomeMois(dateEnergetique, anneeSolaire, false);
    }

    public static String getBinomeMois(LocalDateTime dateEnergetique, int anneeSolaire, boolean modeCalcul) {
        String mStringBinome = "1";
        int monthSwitch = getMoisSolaire(dateEnergetique);
        int lastDigitYear = anneeSolaire % 10;
        Log.d("CALCULBIORYTHME", "  Biorythme Mois ");
        Log.d("CALCULBIORYTHME", "    Mois solaire:" + monthSwitch);
        Log.d("CALCULBIORYTHME", "    Last digit Year :" + lastDigitYear);
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

        if (modeCalcul) {
            String calcul = "Binome Mois \n  Mois solaire : " + monthSwitch;
            calcul += "\n  Chiffre Année :" + lastDigitYear;
            calcul += "\n  Binome :" + mStringBinome;
            return calcul;
        } else
            return mStringBinome;

    }


    /**
     * Récupérer le binome jour
     *
     * @param dateEnergetique
     * @param anneeEnergetique
     * @return
     */
    public static String getBinomeJour(LocalDateTime dateEnergetique, int anneeEnergetique, int anneeSolaire) {
        return getBinomeJour(dateEnergetique, anneeEnergetique, anneeSolaire, false);
    }

    public static String getBinomeJour(LocalDateTime dateEnergetique, int anneeEnergetique, int anneeSolaire, boolean modeCalcul) {
        int binomeJour = dateEnergetique.getDayOfYear() + getCorrespondanceNumAnneeLunaire(anneeEnergetique);
        boolean isLeapYear = LocalDate.of(anneeEnergetique, 1, 1).isLeapYear();
        Log.d("CALCULBIORYTHME", "  Biorythme Jour :");
        Log.d("CALCULBIORYTHME", "    Jour année :" + dateEnergetique.getDayOfYear());
        Log.d("CALCULBIORYTHME", "    Correspondance année lunaire :" + getCorrespondanceNumAnneeLunaire(anneeEnergetique));
        Log.d("CALCULBIORYTHME", "    Année bissextile : " + isLeapYear);
        // Cas particulier Année lunaire bissextile et jour < 29/02
        if ((dateEnergetique.getMonthValue() == 1
                || (dateEnergetique.getMonthValue() == 2 && dateEnergetique.getDayOfMonth() < 29))
                && isLeapYear) {
            binomeJour += 1;
        }
        if ((binomeJour % 60) > 0)
            binomeJour = binomeJour % 60;
        else
            binomeJour = 60;

        String mStringBinome = String.valueOf(binomeJour);

        if (modeCalcul) {
            String calcul = "Binome Jour";
            calcul += "\n  Jour énergétique : " + dateEnergetique.getDayOfMonth() + "/" + dateEnergetique.getMonthValue();
            calcul += "\n  Jour de l'année " + anneeSolaire + " : " + dateEnergetique.getDayOfYear();
            calcul += "\n  Année lunaire :" + anneeEnergetique;
            calcul += "\n  Correspondance année lunaire :" + getCorrespondanceNumAnneeLunaire(anneeEnergetique);
            calcul += "\n  Année bissextile :" + isLeapYear;
            calcul += "\n  Binome :" + mStringBinome;
            return calcul;
        } else
            return mStringBinome;
    }

    /**
     * Récupérer le binome heure
     *
     * @param dateEnergetique
     * @param binomeJour
     * @return
     */
    public static String getBinomeHeure(LocalDateTime dateEnergetique, String binomeJour) {
        return getBinomeHeure(dateEnergetique, binomeJour, false);
    }

    public static String getBinomeHeure(LocalDateTime dateEnergetique, String binomeJour, boolean modeCalcul) {
        String mStringBinome = "1";

        int heure = dateEnergetique.getHour();
        int dayBinomeModulo = Integer.valueOf(binomeJour) % 10;
        Log.d("CALCULBIORYTHME", "  Biorythme Heure :");
        Log.d("CALCULBIORYTHME", "    Heure :" + dateEnergetique.getHour());

        switch (dayBinomeModulo) {
            case 1:
            case 6:
                if (heure >= 23 || heure == 0)
                    mStringBinome = "1";
                if (heure >= 1 && heure < 3)
                    mStringBinome = "2";
                if (heure >= 3 && heure < 5)
                    mStringBinome = "3";
                if (heure >= 5 && heure < 7)
                    mStringBinome = "4";
                if (heure >= 7 && heure < 9)
                    mStringBinome = "5";
                if (heure >= 9 && heure < 11)
                    mStringBinome = "6";
                if (heure >= 11 && heure < 13)
                    mStringBinome = "7";
                if (heure >= 13 && heure < 15)
                    mStringBinome = "8";
                if (heure >= 15 && heure < 17)
                    mStringBinome = "9";
                if (heure >= 17 && heure < 19)
                    mStringBinome = "10";
                if (heure >= 19 && heure < 21)
                    mStringBinome = "11";
                if (heure >= 21 && heure < 23)
                    mStringBinome = "12";
                break;
            case 2:
            case 7:
                if (heure >= 23 || heure == 0)
                    mStringBinome = "13";
                if (heure >= 1 && heure < 3)
                    mStringBinome = "14";
                if (heure >= 3 && heure < 5)
                    mStringBinome = "15";
                if (heure >= 5 && heure < 7)
                    mStringBinome = "16";
                if (heure >= 7 && heure < 9)
                    mStringBinome = "17";
                if (heure >= 9 && heure < 11)
                    mStringBinome = "18";
                if (heure >= 11 && heure < 13)
                    mStringBinome = "19";
                if (heure >= 13 && heure < 15)
                    mStringBinome = "20";
                if (heure >= 15 && heure < 17)
                    mStringBinome = "21";
                if (heure >= 17 && heure < 19)
                    mStringBinome = "22";
                if (heure >= 19 && heure < 21)
                    mStringBinome = "23";
                if (heure >= 21 && heure < 23)
                    mStringBinome = "24";
                break;
            case 3:
            case 8:
                if (heure >= 23 || heure == 0)
                    mStringBinome = "25";
                if (heure >= 1 && heure < 3)
                    mStringBinome = "26";
                if (heure >= 3 && heure < 5)
                    mStringBinome = "27";
                if (heure >= 5 && heure < 7)
                    mStringBinome = "28";
                if (heure >= 7 && heure < 9)
                    mStringBinome = "29";
                if (heure >= 9 && heure < 11)
                    mStringBinome = "30";
                if (heure >= 11 && heure < 13)
                    mStringBinome = "31";
                if (heure >= 13 && heure < 15)
                    mStringBinome = "32";
                if (heure >= 15 && heure < 17)
                    mStringBinome = "33";
                if (heure >= 17 && heure < 19)
                    mStringBinome = "34";
                if (heure >= 19 && heure < 21)
                    mStringBinome = "35";
                if (heure >= 21 && heure < 23)
                    mStringBinome = "36";
                break;
            case 4:
            case 9:
                if (heure >= 23 || heure == 0)
                    mStringBinome = "37";
                if (heure >= 1 && heure < 3)
                    mStringBinome = "38";
                if (heure >= 3 && heure < 5)
                    mStringBinome = "39";
                if (heure >= 5 && heure < 7)
                    mStringBinome = "40";
                if (heure >= 7 && heure < 9)
                    mStringBinome = "41";
                if (heure >= 9 && heure < 11)
                    mStringBinome = "42";
                if (heure >= 11 && heure < 13)
                    mStringBinome = "43";
                if (heure >= 13 && heure < 15)
                    mStringBinome = "44";
                if (heure >= 15 && heure < 17)
                    mStringBinome = "45";
                if (heure >= 17 && heure < 19)
                    mStringBinome = "46";
                if (heure >= 19 && heure < 21)
                    mStringBinome = "47";
                if (heure >= 21 && heure < 23)
                    mStringBinome = "48";
                break;
            case 5:
            case 0:
                if (heure >= 23 || heure == 0)
                    mStringBinome = "49";
                if (heure >= 1 && heure < 3)
                    mStringBinome = "50";
                if (heure >= 3 && heure < 5)
                    mStringBinome = "51";
                if (heure >= 5 && heure < 7)
                    mStringBinome = "52";
                if (heure >= 7 && heure < 9)
                    mStringBinome = "53";
                if (heure >= 9 && heure < 11)
                    mStringBinome = "54";
                if (heure >= 11 && heure < 13)
                    mStringBinome = "55";
                if (heure >= 13 && heure < 15)
                    mStringBinome = "56";
                if (heure >= 15 && heure < 17)
                    mStringBinome = "57";
                if (heure >= 17 && heure < 19)
                    mStringBinome = "58";
                if (heure >= 19 && heure < 21)
                    mStringBinome = "59";
                if (heure >= 21 && heure < 23)
                    mStringBinome = "60";
                break;

        }
        if (modeCalcul) {
            String calcul = "Binome Heure";
            calcul += "\n  Heure : " + dateEnergetique.getHour();
            calcul += "\n  Chiffre binome jour : " + dayBinomeModulo;
            calcul += "\n  Binome :" + mStringBinome;
            return calcul;
        } else
            return mStringBinome;
    }

    /**
     * Récupérer le début des années lunaires
     *
     * @param year
     * @return
     */
    public static LocalDate getAnneesLunaires(int year) {
        int month;
        int dayOfMonth;
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
            case 2025:
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
            case 2024:
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
                month = 1;
                dayOfMonth = 1;
                break;
        }

        return LocalDate.of(year, month, dayOfMonth);
    }
}
