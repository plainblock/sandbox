package com.github.plainblock.multi.db.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * JobDB接続設定
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "job-db-entity-manager",
        transactionManagerRef = "job-db-transaction-manager",
        basePackages = "com.github.plainblock.multi.db.repository.job"
)
@MapperScan(basePackages = "com.github.plainblock.multi.db.mapper.job")
public class JobDBConfig extends DBConfigBase {

    /**
     * 設定ファイルからJobDB接続用設定を読み出す
     *
     * @return JobDB接続用DataSourceProperties
     */
    @Bean(name = "job-db-properties")
    @ConfigurationProperties(prefix = "multi.db.job")
    public DataSourceProperties readDatabaseProperties() {
        return new DataSourceProperties();
    }

    /**
     * JobDBに接続するためのDataSourceインスタンスを生成する
     *
     * @param properties JobDB接続用DataSourceProperties
     * @return JobDB接続用DataSource
     */
    @Bean(name = "job-db-source")
    @ConfigurationProperties(prefix = "multi.db.job.hikari")
    public DataSource createDataSource(@Qualifier("job-db-properties") final DataSourceProperties properties) {
        return dataSource(properties);
    }

    /**
     * JobDBに接続するためのDataSourceInitializerインスタンスを生成する
     *
     * @param properties   JobDB接続用DataSourceProperties
     * @param source       JobDB接続用DataSource
     * @param ddlClasspath 初期化用DDLファイルのクラスパス
     * @param dmlClasspath 初期化用DMLファイルのクラスパス
     * @return JobDB接続用DataSourceInitializer
     */
    @Bean("job-db-initializer")
    public DataSourceInitializer createInitializer(
            @Qualifier("job-db-properties") final DataSourceProperties properties,
            @Qualifier("job-db-source") final DataSource source,
            @Value("${multi.db.job.ddl.classpath}") final String ddlClasspath,
            @Value("${multi.db.job.dml.classpath}") final String dmlClasspath
    ) {
        return dataSourceInitializer(source, ddlClasspath, dmlClasspath);
    }

    /**
     * 【JPA】JobDBに接続するためのEntityManagerFactoryインスタンスを生成する
     *
     * @param source JobDB接続用DataSource
     * @return JobDB接続用EntityManagerFactory
     */
    @Bean("job-db-entity-manager")
    public EntityManagerFactory createEntityManager(@Qualifier("job-db-source") final DataSource source) {
        return entityManager(source, "com.github.plainblock.multi.db.table.job");
    }

    /**
     * 【JPA】JobDBで発給するトランザクション管理用のPlatformTransactionManagerインスタンスを生成する
     *
     * @param entityManagerFactory JobDB接続用EntityManagerFactory
     * @return JobDB用PlatformTransactionManager
     */
    @Bean("job-db-transaction-manager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("job-db-entity-manager") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    /**
     * 【MyBatis】JobDBに接続するためのSqlSessionFactoryインスタンスを生成する
     *
     * @param dataSource JobDB接続用DataSource
     * @return JobDB接続用SqlSessionFactory
     * @throws Exception インスタンス生成失敗時にスロー
     */
    @Bean(name = "job-db-session-factory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("job-db-source") DataSource dataSource) throws Exception {
        return sqlSessionFactory(dataSource);
    }
    
}
