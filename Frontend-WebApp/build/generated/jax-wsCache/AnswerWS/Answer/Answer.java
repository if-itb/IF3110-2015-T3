
package Answer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for answer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="answer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aid" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="qid" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="uemail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="aauthorname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="acontent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="avote" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="atimestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "answer", propOrder = {
    "aid",
    "qid",
    "uemail",
    "aauthorname",
    "acontent",
    "avote",
    "atimestamp"
})
public class Answer {

    protected int aid;
    protected int qid;
    @XmlElement(required = true)
    protected String uemail;
    @XmlElement(required = true)
    protected String aauthorname;
    @XmlElement(required = true)
    protected String acontent;
    protected int avote;
    @XmlElement(required = true)
    protected String atimestamp;

    /**
     * Gets the value of the aid property.
     * 
     */
    public int getAid() {
        return aid;
    }

    /**
     * Sets the value of the aid property.
     * 
     */
    public void setAid(int value) {
        this.aid = value;
    }

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
     * Gets the value of the uemail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUemail() {
        return uemail;
    }

    /**
     * Sets the value of the uemail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUemail(String value) {
        this.uemail = value;
    }

    /**
     * Gets the value of the aauthorname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAauthorname() {
        return aauthorname;
    }

    /**
     * Sets the value of the aauthorname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAauthorname(String value) {
        this.aauthorname = value;
    }

    /**
     * Gets the value of the acontent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcontent() {
        return acontent;
    }

    /**
     * Sets the value of the acontent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcontent(String value) {
        this.acontent = value;
    }

    /**
     * Gets the value of the avote property.
     * 
     */
    public int getAvote() {
        return avote;
    }

    /**
     * Sets the value of the avote property.
     * 
     */
    public void setAvote(int value) {
        this.avote = value;
    }

    /**
     * Gets the value of the atimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtimestamp() {
        return atimestamp;
    }

    /**
     * Sets the value of the atimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtimestamp(String value) {
        this.atimestamp = value;
    }

}
