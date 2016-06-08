package uk.ac.ebi.pride.spectracluster.spectraclustergui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import uk.ac.ebi.pride.spectracluster.spectraclustergui.gui.SpectraClusterGuiApplication;
import uk.ac.ebi.pride.spectracluster.spectraclustergui.util.UserSettings;


/**
 * Created by jg on 08.06.16.
 */
public class DlgClusteringSettingsController extends AbstractSpectraClusterController {
    @FXML private TextField precursorToleranceField;
    @FXML private TextField fragmentToleranceField;
    @FXML private TextField targetThresholdField;
    @FXML private TextField startThresholdField;
    @FXML private TextField clusteringRoundsField;
    @FXML private TextField minComparisonsField;

    @FXML
    protected void handleNextAction(ActionEvent event) {
        try {
            saveUserSelection();

            SpectraClusterGuiApplication guiApplication = new SpectraClusterGuiApplication();
            guiApplication.showDialog(getNextDialog(), primaryStage);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.initOwner(primaryStage);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        }
    }

    @FXML
    protected void handlePrevAction(ActionEvent event) throws Exception {
        try {
            saveUserSelection();

            SpectraClusterGuiApplication guiApplication = new SpectraClusterGuiApplication();
            // TODO: adapt previous dialog based on clustering process
            guiApplication.showDialog(getPrevDialog(), primaryStage);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.initOwner(primaryStage);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        }
    }

    private void saveUserSelection() throws Exception {
        UserSettings.setPrecursorTolerance(new Float(precursorToleranceField.getText()));
        UserSettings.setFragmentTolerance(new Float(fragmentToleranceField.getText()));
        UserSettings.setThreshold(new Float(targetThresholdField.getText()));
        UserSettings.setStartThreshold(new Float(startThresholdField.getText()));
        UserSettings.setClusteringRounds(new Integer(clusteringRoundsField.getText()));
        UserSettings.setMinComparisons(new Integer(minComparisonsField.getText() ));
    }

    @FXML
    public void initialize() throws Exception {
        precursorToleranceField.setText(String.valueOf(UserSettings.getPrecursorTolerance()));
        fragmentToleranceField.setText(String.valueOf(UserSettings.getFragmentTolerance()));
        targetThresholdField.setText(String.valueOf(UserSettings.getThreshold()));
        startThresholdField.setText(String.valueOf(UserSettings.getStartThreshold() ));
        clusteringRoundsField.setText(String.valueOf(UserSettings.getClusteringRounds() ));
        minComparisonsField.setText(String.valueOf(UserSettings.getMinComparisons() ));
    }
}
