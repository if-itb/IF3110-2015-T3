
package com.mycompany.nasipadang.wsdl;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mycompany.nasipadang.jaxws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mycompany.nasipadang.jaxws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Question }
     * 
     */
    public Question createQuestion() {
        return new Question();
    }

    /**
     * Create an instance of {@link Answer }
     * 
     */
    public Answer createAnswer() {
        return new Answer();
    }

    /**
     * Create an instance of {@link AnswerArray }
     * 
     */
    public AnswerArray createAnswerArray() {
        return new AnswerArray();
    }

    /**
     * Create an instance of {@link QuestionArray }
     * 
     */
    public QuestionArray createQuestionArray() {
        return new QuestionArray();
    }

}
