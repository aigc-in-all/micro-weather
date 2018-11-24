package com.heqingbao.spring.cloud.weather.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;

public class XmlBuilder {

    /**
     * 将XML转为指定的POJO
     *
     * @param clazz
     * @param xml
     * @return
     * @throws Exception
     */
    public static Object xmlToObject(Class<?> clazz, String xml) throws Exception {
        Object xmlObj = null;
        JAXBContext context = JAXBContext.newInstance(clazz);
        // XML转为对象的接口
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Reader reader = new StringReader(xml);
        xmlObj = unmarshaller.unmarshal(reader);

        if (reader != null) {
            reader.close();
        }
        return xmlObj;
    }
}
