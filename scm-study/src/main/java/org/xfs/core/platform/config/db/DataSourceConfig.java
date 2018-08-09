package org.xfs.core.platform.config.db;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @author Jeken.Liu
 *
 */
//@PropertySource(value = {"classpath:config/properties/dev/db.properties"})
@EnableTransactionManagement
@MapperScan(basePackages={"org.xfs.core.**.mapper"})  
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


    @Bean(destroyMethod = "close")  
    public DataSource dataSource() {
        logger.info("-------------init dataSource-------------------");

        //logger.info(this.jdbc_username+"--------------------------------"+this.jdbc_url);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(jdbc_url);
        dataSource.setUsername(jdbc_username);
        dataSource.setPassword(jdbc_password);
        // 配置初始化大小、最小、最大
        dataSource.setInitialSize(1);
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

        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        // 监控数据库
        try {
            dataSource.setFilters("stat");
        } catch (SQLException e) {
            logger.error("dataSource stat error:{}", e);
        }

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
    	//logger.info("---------------------datasource:"+dataSource);
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
//
//
//    /**
//     * 
//     * @return
//     * @throws Exception
//     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        // // 通过Mybatis包中的Resources对象很轻松的获取到配置文件\
        // org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        // // configuration.addCache(cache);//cache
        // configuration.addInterceptor(new PageInterceptor());
        // // Resource configLocation = Resources.getResourceAsReader("");
        // // sessionFactory.setConfigLocation(configLocation);
        // // sessionFactory.setConfigurationProperties(sqlSessionFactoryProperties);
        // sessionFactory.setConfiguration(configuration);
          sessionFactory.setTypeAliasesPackage("org.xfs.core.**.mapper");
          sessionFactory.setConfigLocation(new ClassPathResource("/config/mybatis/mybatis-config.xml"));  
     //   sessionFactory.setMapperLocations(mapperLocations);
      //  sessionFactory.setMapperLocations(mapperLocations);
        return sessionFactory;
    }

}
