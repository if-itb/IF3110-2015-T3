/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.webservice.util;

import java.sql.SQLException;

/**
 *
 * @author fauzanrifqy
 */
public class NestedException extends RuntimeException implements Warning {

 protected Exception nestedException;
 protected int issueId;
 private String _warning;

 public String getNestedMessage() {
    String nestedMessage = "";
    try {
        if (nestedException instanceof NestedException) {
            NestedException ne = (NestedException) nestedException;
            nestedMessage = nestedException.getMessage() + ne.getNestedMessage();
        } else {
            nestedMessage = nestedException.getMessage();
        }
    } catch (Exception e) {
    
    }
    return nestedMessage;
 }

 public NestedException(String msg, Exception e, int id) {
    super(msg);
    this.nestedException = e;
    this.issueId = id;
    _warning = msg;
 }
 public NestedException(String msg,String Warning, Exception e, int id) {
    super(msg);
    this.nestedException = e;
    this.issueId = id;
 }

 public Exception getNestedException() {
    return this.nestedException;
 }

 public int getIssue() {
    return this.issueId;
 }

 public String getWarning() {
    return _warning;
 }

 public void setWarning(String warning) {
    this._warning = warning;
 }
}
