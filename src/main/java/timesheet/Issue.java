package timesheet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {

  @Getter
  private String key;

  @Getter
  private Fields fields;



}
