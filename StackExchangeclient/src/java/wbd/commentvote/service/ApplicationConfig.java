/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbd.commentvote.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

/**
 *
 * @author Afrizal
 */
@ApplicationPath("webresources")
public class ApplicationConfig extends Application {

  /**
   *
   * @return Set of Class
   */
  @Override
  public Set<Class<?>> getClasses() {
    return new HashSet<>(Arrays.asList(VoteAnswerRESTFacade.class, VoteQuestionRESTFacade.class, AnswerRESTFacade.class, QuestionRESTFacade.class, CommentRESTFacade.class, UserRESTFacade.class));
  }
}
