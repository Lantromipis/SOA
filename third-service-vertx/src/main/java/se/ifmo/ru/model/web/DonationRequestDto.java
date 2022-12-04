package se.ifmo.ru.model.web;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlType
@JacksonXmlRootElement(localName = "Request")
public class DonationRequestDto {
    private Double amount;
}
