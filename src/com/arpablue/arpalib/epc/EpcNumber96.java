/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.epc;
import java.util.Random;


public class EpcNumber96 {
    protected final String EPC_SGTIN96_PREFIX = "urn:epc:tag:sgtin-96:";
    protected final String EPC_PURE_IDENTITY_PREFIX = "urn:epc:id:sgtin:";
    protected final String RAW_PREFIX = "urn:epc:raw:";
    protected final String RAW_EPC_PREFIX = "na";

    protected final int FILTER_SIZE = 3;
    protected final int HEADER_SIZE = 8;
    protected final int SERIAL_SIZE = 38;
    protected final int PARTITION_SIZE = 3;
    protected final int EPC_HEX_SIZE = 24;

    protected String mEpcHeader;
    protected String mFilter;
    protected String mPartition;
    protected String mCompanyPrefix;
    protected String mItemRef;
    protected String mSerial;

    protected int mEpcHeaderInt;
    protected int mFilterInt;
    protected int mPartitionInt;
    protected int mCompanyPrefixInt;
    protected int mItemRefInt;
    protected int mSerialInt;

    protected static int[][] MATRIX = null;

    /**
     * It is the default constructor, this generate random values for the EPC number.
     */
    public EpcNumber96(){
        initMatrix();
        initHeader();
        initFilter();
        initPartition();
        initSerial();
    }
    /**
     * It initialize the partition matrix the first time. This matrizx contain the size of the Company Prefix and the Item reference,
     * it is according to the partition type used. the detail can be found in:
     * https://www.epc-rfid.info/sgtin-partition-values
     */
    protected static void initMatrix(){
        if( MATRIX != null ){
            return;
        }
        MATRIX = new int[7][4];
//        https://www.epc-rfid.info/sgtin-partition-values
//        Partition Value (P)	GS1 Company Prefix	    Indicator/Pad Digit and Item Reference
//        Index                     Bits (M)	Digits (L)	Bits (N)	   Digits
//          0	                      40	      12	       4	          1
//          1	                      37	      11	       7	          2
//          2	                      34	      10	       10	          3
//          3	                      30	       9	       14	          4
//          4	                      27	       8	       17	          5
//          5	                      24	       7	       20	          6
//          6	                      20	       6	       24	          7

        MATRIX[0][0] = 40;
        MATRIX[0][1] = 12;
        MATRIX[0][2] = 4;
        MATRIX[0][3] = 1;

        MATRIX[1][0] = 37;
        MATRIX[1][1] = 11;
        MATRIX[1][2] = 7;
        MATRIX[1][3] = 2;

        MATRIX[2][0] = 34;
        MATRIX[2][1] = 10;
        MATRIX[2][2] = 10;
        MATRIX[2][3] = 3;

        MATRIX[3][0] = 30;
        MATRIX[3][1] = 9;
        MATRIX[3][2] = 14;
        MATRIX[3][3] = 4;

        MATRIX[4][0] = 27;
        MATRIX[4][1] = 8;
        MATRIX[4][2] = 17;
        MATRIX[4][3] = 5;

        MATRIX[5][0] = 24;
        MATRIX[5][1] = 7;
        MATRIX[5][2] = 20;
        MATRIX[5][3] = 6;

        MATRIX[6][0] = 20;
        MATRIX[6][1] = 6;
        MATRIX[6][2] = 24;
        MATRIX[6][3] = 7;

    }

    /**
     * It return  the respective partition type according to the combination of number of digits of the Company Prefix and the number of
     * digits used by the Item Reference, if the combination not exists then the method raise an exception.
     * @param companyPrefixDigits It is the number of digits in decimal used for Company Prefix.
     * @param itemReferenceDigits It is the number of digits in decimal used for Item Reference.
     * @return
     */
    public static int getPartitionByDigits(int companyPrefixDigits, int itemReferenceDigits){
        for( int i =0 ; i <  7; i++){
            if ( ( MATRIX[i][1] == companyPrefixDigits ) && ( MATRIX[i][3] == itemReferenceDigits ) ){
                return i;
            }
        }
        throw new NumberFormatException("The digits not correspont to any partition.");
    }

