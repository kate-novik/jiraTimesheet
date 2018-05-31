package timesheet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {

  @Getter
  private String summary;

  @Getter
  private int timespent;

}
