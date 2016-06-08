package uk.ac.ebi.pride.spectracluster.spectraclustergui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import uk.ac.ebi.pride.spectracluster.spectraclustergui.gui.SpectraClusterGuiApplication;
import uk.ac.ebi.pride.spectracluster.spectraclustergui.util.UserSettings;

/**
 * Dialog to select the type of clustering performed
 *
 * Created by jg on 08.06.16.
 */
public class DlgSelectActionController extends AbstractSpectraClusterController {
    @FXML private RadioButton radioClusterNew;
    @FXML private RadioButton radioClusterIncremental;
    @FXML private RadioButton radioContinueClustering;

    @FXML
    protected void handleNextAction(ActionEvent event) throws Exception {
        SpectraClusterGuiApplication guiApplication = new SpectraClusterGuiApplication();

        if (radioClusterNew.isSelected()) {
            UserSettings.setClusteringType(UserSettings.CLUSTERING_TYPE.NEW_DATASET);

            guiApplication.showDialog(getNextDialog(), primaryStage);
        }
        if (radioClusterIncremental.isSelected()) {
            UserSettings.setClusteringType(UserSettings.CLUSTERING_TYPE.INCREMENTAL);

            System.out.println("Clustering incremental");
        }
        if (radioContinueClustering.isSelected()) {
            UserSettings.setClusteringType(UserSettings.CLUSTERING_TYPE.BINARY);

            System.out.println("Continuing clustering");
        }
    }

    @FXML
    private void initialize() {
        // load the radio properties
        radioClusterNew.setSelected(UserSettings.getClusteringType() == UserSettings.CLUSTERING_TYPE.NEW_DATASET);
        radioClusterIncremental.setSelected(UserSettings.getClusteringType() == UserSettings.CLUSTERING_TYPE.INCREMENTAL);
        radioContinueClustering.setSelected(UserSettings.getClusteringType() == UserSettings.CLUSTERING_TYPE.BINARY);
    }
}
