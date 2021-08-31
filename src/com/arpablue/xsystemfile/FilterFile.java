/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.xsystemfile;

//import com.arpablue.jsontree.JSONtree;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

abstract class FilterAction{
    protected int mAction = 1;
    protected String mMatch;
    protected String mItem;
    /**
     * Default constructor;
     */
    public FilterAction(){}
    public  FilterAction( String match ){
        this.setMatch(match);
    }
    /**
     * It specify the action to be applied.
     * @param action it is the action identifier.
     */
    public void setAction( int action){  mAction = action;   }
    /**
     * It is the current action to be be applied.
     * @return It is the action to be applied.
     */
    public int getAction(){  return mAction;  }
    /**
     * It specify the match to be used.
     * @param match it is the filter to be used to identify the string.
     */
    public void setMatch(String match){      
        mMatch = match; 
        if( mMatch != null ){
            mMatch = mMatch.toLowerCase();
        }
    }
    /**
     * It return the filter to be used.
     * @return It is the filter to be used/
     */
    public String getMatch(){        return mMatch;    }
    /**
     * It is the item to applied to  the match.
     * @param item It is the item to be applied to the match.
     */
    public void setItem(String item ){   mItem = item; }
    /**
     * It return the Item to apply to the match.
     * @return It is the item to apply to the match..
     */
    public String getItem(){    return mItem; }
    /**
     * It verify the line is a match o f the current action.
     * @param line
     * @return It is true if a match exists.
     */
    public boolean isMatch(String line ){
        line = line.toLowerCase();

        if( ( line == null  )&& ( this.getMatch() == null ) ){
            return true;
        }
        if( line == null ){
            return false;
        }
        if(  this.getMatch() == null ){
            return false;
        }
        if( ( line.length() == 0  )&& ( this.getMatch().length() == 0 ) ){
            return true;
        }
        if( line.length() == 0 ){
            return false;
        }
        
        if(  this.getMatch().length() == 0 ){
            return true;
        }
        if( getMatch().indexOf('*') > -1 ){
           return matchMultiple(line);
        }
        String aux = line.toLowerCase();
        
        if(  aux.contains( getMatch()) ){
            return true;
        }
        return false;
    }
    /**
     * Verify that match of string could be in a line, the * is the comodin for the rest of the text.
     * @param line It is true if the line has the match.
     * @return 
     */
    protected boolean matchMultiple(String line){
        String[] v = getMatch().split("\\*");
        int index = -1;
        int prev = -1;
        boolean flag = true;
        for( int i = 0; i < v.length; i++){
            if( v[i].length() == 0  ){
                continue;
            }
            index = line.indexOf( v[i] );
            if( index < 0 ){
                return false;
            }
            if(prev > index ){
                return false;
            }
            prev = index;
        }
        return true;
    }
    /**
     * It validate if the match corresponding to the current match them apply the action.
     */
    public String apply(String line){
        if( line == null ){
            return null;
        }
        if(  isMatch( line ) ){
            return applyAction( line );
        }
        return line;
    }
    protected abstract String applyAction(String line);
}
/**
 * It is a interface to use over object that needs apply an action over a line.
 * @author augusto
 */
interface IActionFilter{
    public String applyAction(String line);
}
/**
 * It return a empty line.
 * @author augusto
 */
class FilterActionRemoveLine extends FilterAction{
    public FilterActionRemoveLine(){
        super();
    }
    public FilterActionRemoveLine( String match){
        super( match);
        
    }
    @Override
    protected String applyAction(String line) {
       return null;
    }
}
/**
 * It remove all empty lines, this lines with size  equal to zero or 
 * lines formed only with white spaces.
 * @author augusto
 */
class FilterActionRemoveEmtpyLine extends FilterAction{
    public FilterActionRemoveEmtpyLine(){
        super();
    }
    /**
     * It verify if the current kline is empty.
     * @param line It is the target line.
     * @return It return true if the line is empty of if is a line with white spaces.
     */
    @Override
    public boolean isMatch(String line ){
        line = line.replaceAll("\\s","");
        line = line.trim();
        return ( line.length() == 0);
    }
    @Override
    protected String applyAction(String line) {
       return null;
    }
    
}
/**
 * It add a string at the end of the line.
 * @author augusto
 */