    /**
     * It return the number of digits in binary code used for a Company Prefix according a partition type.
     * @param partition It is the partition type, the partition is a value between 0 and 6.
     * @return It is the number of digits used by the in binary code for Company Prefix.
     */
    public static int getCompanyPrefixBitsSize( int partition){
        if( partition < 0){
            partition = 0;
        }
        if( partition > 6){
            partition = 6;
        }

        return MATRIX[partition][0];
    }
    /**
     * It return the number of digits in decimal for a Company Prefix according a partition type.
     * @param partition It is the partition type, the partition is a value between 0 and 6.
     * @return It is the number of digits used by the in decimal for Company Prefix.
     */
    public static int getCompanyPrefixDigitsSize( int partition){
        if( partition < 0){
            partition = 0;
        }
        if( partition > 6){
            partition = 6;
        }
        return MATRIX[partition][1];
    }
    /**
     * It return the number of digits in binary code used for a Company Prefix according a partition type.
     * @param partition It is the partition type, the partition is a value between 0 and 6.
     * @return It is the number of digits used by the in binary code for Company Prefix.
     */
    public static int getItemReferenceBitsSize( int partition){
        if( partition < 0){
            partition = 0;
        }
        if( partition > 6){
            partition = 6;
        }
        return MATRIX[partition][2];
    }
    /**
     * It return the number of digits in decimal for a Company Prefix according a partition type.
     * @param partition It is the partition type, the partition is a value between 0 and 6.
     * @return It is the number of digits used by the in decimal for Company Prefix.
     */
    public static int getItemReferenceDigitsSize( int partition){
        if( partition < 0){
            partition = 0;
        }
        if( partition > 6){
            partition = 6;
        }
        return MATRIX[partition][3];
    }

    /**
     * It assign to the header the SGTIN-96 code in binary.
     */
    protected void initHeader(){

        this.setHeaderBinary("00110000"); // thi is for SGTIN-96
        //this.setHeaderBinary("00110110"); // thi is for SGTIN-198

    }

    /**
     * It generate a random number for the filter, the value generated is beetween 1 and 7.
     */
    protected void initFilter(){
        setFilterBinary( getRandomBinary(FILTER_SIZE) );
    }

    /**
     * It generate a random number for the partition, the number generated is between 0 and 6.
     */
    protected void initPartition(){
        setPartitionBinary( getRandomBinary( 7,PARTITION_SIZE) );
    }

    /**
     * It generated a random number for Company Prefix, this generate in base of the current partition type.
     */
    protected void initCompanyPrefix(){
        int bitSize = getCompanyPrefixBitsSize( this.getPartitionInteger() );
        int decSize = getCompanyPrefixDigitsSize( this.getPartitionInteger() );
        String binary = getRandomBin( decSize, bitSize );
        setCompanyPrefixBinary( binary );
    }
    /**
     * It generated a random number for Item Reference, this generate in base of the current partition type.
     */
    protected  void initItemReference(){
        int bitSize = getItemReferenceBitsSize( this.getPartitionInteger() );
        int decSize = getItemReferenceDigitsSize( this.getPartitionInteger() ) ;
        String binary = getRandomBin( decSize, bitSize );
        setItemReferenceBinary( binary );
    }

    /**
     * It generate a random number used for serial.
     */
    protected  void initSerial(){
        int r = getRandom(900);
        String res = Integer.toBinaryString( r );
        res = zeroFill( res, SERIAL_SIZE);
        setSerialBinary( res );
    }

