package se.ifmo.ru.web.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlType
@XmlRootElement(name = "Request")
public class DonationRequestDto {
    private Double amount;
}
