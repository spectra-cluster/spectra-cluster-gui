package uk.ac.ebi.pride.spectracluster.spectraclustergui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uk.ac.ebi.pride.spectracluster.spectraclustergui.controller.AbstractSpectraClusterController;
import uk.ac.ebi.pride.spectracluster.spectraclustergui.util.Constants;
import uk.ac.ebi.pride.spectracluster.spectraclustergui.util.UserSettings;

/**
 * Created by jg on 08.06.16.
 */
public class SpectraClusterGuiApplication extends Application {
    public enum DIALOG {
        UNKNOWN,
        SELECT_ACTION,
        SELECT_MGF_FILES,
        SET_CLUSTERING_PARAMS,
        SET_OUTPUT_PARAMS
    }

    /**
     * Workflows define the order in which dialogs are
     * presented to the user.
     */
    public static DIALOG[] CLUSTER_WORKFLOW = {
            DIALOG.SELECT_ACTION,
            DIALOG.SELECT_MGF_FILES,
            DIALOG.SET_CLUSTERING_PARAMS,
            DIALOG.SET_OUTPUT_PARAMS };

    public static DIALOG[] getWorkflow(UserSettings.CLUSTERING_TYPE clusteringType) {
        switch (clusteringType) {
            case NEW_DATASET:
                return CLUSTER_WORKFLOW;
        }

        return new DIALOG[] {};
    }

    public static int getWorkflowIndex(DIALOG dialog) {
        DIALOG[] currentWorkflow = getWorkflow(UserSettings.getClusteringType());

        for (int i = 0; i < currentWorkflow.length; i++) {
            if (dialog == currentWorkflow[i]) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMaximized(false);
        primaryStage.initStyle(StageStyle.UTILITY);

        showSelectAction(primaryStage);
    }

    /**
     * Shows the specified dialog on the passed stage.
     * @param dialog The dialog to show.
     * @param primaryStage The stage used for display.
     * @throws Exception
     */
    public void showDialog(DIALOG dialog, Stage primaryStage) throws Exception {
        switch(dialog) {
            case SELECT_ACTION:
                System.out.println("Showing select action dlg");
                showSelectAction(primaryStage);
                break;
            case SELECT_MGF_FILES:
                System.out.println("Showing mgf selection dlg");
                showSelectMgfFiles(primaryStage);
                break;
            case SET_CLUSTERING_PARAMS:
                showSetClusterSettings(primaryStage);
                break;
            case SET_OUTPUT_PARAMS:
                showSetOutputSettings(primaryStage);
                break;
        }
    }

    public void showSelectMgfFiles(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(Constants.FXML_DLG_SELECT_MGF));
        Parent root = loader.load();

        // set the stage in the used controller
        AbstractSpectraClusterController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        controller.setWorkflowIndex(getWorkflowIndex(DIALOG.SELECT_MGF_FILES));

        // create the application window
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("spectra-cluster-gui - Select MGF files");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showSetOutputSettings(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(Constants.FXML_DLG_OUTPUT_SETTINGS));
        Parent root = loader.load();

        // set the stage in the used controller
        AbstractSpectraClusterController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        controller.setWorkflowIndex(getWorkflowIndex(DIALOG.SET_OUTPUT_PARAMS));

        // create the application window
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("spectra-cluster-gui - Set output setttings");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showSetClusterSettings(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(Constants.FXML_DLG_CLUSTER_SETTINGS));
        Parent root = loader.load();

        // set the stage in the used controller
        AbstractSpectraClusterController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        controller.setWorkflowIndex(getWorkflowIndex(DIALOG.SET_CLUSTERING_PARAMS));

        // create the application window
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("spectra-cluster-gui - Clustering settings");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Displays the "select action" dialog.
     * @param primaryStage The stage used for display.
     * @throws Exception
     */
    public void showSelectAction(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(Constants.FXML_DLG_SELECT_ACTION));
        Parent root = loader.load();

        // set the stage in the used controller
        AbstractSpectraClusterController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        controller.setWorkflowIndex(getWorkflowIndex(DIALOG.SELECT_ACTION));

        // create the application window
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("spectra-cluster-gui");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
