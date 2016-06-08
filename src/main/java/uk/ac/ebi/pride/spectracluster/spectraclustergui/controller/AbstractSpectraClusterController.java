package uk.ac.ebi.pride.spectracluster.spectraclustergui.controller;

import javafx.stage.Stage;
import uk.ac.ebi.pride.spectracluster.spectraclustergui.gui.SpectraClusterGuiApplication;
import uk.ac.ebi.pride.spectracluster.spectraclustergui.util.UserSettings;

/**
 * Created by jg on 08.06.16.
 */
public class AbstractSpectraClusterController {
    /**
     * The applications primary stage.
     */
    protected Stage primaryStage;
    /**
     * The index within the current workflow
     */
    protected int workflowIndex;

    protected SpectraClusterGuiApplication.DIALOG getPrevDialog() {
        return getWorkflowDialog(workflowIndex - 1);
    }

    protected SpectraClusterGuiApplication.DIALOG getNextDialog() {
        return getWorkflowDialog(workflowIndex + 1);
    }

    /**
     * Returns the dialog with the specified index from the currently
     * set workflow.
     * @param index
     * @return
     */
    private SpectraClusterGuiApplication.DIALOG getWorkflowDialog(int index) {
        SpectraClusterGuiApplication.DIALOG[] currentWorkflow =
                SpectraClusterGuiApplication.getWorkflow(UserSettings.getClusteringType());

        // make sure the index is valid
        if (index < 0 || index >= currentWorkflow.length) {
            return SpectraClusterGuiApplication.DIALOG.UNKNOWN;
        }

        return currentWorkflow[index];
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public int getWorkflowIndex() {
        return workflowIndex;
    }

    public void setWorkflowIndex(int workflowIndex) {
        this.workflowIndex = workflowIndex;
    }
}