    /**
     * It return the binary cof the header.
     * @return It is header binary code.
     */
    public String getHeaderBinary(){
        return this.mEpcHeader;
    }
    /**
     * It return the binary cof the filter.
     * @return It is filter binary code.
     */
    public String getFilterBinary(){
        return this.mFilter;
    }
    /**
     * It return the binary cof the partition.
     * @return It is partition binary code.
     */
    public String getPartitionBinary(){
        return this.mPartition;
    }
    /**
     * It return the binary cof the Company Prefix.
     * @return It is Company Prefix binary code.
     */
    public String getCompanyPrefixBinary(){
        return this.mCompanyPrefix;
    }
    /**
     * It return the binary cof the Item Reference.
     * @return It is Item Reference binary code.
     */
    public String getItemReferenceBinary(){
        return this.mItemRef;
    }
    /**
     * It return the binary cof the serial.
     * @return It is serial binary code.
     */
    public String getSerialBinary(){
        return this.mSerial;
    }

    /**
     * It specify the a binary number for the header
     * @param value It is the binary number to assign to the header.
     */
    public void setHeaderBinary( String value ){
        mEpcHeader = zeroFill( value, HEADER_SIZE);
        mEpcHeaderInt = Integer.parseInt( value, 2);
    }
    /**
     * It specify the a binary number for the header
     * @param value It is the binary number to assign to the header.
     */
    public void setFilterBinary( String value ){
        mFilter = zeroFill( value, FILTER_SIZE);
        mFilterInt = Integer.parseInt( value, 2);

    }
    /**
     * It specify the a binary number for the header
     * @param value It is the binary number to assign to the header.
     */
    public void setPartitionBinary( String value ){
        mPartition = zeroFill( value, PARTITION_SIZE);
        mPartitionInt = Integer.parseInt( value, 2);
        initCompanyPrefix();
        initItemReference();

    }
    /**
     * It specify the a binary number for the header
     * @param value It is the binary number to assign to the header.
     */
    public void setCompanyPrefixBinary( String value ){

        mCompanyPrefix = zeroFill( value, getCompanyPrefixBitsSize( getPartitionInteger() ) );

        mCompanyPrefixInt = Integer.parseInt( value, 2);

    }
    /**
     * It specify the a binary number for the header
     * @param value It is the binary number to assign to the header.
     */
    public void setItemReferenceBinary( String value ) {
        mItemRef = zeroFill(value, getItemReferenceBitsSize(getPartitionInteger()));
        mItemRefInt = Integer.parseInt(value, 2);
    }
    /**
     * It specify the a binary number for the header
     * @param value It is the binary number to assign to the header.
     */
    public void setSerialBinary( String value ){
        mSerial = zeroFill( value, SERIAL_SIZE);;
        mSerialInt = Integer.parseInt( value, 2);
    }

    /**
     * It the decimal value of the header.
     * @return It is the integer value of the header.
     */
    public int getHeaderInteger(){
        return this.mEpcHeaderInt;
    }
    /**
     * It the decimal value of the filter.
     * @return It is the integer value of the filter.
     */
    public int getFilterInteger(){
        return this.mFilterInt;
    }
    /**
     * It the decimal value of the header.
     * @return It is the integer value of the header.
     */
    public int getPartitionInteger(){
        return this.mPartitionInt;
    }
    /**
     * It the decimal value of the Company Prefix.
     * @return It is the integer value of the Company Prefix.
     */
    public int getCompanyPrefixInteger(){
        return this.mCompanyPrefixInt;
    }
    /**
     * It the decimal value of the Item Reference.
     * @return It is the integer value of the Item Reference.
     */
    public int getItemReferenceInteger(){
        return this.mItemRefInt;
    }
    /**
     * It the decimal value of the serial.
     * @return It is the integer value of the serial.
     */
    public int getSerialInteger(){
        return this.mSerialInt;
    }

