import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.bat.laoyin.dao.base.BaseDO;

/**
 * <p>
 * mysql 代码生成器演示例子
 * </p>
 *
 * @author jobob
 * @since 2018-09-12
 */
public class MysqlGenerator {

    private static final String projectPath = System.getProperty("user.dir");

    private static final String projectName = "laoyin";

    private static final String outPutDir = "输出目录";
    private static final String dataName = "root";
    private static final String dataPwd = "bat";
    private static final String dataUrl =
        "jdbc:mysql://www.limlim.cn:3306/laoyin?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String parentPackage = "com.bat.laoyin";
    private static final String parentPackagePath = "com/bat/laoyin";
    private static final String mapperName = "dao";
    private static final String serviceName = "service";
    private static final String implName = "service.impl";
    private static final String pojoName = "entity";
    private static final String controllerName = "controller";

    // 多模块路径
    private static final String controllerModuleName =
        projectPath + "/" + projectName + "-web/src/main/java/" + parentPackagePath + "/web";
    private static final String serviceModuleName =
        projectPath + "/" + projectName + "-api/src/main/java/" + parentPackagePath + "/api";
    private static final String serviceImplModuleName =
        projectPath + "/" + projectName + "-service/src/main/java/" + parentPackagePath + "/service";
    private static final String daoModuleName =
        projectPath + "/" + projectName + "-dao/src/main/java/" + parentPackagePath + "/dao";
    private static final String mapperModuleName =
        projectPath + "/" + projectName + "-dao/src/main/java/" + parentPackagePath + "/dao";
    private static final String xmlModuleName = projectPath + "/" + projectName + "-dao/src/main/resources/mapper";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = getGlobalConfig();
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = getDataSourceConfig();
        mpg.setDataSource(dsc);

        // 策略配置（因为需要根据表名生成模块名 放前面）
        StrategyConfig strategy = getStrategyConfig();
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = getPackageConfig(mpg);
        mpg.setPackageInfo(pc);

        // 去掉默认的mybatis的xml生成
        // 不设置的话会生成两份xml
        mpg.setTemplate(getTemplate());

        // 自定义xml生成路径
        InjectionConfig cfg = getInjectionConfig();
        mpg.setCfg(cfg);

        mpg.execute();
    }

    /**
     * 全局配置
     *
     * @return
     */
    public static TemplateConfig getTemplate() {
        return new TemplateConfig().setEntity("templates/entity.java").setXml(null);
    }

    /**
     * 全局配置
     *
     * @return
     */
    public static GlobalConfig getGlobalConfig() {
        return new GlobalConfig().setOutputDir(projectPath).setDateType(DateType.ONLY_DATE).setAuthor("lim")
            .setOpen(false).setBaseResultMap(true).setSwagger2(true).setBaseColumnList(true)
            // 覆盖生成的文件
            .setFileOverride(true).setServiceName("%sService").setEntityName("%sDO");
    }

    /**
     * 数据源配置
     *
     * @return
     */
    public static DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig().setUrl(dataUrl).setDriverName(driverName).setUsername(dataName)
            .setPassword(dataPwd).setDbQuery(new MySqlQuery() {
                /**
                 * 重写父类预留查询自定义字段<br>
                 * 这里查询的 SQL 对应父类 tableFieldsSql 的查询字段，默认不能满足你的需求请重写它<br>
                 * 模板中调用： table.fields 获取所有字段信息， 然后循环字段获取 field.customMap 从 MAP 中获取注入字段如下 NULL 或者 PRIVILEGES
                 */
                @Override
                public String[] fieldCustom() {
                    return new String[] {"NULL"};
                }
            });
    }

    /**
     * 包配置
     *
     * @return
     * @param mpg
     */
    public static PackageConfig getPackageConfig(AutoGenerator mpg) {
        String module = "";
        if (mpg.getStrategy().getInclude().length == 1) {
            module = mpg.getStrategy().getInclude()[0];
            if (mpg.getStrategy().getTablePrefix() != null && mpg.getStrategy().getTablePrefix().length == 1) {
                String tablePrefix = mpg.getStrategy().getTablePrefix()[0];
                module = module.replace(tablePrefix, "");
            }
        } else {
            module = scanner("模块名");
        }
        String finalModule = module;
        return new PackageConfig().setParent(parentPackage)
            // .setModuleName(module)
            .setMapper("dao." + finalModule).setEntity("dao." + finalModule + ".dataobject")
            .setService("api." + finalModule).setController("web." + finalModule)
            .setServiceImpl("service." + finalModule).setCmd("api." + finalModule + ".dto")
            .setQry("api." + finalModule + ".dto").setDto("api." + finalModule + ".dto.data")
            .setCmdExe("service." + finalModule + ".executor").setQryExe("service." + finalModule + ".executor")
            .setConvertor("service." + finalModule + ".convertor")
            // 多模块情况下，如果需要自定义生成路径的话，案例如下，配置之后，上述配置全部失效
            .setPathInfo(new HashMap<String, String>() {
                {
                    put(ConstVal.CONTROLLER_PATH, controllerModuleName + "/" + finalModule);
                    put(ConstVal.ENTITY_PATH, daoModuleName + "/" + finalModule + "/dataobject");
                    put(ConstVal.SERVICE_PATH, serviceModuleName + "/" + finalModule);
                    put(ConstVal.SERVICE_IMPL_PATH, serviceImplModuleName + "/" + finalModule);
                    put(ConstVal.MAPPER_PATH, mapperModuleName + "/" + finalModule);
                    put(ConstVal.CMD_PATH, serviceModuleName + "/" + finalModule + "/dto");
                    put(ConstVal.DTO_PATH, serviceModuleName + "/" + finalModule + "/dto/data");
                    put(ConstVal.QRY_EXE_PATH, serviceImplModuleName + "/" + finalModule + "/executor");
                    put(ConstVal.CMD_EXE_PATH, serviceImplModuleName + "/" + finalModule + "/executor");
                    put(ConstVal.CONVERTOR_PATH, serviceImplModuleName + "/" + finalModule + "/convertor");
                }
            });
    }

    /**
     * 数据库表配置
     *
     * @return
     */
    public static StrategyConfig getStrategyConfig() {
        return new StrategyConfig().setNaming(NamingStrategy.underline_to_camel)
            .setColumnNaming(NamingStrategy.underline_to_camel).setEntityTableFieldAnnotationEnable(true)
            .setEntityLombokModel(true).setChainModel(true).setEntityBooleanColumnRemoveIsPrefix(true)
            .setSuperEntityClass(BaseDO.class)
            .setSuperEntityColumns("id", "created_by", "created_at", "updated_by", "updated_at")
            // .setInclude(scanner("表名，多个英文逗号分割").split(","))
            .setInclude(scanner("表名").split(","))
            // 默认生成全部
            // .setExclude(null)
            // .setTablePrefix("t_").
            .setControllerMappingHyphenStyle(true);
    }

    /**
     * 自定义xml文件生成路径 这里注意会生成两个xml，一个是在你指定的下面，一个是在mapper包下的xml 因为源码中的判断，判断的是tableInfo和pathInfo的xml属性是否为null，这两个类都是默认生成属性的
     * 且对if (null != injectionConfig)自定义生成的判断在默认的前面，所以会生成两遍。 具体可见AbstractTemplateEngine batchOutput()的方法
     *
     * @return
     */
    public static InjectionConfig getInjectionConfig() {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {}
        };
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/" + projectName + "-dao/src/main/resources/mapper/" + tableInfo.getEntityName()
                    + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
