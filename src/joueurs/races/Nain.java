package joueurs.races;

import joueurs.*;

public class Nain implements Race {

  public String presentation() {
    return "yolo";
  }

  public int bonus(String c) {
    if (c == "force") {
      return 6;
    } else {
      return 0;
    }
  }
}
