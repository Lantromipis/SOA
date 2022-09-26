package se.ifmo.ru.web.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlType
@JacksonXmlRootElement(localName = "Error")
public class Error {
    private String code;
    private String message;
    private String date;
}