    /**
     * It return the integer value of the header in an string.
     * @return It is the string of the integer value of the header.
     */
    public String getHeader(){
        return this.mEpcHeaderInt + "";
    }
    /**
     * It return the integer value of the filter in an string.
     * @return It is the string of the integer value of the filter.
     */
    public String getFilter(){
        return this.mFilterInt + "";
    }
    /**
     * It return the integer value of the partition in an string.
     * @return It is the string of the integer value of the partition.
     */
    public String getPartition(){
        return this.mPartitionInt + "";
    }
    /**
     * It return the integer value of the Company Prefix in an string.
     * @return It is the string of the integer value of the Company Prefix.
     */
    public String getCompanyPrefix(){
        int decSize = this.MATRIX[this.getPartitionInteger()][1];
        String res = zeroFill( this.mCompanyPrefixInt + "" , decSize );
        return res;
    }
    /**
     * It return the integer value of the Item Reference in an string.
     * @return It is the string of the integer value of the Item Reference.
     */
    public String getItemReference(){
        int decSize = this.MATRIX[this.getPartitionInteger()][3];
        String res = zeroFill( this.mItemRefInt + "" , decSize );
        return res;
    }
    /**
     * It return the integer value of the serial in an string.
     * @return It is the string of the integer value of the serial.
     */
    public String getSerial(){
        return this.mSerialInt + "";
    }
    /**
     * It return the integer value of the header in an string.
     * @return It is the string of the integer value of the header.
     */
    public String getEpcSgtin96(){
        String res = EPC_SGTIN96_PREFIX;
        res = res + this.getFilter()+"."+this.getCompanyPrefix()+"."+this.getItemReference()+"."+this.getSerial();
        return res;
    }
    /**
     * It return the integer value of the header in an string.
     * @return It is the string of the integer value of the header.
     */
    public String getEpcPureIdentity(){
        String res = EPC_PURE_IDENTITY_PREFIX;
        res = res + this.getCompanyPrefix()+"."+this.getItemReference()+"."+this.getSerial();
        return res;
    }
    /**
     * It return the integer value of the header in an string.
     * @return It is the string of the integer value of the header.
     */
    public String getGtinNumber(){
        return getEpcPureIdentity();
    }
    public String getEpcHexNumber(){

        String target = this.getBinaryCode();
        char[] v = target.toCharArray();
        String digit = "";
        int pos = 0;
        String res="";
        String alt = "";
        for(char c: v){
            digit = digit + c;
            pos++;
            if( pos == 8){
                res = res + fromBinToHex( digit );
                alt = alt + " - " + fromBinToHex( digit );
                digit = "";
                pos = 0;
            }
        }
        return res;
    }

    /**
     * It return the EPC number of th EPICS
     * @return It is an string with the EPC NUMBER ID
     */
    public String getEpcNumber(){
        return getEpcHexNumber();
    }
    /**
     * It parse a string to get and code or decode the datafor the EPC. the String suport the following formats:
     *  - EPC number in hexadecimal: 30XXXXXXXXXXXXXXXXXXXXXX
     *  - EPC TAG URI prefix: urn:epc:tag:sgtin-96:XXXXXXX.XXXXXXX.XXXXXXXX
     *  - RAW TAG URI prefix: urn:epc:raw:XXX.xXXXXXXXXXXXXX
     *  - RAM number: NAXXXXXXXXXXXXXXXXXXXXXXX
     * @param target It is the data string.
     */
    public void parse(String target){

        if( target == null ){
            throw new NumberFormatException("It is not possible Parse a null value.");
        }

        target = target.trim();
        target = target.toLowerCase();

        int pos = target.indexOf(EPC_SGTIN96_PREFIX);
        if( pos > -1){
            parseTagUri( target );
            return;
        }
        pos = target.indexOf( RAW_EPC_PREFIX );
        if( pos > -1){
            parseRawEpc( target );
            return;
        }
        pos = target.indexOf( RAW_PREFIX );
        if( pos >-1 ){
            parserRaw( target );
            return;
        }
        pos = target.indexOf( ".x" );
        if( pos >-1 ){
            parserRaw( target );
            return;
        }

        pos = target.indexOf( "." );
        if( pos > -1){
            parseTagUri( target );
            return;
        }
        parseEpc(target);

    }

