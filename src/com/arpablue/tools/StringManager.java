/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.tools;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author cy-proyx1
 */
public class StringManager {
    /**
     * It verify if the is null or its size is 0 or if the string only have white spaces.
     * @return if is true if the string is empty.
     */
    public static boolean isEmpty( String target){
        if( target == null ){
            return true;
        }
        target = target.trim();
        if( target.length() < 1 ){
            return true;
        }
        return false;
    }
    /**
     * It compare two string, it is case sensitive.
     * @param str1 It is the first string to be compare.
     * @param str2 It is the second string to be compare.
     * @return It is true if both string are the same.
     */
    public static boolean strCompare( String str1, String str2){
        if( (str1 == null) && (str2 == null) ){
            return true;
        }
        if( str1== null ){
            return false;
        }
        if( str2 == null){
            return false;
        }
        if( str1.length() != str2.length() ){
            return false;
        }
        char[] v1 = str1.toCharArray();
        char[] v2 = str2.toCharArray();
        char c1;
        char c2;
        for (int i = 0; i < v2.length; i++) {
            c1 = v1[i];
            c2 = v2[i];
            if( c1 != c2 ){
                return false;
            }
            
        }
        return true;
    }
    /**
     * return the string formed only by numbers and letters.
     */
    public static String onlyAlphanumeric(String target) {
        String res = "";
        if (target == null) {
            return null;
        }
        byte[] v = target.getBytes();
        char e = 's';
        for (int i = 0; i < v.length; i++) {
            e = (char) v[i];
            if (Character.isDigit(e) || Character.isAlphabetic(e)) {
                res = res + e;
            }
        }
        return res;
    }
    /**
     * It remove the quotes ate the start and the end of the line.
     */
    public static String CTrim(String target) {
        if (target == null) {
            return null;
        }
        byte[] bytes = target.trim().getBytes();
        char[] res = new char[bytes.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = (char) bytes[i];
        }
        for (int i = 0; i < res.length; i++) {
            if (res[res.length - 1 - i] == '\'') {
                res[res.length - 1 - i] = ' ';
            } else if (res[res.length - 1 - i] == '\r') {
                res[res.length - 1 - i] = ' ';
            } else if (res[res.length - 1 - i] == '\n') {
                res[res.length - 1 - i] = ' ';
            } else {
                break;
            }
        }
        for (int i = 0; i < res.length; i++) {
            if (res[i] == '\'') {
                res[i] = ' ';
            } else if (res[i] == '\r') {
                res[i] = ' ';
            } else if (res[i] == '\n') {
                res[i] = ' ';
            } else {
                break;
            }
        }
        return String.copyValueOf(res).trim();
    }
    //The format counter
    public static String formatCounter(int n) {
        return formatCounter(n, 2);
    }
    /**
     * return a string with a number and 0 in the emty spaces
     *
     * @param n
     * @param fill
     * @return
     */
    public static String formatCounter(int n, int fill) {

        String res = "";
        boolean negative = false;
        if (n < 0) {
            negative = true;
            n = n * 1;
        }
        int dig = ("" + n).length();
        if (n < Caos.potency(10, fill + 1)) {
            res = StringManager.repeat("0", fill - dig) + n;
        } else {
            res = "" + n;
        }
        if (negative) {
            res = "-" + res;
        }
        return res;
    }
    /**
     * Replace all occurrences in a string.
     *
     * @param target
     * @param newStr
     * @param oldStr
     * @return
     */
    public static String replaceStr(String target, String oldStr, String newStr) {
        if (target == null) {
            return null;
        }
        if (newStr == null) {
            return target;
        }
        int pos = target.indexOf(oldStr);
        if (pos < 0) {
            return target;
        }
        do {
            target = target.substring(0, pos) + newStr + target.substring(pos + oldStr.length(), target.length());
            pos = target.indexOf(oldStr);
        } while (pos > -1);
        return target;
    }
    /**
     * this function replace a string in another with a specifyc tag
     */
    public static String setValue(String target, String value, String tag) {
        if (target == null) {
            return null;
        }
        if (value == null) {
            return target;
        }
        if (tag == null) {
            tag = "<#value#>";
        }
        return replaceStr(target, value, tag);
    }
    /**
     * Esta funcion se encarga de completar los caracteres restantes con la
     * mascara que se tiene.
     *
     */
    public static String completeStr(String target, String mask) {
        int t = target.length();
        int m = mask.length();
        if (m <= t) {
            return target.substring(t - m);
        }
        return mask.substring(0, m - t) + target;
    }
    /**
     * put a sibngle space between the words.
     *
     * @param base
     * @return
     */
    public static String singleSpace(String base) {
        if (base == null) {
            return null;
        }
        base = base.trim().replace("\t", " ");
        while (base.indexOf("  ") > -1) {
            base = base.replace("  ", " ");
        }
        return base;
    }
    /**
     * Put the elements of an vector in a array,.
     *
     * @param v
     * @return
     */
    public static String[] vectorToString(Vector v) {
        if (v == null) {
            return null;
        }
        String[] res = new String[v.size()];
        for (int i = 0; i < v.size(); i++) {
            res[i] = v.get(i).toString();
        }
        return res;
    }
    /**
     * put all elements of an array in a string with a separator.
     *
     * @param target
     * @param separator
     * @return
     */
    public static String toString(Object[] target, String separator) {
        String res = "";
        if (target == null) {
            return "";
        }
        if (separator == null) {
            separator = ",";
        }
        for (int i = 0; i < target.length - 1; i++) {
            res = res + target[i] + separator;
        }
        res = res + target[(target.length - 1)];
        return res;
    }
    public static String toString(Object[] target) {
        return toString(target, null);
    }
    public static String setExactitud(double d, int exactitud) {
        String res = "" + d;
        return res.substring(0, res.indexOf(".") + exactitud);
    }
    public static String setExactitud(float d, int exactitud) {
        String res = "" + d;
        return res.substring(0, res.indexOf(".") + exactitud);
    }
    public static String[] getColumn(Vector data, int column) {
        String[] res = new String[data.size()];

        if (column < 0) {
            return res;
        }
        for (int i = 0; i < res.length; i++) {
            String[] elem = (String[]) (String[]) data.get(i);
            if (column < elem.length) {
                res[i] = elem[column];
            }
        }
        return res;
    }
    public static Vector vectorToVectorString(Vector v) {
        Vector res = new Vector();
        if (v == null) {
            return null;
        }
        for (int i = 0; i < v.size(); i++) {
            res.add(vectorToString((Vector) v.get(i)));
        }
        return res;
    }
    public static float getPercent(String fraccion, String total) {
        float fr = Float.parseFloat(fraccion);
        float t = Float.parseFloat(total);
        return (float) (fr / t * 100.0D);
    }
    public static String getPercent(String fraccion, String total, int exactitud) {
        return setExactitud(getPercent(fraccion, total), exactitud);
    }
    /**
     * Get a string from a target string between the limits.
     */
    public static String getSubString(String target, String start, String end) {
        String res = null;
        if (target == null) {
            return null;
        }
        int ini = 0;
        if (start != null) {
            ini = target.indexOf(start);
            ini++;
        }
        int fin = -1;
        if (end != null) {
            fin = target.indexOf(end);
        }
        return getSubString(target, ini, fin);
    }
    /**
     * Get a string from a target string between the bounders.
     */
    public static String getSubString(String target, int start, int end) {
        if (target == null) {
            return null;
        }
        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = target.length();
        }

