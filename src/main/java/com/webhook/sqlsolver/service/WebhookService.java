package com.webhook.sqlsolver.service;

import com.webhook.sqlsolver.model.SqlSolutionRequest;
import com.webhook.sqlsolver.model.WebhookRequest;
import com.webhook.sqlsolver.model.WebhookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebhookService {

    @Autowired
    private SqlSolverService sqlSolverService;

    @Value("${app.user.name:John Doe}")
    private String userName;

    @Value("${app.user.regNo:REG12347}")
    private String userRegNo;

    @Value("${app.user.email:john@example.com}")
    private String userEmail;

    private final WebClient webClient;

    private static final String WEBHOOK_GENERATE_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    private static final String WEBHOOK_TEST_URL = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

    public WebhookService() {
        this.webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public void generateWebhookAndSolveProblem() {
        try {
            System.out.println("Starting webhook generation process...");
            
            // Step 1: Generate webhook
            WebhookRequest request = new WebhookRequest(userName, userRegNo, userEmail);
            WebhookResponse response = generateWebhook(request);
            
            if (response == null || response.getWebhook() == null || response.getAccessToken() == null) {
                System.err.println("Failed to generate webhook");
                return;
            }
            
            System.out.println("Webhook generated successfully!");
            System.out.println("Webhook URL: " + response.getWebhook());
            System.out.println("Access Token: " + response.getAccessToken());
            
            // Step 2: Solve SQL problem based on registration number
            String sqlQuery = sqlSolverService.solveSqlProblem(userRegNo);
            System.out.println("Generated SQL Query: " + sqlQuery);
            
            // Step 3: Submit solution
            submitSolution(response.getWebhook(), response.getAccessToken(), sqlQuery);
            
        } catch (Exception e) {
            System.err.println("Error in webhook process: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private WebhookResponse generateWebhook(WebhookRequest request) {
        try {
            return webClient.post()
                    .uri(WEBHOOK_GENERATE_URL)
                    .body(Mono.just(request), WebhookRequest.class)
                    .retrieve()
                    .bodyToMono(WebhookResponse.class)
                    .block();
        } catch (Exception e) {
            System.err.println("Error generating webhook: " + e.getMessage());
            return null;
        }
    }

    private void submitSolution(String webhookUrl, String accessToken, String sqlQuery) {
        try {
            SqlSolutionRequest solutionRequest = new SqlSolutionRequest(sqlQuery);
            
            String response = webClient.post()
                    .uri(WEBHOOK_TEST_URL)
                    .header(HttpHeaders.AUTHORIZATION, accessToken)
                    .body(Mono.just(solutionRequest), SqlSolutionRequest.class)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            
            System.out.println("Solution submitted successfully!");
            System.out.println("Response: " + response);
            
        } catch (Exception e) {
            System.err.println("Error submitting solution: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