    /**
     * It parse a RAW number with the format: NAXXXXXXXXXXXXX
     * @param target It is the data setring.
     */
    protected void parseRawEpc( String target ){
        target = target.replace( RAW_EPC_PREFIX, "");
        this.parseEpc( target );
    }
    /**
     * It get the data from a RAW TAD id, it use the following format: urn:epc:raw:XXX.xXXXXXXXXXXXXX
     * @param target
     */
    protected void parserRaw(String target ){
        String[] v = target.split("\\.x" );
        this.parseEpc( v[1] );
    }

    /**
     * It parse a string that contains EPC TAG under the following format:  urn:epc:tag:sgtin-96:XX.XXXXXXXXXXXXX.XXXXXXXXX.XXXXXXXXX
     * @param target It is the string taht contain the EPC TAG data.
     */
    protected void parseTagUri(String target){
        if( target == null ){
            return;
        }
        target = target.replace(EPC_SGTIN96_PREFIX,"");
        String[] v = target.split("\\.");
        String filter = v[0];
        String companyPrefix = v[1];
        String itemRef = v[2];
        String serial = v[3];
        String partition = getPartitionByDigits( companyPrefix.length(), itemRef.length())+"";
        int part = Integer.parseInt( partition );
        filter = fromDecToBin( filter );
        filter = zeroFill( filter, FILTER_SIZE);
        this.setFilterBinary( filter );

        partition = fromDecToBin( partition );
        partition = zeroFill( partition, PARTITION_SIZE);
        setPartitionBinary( partition );

        companyPrefix = fromDecToBin( companyPrefix );
        companyPrefix = zeroFill( companyPrefix, getCompanyPrefixBitsSize( part ));
        setCompanyPrefixBinary( companyPrefix );

        itemRef = fromDecToBin( itemRef );
        itemRef = zeroFill( itemRef, getItemReferenceBitsSize( part ) );
        setItemReferenceBinary( itemRef );

        serial = fromDecToBin( serial );
        serial = zeroFill( serial, SERIAL_SIZE);
        setSerialBinary( serial );

    }

    /**
     * It parse an EPC number, an hexadecimal number with a size of 24 characters, to get the data of the EPC.
     * @param target It is the String that contain the EPC number.
     */
    protected void parseEpc(String target ) {
        if( target == null ){
            throw new NumberFormatException("It is not possible get data from a null EPC number.");
        }
        if( target.length() != EPC_HEX_SIZE ){
            throw new NumberFormatException("The EPC number is not correct the length of the EPC number should be " + EPC_HEX_SIZE + " characters, when is of "+ target.length() + " characters. The EPC is " + target );
        }
        char[] v = target.toCharArray();
        int index = 0;
        String e = "";
        String binaryCode = "";
        for( char c : v){
            index++;
            e = e + c;
            if( index == 2 ){
                binaryCode = binaryCode + fromHexToBin( e );
                e = "";
                index = 0;
            }
        }
        parseBinary( binaryCode );
    }

    /**
     * It parese a binary number to extract the data for the EPC.
     * @param target It is the string with the binary value.
     */
    protected void parseBinary(String target){
        if( target == null ){
            throw new NumberFormatException("It is not possible get data from a null binary number.");
        }
        if( target.length() != 96 ){
            throw new NumberFormatException("The EPC number is not correct the length of the binary number should be 96 characters and not "+target.length()+" characters.");
        }
        setHeaderBinary( target.substring( 0, 8) );
        setFilterBinary( target.substring( 8, 11) );
        setPartitionBinary( target.substring( 11, 14) );
        setCompanyPrefixBinary( target.substring( 14, 38) );
        setItemReferenceBinary( target.substring( 38, 58) );
        setSerialBinary( target.substring( 58, 96) );

    }

