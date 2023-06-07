package helpers;

import java.io.File;
import java.util.HashMap;
//import learningcucumber.UnderTheSkinIntegration;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsSpringJUnit4TestCase;
import org.apache.struts2.dispatcher.multipart.MultiPartRequest;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.mock.web.MockPageContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.DefaultLocaleProvider;

public class UnderTheSkinHelpers extends StrutsSpringJUnit4TestCase<ActionSupport> {

	@Autowired
	protected ApplicationContext applicationContext;
	private ActionProxy actionProxy;
	private ActionSupport action;
	private boolean alreadyCreated = false;

	private boolean tilesApplication;
	private HashMap<String, Object> session = new HashMap<String, Object>();

	private ActionContext actionContext;
	private String configPath;

	public MockPageContext getPageContext() {
		return pageContext;
	}

	public void setAction(ActionSupport action) {
		this.action = action;
	}

	public ActionContext getActionContext() {
		return actionContext;
	}

	public HashMap<String, Object> getSession() {
		return session;
	}

	public ActionProxy getActionProxy() {
		return actionProxy;
	}

	/**
	 * Overrides the previous in order to skip applicationContext assignment:
	 * context is @autowired
	 * 
	 * @see org.apache.struts2.StrutsSpringTestCase#setupBeforeInitDispatcher()
	 **/
	@Override
	protected void setupBeforeInitDispatcher() throws Exception {
		// init context

		servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
				applicationContext);
	}

	public MockHttpServletRequest getRequest() {
		return request;
	}

	public MockHttpServletResponse getResponse() {
		return response;
	}

	/**
	 * Creates the action with the given actionPath if it wasn't already created
	 * 
	 * @see isAlreadyCreated
	 *   
	 * @param newParam
	 * @param clearSession TODO
	 * @throws Exception
	 */
	public ActionSupport createAction(String newParam, boolean clearSession) throws Exception {
		
		createAction(getActionProxy(newParam), clearSession);
		return action;
	}
	
	private ActionSupport createAction(ActionProxy actionProxy, boolean clearSession) throws Exception {
		if (!isAlreadyCreated()) {
			this.actionProxy = actionProxy;
			action = (ActionSupport) actionProxy.getAction();
			actionContext = actionProxy.getInvocation().getInvocationContext();
			buildSession(clearSession);
		}
		return action;
	}

	/**
	 * Creates an action with a file upload.
	 * @param actionPath
	 * @param filePropertyName
	 * @param contentType
	 * @param filename
	 * @param file
	 * @param clearSession TODO
	 * @return
	 * @throws Exception
	 */
	public ActionSupport createAction(String actionPath, String filePropertyName, String contentType, String filename, File file, boolean clearSession)
			throws Exception {
		createAction(getActionProxy(actionPath,filePropertyName, contentType, filename, file), clearSession);
		return action;
	}

	public ActionSupport getAction() {
		return action;
	}

	public void setUp() throws Exception {
		super.setUp();
		RequestContextHolder.setRequestAttributes(new ServletWebRequest(new MockHttpServletRequest(), new MockHttpServletResponse()));
	}

	public void initServletRequestMockObject() {
		request = new MockHttpServletRequest();
	}

	/**
	 * We need to override this method so we can use class
	 * MockMultipartHttpServletRequest for the request.
	 */
	@Override
	protected void initServletMockObjects() {
		servletContext = new MockServletContext(resourceLoader);
		response = new MockHttpServletResponse();
		request = new MockMultipartHttpServletRequest();
		pageContext = new MockPageContext(servletContext, request, response);
	}

	/**
	 * This is an alternative way to getActionProxy that accepts a file to be passed to the action.
	 * 
	 * TODO - Refactor to have a more elegant way to pass this information after action creation.
	 * 
	 * @param uri
	 *            struts action uri
	 * @param contentType
	 *            mime content type
	 * @param filename
	 *            name reported to action
	 * @param file
	 *            uploaded file
	 * @return ActionProxy
	 */
	protected ActionProxy getActionProxy(String uri,String propertyName, String contentType, String filename, File file) {
		// Create the proxy
		actionProxy = super.getActionProxy(uri);

		// Wrap the request in a MultiPartRequestWrapper
		MultiPartRequest mpr = createMultiPartRequest(propertyName,contentType, filename, file);
		MultiPartRequestWrapper wrapper = new MultiPartRequestWrapper(mpr, request, null,
				new DefaultLocaleProvider());
		ServletActionContext.setRequest(wrapper);

		return actionProxy;
	}


	/**
	 * Generates a mock multipartRequest
	 * @param propertyName
	 * @param contentType
	 * @param fileName
	 * @param file
	 * @return
	 */
	protected MultiPartRequest createMultiPartRequest(String propertyName, String contentType, String fileName, File file) {
		return new MockMultipartRequest(propertyName, fileName, contentType, file);

	}

	/**
	 * Executes the action created and returns the result
	 * 
	 * @return
	 * @throws Exception
	 */
	public String executeProxy() throws Exception {
		actionProxy.setExecuteResult(false);
		return actionProxy.execute();
	}

	public boolean isTilesApplication() {
		return tilesApplication;
	}

	public void setTilesApplication(boolean isTilesApplication) {
		this.tilesApplication = isTilesApplication;
	}

	/**
	 * Automatically clears the session if parameter set to true and builds it (if action is sessionAware).
	 * @param clearSession TODO
	 *  
	 */
	public void buildSession(boolean clearSession) {
		if(clearSession) {
			session.clear();
		}
		if (action instanceof SessionAware) {
			((SessionAware) action).setSession(session);
			actionProxy.getInvocation().getInvocationContext().setSession(session);
		}
	}

	/**
	 * Can now be run at any time
	 * 
	 * @param key
	 * @param value
	 */
	public void addSessionVariable(String key, Object value) {
		session.put(key, value);

	}

	/**
	 * 
	 * @param answer
	 *            - yes or no
	 * @return - true or false
	 */
	public boolean convertToBoolean(String answer) {
		if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("true")) {
			return true;
		} else
			return false;
	}

	/**
	 * Retrieves the bean with the given name
	 * 
	 * @param beanName
	 * @return
	 */
	public Object createBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	/**
	 * True if the action has already been created and we don't want to create
	 * it again. Defaults to false.
	 * 
	 * @return
	 */
	public boolean isAlreadyCreated() {
		return alreadyCreated;
	}

	public void setAlreadyCreated(boolean alreadyCreated) {
		this.alreadyCreated = alreadyCreated;
	}

	@Override
	protected String getConfigPath() {
		if (configPath != null) {
			return configPath;
		}
		return super.getConfigPath();
	}

	public void setConfigPath(String newPath) {
		configPath = newPath;
	}
}

