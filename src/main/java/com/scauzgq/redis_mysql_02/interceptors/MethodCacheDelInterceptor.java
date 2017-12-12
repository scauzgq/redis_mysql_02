package com.scauzgq.redis_mysql_02.interceptors;

import com.scauzgq.redis_mysql_02.utils.RedisUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MethodCacheDelInterceptor implements MethodInterceptor {
	private Logger logger = Logger.getLogger(MethodCacheDelInterceptor.class);
	private RedisUtil redisUtil;
	private List<String> targetNamesList; // 不加入缓存的service名称
	private List<String> methodNamesList; // 不加入缓存的方法名称
	private Long defaultCacheExpireTime; // 缓存默认的过期时间
	private Long xxxRecordManagerTime; //
	private Long xxxSetRecordManagerTime; //

	/**
	 * 初始化读取不需要加入缓存的类名和方法名称
	 */
	public MethodCacheDelInterceptor() {
		try {
			// 分割字符串 这里没有加入任何方法
			String[] targetNames = {};
			String[] methodNames = {};

			// 加载过期时间设置
			defaultCacheExpireTime = 3600L;
			xxxRecordManagerTime = 60L;
			xxxSetRecordManagerTime = 60L;
			// 创建list
			targetNamesList = new ArrayList<String>(targetNames.length);
			methodNamesList = new ArrayList<String>(methodNames.length);
			Integer maxLen = targetNames.length > methodNames.length ? targetNames.length
					: methodNames.length;
			// 将不需要缓存的类名和方法名添加到list中
			for (int i = 0; i < maxLen; i++) {
				if (i < targetNames.length) {
					targetNamesList.add(targetNames[i]);
				}
				if (i < methodNames.length) {
					methodNamesList.add(methodNames[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object value = null;

		String targetName = invocation.getThis().getClass().getName();
		//String methodName = invocation.getMethod().getName();

		// 不需要缓存的内容
		//if (!isAddCache(StringUtil.subStrForLastDot(targetName), methodName)) {
		//if (!isAddCache(targetName, methodName)) {
		if (!isAddCache(targetName)) {
			// 执行方法返回结果
			return invocation.proceed();
		}
		Object[] arguments = invocation.getArguments();

		String key = getCacheKey(targetName, arguments);
		//String key = getCacheKey(targetName, methodName, arguments);

		logger.debug("****************************************************redisKey: " + key);
		try {
			// 判断是否有缓存
			//if (redisUtil.exists(key)) {
			redisUtil.remove(key);
			value = invocation.proceed();
			//}

		} catch (Exception e) {
			e.printStackTrace();
			if (value == null) {
				return invocation.proceed();
			}
		}
		return value;
	}

	/**
	 * 是否加入缓存
	 *
	 * @return
	 */
	private boolean isAddCache(String targetName) {
		boolean flag = true;
		if (targetNamesList.contains(targetName)) {
			flag = false;
		}
		return flag;
	}
/*
	private boolean isAddCache(String targetName, String methodName) {
		boolean flag = true;
		if (targetNamesList.contains(targetName)
				|| methodNamesList.contains(methodName)) {
			flag = false;
		}
		return flag;
	}
*/

	/**
	 * 创建缓存key
	 *
	 * @param targetName
	 * @param arguments
	 */
	private String getCacheKey(String targetName, Object[] arguments) {
		System.out.println(arguments);
		StringBuffer sbu = new StringBuffer();
		sbu.append(targetName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < 1; i++) {
			//for (int i = 0; i < arguments.length; i++) {
				sbu.append("_").append(arguments[i]);
			}
		}
		return sbu.toString();
	}
/*
	private String getCacheKey(String targetName, String methodName,
							   Object[] arguments) {
		System.out.println(arguments);
		StringBuffer sbu = new StringBuffer();
		sbu.append(targetName).append("_").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < 1; i++) {
			//for (int i = 0; i < arguments.length; i++) {
				sbu.append("_").append(arguments[i]);
			}
		}
		return sbu.toString();
	}
*/

	public void setRedisUtil(RedisUtil redisUtil) {
		this.redisUtil = redisUtil;
	}
}