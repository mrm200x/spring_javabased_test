package spr;

import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/14.
 */
@Configuration
@PropertySource("classpath:db.properties")
@MapperScan(basePackages = "spr.dao", sqlSessionFactoryRef = "factory")
public class AppConfigA {

    @Autowired
    Environment env;

    @Autowired
    @Qualifier("dbA")
    DataSource dbA;

    @Bean(value = "dbA")
    @Qualifier("dbA")
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(env.getProperty("driverClass1"));
        ds.setUsername(env.getProperty("username1"));
        ds.setPassword(env.getProperty("pswd1"));
        ds.setUrl(env.getProperty("connectURI1"));
//        ds.setInitialSize(initialSize); // 初始的连接数；
//        ds.setMaxTotal(maxtotal);
//        ds.setMaxIdle(maxIdle);
//        ds.setMaxWaitMillis(maxWaitMillis);
//        ds.setMinIdle(minIdle);
        return ds;
    }
    @Bean(value = "factory")
    public SqlSessionFactoryBean getFB2() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean =new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dbA);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        ClassPathResource cpr = new ClassPathResource("Test1Mapper.xml");
//        Resource[] resources = new Resource[1];
//        resources[0] = (Resource) cpr;
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:db1/*.xml"));
        return sqlSessionFactoryBean;
    }

    @Bean(value = "transactionManagerA")
    public DataSourceTransactionManager getDataSourceTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dbA);
        return dataSourceTransactionManager;
    }
}
