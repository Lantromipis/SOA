package se.ifmo.ru.model.web;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlType
@XmlRootElement(name = "Request")
public class DonationRequestDto {
    private Double amount;
}
