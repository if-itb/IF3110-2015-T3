
package answer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for answer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="answer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="aid" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="countvotes" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="createdtime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="qid" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="uid" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "answer", propOrder = {
    "aid",
    "content",
    "countvotes",
    "createdtime",
    "qid",
    "uid"
})
public class Answer {

    protected Integer aid;
    protected String content;
    protected int countvotes;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdtime;
    protected int qid;
    protected int uid;

    /**
     * Gets the value of the aid property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * Sets the value of the aid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAid(Integer value) {
        this.aid = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the countvotes property.
     * 
     */
    public int getCountvotes() {
        return countvotes;
    }

    /**
     * Sets the value of the countvotes property.
     * 
     */
    public void setCountvotes(int value) {
        this.countvotes = value;
    }

    /**
     * Gets the value of the createdtime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedtime() {
        return createdtime;
    }

    /**
     * Sets the value of the createdtime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedtime(XMLGregorianCalendar value) {
        this.createdtime = value;
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
     * Gets the value of the uid property.
     * 
     */
    public int getUid() {
        return uid;
    }

    /**
     * Sets the value of the uid property.
     * 
     */
    public void setUid(int value) {
        this.uid = value;
    }

}
