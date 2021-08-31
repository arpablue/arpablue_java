/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.inifile;

import java.util.ArrayList;

/**
 * It contain the the attributes for each section in the ini file.
 */
public class IniFileSection extends IniFileBase {

    protected ArrayList<IniFileAttribute> mAtts;
    public static final String NO_TITLE_SECTION = "<-GENERAL->";

    /**
     * It is the default constructor, set with a default name for attributes
     * that not belong to any section.
     */
    public IniFileSection() {
        this.setName(NO_TITLE_SECTION);
        mAtts = new ArrayList<IniFileAttribute>();
    }
    /**
     * It return the section name specified between [], if 2 or more sections are specified in the string then only the first section is returned.
     * @param line It is the line that contain the section..
     * @return It is the name of the section, if the section do not exists or is empty then return null.
     */
    public static String getSectionName( String line ){
        if( line == null ){
            return null;
        }
        line = line.trim();
        if( line.length() < 0 ){
            return null;
        }
        if( -1 < line.indexOf("=") ){
            return null;
        }
        int i = -1;
        int j = -1;
        
        i = line.indexOf("[");
        j = line.indexOf("]");
        
        if( i < 0 ){
            return null;
        }
        if( j < 0 ){
            return null;
        }
        
        if( j <=  i  ){
           return null; 
        }
        
        String res = line.substring( i+1, j );
        res = res.trim();
        if( res.length() < 1 ){
            return null;
        }
        return res;
    }
    /**
     * It return in a string the structure of the current section.
     *
     * @return
     */
    public String toString() {

        String res = "";
        if (!this.isThis(IniFileSection.NO_TITLE_SECTION)) {
            res = "[" + this.getKeyName() + "]\n";

        }
        for (IniFileAttribute e : mAtts) {
            res = res +  e.toString() + "\n";
        }
        return res;
    }
    @Override
    public boolean setData(String target) {
        if (target == null) {
            return false;
        }
        target = target.trim();
        if (target.length() < 2) {
            return false;
        }
        boolean active = false;
        boolean found = false;
        char[] v = target.toCharArray();
        String res = "";
        for (char c : v) {
            if (c == '[') {
                active = true;
                continue;
            }
            if (c == ']') {
                active = false;
                continue;
            }
            if (active) {
                res = res + c;
                found = true;
            }
        }
        if (found) {
            this.setName(res);;
        }
        return false;
    }
    @Override
    public boolean isThisType(String target) {
        if (target == null) {
            return false;
        }
        int pos = target.indexOf(']');
        if (pos < 0) {
            return false;
        }
        int pos2 = target.indexOf("[");
        if (pos2 < 0) {
            return false;
        }
        if (pos2 > pos) {
            return false;
        }
        pos2 = target.indexOf(";");
        if (pos2 > -1) {
            if (pos2 == 0) {
                return false;
            }
            if (pos2 < pos) {
                return false;
            }
        }
        return true;
    }
    /**
     * It verify if the current section is a general section or a section without a name.
     * @return It is true if the section is a general section.
     */
    public boolean isGeneralSection(){
        return NO_TITLE_SECTION.equalsIgnoreCase( this.getName() );
    }
    /**
     * It create a section with an specific name.
     *
     * @param name It is the name of the section.
     */
    public IniFileSection(String name) {
        this();
        setName(name);
        mAtts = new ArrayList<IniFileAttribute>();
    }
    /**
     * It return the attributes of the current Section.
     *
     * @return It is the list of attributes.
     */
    ArrayList<IniFileAttribute> getAttributes() {
        return mAtts;
    }
    /**
     * It return the keys used to access to the current sections,
     * @return It is a list of the keys in string,
     */
    ArrayList<String> getKeys(){
        ArrayList<String> res = new ArrayList<String>();
        String path = this.getName()+"/";
        if( this.isGeneralSection() ){
            path = "";
        }
        for( IniFileAttribute e: mAtts ){
            res.add(path + e.getName());
        }
        
        return res;
    }
    /**
     * It add a new attribute to the current section, no matter if another
     * attribute has the same value.
     *
     * @param vame It is the name of the attribute to be added.
     * @param value It is the value assigned to the new attribute.
     */
    public void add(String name, Object value) {

        IniFileAttribute e = new IniFileAttribute(name, value);
        mAtts.add(e);
    }
    /**
     * It return the attribute of a specific position.
     *
     * @param index it is the position of the attribute, if is a negative value
     * then return the element counting from the last to the first.
     * @return it is the attribute, if is null the attribute not exist or the
     * index is bigger that attributes found.
     */
    public IniFileAttribute get(int index) {
        if (index < 0) {
            index = mAtts.size() + index;
        }
        if (index < 0) {
            return null;
        }
        if (index >= mAtts.size()) {
            return null;
        }
        return mAtts.get(index);
    }
    /**
     * It return an attribute with the specified name.
     *
     * @param attribute It is the name the attribute to search.
     * @return It is the attribute found, return null if the attribute with the
     * name specified not exist.
     */
    public Object get(String attribute) {
        IniFileAttribute attr = getAttribute(attribute);
        if (attr == null) {
            return null;
        }
        return attr.getValue();
    }
    /**
     * It return an attribute with the specified name.
     *
     * @param attribute It is the name the attribute to search.
     * @return It is the attribute found, return null if the attribute with the
     * name specified not exist.
     */
    public IniFileAttribute getAttribute(String attribute) {
        for (IniFileAttribute e : this.getAttributes()) {
            if (e.isThis(attribute)) {
                return e;
            }
        }
        return null;
    }
    /**
     * It set a value to an attribute that exist.
     *
     * @param name It is the name of the attribute.
     * @param value It is the new value for the attribute specified.
     * @return It is true if the attribute has been found and the new value has
     * been assigned, if is false then the attribute not exists.
     */
    public boolean put(String name, Object value) {
        IniFileAttribute e = getAttribute(name);
        if (e == null) {
            return false;
        }
        e.setValue(value);
        return true;
    }
    /**
     * It set a value to an attribute that exist.
     *
     * @param index It is the possition of the attribute to assign the value.
     * @param value It is the new value for the attribute specified.
     * @return It is true if the attribute has been found and the new value has
     * been assigned, if is false then the attribute not exists.
     */
    public boolean put(int index, Object value) {
        IniFileAttribute e = get(index);
        if (e == null) {
            return false;
        }
        e.setValue(value);
        return true;
    }
    /**
     * It set a value to an attribute that exists, if not exists then a new
     * attribute is created with the value and is add to the group of attributes
     * of the current section..
     *
     * @param name It is the name of the attribute.
     * @param value It is the new value for the attribute specified.
     * @return It is true if the attribute has been found and the new value has
     * been assigned, if is false then the attribute not exists.
     */
    public void set(String name, Object value) {
        IniFileAttribute e = getAttribute(name);
        if (e == null) {
            e = new IniFileAttribute();
            e.setName(name);
            e.setValue(value);
            mAtts.add(e);
            return;
        }
        e.setValue(value);
    }
    /**
     * It return the quantity of attributes in the section.
     *
     * @return It is the quantity of attributes in the section.
     */
    public int size() {
        return mAtts.size();
    }
    /**
     * It verify if an attribute with the name specified exists in the section.
     *
     * @param name It is the name of the attribute to verify if exists in the
     * section/
     * @return It is true if at less one attribute has the same name.
     */
    public boolean exists(String name) {
        for (IniFileAttribute e : this.getAttributes()) {
            if (e.isThis(name)) {
                return true;
            }
        }
        return false;
    }
    /**
     * It verify if an attribute exists in the current section.
     *
     * @param target it is the attribute to search in the section.
     * @return It is true if at less one attribute has the same name.
     */
    boolean exists(IniFileAttribute target) {
        for (IniFileAttribute e : this.getAttributes()) {
            if (e.isThis(target)) {
                return true;
            }
        }
        return false;
    }
    /**
     * It compare the current Section object with another Section Object, the
     * comparison is with the name, and the name of its attributes, no matter
     * the order of the attributes.
     *
     * @param target It is the Section object to compare.
     * @return Only if the the name of the section, the attributes and the
     * number of attributes are the same return true.
     */
    public boolean compareTo(IniFileSection target) {
        if (target == null) {
            return false;
        }
        if ((target.getKeyName() == null) && (this.getKeyName() == null)) {
            return true;
        }
        if (target.getName() == null) {
            return false;
        }
        if (this.getName() == null) {
            return false;
        }
        if (!this.getKeyName().equalsIgnoreCase(target.getKeyName())) {
            return false;
        }
        if (target.size() != this.size()) {
            return false;
        }
        boolean flag = true;
        ArrayList<IniFileAttribute> l1 = target.getAttributes();
        for (IniFileAttribute e1 : l1) {
            flag = flag && this.exists(e1);
        }
        return flag;
    }
    /**
     * It compare the name of the section, the names of the attributes and its
     * values, no matter the order.
     *
     * @param target It is another IniFileSection object to compare the current
     * object.
     * @return It is true if the section are equals in names and values.
     */
    public boolean compareToFull(IniFileSection target) {
        if (target == null) {
            return false;
        }
        if ((target.getKeyName() == null) && (this.getKeyName() == null)) {
            return true;
        }
        if (target.getName() == null) {
            return false;
        }
        if (this.getName() == null) {
            return false;
        }
        if (!this.getKeyName().equalsIgnoreCase(target.getKeyName())) {
            return true;
        }
        boolean flag = true;
        boolean each = true;
        ArrayList<IniFileAttribute> l1 = target.getAttributes();
        IniFileAttribute attr = null;
        for (IniFileAttribute e1 : l1) {
            each = false;
            attr = this.getAttribute(e1.getKeyName());
            if (attr != null) {
                if (e1.compareTo(attr)) {
                    each = true;

                }
            }
            flag = flag && each;
        }
        return flag;
    }
    /**
     * It return the structure of the section in a JSON format.
     * @return It string contain 
     */
    public String toJSON() {
        String res = "";
        boolean titled = false;
        if ( (this.getName() != null)  &&
              ( !this.getName().equalsIgnoreCase(IniFileSection.NO_TITLE_SECTION) ) ){
            titled = true;
        }
        
        if (this.mAtts == null) {
            res = "{}";
        } else if (this.mAtts.size() < 1) {
            res = "{}";
        } else {
            boolean flag = false;
            for (IniFileAttribute attr : this.mAtts) {
                if (flag) {
                    res = res + ",";
                }
                res = res + attr.toJSON();
                flag = true;
            }
            
        }
        if ( titled ){
                res = "\"" + this.getName() + "\":{" + res  + "}";
        }
        return res;
    }
    public String toJSONNicely(){
        String res = "";
        String space = "  ";
        boolean titled = false;
        if ( (this.getName() != null)  &&
              ( !this.getName().equalsIgnoreCase(IniFileSection.NO_TITLE_SECTION) ) ){
            titled = true;
            space = "    ";
        }
        
        if (this.mAtts == null) {
            res = "{}";
        } else if (this.mAtts.size() < 1) {
            res = "{}";
        } else {
            boolean flag = false;
            for (IniFileAttribute attr : this.mAtts) {
                if (flag) {
                    res = res + ", ";
                }
                res = res + "\n"+space+space;
                res = res + attr.toJSON();
                flag = true;
            }
            
        }
        if ( titled ){
                res = space+"\"" + this.getName() + "\":{" + res  + "\n"+space+"}";
        }
        return res;
    }
}
