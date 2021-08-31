/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.inifile;

import com.arpablue.interfaces.IPrintable;
import com.arpablue.tools.StringManager;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author engau
 */
public class IniFileData implements IPrintable{
    protected String mErrors;
    protected String mFile;
    protected ArrayList<IniFileSection> mSections;
    /**
     * Default constructor.
     */
    public IniFileData(){
        initer();
    }

    /**
     * It initialize the object with default values, this is only with the default section
     */
    protected void initer(){
        mSections = new ArrayList<IniFileSection>();
        mSections.add( new IniFileSection() );
    }
    /**
     * It remove all attributes and all sections.
     */
    public void clean(){
         initer();
    }
    /**
     * It return the quantity of sections.
     * @return It is the quantity of sections.
     */
    public int size(){
        return mSections.size();
    }
    /**
     * It return a section from an index, if the index is out of the size then return null.
     * @param index iIt is the position of the the section.
     * @return It is the section corresponding of the index, if it is null the 
     */
    public IniFileSection getSection( int index){
        if( this.size() < 1){
            return null;
        }
        if( index < 0){
            index = index * -1;
            index = this.size() % index;
            index++;
            index = this.size() - index;
        }
        
        return mSections.get(index);
    }
    /**
     * It return the name of a section.
     * @param name It is the section of the section.
     * @return It is the section with the same name, if the section not exists, then return null.
     */
    public IniFileSection getSection( String name){
        for( IniFileSection section : mSections ){
            if( section.isThis( name ) ){
                return section;
            }
            
        }
        return null;
    }
    /**
     * It verify is a section exists.
     * @param section it is the name of the section.
     * @return It is true if the section exists.
     */
    public boolean sectionExist( String section ){
        return ( getSection( section ) != null);
    }
    /**
     * It add a new section with the name specified.
     * @param name 
     */
    public void setSection(String name){
        if( name == null ){
            return;
        }
        IniFileSection section  = new IniFileSection();
        section.setName(name);
        setSection( section );
    }
    /**
     * Add a new IniFile Section, if the another section has the same name, the section is not added.
     * @param section It is the new IniFileSection to be added.
     */
    protected void putSection( IniFileSection section ){
        if( section == null ){
            return;
        }
        int pos = getSectionIndex( section.getName() );
        if( pos == -1 ){
            return;
        }
        mSections.set(pos, section);
    }
    /**
     * It replace the section with the same name by the new section, if the section not exists then is added.
     * @param section It is the new IniFileSection to be added.
     */
    protected void setSection( IniFileSection section ){
        if( section == null ){
            return;
        }
        int pos = getSectionIndex( section.getName() );
        if( pos == -1 ){
            this.mSections.add( section );
            return;
        }
        mSections.set(pos, section);
        
    }
    /**
     * 
     * @return 
     */
    public ArrayList<String> getKeys(){
        ArrayList<String> res = new ArrayList<String>();
        ArrayList<String> keys = null;
        for(IniFileSection section : this.mSections ){
            keys = section.getKeys();
            for( String key : keys ){
                res.add(key);
            }
        }
        return res;
    }
    /**
     * It return the index of section with a specific name.
     * @param section it is the name of the section to be search.
     * @return It is the position of the index, if the index not exists then return -1.
     */
    public int getSectionIndex( String section){
        IniFileSection sec = null;
        
        for( int i = 0;  i < this.mSections.size(); i++ ){
            sec = mSections.get(i);
            if( sec.isThis(section) ) {
                return i;
            }
        }
        return -1;
    }
    /**
     * It specify the ini file to be loaded.
     * @param file
     */
    protected void setPathFile( String file){
        mFile = file;
    }
    /**
     * It get the section and the attribute in a string, the string should have the following format: <section>/<attribute>
     * @param line
     * @return 
     */
    protected String[] getDupla(String line){
        if( line == null ){
            return null;
        }
        String[] v = line.split("/");
        String section = IniFileSection.NO_TITLE_SECTION;
        String attr = "";
        if(v.length ==0 ){
            return null;
        }
        
        if( v.length == 1){
            attr = v[0];
        }else{
                section = v[0].trim();
                attr = v[1].trim();
         }
        if( section.length() == 0){
            section = IniFileSection.NO_TITLE_SECTION;
        }
        if( attr.length() == 0 ){
            return null;
        }
        String[] res = new String[2];
        res[0] = section;
        res[1] = attr;
        return res;
    }
    /**
     * It update the value of an attribute
     * @param key It is the key reference of an attribute in the structure, it use the following format: section / attribute.
     * If the / is not present then it is a reference for an attribute without section or in the general section.
     * If the attribute not exists then the value is added.
     * @param value It is the value to be set,
     */
    public void put( String key, Object value){
        String[] dupla = getDupla( key );
        if( dupla == null ){
            return;
        }
        IniFileSection section = this.getSection( dupla[0] );
        if( section != null ){
            section.set( dupla[1], value);
            return;
        }
        section = new IniFileSection( dupla[0] );
        section.set(dupla[1], value);
        this.putSection(section);
    }
    /**
     * It update the value of an attribute
     * @param key It is the key reference of an attribute in the structure, it use the following format: section / attribute.
     * If the / is not present then it is a reference for an attribute without section or in the general section.
     * If the attribute not exists then the value is not added.
     * @param value It is the value to be set,
     */
    public void set( String key, Object value){
        String[] dupla = getDupla( key );
        if( dupla == null ){
            return;
        }
        IniFileSection section = this.getSection( dupla[0] );
        if( section != null ){
            section.set( dupla[1], value);
            return;
        }
        section = new IniFileSection( dupla[0] );
        section.set(dupla[1], value);
        this.setSection(section);
    }
    /**
     * It return the value from an attribute.
     * @param key it is the path of the attribute.
     * @return It is the value  of the attribute, if return null then the attribute maybe not exists, use the exists method to verify.
     */
    public Object get( String key )    {
        String[] dupla = getDupla( key );
        if( dupla == null ){
            return null;
        }
        IniFileSection section = this.getSection( dupla[0] );
        if( section == null ) {
            return null;
        }
        return section.get( dupla[1] );
    }
    /**
     * It verify if an attribute exists.
     * @param key it is the attribute, it necessary use the format : section / attribute
     * @return It is true if the attribute exists.
     */
    public boolean exists(String key){
        String[] dupla = getDupla( key );
        if( dupla == null ){
            return false;
        }
        IniFileSection section = this.getSection( dupla[0] );
        return ( section != null );
        
    }
    /**
     * It return the current Structure in a Ini file.
     * @return 
     */
    @Override
    public String toString(){
        String res = "";
        for( IniFileSection section : mSections ){
            res = res + section + ",";
        }
        return res;
    }
    @Override
    public String toPrint() {
        String res = "";
        for( IniFileSection section : mSections ){
            res = res + section + "\n";
        }
        return res;
    }
/**
     * It return the structure of the IniFile in a json file format, in an structure that is more understandable  
     * for the human eye .
     * @return It is the string that have the structure of the in file in json format.
     */
    public String toJSONNicely(){
        String res = "{   \n";
        if( this.mSections == null){
            return "{}";
        }
        if( this.mSections.size() < 1){
            return "{}";
        }
        
        boolean flag = false;
        for( IniFileSection section : this.mSections ){
            if( flag ){
                res = res + ",\n";
            }
            res = res + "" + section.toJSONNicely();
            flag = true;
        }
        res = res + "\n}";
        return res;
    }
    /**
     * It return the structure of the IniFile in a json file format.
     * @return It is the string that have the structure of the 
     */
    public String toJSON(){
        String res = "{";
        if( this.mSections == null){
            return "{}";
        }
        if( this.mSections.size() < 1){
            return "{}";
        }
        boolean flag = false;
        for( IniFileSection section : this.mSections ){
            if( flag ){
                res = res + ",";
            }
            res = res + section.toJSON();
            flag = true;
        }
        res = res + "}";
        return res;
    }



}
