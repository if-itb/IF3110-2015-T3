
package service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for editQuestion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="editQuestion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="qid" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qtopic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qcontent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "editQuestion", propOrder = {
    "qid",
    "name",
    "email",
    "qtopic",
    "qcontent",
    "token",
    "expirationDate"
})
public class EditQuestion {

    protected int qid;
    protected String name;
    protected String email;
    protected String qtopic;
    protected String qcontent;
    protected String token;
    protected long expirationDate;

    /**
     * Gets the value of the qid property.
     * 
     */
    public int getQid() {
        return qid;
    }

    /**
     * Sets the value of the qid property.
     * 
     */
    public void setQid(int value) {
        this.qid = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the qtopic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQtopic() {
        return qtopic;
    }

    /**
     * Sets the value of the qtopic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQtopic(String value) {
        this.qtopic = value;
    }

    /**
     * Gets the value of the qcontent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQcontent() {
        return qcontent;
    }

    /**
     * Sets the value of the qcontent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQcontent(String value) {
        this.qcontent = value;
    }

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     */
    public long getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     */
    public void setExpirationDate(long value) {
        this.expirationDate = value;
    }

}
