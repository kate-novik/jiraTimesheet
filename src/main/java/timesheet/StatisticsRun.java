package timesheet;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;


public class StatisticsRun {

  public static void main(String[] args) {

    String url = "https://rednavis.atlassian.net/rest/api/2/search?jql=assignee="+ args[2];

    HttpEntity<String> request = new HttpEntity<>(createHeaders(args[0], args[1]));

    RestTemplate restTemplate = new RestTemplate();

//    To print full response
//    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//    String body = response.getBody();
//    System.out.println(body);

    ResponseEntity<JiraResponseWrapper> response = restTemplate.exchange(url, HttpMethod.GET, request, JiraResponseWrapper.class);
    JiraResponseWrapper responseWrapper = response.getBody();

    List<Issue> issues = responseWrapper.getIssues();
    int totalTimeSpent = 0;
    System.out.println(args[0]);
    for (Issue i : issues)
    {
      System.out.print(i.getKey() + " ");
      System.out.print(i.getFields().getSummary() + " ");
      int timeSpent = (i.getFields().getTimespent())/3600;
      totalTimeSpent += timeSpent;
      System.out.println(timeSpent + " h");
    }
    System.out.println("\nTotal timeSpent = " + totalTimeSpent + " h");

  }

  private static HttpHeaders createHeaders(String username, String password){
    return new HttpHeaders() {{
      String auth = username + ":" + password;
      String encodedAuth = Base64.getMimeEncoder().encodeToString(
          auth.getBytes(Charset.forName("US-ASCII")) );
      String authHeader = "Basic " + encodedAuth;
      set( "Authorization", authHeader );
    }};
  }

}
