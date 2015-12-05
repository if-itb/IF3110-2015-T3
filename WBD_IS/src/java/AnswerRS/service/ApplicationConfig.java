/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerRS.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Vanji
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(AnswerRS.service.AnswerFacadeREST.class);
        resources.add(AnswerRS.service.QuestionFacadeREST.class);
        resources.add(AnswerRS.service.TokenFacadeREST.class);
        resources.add(AnswerRS.service.UserFacadeREST.class);
        resources.add(AnswerRS.service.VoteFacadeREST.class);
        resources.add(QuestionRS.service.AnswerFacadeREST.class);
        resources.add(QuestionRS.service.QuestionFacadeREST.class);
        resources.add(QuestionRS.service.TokenFacadeREST.class);
        resources.add(QuestionRS.service.UserFacadeREST.class);
        resources.add(QuestionRS.service.VoteFacadeREST.class);
    }
    
}
