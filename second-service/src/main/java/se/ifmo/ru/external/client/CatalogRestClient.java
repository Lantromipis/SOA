package se.ifmo.ru.external.client;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import se.ifmo.ru.external.SSLValidation;
import se.ifmo.ru.external.model.RestClientFlat;
import se.ifmo.ru.service.model.Flat;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.util.List;
import java.util.Objects;

@Component
public class CatalogRestClient implements InitializingBean {
    private RestTemplate restTemplate;

    @Value("${custom.rest-client.catalog.service-url}")
    private String serviceUrl;

    @Value("${custom.rest-client.catalog.trust-store}")
    private String trustStore;

    @Value("${custom.rest-client.catalog.trust-store-password}")
    private String trustStorePassword;

    @Override
    public void afterPropertiesSet() throws Exception {
        File file = new File(trustStore);
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(file, trustStorePassword.toCharArray())
                .build();
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

        HttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(socketFactory)
                .build();

        restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
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