    /**
     * It return the binary corresponding to the currents cvalues of the EPC.
     * @return It is the string that contain the binary code.
     */
    public String getBinaryCode(){
        String res = "";
        res = res + this.getHeaderBinary();
        res = res + this.getFilterBinary();
        res = res + this.getPartitionBinary();
        res = res + this.getCompanyPrefixBinary();
        res = res + this.getItemReferenceBinary();
        res = res + this.getSerialBinary();
        return res;
    }

    /**
     * It return the binary code corresp'onding to the GTIN number.
     * @return It is the string that contain the binary code of the GTIN number.
     */
    public String getGTIN(){
        //limit: (274877906943)10
        return getPartitionBinary() + getCompanyPrefix() + getItemReference();
    }

    /**
     * It return the RAW TAG id of the current EPC.
     * @return It is the string that containt the RAW TAG id.
     */
    public String getRaw(){
        String id = this.getEpcHexNumber();
        int bits = id.length() * 8;
        return RAW_PREFIX + bits + ".x" + id;

    }

    /**
     * It return the RAW EPC TAG id of the EPC.
     * @return It is the string that contain the RAW EPC TAG.
     */
    public String getRawHexNumber(){
        String res = RAW_EPC_PREFIX + this.getEpcHexNumber();
        res =res.toUpperCase();
        return res;
    }

    /**
     * This return a string with all data about the EPC number, his values decimal, hexadecimal and binaries.
     * @return It the STring with the general data.
     */
    @Override
    public String toString(){
        String bi =  this.getBinaryCode();
        String hex = getEpcHexNumber();
        String res = "\n\tEPC number:";
        res = res + "\n\t\tEPC Hex number("+hex.length()+"): " +  hex;
        res = res + "\n\t\tRaw Hex Number: " + this.getRawHexNumber();
        res = res + "\n\t\tSgtin-96: " +   getEpcSgtin96();
        res = res + "\n\t\tSgtin Pure Identity: " +   getEpcPureIdentity();
        res = res + "\n\t\tRaw: "+  this.getRaw();
        res = res + "\n\t\tComplete Binary Code("+ getBinaryCode().length()+ "): " + this.getFilter() + " - " +  getBinaryCode();
        res = res + "\n\t\tHeader("+ getHeaderBinary().length()+ "): "+ this.getHeader() + " - " + this.getHeaderBinary();
        res = res + "\n\t\tFilter("+ getFilterBinary().length()+ "): " + this.getFilter() + " - " + this.getFilterBinary();
        res = res + "\n\t\tPartition("+ getPartitionBinary().length()+ "): " + this.getPartition() + " - " +   this.getPartitionBinary();
        res = res + "\n\t\tCompany Prefix("+ getCompanyPrefixBinary().length()+ "): " + this.getCompanyPrefix() + " - " +   this.getCompanyPrefixBinary();
        res = res + "\n\t\tItem reference("+ getItemReferenceBinary().length()+ "): " + this.getItemReference() + " - " +   this.getItemReferenceBinary();
        res = res + "\n\t\tSerial("+ getSerialBinary().length()+ "): " + this.getSerial() + " - " +   this.getSerialBinary();
        res = res + "\n\t\tGTIN: " + this.getGTIN();
        return res;
    }

    /**
     * It method convert a Hexadecimal value to binary value.
     * @param hexadecimal It is the hexadecimal number.
     * @return It is the binary number corresponding to the hexadecimal value.
     */
    protected static String fromHexToBin(String hexadecimal ){
        boolean zeril = false;
        int decimal = Integer.parseInt(hexadecimal,16);
        String res = Integer.toBinaryString(decimal);
        while( res.length() < 8 ){
            res = "0" + res;
        }
        return res;
    }

    /**
     * It convert a decimal number to binary number.
     * @param target it is the decimal number/
     * @return it is the binary number corresponding to the decimal value.
     */
    protected static String fromDecToBin(String target ){
        boolean zeril = false;
        int decimal = Integer.parseInt(target);
        String res = Integer.toBinaryString(decimal);
        return res;
    }

