package com.ad.tools;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class ClientTest {

    private static final String DEFAULT_URI = "http://localhost:9998/";

    private final ClientConfig clientConfig = new DefaultClientConfig();
    private final Client client = Client.create(clientConfig);
    private final URI uri = UriBuilder.fromUri(DEFAULT_URI).build();
    private final WebResource service = client.resource(uri);

    public static void main(String[] args) throws IOException {
        ClientTest.test();
    }

    public static void test()
    {
        ClientTest clientTest = new ClientTest();
        clientTest.testADService();
        clientTest.testReportService();
        clientTest.testStateService();
    }

    private void testADService() {
        String adJSONResponse = service.path("ad").path("json")
                .queryParam("platform", "platform")
                .queryParam("app", "app")
                .queryParam("pub_id", "1")
                .queryParam("version", "1")
                .queryParam("width", "0")
                .queryParam("height", "0")
                .accept(MediaType.APPLICATION_JSON)
                .get(String.class);
        System.out.println("adJSONResponse=" + adJSONResponse);

        String adHTMLResponse = service.path("ad").path("html")
                .accept(MediaType.TEXT_HTML)
                .get(String.class);
        System.out.println("adHTMLResponse=" + adHTMLResponse);
    }

    private void testReportService() {
        String reportResponse = service.path("reporting").path("campaign")
                .queryParam("pub_id", "1")
                .queryParam("campaign_id", "1")
                .accept(MediaType.APPLICATION_JSON)
                .get(String.class);

        System.out.println("reportResponse=" + reportResponse);
    }

    private void testStateService() {
        String statImpressionResponse = service.path("stat").path("impression")
                .queryParam("platform", "platform")
                .queryParam("app", "app")
                .queryParam("pub_id", "1")
                .queryParam("owner_app_id", "1")
                .queryParam("ads_offer_id", "1")
                .queryParam("ads_banner_id", "1")
                .queryParam("version", "version")
                .queryParam("network_id", "1")
                .queryParam("html_type", "html_type")
                .queryParam("position", "position")
                .queryParam("country", "country")
                .queryParam("unlocker", "true")
                .get(String.class);
        System.out.println("statImpressionResponse=" + statImpressionResponse);

        String statClickResponse = service.path("stat").path("click")
                .queryParam("platform", "platform")
                .queryParam("app", "app")
                .queryParam("pub_id", "1")
                .queryParam("owner_app_id", "1")
                .queryParam("ads_offer_id", "1")
                .queryParam("ads_banner_id", "1")
                .queryParam("version", "version")
                .queryParam("network_id", "1")
                .queryParam("html_type", "html_type")
                .queryParam("position", "position")
                .queryParam("country", "country")
                .queryParam("unlocker", "true")
                .get(String.class);
        System.out.println("statClickResponse=" + statClickResponse);

        String statDownloadResponse = service.path("stat").path("download")
                .queryParam("platform", "platform")
                .queryParam("app", "app")
                .queryParam("pub_id", "1")
                .queryParam("owner_app_id", "1")
                .queryParam("ads_offer_id", "1")
                .queryParam("ads_banner_id", "1")
                .queryParam("version", "version")
                .queryParam("network_id", "1")
                .queryParam("html_type", "html_type")
                .queryParam("position", "position")
                .queryParam("country", "country")
                .queryParam("unlocker", "true")
                .get(String.class);
        System.out.println("statDownloadResponse=" + statDownloadResponse);
    }
}
