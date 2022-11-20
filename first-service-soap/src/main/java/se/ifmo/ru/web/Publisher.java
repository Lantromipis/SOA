package se.ifmo.ru.web;

import jakarta.xml.ws.Endpoint;
import se.ifmo.ru.service.impl.FlatServiceImpl;

public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish(
                "http://localhost:8090/first-service/FlatService",
                new FlatServiceImpl()
        );
    }
}
