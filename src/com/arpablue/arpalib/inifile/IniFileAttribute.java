/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.inifile;

/**
 * It class group the data of a file with his attribute name.
 */
public class IniFileAttribute extends IniFileBase{
    protected Object mValue;

    /**
     * default constructor.
     */
    public IniFileAttribute(){
        super();
    }
    /**
     * It extract the data form a line that use the following format: attr=value
     * @param line It is the attribute of the line.
     * @return It an attribute with the values, it the format is not valid then return null.
     */
    public static IniFileAttribute parse(String line){
        if( line == null ){
            return null;
        }
        line = line.trim();
        if( line.length() < 1){
            return null;
        }
        int p = line.indexOf("=");
        
        String attr;
        String value;
        
        if( p < 0 ){
            attr = line;
            value = null;
        }else if( p == line.length() - 1 ){
            attr = line.substring(0, p);
            value = null;
        }else{
            attr = line.substring(0, p);
            value = line.substring( p + 1 );
            value = value.trim();
        }
        IniFileAttribute res = new IniFileAttribute( attr, value );
        return res;
    }
    /**
     * It create a IniFileAttribute with a name and a value.
     * @param name It is the name of the attribute.
     * @param value It is the value assigned to the attribute.
     */
    public IniFileAttribute( String name, Object value){
        this.setName( name );
        this.setValue( value );
    }
    /**
     * It specify the value for the current attribute.
     * @param value It is the value to be assigned to the attribute.
     */
    public void setValue( Object value  ){
        mValue = value;
    }
    /**
     * It return the value of the current attribute.
     * @return It is the current value.
     */
    public Object getValue(){
        return mValue;
    }
    /**
     * It string representative of the current attribute.
     * @return
     */
    @Override
    public String toString(){
        return this.getName() + " = " + this.getValue();
    }
    /**
     * It pase a string to get the data of the string.
     * @param target It is the string with the data.
     * @return It is true if the parse has been successful
     */
    @Override
    public boolean setData(String target) {
        if ( target == null ){
            return false;
        }
        target = target.trim();
        if( target.length() < 1 ){
            return false;
        }
        int pos = target.indexOf(';');
        int equal = target.indexOf('=');
        if( equal > pos  ){
            return false;
        }
        String name = target.substring(0,equal);
        String value =  target.substring(equal+1);
        this.setName( name );
        this.setValue( value );
        return true;
    }
    /**
     * It verify if the a string has in format to parse the attribute.
     * @param target
     * @return 
     */
    @Override
    public boolean isThisType(String target) {
        if( target == null ){
            return false;
        }
        int pos = target.indexOf('=');
        if( pos < 0 ){
            return false;
        }
        int pos2 = target.indexOf(";");
        if( pos2 > -1){
            if( pos2 == 0 ){
                return false;
            }
            if( pos2 < pos ){
                return false;
            }
        }
        pos2 = target.indexOf("]");
        if( pos2 > -1){
            if( pos2 > pos ){
                return false;
            }
        }
        return true;

    }
    /**
     * It compare the name and the value of the attribute.
     * @param attb It si the attribute to be compare.
     * @return It is true if the name and the value are the equals.
     */
    public boolean compareTo( IniFileAttribute attb){
        if( attb == null){
            return false;
        }
        if( !this.getKeyName().equalsIgnoreCase( attb.getKeyName() ) ){
            return false;
        }
        if( !this.getValue().toString().equalsIgnoreCase( attb.getValue().toString() )){
            return false;
        }
        return true;
    }
    /**
     * It return the attribute in a JSON format.
     * @return It is the attribute in a JSON format.
     */
    public String toJSON(){
        String res = "\""+this.getName()+"\":";
        if( this.getValue() == null ){
            res = res + "null";
            return res;
        }
        if(this.getValue() instanceof String ){
            res = res + "\""+this.getValue()+"\"";
            return res;
        }
        res = res +this.getValue();
        return res;
    }
}
