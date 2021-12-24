package edu.gzhh.forum.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.io.File;

/**
 * 代码生成类
 * @Author 林学文
 * @Date 2021/12/9 11:12
 * @Version 1.0
 */

public class AutoGenerate {
    @Test
    public void autoGenerate(){
        /*代码生成器*/
        AutoGenerator autoGenerator = new AutoGenerator();
        /*全局配置*/
        GlobalConfig globalConfig = new GlobalConfig();
        /*代码生成目录(本项目下)*/
        String code_path =  "C:\\Users\\Administrator\\Desktop\\code";
        /*拼接生成最终生成目录*/
        globalConfig.setOutputDir(code_path+"\\src\\main\\java");
        /*配置开发者信息*/
        globalConfig.setAuthor("lxw");
        /*配置是否打开目录*/
        globalConfig.setOpen(false);
        /*生成文件是否覆盖*/
        globalConfig.setFileOverride(true);
        /*配置主键生成策略*/
        globalConfig.setIdType(IdType.ASSIGN_ID);
        /*配置日期类型*/
        globalConfig.setDateType(DateType.ONLY_DATE);
        /*默认生成的service有前缀*/
        globalConfig.setServiceName("%sService");
        autoGenerator.setGlobalConfig(globalConfig);

        /*数据源配置*/
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        /*配置数据库url*/
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/forum?useUnicode=true&serverTimezone=Asia/Shanghai&characterEncoding=utf8&autoReconnect=true&useSSL=false&allowMultiQueries=true");
        /*配置数据库驱动*/
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        /*配置连接用户名*/
        dataSourceConfig.setUsername("root");
        /*设置连接密码*/
        dataSourceConfig.setPassword("123456");
        autoGenerator.setDataSource(dataSourceConfig);

        /*包配置*/
        PackageConfig packageConfig = new PackageConfig();
        /*配置父包名*/
        packageConfig.setParent("edu.gzhh.forum");
        /*配置模块名*/
//        packageConfig.setModuleName("mybatis_plus");
        /*配置实体包名*/
        packageConfig.setEntity("entity");
        /*配置mapper包名*/
        packageConfig.setMapper("mapper");
        /*配置service包名*/
        packageConfig.setService("service");
        /*配置controller包名*/
        packageConfig.setController("controller");
        autoGenerator.setPackageInfo(packageConfig);

        /*配置策略(数据库表配置)*/
        StrategyConfig strategyConfig = new StrategyConfig();
        /*指定表名*/
        strategyConfig.setInclude("user");
        /*配置数据库表与实体类名之间映射策略(驼峰)*/
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        /*配置数据表字段与实体类属性名之间映射的策略（驼峰）*/
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        /*配置lombok模式*/
        strategyConfig.setEntityLombokModel(true);
        /*配置rest风格的控制器@restController*/
        strategyConfig.setRestControllerStyle(false);
        /*驼峰转连字符*/
        strategyConfig.setControllerMappingHyphenStyle(true);
        /*配置表前缀*/
        strategyConfig.setTablePrefix(packageConfig.getModuleName()+"_");
        autoGenerator.setStrategy(strategyConfig);

        /*执行代码生成操作*/
        autoGenerator.execute();

    }


}
