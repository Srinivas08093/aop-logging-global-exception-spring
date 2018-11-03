package com.example.application.bean;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.slf4j.MDC;

import com.example.application.filter.Log4jMDCFilterConfiguration;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * This class contains the whole bug tracking related data which is used for
 * logging
 *
 */
@JsonInclude(Include.NON_NULL)
public class BugTrackPojo {

	private String transactionId;
	private String orgId;
	private String systemName;
	private String moduleName;
	private String apiName;
	private String methodName;
	private String userName;
	private String userId;
	private String timeStamp;
	private String status;
	private String errorCode;
	private String errorType;
	private String errorSummary;
	private String errorMessage;
	private Object payLoad;
	private String exception;
	private static ObjectMapper mapper = new ObjectMapper();
	private static final String START = "START";
	private static final String END = "END";
	private static final String ERROR = "ERROR";

	/**
	 * 
	 * @return String contains the computer name
	 */
	private static String getComputerName() {
		try {
			Map<String, String> env = System.getenv();
			if (env.containsKey("COMPUTERNAME")) {
				return (String) env.get("COMPUTERNAME");
			} else {
				return env.containsKey("HOSTNAME") ? (String) env.get("HOSTNAME") : "Unknown Computer";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Unknown Computer";
	}

	/**
	 * 
	 * This method used for logging starting of an API call
	 * 
	 * @param apiName
	 *            present API name(normally this should be same as method name)
	 * @param moduleName
	 *            (present module name)
	 * @param payLoad
	 *            (request pay load
	 *            {@link org.springframework.web.bind.annotation.RequestBody} )
	 * @return BugTrackPojo this is used for making request pay load as JSON
	 *         data
	 * @author Srinivas Nangana
	 */

	public static BugTrackPojo setBugsTrackStart(String apiName, String moduleName, Object payLoad) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		LocalDateTime now = LocalDateTime.now();
		String formatDateTime = now.format(dtf);
		BugTrackPojo bugTrack = new BugTrackPojo();
		bugTrack.setPayLoad(payLoad);

		bugTrack.setTransactionId(MDC.get(Log4jMDCFilterConfiguration.DEFAULT_MDC_UUID_TOKEN_KEY));

		bugTrack.setSystemName(getComputerName());
		bugTrack.setModuleName(moduleName);
		bugTrack.setApiName(apiName);
		bugTrack.setTimeStamp(formatDateTime);
		bugTrack.setStatus(START);

		return bugTrack;
	}

	/**
	 * 
	 * This method used for logging ending of an API call
	 * 
	 * @param apiName
	 *            present API name(normally this should be same as method name)
	 * @param moduleName
	 *            (present module name)
	 * @param payLoad
	 *            (request pay load
	 *            {@link org.springframework.web.bind.annotation.RequestBody} )
	 * @return BugTrackPojo this is used for making request pay load as JSON
	 *         data
	 * @author Srinivas Nangana
	 */
	public static BugTrackPojo setBugsTrackEnd(String apiName, String moduleName, Object payLoad) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		LocalDateTime now = LocalDateTime.now();
		String formatDateTime = now.format(dtf);

		BugTrackPojo bugTrack = new BugTrackPojo();
		bugTrack.setPayLoad(payLoad);
		bugTrack.setTransactionId(MDC.get(Log4jMDCFilterConfiguration.DEFAULT_MDC_UUID_TOKEN_KEY));
		bugTrack.setSystemName(getComputerName());
		bugTrack.setModuleName(moduleName);
		bugTrack.setApiName(apiName);
		bugTrack.setTimeStamp(formatDateTime);
		bugTrack.setStatus(END);

		return bugTrack;
	}

	/**
	 * 
	 * This method used for logging ending of an API call
	 * 
	 * @param apiName
	 *            present API name(normally this should be same as method name)
	 * @param moduleName
	 *            (present module name)
	 * @param payLoad
	 *            (request pay load
	 *            {@link org.springframework.web.bind.annotation.RequestBody} )
	 * @param ex
	 *            receives {@link Exception} object and converts this stack
	 *            trace into string with the help of {@link StringWriter} class
	 * @return BugTrackPojo this is used for making request pay load as JSON
	 *         data
	 * @author Srinivas Nangana
	 */
	public static BugTrackPojo setBugsTrackExcepion(String apiName, String moduleName, Object payLoad, Exception ex) {

		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));

		BugTrackPojo bugTrack = new BugTrackPojo();
		bugTrack.setPayLoad(payLoad);
		bugTrack.setTransactionId(MDC.get(Log4jMDCFilterConfiguration.DEFAULT_MDC_UUID_TOKEN_KEY));
		bugTrack.setSystemName(getComputerName());
		bugTrack.setModuleName(moduleName);
		bugTrack.setApiName(apiName);
		bugTrack.setTimeStamp(getCurrentDate());
		bugTrack.setException(errors.toString());
		bugTrack.setStatus(ERROR);

		return bugTrack;
	}

	/**
	 * 
	 * @param recevies
	 *            data in {@link Object} format
	 * @return converted JSON object in {@code String} format
	 * @author Srinivas Nangana
	 */
	public static String objectToJson(Object data) {

		try {
			return mapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "invalid data";

	}

	/**
	 * It returns this current date in {@code yyyy-MM-dd HH:mm:ss.SSS } this
	 * format
	 * 
	 * @return date in String format
	 * @author Srinivas Nangan
	 */
	private static String getCurrentDate() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		LocalDateTime now = LocalDateTime.now();
		return now.format(dtf);

	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorSummary() {
		return errorSummary;
	}

	public void setErrorSummary(String errorSummary) {
		this.errorSummary = errorSummary;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Object getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(Object payLoad) {
		this.payLoad = payLoad;
	}

}