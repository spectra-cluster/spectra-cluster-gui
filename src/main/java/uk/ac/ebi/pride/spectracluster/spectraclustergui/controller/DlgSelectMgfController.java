package uk.ac.ebi.pride.spectracluster.spectraclustergui.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import uk.ac.ebi.pride.spectracluster.spectraclustergui.gui.SpectraClusterGuiApplication;
import uk.ac.ebi.pride.spectracluster.spectraclustergui.util.UserSettings;

import java.io.File;
import java.util.List;


/**
 * Created by jg on 08.06.16.
 */
public class DlgSelectMgfController extends AbstractSpectraClusterController {
    @FXML private ListView<String> fileList;

    @FXML
    public void handleAddFileAction(ActionEvent event) {
        // create the file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select MGF files");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MGF (*.mgf)", "*.mgf"),
                new FileChooser.ExtensionFilter("All files (*.*)", "*.*")
        );

        // get the selected files
        List<File> selectedMgfFiles = fileChooser.showOpenMultipleDialog(primaryStage);

        // if the user selected files, populate the list
        if (selectedMgfFiles != null) {
            ObservableList<String> items = fileList.getItems();

            for (File mgfFile : selectedMgfFiles) {
                items.add(mgfFile.getAbsolutePath());
            }
        }
    }

    @FXML
    public void handleRemoveFileAction(ActionEvent event) {
        ObservableList<String> items = fileList.getItems();
        ObservableList<String> selectedItems = fileList.getSelectionModel().getSelectedItems();

        items.removeAll(selectedItems);
    }

    @FXML
    public void handleAddDirectoryAction(ActionEvent event) {
        // create the file chooser
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select directory (all MGF files will be added)");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File directory = directoryChooser.showDialog(primaryStage);

        if (directory != null) {
            ObservableList<String> listItems = fileList.getItems();

            File[] mgfFiles = directory.listFiles(new java.io.FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().toLowerCase().endsWith(".mgf");
                }
            });

            for (File mgfFile : mgfFiles) {
                listItems.add(mgfFile.getAbsolutePath());
            }
        }
    }

    @FXML
    protected void handleNextAction(ActionEvent event) throws Exception {
        int items = saveUserSelection();

        if (items < 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No MGF files selected for clustering");
            alert.initOwner(primaryStage);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();

            return;
        }

        SpectraClusterGuiApplication guiApplication = new SpectraClusterGuiApplication();
        guiApplication.showDialog(getNextDialog(), primaryStage);
    }

    @FXML
    protected void handlePrevAction(ActionEvent event) throws Exception {
        saveUserSelection();

        SpectraClusterGuiApplication guiApplication = new SpectraClusterGuiApplication();
        guiApplication.showDialog(getPrevDialog(), primaryStage);
    }

    private int saveUserSelection() {
        // save the list
        ObservableList<String> listItems = fileList.getItems();

        UserSettings.getMgfFiles().clear();
        UserSettings.getMgfFiles().addAll(listItems);

        return listItems.size();
    }

    @FXML
    public void initialize() throws Exception {
        ObservableList<String> listItems = fileList.getItems();

        for (String filename : UserSettings.getMgfFiles()) {
            listItems.add(filename);
        }
    }
}
