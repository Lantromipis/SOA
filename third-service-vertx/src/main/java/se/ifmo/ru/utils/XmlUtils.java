package se.ifmo.ru.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlUtils {
    private static XmlMapper xmlMapper = new XmlMapper();

    public static String objectToXmlString(Object o){
        try {
            return xmlMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