class FilterActionAddAtEnd extends FilterAction{
    public FilterActionAddAtEnd(){
        super();
    }
    public FilterActionAddAtEnd( String match){
        super( match );
    }
    @Override
    protected String applyAction(String line) {
       return line + this.getItem();
    }
}
/**
 * It add an string tot he Beginning Of theSTRING.
 * @author augusto
 */
class FilterActionAddBeginning extends FilterAction{
    public FilterActionAddBeginning(){
        super();
    }
    public FilterActionAddBeginning( String match){
        super( match );
    }
    @Override
    protected String applyAction(String line) {
       return this.getItem() + line;
    }
}
/**
 * It add an string tot he Beginning Of theSTRING.
 * @author augusto
 */
class FilterActionApplyObject extends FilterAction{
    
    protected IActionFilter mApplyObj;
    public FilterActionApplyObject(){
        super();
    }
    public FilterActionApplyObject( String match){
        super( match );
    }
    public FilterActionApplyObject( String match,IActionFilter obj){
        super( match );
        this.setApplyObject(obj);
    }
    public void setApplyObject( IActionFilter obj){
        mApplyObj = obj;
    }
    @Override
    protected String applyAction(String line) {
       if( mApplyObj != null){
           return mApplyObj.applyAction(line);
       }
       
       return null;
    }
}
//class JsonAction extends JSONtree implements IActionFilter{
//    protected String mItem = "";
//    public void setItem( String item){
//        mItem = item;
//    }
//    public String getItem(){
//        return mItem;
//    }
//    protected static int lastOcurrence( String target, char str){
//       return -1;
//    }
//    @Override
//    public String applyAction(String line) {
//       int from = line.indexOf("{");
//       String str = line.substring(0, from);
//       line = line.substring(from);
//        try{
//            this.parse( line );
//            line = str + "\n" +this.getNicely()+this.getItem();
//        }catch( Exception e ){
//            line = "ERROR: " + e.getMessage() + this.getItem();
//        }
//        
//       return line;
//    }
//    
//}
    
/**
 * It filter the content of the lines of a file and apply an action depending of the match that found.
 * @author augusto
 */
