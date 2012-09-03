package com.ad.api;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class ClientTest {
    private static final String ILLEGAL_ARGUMENT = "Use: 1) httpServerAddress (127.0.0.1:1111)";
    private final WebResource service;

    public ClientTest(String URI) {
        URI uri = UriBuilder.fromUri(URI).build();
        ClientConfig clientConfig = new DefaultClientConfig();
        Client client = Client.create(clientConfig);
        service = client.resource(uri);
    }

    public static void main(String[] args) throws IOException {
        if (args.length >= 1)
        {
            ClientTest.test(Constants.HTTP_URI_PREFIX + args[0]);
        }
        else
        {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT);
        }

    }

    public static void test(String uri)
    {
        ClientTest clientTest = new ClientTest(uri);
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
