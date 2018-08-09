package org.xfs.scm.platform.config.data.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mockito.internal.configuration.GlobalConfiguration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@EnableTransactionManagement
@MapperScan(basePackages={"org.xfs.scm.**.mapper"})
public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
    /*
     * 绑定资源属性
     */
    @Value("${jdbc.driverClassName}")
    String driverclass;

    @Value("${jdbc.url}")
    String jdbc_url;

    @Value("${jdbc.username}")
    String jdbc_username;

    @Value("${jdbc.password}")
    String jdbc_password;


    //
    @Value("${jdbc.validationQuery}")
    String jdbc_validationQuery;
    @Value("${jdbc.datasource.connectionProperties}")
    private String connectionProperties;


    @Bean
    public DataSource dataSource() {
        if(logger.isDebugEnabled()){
            logger.debug("-------------init dataSource-------------------");
        }
        //logger.info(this.jdbc_username+"--------------------------------"+this.jdbc_url);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(jdbc_url);
        dataSource.setUsername(jdbc_username);
        dataSource.setPassword(jdbc_password);
        // 配置初始化大小、最小、最大
        dataSource.setInitialSize(3);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(20);

        // 配置获取连接等待超时的时间
        dataSource.setMaxWait(60000);
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery(jdbc_validationQuery);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setLogAbandoned(true);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        // 监控数据库
        try {
            dataSource.setFilters("mergeStat");
        } catch (SQLException e) {
            logger.error("dataSource stat error:{}", e);
        }
      //  dataSource.setConnectProperties(connectionProperties);
        return dataSource;
    }
//    @Bean(name = "druid-stat-interceptor")
//    public static DruidStatInterceptor druidStatInterceptor(){
//        DruidStatInterceptor druidStatInterceptor=new DruidStatInterceptor();
//        return druidStatInterceptor;
//    }
//
//    @Bean(name = "druid-stat-pointcut")
//    public JdkRegexpMethodPointcut JdkRegexpMethodPointcut(){
//        JdkRegexpMethodPointcut jdkRegexpMethodPointcut=new JdkRegexpMethodPointcut();
//        jdkRegexpMethodPointcut.setPatterns("scm.xfs.scm.data..service.*");
//        return jdkRegexpMethodPointcut;
//    }
//全局配置
    public GlobalConfiguration globalConfigurationBean(){
        GlobalConfiguration globalConfiguration = new GlobalConfiguration();
//        globalConfiguration.setIdType(2);//AUTO->`0`("数据库ID自增")、INPUT->`1`(用户输入ID")、ID_WORKER->`2`("全局唯一ID")、UUID->`3`("全局唯一ID")
//        globalConfiguration.setDbColumnUnderline(true);//全局表为下划线命名设置 true
        return globalConfiguration;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {

        if(logger.isDebugEnabled()){
            logger.debug("---------------------datasource:"+dataSource);
        }
        return new DataSourceTransactionManager(dataSource);
    }
    /**
     * 获取jdbcTemplate
     *
     * @return
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        sessionFactory.setTypeAliasesPackage("org.xfs.scm.**.mapper");
        sessionFactory.setConfigLocation(new ClassPathResource("/config/mybatis/mybatis-config.xml"));

        return sessionFactory.getObject();
    }

}
