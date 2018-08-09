package org.xfs.scm.platform.config.data.db.mybatis;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * 
 * @author fengling9874
 *
 */
@Intercepts({

		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),})
public class OrderByInterceptor implements Interceptor {
	private Field additionalParametersField;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		final Object[]args=invocation.getArgs();
		MappedStatement mappedStatement=(MappedStatement)args[0];
		Object parameter = args[1];
		RowBounds rowBounds = (RowBounds) args[2];
		ResultHandler resultHandler = (ResultHandler) args[3];

//		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
//		BoundSql boundSql=statementHandler.getBoundSql();
		SqlSource sqlSource=mappedStatement.getSqlSource();
		BoundSql boundSql=mappedStatement.getBoundSql(args[1]);
		Executor executor = (Executor) invocation.getTarget();

		String orderSql=boundSql.getSql();


		//反射获取动态参数
		Map<String, Object> additionalParameters = (Map<String, Object>) additionalParametersField.get(boundSql);
		String id=mappedStatement.getId();
		if(additionalParameters.size()>1){

			orderSql+=" AND 123456=123456";
			// 重新new一个查询语句对像
			BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), orderSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
			// 把新的查询放到statement里
			MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
			for (ParameterMapping mapping : boundSql.getParameterMappings()) {
				String prop = mapping.getProperty();
				if (boundSql.hasAdditionalParameter(prop)) {
					newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
				}
			}
			args[0]=mappedStatement;
		}
		System.out.println("----------------->"+orderSql);

		//生成分页的缓存 key
		//CacheKey pageKey = cacheKey;
	//	BoundSql pageBoundSql = new BoundSql(mappedStatement.getConfiguration(), orderSql, boundSql.getParameterMappings(), parameter);
	//	Object result = executor.query(mappedStatement, parameter, RowBounds.DEFAULT, resultHandler, pageKey, pageBoundSql);
		//mappedStatement.
		//(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler);



		return invocation.proceed();
	}
	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
			builder.keyProperty(ms.getKeyProperties()[0]);
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		return builder.build();
	}
	@Override
	public Object plugin(Object target) {
		System.out.println("plugin");
		return Plugin.wrap(target, this);

	}

	@Override
	public void setProperties(Properties properties) {
		try {
			//反射获取 BoundSql 中的 additionalParameters 属性
			additionalParametersField = BoundSql.class.getDeclaredField("additionalParameters");
			additionalParametersField.setAccessible(true);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		String dialect = properties.getProperty("dialect");
		System.out.println("setProperties:"+dialect);
	}

}
