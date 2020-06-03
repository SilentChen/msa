package com.silent.msa.admin.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Mybatis配置
 * @author silent
 * @date Oct 29, 2018
 */
@Configuration
@MapperScan("com.silent.msa.*.dao")	// 扫描DAO
public class MybatisConfig {
  @Autowired
  private DataSource dataSource;

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setTypeAliasesPackage("com.silent.msa.*.model");	// 扫描Model
    
	PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/sqlmap/*.xml"));	// 扫描映射文件
	
    return sessionFactory.getObject();
  }
}