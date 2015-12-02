
package AnswerWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for voteAnswer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="voteAnswer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="access_token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="voteUp" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "voteAnswer", propOrder = {
    "accessToken",
    "aid",
    "voteUp"
})
public class VoteAnswer {

    @XmlElement(name = "access_token")
    protected String accessToken;
    @XmlElement(name = "AID")
    protected int aid;
    protected boolean voteUp;

    /**
     * Gets the value of the accessToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Sets the value of the accessToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessToken(String value) {
        this.accessToken = value;
    }

    /**
     * Gets the value of the aid property.
     * 
     */
    public int getAID() {
        return aid;
    }

    /**
     * Sets the value of the aid property.
     * 
     */
    public void setAID(int value) {
        this.aid = value;
    }

    /**
     * Gets the value of the voteUp property.
     * 
     */
    public boolean isVoteUp() {
        return voteUp;
    }

    /**
     * Sets the value of the voteUp property.
     * 
     */
    public void setVoteUp(boolean value) {
        this.voteUp = value;
    }

}
