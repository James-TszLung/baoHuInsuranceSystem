package com.xiaobaozi.commons.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

import com.xiaobaozi.commons.Constants;
import com.xiaobaozi.commons.service.CustomInvocationSecurityMetadataSourceService;
import com.xiaobaozi.commons.utils.DateUtil;
import com.xiaobaozi.commons.utils.PropertiesUtil;
import com.xiaobaozi.commons.view.AjaxJsonView;

/**
 * 在MultiActionController的基础上提供一些与范型无关的常用方法，前后台都可以用，主要功能如下： 1.表单绑定和验证 2.I18n信息读取
 * 3.获取ModelAndView,如AjaxJsonView 4.保存正常或错误返回信息到页面显示
 * 
 * 
 */
@Controller
public class BaseController extends MultiActionController implements InitializingBean {

	private static Logger log = Logger.getLogger(BaseController.class);

	// 初始化配置文件
	protected Properties props = PropertiesUtil.getProperties("/conf/properties/systemconf.properties");

	// 用户权限读取与请求拦截服务---每次权限变动都要调用该类的loadResourceDefine()方法刷新一下本地存放的所有权限
	@Resource
	private CustomInvocationSecurityMetadataSourceService customSecurityMetadataSource;

	/**
	 * 控制器工具类，包含一些公共方法
	 */
	@Resource
	protected ControllerKit controllerKit;

	public void setControllerKit(ControllerKit controllerKit) {
		this.controllerKit = controllerKit;
		this.controllerKit.setController(this);
	}

	public CustomInvocationSecurityMetadataSourceService getCustomSecurityMetadataSource() {
		return customSecurityMetadataSource;
	}

	public void setCustomSecurityMetadataSource(CustomInvocationSecurityMetadataSourceService customSecurityMetadataSource) {
		this.customSecurityMetadataSource = customSecurityMetadataSource;
	}
	@Resource
	MessageSource messageSource;

	// Security获取用户信息的实体类
	@Resource
	private SessionRegistry sessionRegistry;

	public SessionRegistry getSessionRegistry() {
		return sessionRegistry;
	}

	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

	public BaseController() {
		super();
	}

	/**
	 * 获取当前登录用户的详细信息
	 * 
	 * @return <b>Authentication：</b><br>
	 *         1.auth.getDetails()可以获取WebAuthenticationDetails对象，用于获取用户的登录IP等信息；<br>
	 *         2.auth.getPrincipal()可以获取user对象，里面包含用户的账号详情，如账号、密码
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 根据request对象来获取applicationcontext，用于获取已注入的bean
	 * 
	 * @param request
	 * @return
	 */
	public ApplicationContext getApplicationContext(HttpServletRequest request) {
		ServletContext sc = request.getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		return ctx;
	}

	/**
	 * 根据相对路径来获取applicationcontext，用于获取已注入的bean
	 * 
	 * @param
	 * @return
	 */
	public ApplicationContext getApplicationContext(String path) {
		return new ClassPathXmlApplicationContext(path);
	}

	/**
	 * 注入表单验证器
	 * 
	 * @param validator
	 */
	public final void setValidator(Validator validator) {
		super.setValidators(new Validator[] { validator });
	}

	/**
	 * 表单Command绑定
	 * 
	 * @param request
	 * @param command
	 * @param commandName
	 * @throws Exception
	 */
	protected ServletRequestDataBinder bindOnly(HttpServletRequest request, Object command, String commandName) {
		ServletRequestDataBinder binder = new ServletRequestDataBinder(command, commandName);
		initBinder(request, binder);
		binder.bind(request);
		return binder;
	}

