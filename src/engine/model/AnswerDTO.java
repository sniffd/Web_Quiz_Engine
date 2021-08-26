package engine.model;

import java.util.ArrayList;
import java.util.List;

public class AnswerDTO {

  List<Integer> answer;

  public List<Integer> getAnswer() {
    return answer == null ? new ArrayList<>() : answer;
  }

  public void setAnswer(List<Integer> answer) {
    this.answer = answer;
  }
}
