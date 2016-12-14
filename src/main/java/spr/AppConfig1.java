package spr;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import javax.transaction.SystemException;
import java.io.IOException;


//@Configuration
@PropertySource("classpath:db.properties")
@MapperScan(basePackages = "spr.dao", sqlSessionFactoryRef = "factory1")
public class AppConfig1 {
    @Autowired
    Environment env;
    @Autowired
    @Qualifier("db1")
    DataSource db1;

//    public DataSource dataSource() {
//        BasicDataSource ds = new BasicDataSource();
//        ds.setDriverClassName(env.getProperty("driverClass1"));
//        ds.setUsername(env.getProperty("username1"));
//        ds.setPassword(env.getProperty("pswd1"));
//        ds.setUrl(env.getProperty("connectURI1"));
////        ds.setInitialSize(initialSize); // 初始的连接数；
////        ds.setMaxTotal(maxtotal);
////        ds.setMaxIdle(maxIdle);
////        ds.setMaxWaitMillis(maxWaitMillis);
////        ds.setMinIdle(minIdle);
//        return ds;
//    }
    @Bean(value = "db1")
    @Qualifier("db1")
    public DataSource getDataSource1(){
        AtomikosNonXADataSourceBean bean = new AtomikosNonXADataSourceBean();
        bean.setUniqueResourceName("ddb1");
        bean.setDriverClassName(env.getProperty("driverClass1"));
        bean.setUser(env.getProperty("username1"));
        bean.setPassword(env.getProperty("pswd1"));
        bean.setUrl(env.getProperty("connectURI1"));
        return bean;
    }


    @Bean(value = "transactionManager")
    public JtaTransactionManager getJtaTransactionManager() throws SystemException {
        JtaTransactionManager jtaTransactionManager =new JtaTransactionManager();
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);
        UserTransactionImp userTransactionImp =new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(300);

        jtaTransactionManager.setTransactionManager(userTransactionManager);
        jtaTransactionManager.setUserTransaction(userTransactionImp);
        jtaTransactionManager.setAllowCustomIsolationLevels(true);
        return jtaTransactionManager;
    }




    @Bean(value = "factory1")
    public SqlSessionFactoryBean getFB1() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean =new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db1);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        ClassPathResource cpr = new ClassPathResource("Test1Mapper.xml");
//        Resource[] resources = new Resource[1];
//        resources[0] = (Resource) cpr;
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:db1/*.xml"));
        return sqlSessionFactoryBean;
    }


//    public MapperScannerConfigurer getMapperScannerConfigurer1(){
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setBasePackage("spr.dao");
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("factory1");
//        return mapperScannerConfigurer;
//    }
}