	/**
	 * 支持多次、多个对象的绑定
	 * 
	 * @param request
	 *            current HTTP request
	 * @param command
	 *            the command to bind onto
	 * @param commandName
	 *            the command name
	 * @return the ServletRequestDataBinder instance for additional custom
	 *         validation
	 */
	protected ServletRequestDataBinder bindAndValidate(HttpServletRequest request, Object command, String commandName) {
		ServletRequestDataBinder binder = new ServletRequestDataBinder(command, commandName);
		initBinder(request, binder);
		binder.bind(request);
		if (this.getValidators() != null) {
			for (int i = 0; i < this.getValidators().length; i++) {
				if (this.getValidators()[i].supports(command.getClass())) {
					ValidationUtils.invokeValidator(this.getValidators()[i], command, binder.getBindingResult());
				}
			}
		}
		return binder;
	}

	@Override
	protected void bind(HttpServletRequest request, Object command) {
		this.bindAndValidate(request, command, getCommandName(command));
	}

	@Override
	protected String getCommandName(Object command) {
		return DEFAULT_COMMAND_NAME;
	}

	/**
	 * 初始化复杂类型数据绑定 spring支持的基本类型是：int、char、String
	 * 增加支持类型是：BigDecimal,Date(yyyy-MM-dd),Integer,Long,byte[]
	 */
	@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setAutoGrowCollectionLimit(2048);

		NumberFormat dcf = new DecimalFormat(Constants.BINDER_DECIMAL_FORMAT);
		binder.registerCustomEditor(BigDecimal.class, null, new CustomNumberEditor(BigDecimal.class, dcf, true));

