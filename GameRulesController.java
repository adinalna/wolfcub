package warewolfculb;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GameRulesController implements Initializable {
    @FXML
    private VBox rulesContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add rules to the rulesContainer
        Label rule1 = new Label("Rule 1: Description of Rule 1");
        Label rule2 = new Label("Rule 2: Description of Rule 2");
        Label rule3 = new Label("Rule 3: Description of Rule 3");
        // Add more rules as needed

        rulesContainer.getChildren().addAll(rule1, rule2, rule3);
    }
}


