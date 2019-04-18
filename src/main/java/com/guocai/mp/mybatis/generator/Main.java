package com.guocai.mp.mybatis.generator;

import com.guocai.mp.mybatis.entity.Table;
import com.guocai.mp.mybatis.mapper.TableMapper;
import com.guocai.mp.mybatis.util.Constants;
import com.guocai.mp.mybatis.util.GeneratorUtil;
import com.guocai.mp.mybatis.util.MapperMethodSwitch;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.velocity.VelocityContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * java类简单作用描述
 *
 * @ClassName: Main
 * @Package: com.guocai.mp.mybatis.generator
 * @Description: 生成文件类
 * @Author: Sun GuoCai
 * @Version: 1.0
 * @Create: 2019-04-16-14:36
 */
public class Main {

    public static void main(String[] args){
        generator("org");
    }

    private static void generator(String tableName) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        SqlSession session = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(inputStream);
            System.out.println(sqlSessionFactory);
            session = sqlSessionFactory.openSession();
            TableMapper mapper = session.getMapper(TableMapper.class);
            System.out.println("mapper = " + mapper);
            Map<String,Object> map = new HashMap<>();

            map.put("tablePrefix", "");
            map.put("tableName",tableName);
            List<Table> tables = mapper.getTablesByPrefix(map);
            for (Table table : tables) {
                table.setTablePrefix("");
                table.setTableName(table.getTableName());
                System.out.println("table--->" + table);
            }

            generateEntity(tables);
            generateController(tables);
            generateService(tables);
            generateServiceImpl(tables);
            generateDao(tables);
            generateJavaAccess(tables);
            generateDaoMy(tables);
            generateQryReq(tables);
            generateQryRsp(tables);
            generateEdtReq(tables);
            generateXMLMapper(tables);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
    }

    /**
     * 生成Java实体
     * @param tables
     *
     */
    private static void generateEntity(List<Table> tables) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, "EntityTemplate.vm");
            String path = basePath  + t.getEntityPackage().replace(".",  Constants.FILE_SEPERATOR);
            String fileName = t.getEntityName()+ "PO" + ".java";
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }

    /**
     * 生成QryReq实体
     * @param tables
     *
     */
    private static void generateQryReq(List<Table> tables) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, "QryReqTemplate.vm");
            String path = basePath  + t.getAllParameterPackage().replace(".",  Constants.FILE_SEPERATOR);
            String fileName = t.getEntityName()+ "QryReq" + ".java";
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }

    /**
     * 生成QryRsp实体
     * @param tables
     *
     */
    private static void generateQryRsp(List<Table> tables) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, "QryRspTemplate.vm");
            String path = basePath  + t.getAllParameterPackage().replace(".",  Constants.FILE_SEPERATOR);
            String fileName = t.getEntityName()+ "QryRsp" + ".java";
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }

    /**
     * 生成EdtReq实体
     * @param tables
     *
     */
    private static void generateEdtReq(List<Table> tables) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, "EdtReqTemplate.vm");
            String path = basePath  + t.getAllParameterPackage().replace(".",  Constants.FILE_SEPERATOR);
            String fileName = t.getEntityName()+ "EdtReq" + ".java";
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }

    /**
     * 生成Controller
     * @param tables
     *
     */
    private static void generateController(List<Table> tables) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, "ControllerTemplate.vm");
            String path = basePath +  t.getControllerPackage().replace(".",  Constants.FILE_SEPERATOR);
            String fileName = t.getEntityName() + "Controller" + ".java";
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }

    /**
     * 生成Service
     * @param tables
     *
     */
    private static void generateService(List<Table> tables) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, "ServiceTemplate.vm");
            String path = basePath +  t.getServicePackage().replace(".",  Constants.FILE_SEPERATOR);
            String fileName = t.getEntityName() + "Service" + ".java";
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }
    /**
     * 生成Service Implements
     * @param tables
     *
     */
    private static void generateServiceImpl(List<Table> tables) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, "ServiceImplTemplate.vm");
            String path = basePath +  t.getServiceImplPackage().replace(".",  Constants.FILE_SEPERATOR);
            String fileName = t.getEntityName() + "ServiceImpl" + ".java";
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }

    /**
     * 生成Dao
     * @param tables
     *
     */
    private static void generateDao(List<Table> tables) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, "DaoTemplate.vm");
            String path = basePath +  t.getDaoPackage().replace(".",  Constants.FILE_SEPERATOR);
            String fileName = t.getEntityName() + "Dao" + ".java";
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }
    /**
     * 生成Access
     * @param tables
     *
     */
    private static void generateJavaAccess(List<Table> tables) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, "MapperTemplate.vm");
            String path = basePath +  t.getJavaMapperPackage().replace(".",  Constants.FILE_SEPERATOR);
            String fileName = t.getEntityName()+ "Access" + ".java";
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }

    /**
     * 生成DaoMy
     * @param tables
     *
     */
    private static void generateDaoMy(List<Table> tables) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, "DaoMyTemplate.vm");
            String path = basePath +  t.getDaoMyPackage().replace(".",  Constants.FILE_SEPERATOR);
            String fileName = t.getEntityName()+ "DaoMy" + ".java";
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }


    /**
     * 生成Mybatis XML Mapper
     * @param tables
     *
     */
    private static void generateXMLMapper(List<Table> tables) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.XML_MAPPER_BASE + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            velocityCtx.put("methodSwitch",  getMethodSwitch());
            String entityContent = GeneratorUtil.generate(velocityCtx, "XmlMapperTemplate.vm");
            String path = basePath +  t.getXmlMapperPackage().replace(".",  Constants.FILE_SEPERATOR);
            String fileName = t.getEntityName() + "Mapper" + ".xml";
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }

    private static MapperMethodSwitch getMethodSwitch() {
        return new MapperMethodSwitch();
    }


}
