/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbd.identity.service;

import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wbd.identity.User;

/**
 *
 * @author Afrizal
 */
public class UserRESTFacadeTest {
  
  public UserRESTFacadeTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of create method, of class UserRESTFacade.
   */
  @Test
  public void testCreate() {
    System.out.println("create");
    User entity = null;
    UserRESTFacade instance = new UserRESTFacade();
    Response expResult = null;
    Response result = instance.create(entity);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of edit method, of class UserRESTFacade.
   */
  @Test
  public void testEdit() {
    System.out.println("edit");
    User entity = null;
    UserRESTFacade instance = new UserRESTFacade();
    Response expResult = null;
    Response result = instance.edit(entity);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of remove method, of class UserRESTFacade.
   */
  @Test
  public void testRemove() {
    System.out.println("remove");
    Integer id = null;
    UserRESTFacade instance = new UserRESTFacade();
    Response expResult = null;
    Response result = instance.remove(id);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of find method, of class UserRESTFacade.
   */
  @Test
  public void testFind() {
    System.out.println("find");
    Integer id = null;
    UserRESTFacade instance = new UserRESTFacade();
    User expResult = null;
    User result = instance.find(id);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of findAll method, of class UserRESTFacade.
   */
  @Test
  public void testFindAll() {
    System.out.println("findAll");
    UserRESTFacade instance = new UserRESTFacade();
    List<User> expResult = null;
    List<User> result = instance.findAll();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of findRange method, of class UserRESTFacade.
   */
  @Test
  public void testFindRange() {
    System.out.println("findRange");
    Integer max = null;
    Integer first = null;
    UserRESTFacade instance = new UserRESTFacade();
    List<User> expResult = null;
    List<User> result = instance.findRange(max, first);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of count method, of class UserRESTFacade.
   */
  @Test
  public void testCount() {
    System.out.println("count");
    UserRESTFacade instance = new UserRESTFacade();
    String expResult = "";
    String result = instance.count();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
