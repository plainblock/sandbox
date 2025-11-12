package com.github.plainblock.config.db;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.scripting.defaults.RawLanguageDriver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * DB接続設定のベースクラス
 */
abstract class DBConfigBase {

    /**
     * テスト用スクリプトの文字コード
     */
    private static final String TEST_SCRIPT_ENCODING = "UTF-8";

    /**
     * DataSourceインスタンスを生成する
     *
     * @param properties DataSourceProperties
     * @return DataSource
     */
    DataSource dataSource(final DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    /**
     * SqlSessionFactoryインスタンスを生成する
     *
     * @param dataSource DataSource
     * @return SqlSessionFactory
     * @throws Exception インスタンス生成失敗時にスロー
     */
    SqlSessionFactory sqlSessionFactory(final DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        if (Objects.isNull(sqlSessionFactory)) {
            throw new NullPointerException();
        }
        sqlSessionFactory.getConfiguration().setCacheEnabled(false);
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        sqlSessionFactory.getConfiguration().setDefaultScriptingLanguage(RawLanguageDriver.class);
        return sqlSessionFactory;
    }


    /**
     * DataSourceInitializerインスタンスを生成する
     *
     * @param source       DataSource
     * @param ddlClasspath 初期化用DDLファイルのクラスパス
     * @param dmlClasspath 初期化用DMLファイルのクラスパス
     * @return DataSourceInitializer
     */
    DataSourceInitializer initializer(final DataSource source, final String ddlClasspath, final String dmlClasspath) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(source);
        if (StringUtils.hasText(ddlClasspath) && StringUtils.hasText(dmlClasspath)) {
            ResourceLoader loader = new DefaultResourceLoader();
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.setSqlScriptEncoding(TEST_SCRIPT_ENCODING);
            populator.addScript(loader.getResource(ddlClasspath));
            populator.addScript(loader.getResource(dmlClasspath));
            initializer.setDatabasePopulator(populator);
        }
        return initializer;
    }

}
