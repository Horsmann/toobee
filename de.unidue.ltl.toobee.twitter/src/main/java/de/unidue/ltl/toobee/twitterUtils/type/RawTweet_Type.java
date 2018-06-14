
/* First created by JCasGen Sun Jan 31 13:22:30 CET 2016 */
package de.unidue.ltl.toobee.twitterUtils.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Jun 14 13:41:35 CEST 2018
 * @generated */
public class RawTweet_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = RawTweet.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.unidue.ltl.toobee.twitterUtils.type.RawTweet");
 
  /** @generated */
  final Feature casFeat_rawTweet;
  /** @generated */
  final int     casFeatCode_rawTweet;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getRawTweet(int addr) {
        if (featOkTst && casFeat_rawTweet == null)
      jcas.throwFeatMissing("rawTweet", "de.unidue.ltl.toobee.twitterUtils.type.RawTweet");
    return ll_cas.ll_getStringValue(addr, casFeatCode_rawTweet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setRawTweet(int addr, String v) {
        if (featOkTst && casFeat_rawTweet == null)
      jcas.throwFeatMissing("rawTweet", "de.unidue.ltl.toobee.twitterUtils.type.RawTweet");
    ll_cas.ll_setStringValue(addr, casFeatCode_rawTweet, v);}
    
  
 
  /** @generated */
  final Feature casFeat_payload;
  /** @generated */
  final int     casFeatCode_payload;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getPayload(int addr) {
        if (featOkTst && casFeat_payload == null)
      jcas.throwFeatMissing("payload", "de.unidue.ltl.toobee.twitterUtils.type.RawTweet");
    return ll_cas.ll_getStringValue(addr, casFeatCode_payload);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPayload(int addr, String v) {
        if (featOkTst && casFeat_payload == null)
      jcas.throwFeatMissing("payload", "de.unidue.ltl.toobee.twitterUtils.type.RawTweet");
    ll_cas.ll_setStringValue(addr, casFeatCode_payload, v);}
    
  
 
  /** @generated */
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "de.unidue.ltl.toobee.twitterUtils.type.RawTweet");
    return ll_cas.ll_getStringValue(addr, casFeatCode_id);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "de.unidue.ltl.toobee.twitterUtils.type.RawTweet");
    ll_cas.ll_setStringValue(addr, casFeatCode_id, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public RawTweet_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_rawTweet = jcas.getRequiredFeatureDE(casType, "rawTweet", "uima.cas.String", featOkTst);
    casFeatCode_rawTweet  = (null == casFeat_rawTweet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_rawTweet).getCode();

 
    casFeat_payload = jcas.getRequiredFeatureDE(casType, "payload", "uima.cas.String", featOkTst);
    casFeatCode_payload  = (null == casFeat_payload) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_payload).getCode();

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.String", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

  }
}



    