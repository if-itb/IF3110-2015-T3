
package questionmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteQuesetionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteQuesetionResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="delQuestion" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteQuesetionResponse", propOrder = {
    "delQuestion"
})
public class DeleteQuesetionResponse {

    protected int delQuestion;

    /**
     * Gets the value of the delQuestion property.
     * 
     */
    public int getDelQuestion() {
        return delQuestion;
    }

    /**
     * Sets the value of the delQuestion property.
     * 
     */
    public void setDelQuestion(int value) {
        this.delQuestion = value;
    }

}
