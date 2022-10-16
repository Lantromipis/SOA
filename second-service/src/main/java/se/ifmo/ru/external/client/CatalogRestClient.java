package se.ifmo.ru.external.client;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import se.ifmo.ru.external.SSLValidation;
import se.ifmo.ru.external.model.RestClientFlat;
import se.ifmo.ru.service.model.Flat;

import java.util.List;
import java.util.Objects;

@Component
public class CatalogRestClient implements InitializingBean {
    private RestTemplate restTemplate;

    @Value("${custom.rest-client.catalog-service-url}")
    private String serviceUrl;

    @Override
    public void afterPropertiesSet() throws Exception {
        SSLValidation.turnOffSslChecking();
        restTemplate = new RestTemplate();
    }

    public RestClientFlat getFlatById(long id) {
        String url = serviceUrl + "/catalog/flats/" + id;
        try {
            ResponseEntity<RestClientFlat> response = restTemplate.getForEntity(url, RestClientFlat.class);
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e){
            if(Objects.equals(e.getStatusCode(), HttpStatus.NOT_FOUND)){
                return null;
            }
            throw e;
        }
    }

    public List<RestClientFlat> getAllFlats() {
        String url = serviceUrl + "/catalog/flats";
        ResponseEntity<RestClientFlat[]> response =
                restTemplate.getForEntity(
                        url,
                        RestClientFlat[].class);

        return List.of(response.getBody());
    }
}
