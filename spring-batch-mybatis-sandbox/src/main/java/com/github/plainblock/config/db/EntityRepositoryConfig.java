package com.github.plainblock.config.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;

import javax.sql.DataSource;

/**
 * DataDB接続設定
 */
@Configuration
@MapperScan(basePackages = "com.github.plainblock.repository.entity", sqlSessionFactoryRef = "entity-db-factory")
public class EntityRepositoryConfig extends DBConfigBase {

    /**
     * 設定ファイルからDataDB接続用設定を読み出す
     *
     * @return DataDB接続用DataSourceProperties
     */
    @Bean(name = "entity-db-properties")
    @ConfigurationProperties(prefix = "sandbox.db.entity")
    public DataSourceProperties readDatabaseProperties() {
        return new DataSourceProperties();
    }

    /**
     * DataDBに接続するためのDataSourceインスタンスを生成する
     *
     * @param properties DataDB接続用DataSourceProperties
     * @return DataDB接続用DataSource
     */
    @Bean(name = "entity-db-source")
    @ConfigurationProperties(prefix = "sandbox.db.entity.hikari")
    public DataSource createDataSource(@Qualifier("entity-db-properties") final DataSourceProperties properties) {
        return dataSource(properties);
    }

    /**
     * DataDBに接続するためのSqlSessionFactoryインスタンスを生成する
     *
     * @param dataSource DataDB接続用DataSource
     * @return DataDB接続用SqlSessionFactory
     * @throws Exception インスタンス生成失敗時にスロー
     */
    @Bean(name = "entity-db-factory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("entity-db-source") DataSource dataSource) throws Exception {
        return sqlSessionFactory(dataSource);
    }

    /**
     * DataDBに接続するためのDataSourceInitializerインスタンスを生成する
     *
     * @param properties   DataDB接続用DataSourceProperties
     * @param source       DataDB接続用DataSource
     * @param ddlClasspath 初期化用DDLファイルのクラスパス
     * @param dmlClasspath 初期化用DMLファイルのクラスパ
     * @return DataDB接続用DataSourceInitializer
     */
    @Bean("entity-db-initializer")
    public DataSourceInitializer createInitializer(
            @Qualifier("entity-db-properties") final DataSourceProperties properties,
            @Qualifier("entity-db-source") final DataSource source,
            @Value("${sandbox.db.entity.ddl.classpath}") final String ddlClasspath,
            @Value("${sandbox.db.entity.dml.classpath}") final String dmlClasspath
    ) {
        return initializer(source, ddlClasspath, dmlClasspath);
    }

}
