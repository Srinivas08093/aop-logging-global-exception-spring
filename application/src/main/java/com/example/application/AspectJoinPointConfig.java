package com.example.application;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.example.application.bean.BugTrackPojo;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 
 * This class is used for Logging purpose across whole application
 * 
 * @author Srinivas Nangana
 *
 */
@Aspect
@Configuration
public class AspectJoinPointConfig {
	private static final Logger logger = LoggerFactory.getLogger(AspectJoinPointConfig.class);

	private static String MODULE_NAME = "SAMPLE";

	/**
	 * This method logg's the every method in this whole application at the time
	 * of before calling with method name and method arguments
	 * 
	 * @param joinPoint
	 * @author Srinivas Nangana
	 * @throws JsonProcessingException
	 */
	@Before("execution(* com.example.application.controller.*.*(..))")
	public void beforeForEveryMethodInThisApplication(JoinPoint joinPoint) {
		logger.info(BugTrackPojo.objectToJson(
				BugTrackPojo.setBugsTrackStart(joinPoint.getSignature().getName(), MODULE_NAME, joinPoint.getArgs())));
	}

	/**
	 * This method logg's the every method in this whole application only at the
	 * time of successful completion only
	 * 
	 * @param joinPoint
	 * @author Srinivas Nangana
	 * @throws JsonProcessingException
	 */
	@AfterReturning(pointcut = "execution(* com.example.application.controller.*.*(..))", returning = "retunValue")
	public void afterSuccessfulReturningForEveryMethodInThisApplication(JoinPoint joinPoint, Object retunValue) {
		logger.info(BugTrackPojo.objectToJson(
				BugTrackPojo.setBugsTrackEnd(joinPoint.getSignature().getName(), MODULE_NAME, retunValue)));

	}

	/**
	 * This method logg's the every method in this whole application only at the
	 * time of Exception
	 * 
	 * @param joinPoint
	 * @author Srinivas Nangana
	 * @throws JsonProcessingException
	 * @throws AppServiceException
	 */
	@AfterThrowing(pointcut = "execution(* com.example.application.controller.*.*(..))", throwing = "exception")
	public void ifJoinPointMethodThrowsExceptionForEveryMethodInThisApplication(JoinPoint joinPoint,
			Exception exception) {
		logger.info(BugTrackPojo.objectToJson(BugTrackPojo.setBugsTrackExcepion(joinPoint.getSignature().getName(),
				MODULE_NAME, joinPoint.getArgs(), exception)));

	}
}