package clients;

import domain.Listing;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ListingsClient {
    private String url; //ie: http://localhost:8091
    private RestTemplate restTemplate;

    public ListingsClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public List<Listing> getListings() {
        ResponseEntity<List<Listing>> response = restTemplate.exchange(
                url +"/listings/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Listing>>() {
                });
        return response.getBody();
    }

}
