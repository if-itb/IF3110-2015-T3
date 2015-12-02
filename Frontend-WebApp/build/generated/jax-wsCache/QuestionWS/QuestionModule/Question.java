
package QuestionModule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for question complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="question">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="qid" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="uemail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="qtopic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="qcontent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="qauthorname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="qvote" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="qtimestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "question", propOrder = {
    "qid",
    "uemail",
    "qtopic",
    "qcontent",
    "qauthorname",
    "qvote",
    "qtimestamp"
})
public class Question {

    protected int qid;
    @XmlElement(required = true)
    protected String uemail;
    @XmlElement(required = true)
    protected String qtopic;
    @XmlElement(required = true)
    protected String qcontent;
    @XmlElement(required = true)
    protected String qauthorname;
    protected int qvote;
    @XmlElement(required = true)
    protected String qtimestamp;

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
     * Gets the value of the qauthorname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQauthorname() {
        return qauthorname;
    }

    /**
     * Sets the value of the qauthorname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQauthorname(String value) {
        this.qauthorname = value;
    }

    /**
     * Gets the value of the qvote property.
     * 
     */
    public int getQvote() {
        return qvote;
    }

    /**
     * Sets the value of the qvote property.
     * 
     */
    public void setQvote(int value) {
        this.qvote = value;
    }

    /**
     * Gets the value of the qtimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQtimestamp() {
        return qtimestamp;
    }

    /**
     * Sets the value of the qtimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQtimestamp(String value) {
        this.qtimestamp = value;
    }

}
