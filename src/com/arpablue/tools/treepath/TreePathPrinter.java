/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.tools.treepath;

import com.arpablue.interfaces.IJson;
import com.arpablue.interfaces.IPrintable;
import com.arpablue.tools.treepath.TreePathPrimitive;
import com.arpablue.tools.treepath.TreeLeaf;
import com.arpablue.tools.treepath.TreeBase;

/**
 *
 * @author Augusto Flores
 */
public class TreePathPrinter extends TreePathPrimitive implements IPrintable, IJson{

    public TreePathPrinter() {
        super();
    }
    /**
     * This return an string with tabs character, the number of tabs characters is 
     * related to the deep of the level.
     * @return It is the String with the tabs character corresponding to the level.
     */
    private static String tabs() {
        String res = "";
        for (int i = 0; i < DEEPER; i++) {
            res = res + "   ";
        }

        return res;
    }

    private static void msg(String text) {
        System.out.println(text);
    }
    /**
     * This function return the STRING format for element according its type.
     * @param target Its return a object that represent the object that content 
     */
    protected String objectToString(Object target) {
        if (target == null) {
            return "null";
        }
        String key;
        key = null;
        Object value;
        value = null;
        boolean flag;
        String res;
        res = null;
        if (!TreeBase.isThPath(target)) {
            key = ((TreeLeaf) target).getKey();
            value = ((TreeLeaf) target).getValue();
            if ((key != null) && (res != null)) {
                res = "\"" + key + "\":" + res;
            }

        }
        if (res == null) {
            res = ((TreeBase) target).toString();
        }
        return res;
    }

    /**
     * It return the tabulation characters n times in an string.
     *
     * @param n it is the number of times that tabulation characters will
     * repeated.
     * @return It is the string with the tabulation characters.
     */
    protected String getTab(int n) {
        String res = "";
        for (int i = 0; i < n; i++) {
            res = res + "    ";
        }
        return res;
    }
    /**
     * It return the structure of the Tree path in
     *
     * @return
     */
    public String toPrint() {
        return this.toJSONnicelly();
    }
    /**
     * It print the structure in the screen.
     */
    public void println() {
        msg(this.toString());
    }

    @Override
    public String toJSON() {
        String res = "";
        String target = this.toString();
        if (target == null) {
            return "{}";
        }
        char c;
        int level = 0;
        target = target + " ";
        boolean flag = true;
        for (int i = 0; i < target.length() - 1; i++) {
            c = target.charAt(i);
            if( c == '"' ){
                flag = !flag;
            }
            if (flag) {
                if ((c == '{') || (c == '[')) {
                    level++;
                    res = res + c;
                    continue;
                }
                if ((c == '}') || (c == ']')) {
                    level--;
                    if( target.charAt(i+1) == ',' ){
                        res = res + getTab(level) + c + ",";
                        i++;
                        continue;
                    }
                    res = res +  getTab(level) + c;
                    continue;
                }
                if (c == ',') {
                    res = res + c + getTab(level);
                    continue;
                }
                if ((c == ' ') || (c == '\t')) {
                    continue;
                }
            }
            res = res + c;
        }
        return res;
    }

    @Override
    public String toJSONnicelly() {
        String res = "";
        String target = this.toString();
        if (target == null) {
            return "{}";
        }
        char c;
        int level = 0;
        target = target + " ";
        boolean flag = true;
        for (int i = 0; i < target.length() - 1; i++) {
            c = target.charAt(i);
            if( c == '"' ){
                flag = !flag;
            }
            if (flag) {
                if ((c == '{') || (c == '[')) {
                    level++;
                    res = res + c + "\n" + getTab(level);
                    continue;
                }
                if ((c == '}') || (c == ']')) {
                    level--;
                    if( target.charAt(i+1) == ',' ){
                        res = res + "\n" + getTab(level) + c + ",\n" + getTab(level);
                        i++;
                        continue;
                    }
                    res = res + "\n" + getTab(level) + c + "\n" + getTab(level);
                    continue;
                }
                if (c == ',') {
                    res = res + c + "\n" + getTab(level);
                    continue;
                }
                if ((c == ' ') || (c == '\t')) {
                    continue;
                }
            }
            res = res + c;
        }
        return res;
    }

}
