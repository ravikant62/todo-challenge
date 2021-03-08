package com.todolist.challenge;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.challenge.message.request.LoginForm;
import com.todolist.challenge.message.response.JwtResponse;
import com.todolist.challenge.model.Todo;
import com.todolist.challenge.model.TodoList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoListChallengeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoListChallengeApplicationsTests {

	 @Autowired
	 private TestRestTemplate testRestTemplate;

	    @LocalServerPort
	    private int port;
	    
	    private static HttpHeaders httpHeaders;
	    @BeforeAll
	    public static void setUp() {
	    	httpHeaders = new HttpHeaders();
	    	httpHeaders.add("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTYxNTE0NjEyMCwiZXhwIjoxNjE1MjMyNTIwfQ.xI_t4e-KFtHi_kUKrofvAXRj-W7YpQe-IIbMRUc2I1CQnNuBE_fagbEuvFojh9K3ctIQIgrkqsmPajTwJgZvRA");
	    }

	    /**
	     * This function creates the full web path of the users api.
	     *
	     * @return The web url of the users api.
	     */
	    private String getLoginURL() {
	        return "http://localhost:" + port + "/login";
	    }

	    /**
	     * This function creates the full web path of the todos api.
	     *
	     * @return The web url of the todos api.
	     */
	    private String getTodosURL() {
	        return "http://localhost:" + port + "/todos";
	    }

	    /**
	     * This function creates the full web path of the lists api.
	     *
	     * @return The web url of the lists api.
	     */
	    private String getListsURL() {
	        return "http://localhost:" + port + "/lists";
	    }


	    /**
	     * Here we test if we can create a todo using the POST method
	     */
	    @Test
	    public void testLogin() {
	        LoginForm loginRequest = new LoginForm();
	        loginRequest.setUsername("user1");
	        loginRequest.setPassword("pass");
	        HttpEntity<LoginForm> entity = new HttpEntity<>(loginRequest);

	        ResponseEntity<JwtResponse> postResponse = testRestTemplate.exchange(getLoginURL(), HttpMethod.POST, entity, JwtResponse.class);
	        Assert.assertNotNull(postResponse);
	        Assert.assertNotNull(postResponse.getBody());
	    }
	    /**
	     * Here we test getting all the todos in the database
	     * using the GET method
	     */
	    @Test
	    public void testGetAllTodos() {
	        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);

	        ResponseEntity<String> response = testRestTemplate.exchange(getTodosURL(),
	                HttpMethod.GET, entity, String.class);

	        Assert.assertNotNull(response.getBody());
	    }

	    /**
	     * Here we test if we can fetch a single todo using its id
	     */
	    @Test
	    public void testGetTodoById() {
	        testCreateTodo();
	        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
	        ResponseEntity<Todo> responseEntity = testRestTemplate.exchange(getTodosURL() + "/1",HttpMethod.GET, entity, Todo.class);
	        System.out.println(responseEntity.getBody().getText());
	        Assert.assertNotNull(responseEntity.getBody());
	    }

	    /**
	     * Here we test if we can create a todo using the POST method
	     */
	    @Test
	    public void testCreateTodo() {
	        Todo todo = new Todo();
	        todo.setText("Test Todo");
	        todo.setCompleted(false);
	        HttpEntity<Todo> entity = new HttpEntity<>(todo, httpHeaders);

	        ResponseEntity<Todo> postResponse = testRestTemplate.exchange(getTodosURL(), HttpMethod.POST, entity, Todo.class);
	        //ResponseEntity<Todo> postResponse = testRestTemplate.postForEntity(getTodosURL(), todo, Todo.class);
	        Assert.assertNotNull(postResponse);
	        Assert.assertNotNull(postResponse.getBody());
	    }

	    /**
	     * Here we test that we can update a todo's information using the PUT method
	     */
	    @Test
	    public void testUpdateTodo() {
	        testCreateTodo();
	        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
	        int id = 1;
	        ResponseEntity<Todo> responseEntity = testRestTemplate.exchange(getTodosURL() + "/" + id, HttpMethod.GET, entity, Todo.class);
	        Todo todoNew = responseEntity.getBody();
	        todoNew.setText("Test Todo New Name");
	        todoNew.setCompleted(true);
	        
	        HttpEntity<Todo> entityTodo = new HttpEntity<>(todoNew, httpHeaders);

	        testRestTemplate.exchange(getTodosURL() + "/" + id, HttpMethod.PUT, entityTodo, Todo.class);
	        
	        HttpEntity<Todo> entityTodoGet = new HttpEntity<>(null, httpHeaders);

	        ResponseEntity<Todo> responseEntityTodo = testRestTemplate.exchange(getTodosURL() + "/" + id, HttpMethod.GET, entityTodoGet,  Todo.class);
	        Assert.assertNotNull(responseEntityTodo.getBody());
	    }

	    /**
	     * Here we test that we can delete a todo by using the DELETE method,
	     * then we verify that it no longer exists in the database
	     */
	    @Test
	    public void testDeleteTodo() {
	        testCreateTodo();

	        try {
	            int id = 1;
	            Todo todo = testRestTemplate.getForObject(getTodosURL() + "/" + id, Todo.class);
	            Assert.assertNotNull(todo);
	            testRestTemplate.delete(getTodosURL() + "/" + id);
	        } catch (final HttpClientErrorException e) {
	            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
	        }
	    }

	    /**
	     * Here we test getting all the lists in the database
	     * using the GET method
	     */
	    @Test
	    public void testGetAllLists() {
	        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);

	        ResponseEntity<String> response = testRestTemplate.exchange(getListsURL(),
	                HttpMethod.GET, entity, String.class);

	        Assert.assertNotNull(response.getBody());
	    }

	    /**
	     * Here we test if we can fetch a single list using its id
	     */
	    @Test
	    public void testGetListById() {
	        testCreateList();
	        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);

	        ResponseEntity<TodoList> responseEntity = testRestTemplate.exchange(getListsURL() + "/1", HttpMethod.GET, entity, TodoList.class);
	        System.out.println(responseEntity.getBody().getTitle());
	        Assert.assertNotNull(responseEntity.getBody());
	    }

	    /**
	     * Here we test if we can create a list using the POST method
	     */
	    @Test
	    public void testCreateList() {
	    	TodoList list = new TodoList();
	    	list.setTitle("Test TodoList");
	    	HttpEntity<TodoList> entity = new HttpEntity<>(list, httpHeaders);
	    	

	    	ResponseEntity<TodoList> postResponse = testRestTemplate.exchange(getListsURL(), HttpMethod.POST, entity, TodoList.class);
	    	//ResponseEntity<TodoList> postResponse = testRestTemplate.postForEntity(getListsURL(), list, TodoList.class);
	    	Assert.assertNotNull(postResponse);
	    	Assert.assertNotNull(postResponse.getBody());
	    }

	    /**
	     * Here we test that we can update a list's information using the PUT method
	     */
	    @Test
	    public void testUpdateList() {
	        testCreateList();
	        HttpEntity<TodoList> entity = new HttpEntity<>(null, httpHeaders);
	        int id = 1;
	        ResponseEntity<TodoList> responseEntity = testRestTemplate.exchange(getListsURL() + "/" + id, HttpMethod.GET, entity,  TodoList.class);
	        TodoList listNew = responseEntity.getBody();
	        listNew.setTitle("Test TodoList New Name");

	        HttpEntity<TodoList> entityTodoList = new HttpEntity<>(listNew, httpHeaders);
	        testRestTemplate.exchange(getListsURL() + "/" + id,HttpMethod.PUT,entityTodoList,  TodoList.class);
	        
	        
	        HttpEntity<String> entityGetTodoList = new HttpEntity<>(null, httpHeaders);
	        ResponseEntity<TodoList> responseEntityGet = testRestTemplate.exchange(getListsURL() + "/" + id,HttpMethod.GET, entityGetTodoList,TodoList.class);
	        Assert.assertNotNull(responseEntityGet.getBody());
	    }

	    /**
	     * Here we test that we can delete a list by using the DELETE method,
	     * then we verify that it no longer exists in the database
	     */
	    @Test
	    public void testDeleteList() {
	        testCreateList();

	        try {
	            int id = 4;
	            HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
	            ResponseEntity<TodoList> responseEntity = testRestTemplate.exchange(getListsURL() + "/" + id, HttpMethod.GET,entity,  TodoList.class);
	            Assert.assertNotNull(responseEntity.getBody());
	            HttpEntity<String> entityDelete = new HttpEntity<>(null, httpHeaders);
	            testRestTemplate.exchange(getListsURL() + "/" + id, HttpMethod.DELETE, entityDelete, Void.class);
	        } catch (final HttpClientErrorException e) {
	            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
	        }
	    }
	    
	    
	    private void writeValueAsString(Object object) {
	    	ObjectMapper mapper = new ObjectMapper();
	    	try {
				mapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

}