        return target.substring(start, end);
    }
    /**
     * Return a substring formed bye an specific words.
     */
    public static String getSubString(String target, String separator, int start, int end) {
        String res = "";
        if (target == null) {
            return null;
        }
        if (separator == null) {
            separator = " ";
        }
        String[] v = target.split(separator);
        if (start >= v.length - 1) {
            return res;
        }
        if (start < 0) {
            start = 0;
        }
        if (end < start) {
            end = start;
        }
        if (end > v.length - 1) {
            end = v.length - 1;
        }
        if (end == start) {
            return v[start];
        }
        for (int i = start; i < end; i++) {
            res = res + v[i] + separator;
        }
        res = res + v[end];
        return res;
    }
    /**
     * Repeat a string a specific number of times, if the number is negative,
     * then is change to positive.
     */
    public static String repeat(String target, int times) {
        String res = "";
        if (target == null) {
            return null;
        }
        if (times < 0) {
            times *= -1;
        }
        for (int i = 0; i < times; i++) {
            res += target;
        }
        return res;
    }
    /**
     * Return a substring from las character until found a top character.
     */
    public static String getStringFromLast(String target, char until) {
        int pos = -1;
        char[] v = null;
        if (target == null) {
            return null;
        }
        pos = getLastOccurrence(target, until);
        if (pos < 0) {
            return target;
        }
        return target.substring(pos);
    }
    /**
     * Return a substring from las character until found a top character.
     */
    public static String getStringFromBegin(String target, char until) {
        int pos = -1;
        if (target == null) {
            return null;
        }
        pos = target.indexOf(until);
        if (pos < 0) {
            return target;
        }

        return target.substring(0, pos);
    }
    /**
     * return a string from the first occurrence of the char until the end of
     * the string. if the occurrence not exist, then the function return a empty
     * string, not null.
     */
    public static String getStringSecond(String target, char begin) {
        int pos = -1;
        if (target == null) {
            return null;
        }
        pos = target.indexOf(begin);
        if (pos < 0) {
            return "";
        }
        return target.substring(pos + 1);
    }
    /**
     * return a string from the first occurrence of the char until the end of
     * the string. if the occurrence not exist, then the function return a empty
     * string, not null.
     */
    public static String getStringFirst(String target, char begin) {
        int pos = -1;
        if (target == null) {
            return null;
        }
        pos = target.indexOf(begin);
        if (pos < 0) {
            return "";
        }
        return target.substring(0, pos);
    }
    /**
     * It returnthe the position of the last ocurrence of a char.
     * @param target it is the string where the char will be search.
     * @param occurrence It i sthe char to the search.
     * @return It is the position of the last occurence pf the char, if the position is negative, then means the 
     * character not exists.
     */
    public static int getLastOccurrence(String target, char occurrence) {
        if (target == null) {
            return -1;
        }
        char[] v = target.toCharArray();
        for (int i = v.length - 1; i >= 0; i--) {
            char c = v[i];
            if (occurrence == c) {
                return i;
            }
        }
        return -1;
    }
    /**
     * Transform in a capital word, only the firs letter is set to uppercase and the rest of the letter are set in lowercase.
     * @param target It is the word to be convert in capitalizwae word.
     * @return It is the caopitalizaed word,
     */
    public static String toCapital(String target) {
        String res = null;
        if (target == null) {
            return null;
        }

        target = target.trim().toLowerCase();
        if (target.length() < 1) {
            return target;
        }
        char[] v = target.toCharArray();
        v[0] = Character.toUpperCase(v[0]);
        return new String(v);
    }
    /**
     * Return the last string, the separator is the limits of each word, this
     * should be a regular expression.
     */
    public static String getLastString(String target, String separator) {
        if (target == null) {
            return null;
        }
        if (separator == null) {
            return target;
        }
        String res = null;
        String[] v = target.split(separator);
        res = v[v.length - 1];
        return res;
    }
    /**
     * Return the the string without a specific position string, if the position
     * is a negative number or bigger of the number of words then remove the las
     * word.
     */
    public static String filterString(String target, String separator, int pos) {
        if (target == null) {
            return null;
        }
        if (separator == null) {
            return target;
        }
        String res = "";
        String[] v = target.split(separator);
        String[] v2 = new String[v.length - 1];
        int index = -1;
        int save = index;
        while (save < v2.length - 1) {
            index++;
            if (index != pos) {
                save++;
                v2[save] = v[index];
            }
        }
        for (int i = 0; i < v2.length - 1; i++) {
            String string = v2[i];
            res = res + string + separator;
        }
        res = res + v2[v2.length - 1];
        return res;
    }
    /**
     * Return a string without the las N words. If N is negative, then the
     * number is converted to positive.
     */
    public static String withoutLastWords(String target, String separator, int n) {
        if (target == null) {
            return null;
        }
        String[] v = target.split(separator);
        if (n < 0) {
            n = n * -1;
        }

        if (n == 0) {
            return target;
        }
        String res = v[0];
        for (int i = 1; i < v.length - n; i++) {
            res = res + separator + v[i];
        }
        return res;
    }
    /**
     * Compare two string , using the Levenshtein Distance algorithm
     * @param a
     * @param b
     * @param c
     * @return 
     */
    private static int minimum(int a, int b, int c) {
        if (a <= b && a <= c) {
            return a;
        }
        if (b <= a && b <= c) {
            return b;
        }
        return c;
    }
    /**
     * It return the substring until find the first occurrence, if the occurrence
     * not exists the the target string is returned.
     * @param target It is the string where the substring will be obtained.
     * @param sequence It is the character sequence to be search.
     * @return It is the string until the first occurrence.
     */
    public static String getStringUntil( String target, String sequence ){
        if( target == null ){
            return null;
        }
        if( sequence == null ){
            return target;
        }
        if( sequence.length() < 1 ){
            return target;
        }
        int pos = target.indexOf(sequence);
        if( pos < 0){
            return target;
        }
        String res = target.substring(0, pos);
        return res;
    }
    /**
     * It return the substring until find the first occurrence, if the occurrence
     * not exists the the target string is returned.
     * @param target It is the string where the substring will be obtained.
     * @param sequence It is the character sequence to be search.
     * @return It is the string until the first occurrence.
     */
    public static String getStringUntilLast( String target, String sequence ){
        if( target == null ){
            return null;
        }
        if( sequence == null ){
            return target;
        }
        if( sequence.length() < 1 ){
            return target;
        }
        String res = "";
        char[] v = target.toCharArray();
        for (int i = 0; i < v.length; i++) {
            char c = v[i];
            res = res + c;
            if( res.indexOf(sequence) > -1){
                return target.substring(0, i);
            }
            
        }
        return target;
    }
    /**
     * It remove all white spaces from a string.
     * @param target It is the string where the white spaces will be removed.
     * @return It is the string without white spaces.
     */
    public static String removeWitheSpace(String target) {
        return StringManager.removeWitheSpace(target,"");
    }
    /**
     * It remove all with space and replace the white space by a character.
     * @param mOutputTestFile It is the string where the white spaces will be 
     * @param newChar It is the character where the white spaces will be replaced.
     * @return Return the string without white spaces.
     */
    public static String removeWitheSpace(String target, String newChar) {
        if( target == null){
            return "";
        }
        target = StringManager.replaceAll(target, "\t", " ");
        target = target.trim();
        if( target.length() < 1){
            return target;
        }
        target = StringManager.replaceAll(target, " ", newChar);
        return target;
    }
    /**
     * @author Augusto Flores
     * @description It replace all matches of oldStr by newStr in the target String.
     * @param target It the string where all matches of oldStr will be replaced by newString
     * @param oldStr
     * @param newStr
     * @return 
     */
    public static String replaceAll(String target, String oldStr, String newStr){
        if( target == null){
            return null;
        }
        if( target.length() < 1){
            return "";
        }
        if( oldStr == null ){
            return target;
        }
        if( oldStr.length() < 1 ){
            return target;
        }
        if( newStr == null ){
            newStr = "";
        }
        while( target.indexOf( oldStr ) > -1 ){
            target = target.replace( oldStr, newStr );
        }
        return target;
    }
    /**
     * @author Augusto Flores
     * It set on format any path, remove the white space in the borders,
     * replace the // character by the / character and eliminate the / from the end of the string.
     * @param target It is the path to be on format 
     * @return It is the target  value on format, if the string is null or empty
     * then return null
     */
    public static String setFormatPath( String target ){
        if( target == null){
            return null;
        }
        target = target.trim();
        if( target.length() < 1){
            return null;
        }
        target = target.trim();
        target = StringManager.replaceAll(target, "\\", "/");
        target = StringManager.replaceAll(target, " ", "0x|x0");
        target = StringManager.replaceAll(target, "/", " ");
        target = target.trim();
        target = StringManager.replaceAll(target, " ", "/");
        target = StringManager.replaceAll(target, "0x|x0", " ");
        target = target.trim();
        return target;
    }
    /**
     * It divide in substring the target string, separate by the sequential of characters,.
     * @param target It is the target string where the split will be applied.
     * @param car It is the sequential of characters to be search and use to split
     * @return 
     */
    public static ArrayList<String> split(String target, String car){
        ArrayList<String> res = new ArrayList<String>();
        if( target == null ){
            return res;
        }
        if( car == null ){
            
            res.add(target);
            return res;
        }
        target = StringManager.replaceAll(target, car, "⌂");
        
        String line = "";
        char[] v = target.toCharArray();
        for (int i = 0; i < v.length; i++) {
            char c = v[i];
            if( c == '⌂'){
                res.add(line);
                line="";
            }else{
                line = line + c;
            }
            
        }
        res.add(line);
        return res;
    }
    public static int computeLevenshteinDistance(String str1, String str2) {
        return computeLevenshteinDistance(str1.toCharArray(),
                str2.toCharArray());
    }

    private static int computeLevenshteinDistance(char[] str1, char[] str2) {
        int[][] distance = new int[str1.length + 1][str2.length + 1];

        for (int i = 0; i <= str1.length; i++) {
            distance[i][0] = i;
        }
        for (int j = 0; j <= str2.length; j++) {
            distance[0][j] = j;
        }
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                distance[i][j] = minimum(distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1]
                        + ((str1[i - 1] == str2[j - 1]) ? 0 : 1));
            }
        }
        return distance[str1.length][str2.length];

    }
    /**
     * It return true if the current character is blank space character.
     * @param car It is the target character.
     * @return It is true if the character is blank space character.
     */
    public static boolean isBlankSpace(char car) {
        if( car == ' ' ){
            return true;
        }
        if( car == '\t' ){
            return true;
        }
        if( car == '\r' ){
            return true;
        }
        if( car == '\n' ){
            return true;
        }
        return false;
    }
    /**
     * It capitalize each word of the text, this preserve the format of the word and only the first letter
     * is converted to uppercase..
     * @param str It is the text to capitalize.
     * @return It is the text with each word capitalize.
     */
    public static String toCapitalize(String str){  
        String words[]=str.split("\\s");  
        String capitalizeWord="";  
        for(String w:words){  
            String first=w.substring(0,1);  
            String afterfirst=w.substring(1);  
            capitalizeWord+=first.toUpperCase()+afterfirst+" ";  
        }  
        return capitalizeWord.trim();  
    }  
    /**
     * It capitalize each word of each line of the array.
     * @param target It is the array to capitalize.
     * @return It the array capitalized.
     */
    public static ArrayList<String> toCapitalize(ArrayList<String> target){
        if( target == null ){
            return null;
        }
        if( target.size() < 1 ){
            return target;
        }
        ArrayList<String> res = new ArrayList<String>();
        for( String line : target ){
            line = toCapitalize( line );
            res.add( line );
        }
        return res;
    }
}