public class FilterFile {
    public static final int ACTION_REMOVE_LINE = 1;
    public static final int ACTION_REMOVE = 1;
    public static final int ACTION_APPLY = 2;
    public static final int ACTION_ADD_END = 3;
    public static final int ACTION_ADD_BEGINNING = 4;
    public static final int ACTION_REMOVE_EMPTY = 4;
    protected String mFile;
    protected String mOutput;
    protected ArrayList<FilterAction> mActions;
    /**
     * Default constructor.
     */
    public FilterFile(){
            mActions = new ArrayList<FilterAction>();
    }
    public FilterFile(String file){
        this();
        this.setFile( file );
    }
    /**
     * It specify the file to be apply and action.
     * @param path 
     */
    public void setFile(String path){
        if( path != null ){
            path = path.trim();
            path = path.replace("\\","//");
        }
        mFile = path;
    }
    /**
     * It return the file where the filters will be applied.
     * @return 
     */
    public String getFile(){
        return mFile;
    }
    /**
     * It specify the result file after apply the filter tot he target file.
     * @param file 
     */
    public void setOutputFile(String path){
        if( path != null ){
            path = path.trim();
            path = path.replace("\\","//");
        }
        mOutput = path;
    }
    /**
     * It return the output used to set the result.
     * @return 
     */
    public String getOuputFile(){
        return mOutput;
    }
    /**
     * Add an action to the list of action to be applied.
     * @param action 
     */
    public void addAction(FilterAction action ){
        if( action == null ){
            return;
        }
        mActions.add( action );
    }
    /**
     * Clean all actions.
     */
    public void cleanActions(){
        mActions.clear();
    }
    /**
     * Remove any line with the match specified.
     * @param match 
     */
    public void addActionRemoveLine(String match ){
        FilterAction a = new FilterActionRemoveLine( match );
        this.mActions.add(a);
    }
    /**
     * It add an action to remove the empty lines of the file
     */
    public void addActionRemoveEmptyLine(){
        FilterAction a = new FilterActionRemoveEmtpyLine();
        this.mActions.add(a);
    }
    /**
     * It add a text in the beginning of any line that have a match.
     * @param match it is the match to found in the line.
     * @param str It is the string to be add at the beginning of the line.
     */
    public void addActionAddAtBeginning(String match, String str){
        FilterAction a = new FilterActionAddBeginning(match);
        a.setItem( str );
        this.mActions.add(a);
    }
    /**
     * It add a text in the beginning of any line that have a match.
     * @param match it is the match to found in the line.
     * @param str It is the string to be add at the beginning of the line.
     */
    public void addActionAddAtTheEnd(String match, String str){
        FilterAction a = new FilterActionAddAtEnd(match);
        a.setItem( str );
        this.mActions.add(a);
    }
    /**
     * It add a text in the beginning of any line that have a match.
     * @param match it is the match to found in the line.
     * @param str It is the string to be add at the beginning of the line.
     */
    public void addActionApplyAction(String match, IActionFilter obj){
        FilterActionApplyObject a = new FilterActionApplyObject(match);
        a.setApplyObject(obj);
        this.mActions.add(a);
    }
//    /**
//     * It add a text in the beginning of any line that have a match.
//     * @param match it is the match to found in the line.
//     * @param str It is the string to be add at the beginning of the line.
//     */
//    public void addActionNiceJSON(String match,String item){
//        JsonAction j = new JsonAction();
//        j.setItem(item);
//        addActionApplyAction( match, j );
//    }
//    /**
//     * It add a text in the beginning of any line that have a match.
//     * @param match it is the match to found in the line.
//     * @param str It is the string to be add at the beginning of the line.
//     */
//    public void addActionNiceJSON(String match){
//        addActionNiceJSON(match,"");
//    }
    /**
     * It apply the filters to an specific file, the autoput file will be created tin the same folder with the 
     * the text _res at the end of the file name.
     * @param file It is the target file.
     * @return It is true if th file exists and it can be processed.
     */
    public boolean apply(String file){
        return apply( file, null);
    }
    /**
     * It apply the filter to the file file and set the result in another file.
     * @param file It is the target file where the filters will be applied.
     * @param ouputfile It is the file where the content oof the target file will be set after apply the filters.
     * @return 
     */
    public boolean apply(String file, String outputFile){
        if( file == null ){
            return false;
        }
        file = file.trim();
        if( file.length()  == 0){
            return false;
        }
        this.setFile(file);
        this.setOutputFile( outputFile ) ;
        return this.apply();
    }
    /**
     * If the output file is not specified then create the output file in the same folder of the target file 
     * with the "_res" at the end of the file name.
     */
    protected void formatOutput(){
        if( this.getFile() == null ){
            return;
        }
        if( this.getOuputFile() != null ){
            return;
        }
        String[] files = mFile.split( "\\\\" );
        String res = files[ files.length - 1];
        String target = res;
        files = res.split("\\.");
        files[0] = files[0]+"_res";
        res = "";
        for( int i = 0; i < files.length; i++ ){
            if( i != 0){
                res = res + ".";
            }
                
            res = res + files[i];
        }
        res = mFile.replace( target, res);
        this.setOutputFile( res );
    }
    /**
     * It delete the oupput files, to create a new file.
     */
    protected void deleteOutputFiles(){
        if( this.getOuputFile() == null ){
            return;
        }
        try{
            File f = new File( this.getOuputFile() );
            if( f.exists() && f.isFile() ){
                f.delete();
            }
        }catch( Exception e){
            
        }
    }
    /**
     * It apply the filter to the file and set the result in a ouput file.
     * @return It is ture if the file can be processed.
     */
    public boolean apply(){
        formatOutput();
        deleteOutputFiles();
        try{
            RandomAccessFile read = new RandomAccessFile( this.getFile(), "r");
            RandomAccessFile write = new RandomAccessFile( this.getOuputFile(), "rw");
            String line = "";
            while( line != null){
                line = read.readLine();
                if( line == null){
                    break;
                }
                line = applyFilters( line );
                if( line != null ){
                    write.writeBytes( line+"\n" );
                    
                }else{
                    line = "";
                }
                
            }
            read.close();
            write.close();
        }catch( Exception e ){
            System.out.println("ERROR( FilterFile - apply ): "+e.getMessage() );
            e.printStackTrace();
        }
        return true;
    }
    protected String applyFilters( String line ){
        for( FilterAction action : this.mActions){
            line = action.apply( line );
        }
        return line;
    }
}
