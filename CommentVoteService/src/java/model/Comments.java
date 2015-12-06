package model;

import java.io.StringWriter;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "comments")
public class Comments {
    
    private ArrayList<CommentModel> comments = new ArrayList<>();
    
    public Comments() {}
    
    public Comments(ArrayList<CommentModel> comments) {
	this.comments = comments;
    }
    
    @XmlElement
    public ArrayList<CommentModel> getComments() {
	return this.comments;
    }
    
    public String toXML() {
	try {
	    JAXBContext context = JAXBContext.newInstance(Comments.class);
	    Marshaller marshaller = context.createMarshaller();
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	    StringWriter stringWriter = new StringWriter();
	    marshaller.marshal(this, stringWriter);
	    return stringWriter.toString();
	} catch (JAXBException exception) {
	    System.out.println(exception);
	    return "JANCUK";
	}
    }
    
}
