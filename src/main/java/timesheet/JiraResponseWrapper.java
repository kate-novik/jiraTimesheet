package timesheet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraResponseWrapper {

  @Getter
  private String username;
  @Getter
  private List<Issue> issues;
}