    /**
     * It convert a hexadecimal number to a representative binary number, character by character.
     * @param binary It is the binary number.
     * @return It is the hexadecimal number.
     */
    protected static String fromBinToHex(String binary ){
        boolean zeril = false;
        int decimal = Integer.parseInt(binary,2);
        if( decimal < 16 ){
            zeril = true;
        }
        String res = Integer.toString(decimal,16);
        res = res.toUpperCase();
        if( zeril ){
            res = "0" + res;
        }

        return res;
    }

    /**
     * It generate a randonm decimal value with a number of this quantity of digits, generate a unber between 0 and ((10 exp digits)-1).
     * @param digits it is the number of digits of the number generated.
     * @return It is
     */
    public static long getRamdonDecimal( int digits ){
        if( digits < -1){
            digits = digits -1;
        }
        if( digits == 0){
            return 0;
        }
        int limit = 0;
        for( int i = 0; i < digits; i++){
            limit = (limit * 10) + 9;
        }

        int res = getRandom( limit );
        return res;
    }

    /**
     * this generate a number between 0 and ( 10 exp digitsDec ) - 1 and return the binary of the quantity of digits specified.
     * @param digitsDec It is quantity of digits for the limit.
     * @param digitBits It is the quantity of digits for binary number generated.
     * @return It is the binary number generated.
     */
    public static String getRandomBin( int digitsDec, int digitBits ){
        if( digitsDec < -1){
            digitsDec = digitsDec -1;
        }
        if( digitsDec == 0){
            return "0";
        }
        int limit = 0;
        for( int i = 0; i < digitsDec; i++){
            limit = (limit * 10) + 9;
        }

        String res = getRandomBinary( limit,  digitBits );
        res = zeroFill( res, digitBits );
        return res;
    }

    /**
     * It generate a binary number with a specific number of digits.
     * @param n It is the range of number to be generated.
     * @param digits It is teh the quantity of digits for the binary number generated.
     * @returnIt is the String that contain the binary number.
     */
    public static String getRandomBinary( int n, int digits ){
        int r = getRandom(n);
        String res = Integer.toBinaryString( r );
        res = zeroFill( res , digits );
        return res;

    }

    /**
     * It add zero to the begining of the string until a size specified, if the size is less than string then
     * return a substring with the specified size from the end of the character.
     * @param target It is string to fill with zero.
     * @param digits It is the final size of the string.
     * @return It is the string with the zeros.
     */
    public static String zeroFill( String target, int digits ){
        if( target.length() > digits ){
            return target.substring( 0, digits );
        }
        while( target.length() < digits ){
            target = "0" + target;
        }
        return target;
    }

    /**
     * It return the a random number and return the corresponding binary with a quantity of digits specified.
     * @param digits It is the quantity of digits of the random binary number generated.
     * @return It is the number generated.
     */
    public static String getRandomBinary( int digits ){
        String res = "";
        if( digits < 0 ){
            digits = digits * -1;
        }
        if( digits == 0){
            return "0";
        }
        int ran = -1;
        try {
            while ( res.length() < digits ) {
                ran = getRandom(10 );
                res = res + Integer.toBinaryString( ran );
                Thread.sleep(ran + 1 );
            }
            if ( res.length() > digits ) {
                res = res.substring( 0, digits );
            }
        }catch( Exception e ){}
        return res;
    }
    /**
     * It generate an int number in the limit spcified.
     * @param limit it is range to generate the number.
     * @returnIr t is the number generated.
     */
    public static int getRandom( int limit ){
        int res = 0;
        try{
            Random r = new Random( System.currentTimeMillis() );
            res = r.nextInt( limit );
            Thread.sleep(( res % 11 ) + 1 );
        }catch( Exception e ){

        }
        return res;
    }


}
