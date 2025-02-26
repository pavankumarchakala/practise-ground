package com.practise_ground.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author Pavankumar - created date : Feb 14, 2025
 */
@Service
public class BeanUtility implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public static Object getBean(String beanId) {
		return applicationContext.getBean(beanId);
	}

	public void setApplicationContext(ApplicationContext applnContext) throws BeansException {
		applicationContext = applnContext;
	}

}
