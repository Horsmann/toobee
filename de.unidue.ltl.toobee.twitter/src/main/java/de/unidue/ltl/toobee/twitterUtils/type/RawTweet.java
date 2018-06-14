

/* First created by JCasGen Sun Jan 31 13:22:30 CET 2016 */
package de.unidue.ltl.toobee.twitterUtils.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Jun 14 13:41:35 CEST 2018
 * XML source: /Users/toobee/Documents/Eclipse/toobee/de.unidue.ltl.toobee.twitter/src/main/resources/desc/type/RawTweet.xml
 * @generated */
public class RawTweet extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(RawTweet.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected RawTweet() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public RawTweet(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public RawTweet(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public RawTweet(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: rawTweet

  /** getter for rawTweet - gets 
   * @generated
   * @return value of the feature 
   */
  public String getRawTweet() {
    if (RawTweet_Type.featOkTst && ((RawTweet_Type)jcasType).casFeat_rawTweet == null)
      jcasType.jcas.throwFeatMissing("rawTweet", "de.unidue.ltl.toobee.twitterUtils.type.RawTweet");
    return jcasType.ll_cas.ll_getStringValue(addr, ((RawTweet_Type)jcasType).casFeatCode_rawTweet);}
    
  /** setter for rawTweet - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRawTweet(String v) {
    if (RawTweet_Type.featOkTst && ((RawTweet_Type)jcasType).casFeat_rawTweet == null)
      jcasType.jcas.throwFeatMissing("rawTweet", "de.unidue.ltl.toobee.twitterUtils.type.RawTweet");
    jcasType.ll_cas.ll_setStringValue(addr, ((RawTweet_Type)jcasType).casFeatCode_rawTweet, v);}    
   
    
  //*--------------*
  //* Feature: payload

  /** getter for payload - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPayload() {
    if (RawTweet_Type.featOkTst && ((RawTweet_Type)jcasType).casFeat_payload == null)
      jcasType.jcas.throwFeatMissing("payload", "de.unidue.ltl.toobee.twitterUtils.type.RawTweet");
    return jcasType.ll_cas.ll_getStringValue(addr, ((RawTweet_Type)jcasType).casFeatCode_payload);}
    
  /** setter for payload - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPayload(String v) {
    if (RawTweet_Type.featOkTst && ((RawTweet_Type)jcasType).casFeat_payload == null)
      jcasType.jcas.throwFeatMissing("payload", "de.unidue.ltl.toobee.twitterUtils.type.RawTweet");
    jcasType.ll_cas.ll_setStringValue(addr, ((RawTweet_Type)jcasType).casFeatCode_payload, v);}    
   
    
  //*--------------*
  //* Feature: id

  /** getter for id - gets 
   * @generated
   * @return value of the feature 
   */
  public String getId() {
    if (RawTweet_Type.featOkTst && ((RawTweet_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "de.unidue.ltl.toobee.twitterUtils.type.RawTweet");
    return jcasType.ll_cas.ll_getStringValue(addr, ((RawTweet_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (RawTweet_Type.featOkTst && ((RawTweet_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "de.unidue.ltl.toobee.twitterUtils.type.RawTweet");
    jcasType.ll_cas.ll_setStringValue(addr, ((RawTweet_Type)jcasType).casFeatCode_id, v);}    
  }

    