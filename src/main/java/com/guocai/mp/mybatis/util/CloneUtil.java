package com.guocai.mp.mybatis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * java类简单作用描述
 *
 * @ClassName: CloneUtil
 * @Package: com.guocai.mp.mybatis.util
 * @Description: 自定义拷贝对象属性
 * @Author: Sun GuoCai
 * @Version: 1.0
 * @Create: 2019-05-29 9:16
 */
public class CloneUtil {
    private static final Logger logger = LoggerFactory.getLogger(CloneUtil.class);


    @SuppressWarnings("unchecked")
    public static <T extends Serializable> List<T> deepCopy(List<T> src) {
        if (logger.isDebugEnabled()) {
            logger.debug("method deepCopy List.");
        }
        return (List<T>) readObject(src);
    }


    @SuppressWarnings("unchecked")
    public static <T extends Serializable> Set<T> deepCopy(Set<T> src) {
        if (logger.isDebugEnabled()) {
            logger.debug("method deepCopy Set.");
        }
        return (Set<T>) readObject(src);
    }

    //    //
    @SuppressWarnings("unchecked")
    public static <K extends Serializable, V extends Serializable> Map<K, V> deepCopy(Map<K, V> src) {
        if (logger.isDebugEnabled()) {
            logger.debug("method deepCopy Map.");
        }
        return (Map<K, V>) readObject(src);
    }


    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepCopy(T src) {
        if (logger.isDebugEnabled()) {
            logger.debug("method deepCopy.");
        }
        return (T) readObject(src);
    }
    @SuppressWarnings("unchecked")
    public static <T> T deepCopyObject(T src) {
        if (logger.isDebugEnabled()) {
            logger.debug("method deepCopyObject.");
        }
        return (T) readObject(src);
    }



    // 通过序列化方法实现深拷贝
    public static Object readObject(Object src) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream inStream = new ObjectInputStream(byteIn);
            return inStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("对象深拷贝时出现错误。", e);
        }
        return null;
    }

}
