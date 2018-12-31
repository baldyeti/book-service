package io.ibex.bookservice.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

@Slf4j
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body,
                                        ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        logRequest(httpRequest, body);
        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, body);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");
        stringBuilder.append("\n ================================================ REQUEST BEGIN ================================================");
        stringBuilder.append("\n URI         :" + request.getURI());
        stringBuilder.append("\n Method      :" + request.getMethod());
        stringBuilder.append("\n Headers     :" + request.getHeaders());
        stringBuilder.append("\n Request body:" + new String(body, "UTF-8"));
        stringBuilder.append("\n ================================================ REQUEST END ================================================");
        stringBuilder.append(" ");

        log.info(stringBuilder.toString());
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");
        stringBuilder.append("\n ================================================ RESPONSE BEGIN ================================================");
        stringBuilder.append("\n Status code  : " + response.getStatusCode());
        stringBuilder.append("\n Status text  : " + response.getStatusText());
        stringBuilder.append("\n Headers      : " + response.getHeaders());
        stringBuilder.append("\n Response body: " + StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
        stringBuilder.append("\n ================================================ RESPONSE END ================================================");
        stringBuilder.append(" ");

        log.info(stringBuilder.toString());
    }
}
