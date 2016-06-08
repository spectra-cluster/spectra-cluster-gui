package uk.ac.ebi.pride.spectracluster.spectraclustergui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import uk.ac.ebi.pride.spectracluster.spectraclustergui.gui.SpectraClusterGuiApplication;
import uk.ac.ebi.pride.spectracluster.spectraclustergui.util.UserSettings;

import java.io.File;


/**
 * Created by jg on 08.06.16.
 */
public class DlgOutputSettingsController extends AbstractSpectraClusterController {
    @FXML private TextField resultFileField;
    @FXML private TextField parallelJobsField;
    @FXML private TextField temporaryDirectoryField;
    @FXML private CheckBox keepClusteringResultsCheckbox;

    @FXML protected void handleBrowseTemporayDirectoryAction(ActionEvent event) throws Exception {
        // create the file chooser
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select temporary directory");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File directory = directoryChooser.showDialog(primaryStage);

        if (directory != null) {
            temporaryDirectoryField.setText(directory.getAbsolutePath());
        }
    }

    @FXML protected void handleActionBrowseResultFile(ActionEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save result");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            resultFileField.setText(file.getAbsolutePath());
        }
    }

    @FXML protected void handleStartAction(ActionEvent event) throws Exception {
        throw new IllegalStateException("Not implemented yet.");
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
        UserSettings.setOutputName(resultFileField.getText());
        UserSettings.setNumberJobs(new Integer(parallelJobsField.getText()));
        UserSettings.setTempDir(temporaryDirectoryField.getText());
        UserSettings.setSaveBinary(keepClusteringResultsCheckbox.isSelected());
    }

    @FXML
    public void initialize() throws Exception {
        resultFileField.setText(UserSettings.getOutputName());
        parallelJobsField.setText(String.valueOf(UserSettings.getNumberJobs()));
        temporaryDirectoryField.setText(UserSettings.getTempDir());
        keepClusteringResultsCheckbox.setSelected(UserSettings.isSaveBinary());
    }
}
