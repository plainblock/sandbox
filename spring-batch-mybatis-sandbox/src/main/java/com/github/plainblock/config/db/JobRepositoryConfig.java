package com.github.plainblock.config.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;

import javax.sql.DataSource;

/**
 * JobDB接続設定
 */
@Configuration
@MapperScan(basePackages = "com.github.plainblock", sqlSessionFactoryRef = "job-db-factory")
public class JobRepositoryConfig extends DBConfigBase {

    /**
     * 設定ファイルからJobDB接続用設定を読み出す
     *
     * @return JobDB接続用DataSourceProperties
     */
    @Primary
    @Bean(name = "job-db-properties")
    @ConfigurationProperties(prefix = "sandbox.db.job")
    public DataSourceProperties readDatabaseProperties() {
        return new DataSourceProperties();
    }

    /**
     * JobDBに接続するためのDataSourceインスタンスを生成する
     *
     * @param properties JobDB接続用DataSourceProperties
     * @return JobDB接続用DataSource
     */
    @Primary
    @Bean(name = "job-db-source")
    @ConfigurationProperties(prefix = "sandbox.db.job.hikari")
    public DataSource createDataSource(@Qualifier("job-db-properties") final DataSourceProperties properties) {
        return dataSource(properties);
    }

    /**
     * JobDBに接続するためのSqlSessionFactoryインスタンスを生成する
     *
     * @param dataSource JobDB接続用DataSource
     * @return JobDB接続用SqlSessionFactory
     * @throws Exception インスタンス生成失敗時にスロー
     */
    @Primary
    @Bean(name = "job-db-factory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("job-db-source") DataSource dataSource) throws Exception {
        return sqlSessionFactory(dataSource);
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
    @Primary
    @Bean("job-db-initializer")
    public DataSourceInitializer createInitializer(
            @Qualifier("job-db-properties") final DataSourceProperties properties,
            @Qualifier("job-db-source") final DataSource source,
            @Value("${sandbox.db.job.ddl.classpath}") final String ddlClasspath,
            @Value("${sandbox.db.job.dml.classpath}") final String dmlClasspath
    ) {
        return initializer(source, ddlClasspath, dmlClasspath);
    }

}
