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
 * IsoDB接続設定
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "iso-db-entity-manager",
        transactionManagerRef = "iso-db-transaction-manager",
        basePackages = "com.github.plainblock.multi.db.repository.iso"
)
@MapperScan(basePackages = "com.github.plainblock.multi.db.mapper.iso")
public class IsoDBConfig extends DBConfigBase {

    /**
     * 設定ファイルからIsoDB接続用設定を読み出す
     *
     * @return IsoDB接続用DataSourceProperties
     */
    @Bean(name = "iso-db-properties")
    @ConfigurationProperties(prefix = "multi.db.iso")
    public DataSourceProperties readDatabaseProperties() {
        return new DataSourceProperties();
    }

    /**
     * IsoDBに接続するためのDataSourceインスタンスを生成する
     *
     * @param properties IsoDB接続用DataSourceProperties
     * @return IsoDB接続用DataSource
     */
    @Bean(name = "iso-db-source")
    @ConfigurationProperties(prefix = "multi.db.iso.hikari")
    public DataSource createDataSource(@Qualifier("iso-db-properties") final DataSourceProperties properties) {
        return dataSource(properties);
    }

    /**
     * IsoDBに接続するためのDataSourceInitializerインスタンスを生成する
     *
     * @param properties   IsoDB接続用DataSourceProperties
     * @param source       IsoDB接続用DataSource
     * @param ddlClasspath 初期化用DDLファイルのクラスパス
     * @param dmlClasspath 初期化用DMLファイルのクラスパス
     * @return IsoDB接続用DataSourceInitializer
     */
    @Bean("iso-db-initializer")
    public DataSourceInitializer createInitializer(
            @Qualifier("iso-db-properties") final DataSourceProperties properties,
            @Qualifier("iso-db-source") final DataSource source,
            @Value("${multi.db.iso.ddl.classpath}") final String ddlClasspath,
            @Value("${multi.db.iso.dml.classpath}") final String dmlClasspath
    ) {
        return dataSourceInitializer(source, ddlClasspath, dmlClasspath);
    }

    /**
     * 【JPA】IsoDBに接続するためのEntityManagerFactoryインスタンスを生成する
     *
     * @param source IsoDB接続用DataSource
     * @return IsoDB接続用EntityManagerFactory
     */
    @Bean("iso-db-entity-manager")
    public EntityManagerFactory createEntityManager(@Qualifier("iso-db-source") final DataSource source) {
        return entityManager(source, "com.github.plainblock.multi.db.table.iso");
    }

    /**
     * 【JPA】IsoDBで発給するトランザクション管理用のPlatformTransactionManagerインスタンスを生成する
     *
     * @param entityManagerFactory IsoDB接続用EntityManagerFactory
     * @return IsoDB用PlatformTransactionManager
     */
    @Bean("iso-db-transaction-manager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("iso-db-entity-manager") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    /**
     * 【MyBatis】IsoDBに接続するためのSqlSessionFactoryインスタンスを生成する
     *
     * @param dataSource IsoDB接続用DataSource
     * @return IsoDB接続用SqlSessionFactory
     * @throws Exception インスタンス生成失敗時にスロー
     */
    @Bean(name = "iso-db-session-factory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("iso-db-source") DataSource dataSource) throws Exception {
        return sqlSessionFactory(dataSource);
    }

}