		SimpleDateFormat dtf = new SimpleDateFormat(DateUtil.getDatePattern());
		dtf.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dtf, true));

		NumberFormat nf = NumberFormat.getNumberInstance();
		binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, nf, true));

		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, nf, true));

		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

	/**
	 * 显示当前request的详细信息
	 * 
	 * @param request
	 */
	protected void debugRequest(HttpServletRequest request) {
		// logger.debug(RequestUtil.getRequestInfo(request));
	}

	/**
	 * 保存正常操作类信息,在页面中显示
	 * 
	 * @param request
	 * @param msg
	 * @see #saveErrorMessage(HttpServletRequest, String)
	 */
	protected final void saveMessage(HttpServletRequest request, String msg) {
		// RequestContext.saveSessionMessage(request, msg);
	}

	/**
	 * 保存警告错误类信息,在页面中显示
	 * 
	 * @param request
	 * @param msg
	 * @see #saveMessage(HttpServletRequest, String)
	 */
	protected final void saveErrorMessage(HttpServletRequest request, String msg) {
		// RequestContext.saveSessionErrorMessage(request, msg);
	}

	@Override
	public long getLastModified(HttpServletRequest request) {
		return getCacheSeconds() > 0 ? System.currentTimeMillis() : -1L;
	}

	/**
	 * 调用StringUtil的format方法
	 * 
	 * @param msgWithFormat
	 * @param args
	 * @return
	 */
	public final static StringBuilder formatMsg(String msgWithFormat, Object... args) {
		// return StringUtil.format(msgWithFormat, args);
		return null;
	}

	/**
	 * 根据i18n key获得值 Convenience method for getting a i18n key's value. Calling
	 * getMessageSourceAccessor() is used because the RequestContext variable is
	 * not set in unit tests b/c there's no DispatchServlet Request.
	 * 
	 * @param msgKey
	 * @return
	 */
	protected final String getMessage(String msgKey) {
		return super.getMessageSourceAccessor().getMessage(msgKey);
	}

	/**
	 * 根据i18n key获得值 Convenience method for getting a i18n key's value with
	 * arguments. User preferred locale will be tried first, then system default
	 * locale. If no text found, return ???msgKey???.
	 * 
	 * @param msgKey
	 * @param args
	 * @return
	 */
	protected String getMessage(String msgKey, Object[] args) {
		return super.getMessageSourceAccessor().getMessage(msgKey, args);
	}

	/**
	 * 创建ModelAndView，适合Model传入多个对象的用法，Model的填充应在传入之前完成。
	 * 
	 * @param viewName
	 * @param model
	 * @return
	 */
	protected final ModelAndView getModelAndView(final String viewName, final Map model) {
		return new ModelAndView(viewName, model);
	}

	/**
	 * 创建ModelAndView，适合Model传入一个对象用法。当然在创建后还是可以继续添加modelObject。
	 * 
	 * @param viewName
	 * @param modelName
	 * @param modelObject
	 * @return
	 */
	protected final ModelAndView getModelAndView(final String viewName, String modelName, Object modelObject) {
		return new ModelAndView(viewName, modelName, modelObject);
	}

	/**
	 * 创建Ajax json返回类的ModelAndView，适合Model传入多个对象的用法，Model的填充应在传入之前完成。
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	protected final ModelAndView getModelAndAjaxJsonView(final Map model) {
		return new ModelAndView(new AjaxJsonView(), model);
	}

	/**
	 * 创建Ajax json返回类的ModelAndView，适合Model传入一个对象用法。当然在创建后还是可以继续添加modelObject。
	 * 
	 * @param view
	 * @param model
	 * @return
	 */
	// protected final ModelAndView getModelAndAjaxJsonView(final String
	// modelName, final Object modelObject) {
	// return new ModelAndView(new AjaxJsonView(), modelName, modelObject);
	// }
	/**
	 * 创建excel返回类的ModelAndView，适合Model传入多个对象的用法，Model的填充应在传入之前完成。
	 * 
	 * @param view
	 * @param model
	 * @return
	 */
	// protected final ModelAndView getModelAndExcelView(final Map model) {
	// return new ModelAndView(new ExportView(), model);
	// }

	/**
	 * 创建excel返回类的ModelAndView，适合Model传入一个对象用法。当然在创建后还是可以继续添加modelObject。
	 * 
	 * @param view
	 * @param model
	 * @return
	 */
	// protected final ModelAndView getModelAndExportView(final String
	// modelName, final Object modelObject) {
	// return new ModelAndView(new ExportView(), modelName, modelObject);
	// }

	/**
	 * 创建重定向返回的ModelAndView，转向指定的uri（框架自动加上Context Path）
	 * 
	 * @param uri
	 *            举例：/catalog/categorys_dy.html?doAction=doNotFoundAction
	 * @return
	 */
	protected final ModelAndView getRedirectView(final String uri) {
		return new ModelAndView(new RedirectView(uri, true));
	}

	/**
	 * 处理可恢复、可处理的（一般是用户或应用级别的，而不是系统级别的），这种错误需要在当前页面显示友好的错误信息。
	 * <P>
	 * 注意：如果是输入验证等的错误，应该用errors.reject等处理方法，而不要抛出这样的错误。这里主要是Mgr层处理（已经不能使用errors.
	 * reject）出错并需要显示友好错误信息的时候使用。
	 * <P>
	 * 说明：本来想用ExceptionHandler机制，但是那样很难设置errors和调用showForm了。
	 * 
	 * @param errors
	 *            会把具体错误信息添加到这里面并回传
	 * @param exception
	 *            具体的错误
	 */
	// protected void handleApplicationException(BindException errors,
	// ApplicationException exception) {
	// if (exception.getErrorCode() != null) {
	// errors.reject(exception.getErrorCode(), exception.getArgs(),
	// exception.getMessage());
	// }
	// logger.warn("An application exception is being logged. " +
	// exception.getMessage());
	// }

	/**
	 * 缓存配置变更时调用,设置页面端缓存时间
	 */
	// public void onConfigChanged(ConfigChangedEvent event) {
	// }

	/**
	 * 令到Spring在完成设置后调用，并进一步调用各Controller的初始化方法
	 * 
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		initController();
	}

	/**
	 * 子类可以覆写这个方法来初始化的工作
	 * 
	 */
	protected void initController() throws Exception {

	}

	/**
	 * 处理未捕获的异常
	 */
	@ExceptionHandler
	public void resolveException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		log.error("Controller发生异常", e);
	}

}