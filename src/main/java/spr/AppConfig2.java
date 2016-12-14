package spr;

import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
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

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/14.
 */

@PropertySource("classpath:db.properties")
@MapperScan(basePackages = "spr.dao2", sqlSessionFactoryRef = "factory2")
public class AppConfig2 {

    @Autowired
    Environment env;

    @Autowired
    @Qualifier("db2")
    DataSource db2;

    @Bean(value = "db2")
    @Qualifier("db2")
    public DataSource getDataSource2(){
        AtomikosNonXADataSourceBean bean = new AtomikosNonXADataSourceBean();
        bean.setUniqueResourceName("ddb2");
        bean.setDriverClassName(env.getProperty("driverClass1"));
        bean.setUser(env.getProperty("username1"));
        bean.setPassword(env.getProperty("pswd1"));
        bean.setUrl(env.getProperty("connectURI2"));
        return bean;
    }
    @Bean(value = "factory2")
    public SqlSessionFactoryBean getFB2() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean =new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db2);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        ClassPathResource cpr = new ClassPathResource("Test1Mapper.xml");
//        Resource[] resources = new Resource[1];
//        resources[0] = (Resource) cpr;
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:db2/*.xml"));
        return sqlSessionFactoryBean;
    }
}
