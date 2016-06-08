package uk.ac.ebi.pride.spectracluster.spectraclustergui.util;

import java.util.ArrayList;
import java.util.List;

/**
 * This (static only) class contains all settings
 * made by the user when using the application. It
 * is also used to load the respective default values
 * for the application.
 *
 * Created by jg on 08.06.16.
 */
public class UserSettings {
    /**
     * This class must never be instantiated.
     */
    private UserSettings() {

    }

    public enum CLUSTERING_TYPE {
        NEW_DATASET,
        INCREMENTAL,
        BINARY
    }

    /**
     * Default values
     */
    public static final CLUSTERING_TYPE DEFAULT_CLUSTERING_TYPE = CLUSTERING_TYPE.NEW_DATASET;
    public static final float DEFAULT_PRECURSOR_TOLERANCE = 2.0F;
    public static final float DEFAULT_FRAGMENT_TOLERANCE = 0.5F;
    public static final float DEFAULT_START_THRESHOLD = 0.999F;
    public static final float DEFAULT_THRESHOLD = 0.99F;
    public static final int DEFAULT_CLUSTERING_ROUNDS = 4;
    public static final int DEFAULT_MIN_COMPARISONS = 10000;
    public static final String DEFAULT_TEMP_DIR = System.getProperty("java.io.tmpdir");
    public static final boolean DEFAULT_SAVE_BINARY = false;
    public static final String DEFAULT_OUTPUT_NAME = null;
    public static final int DEFAULT_NUMBER_JOBS = Runtime.getRuntime().availableProcessors();

    /**
     * Local (actual) values
     */
    private static CLUSTERING_TYPE clusteringType = DEFAULT_CLUSTERING_TYPE;
    private static List<String> mgfFiles = new ArrayList<String>();
    private static float precursorTolerance = DEFAULT_PRECURSOR_TOLERANCE;
    private static float fragmentTolerance = DEFAULT_FRAGMENT_TOLERANCE;
    private static float startThreshold = DEFAULT_START_THRESHOLD;
    private static float threshold = DEFAULT_THRESHOLD;
    private static int clusteringRounds = DEFAULT_CLUSTERING_ROUNDS;
    private static int minComparisons = DEFAULT_MIN_COMPARISONS;
    private static String tempDir = DEFAULT_TEMP_DIR;
    private static boolean saveBinary = DEFAULT_SAVE_BINARY;
    private static String outputName = DEFAULT_OUTPUT_NAME;
    private static int numberJobs = DEFAULT_NUMBER_JOBS;

    public static CLUSTERING_TYPE getClusteringType() {
        return clusteringType;
    }

    public static void setClusteringType(CLUSTERING_TYPE clusteringType) {
        UserSettings.clusteringType = clusteringType;
    }

    public static List<String> getMgfFiles() {
        return mgfFiles;
    }

    public static void setMgfFiles(List<String> mgfFiles) {
        UserSettings.mgfFiles = mgfFiles;
    }

    public static float getPrecursorTolerance() {
        return precursorTolerance;
    }

    public static void setPrecursorTolerance(float precursorTolerance) {
        UserSettings.precursorTolerance = precursorTolerance;
    }

    public static float getFragmentTolerance() {
        return fragmentTolerance;
    }

    public static void setFragmentTolerance(float fragmentTolerance) {
        UserSettings.fragmentTolerance = fragmentTolerance;
    }

    public static float getStartThreshold() {
        return startThreshold;
    }

    public static void setStartThreshold(float startThreshold) {
        UserSettings.startThreshold = startThreshold;
    }

    public static float getThreshold() {
        return threshold;
    }

    public static void setThreshold(float threshold) {
        UserSettings.threshold = threshold;
    }

    public static int getClusteringRounds() {
        return clusteringRounds;
    }

    public static void setClusteringRounds(int clusteringRounds) {
        UserSettings.clusteringRounds = clusteringRounds;
    }

    public static int getMinComparisons() {
        return minComparisons;
    }

    public static void setMinComparisons(int minComparisons) {
        UserSettings.minComparisons = minComparisons;
    }

    public static String getTempDir() {
        return tempDir;
    }

    public static void setTempDir(String tempDir) {
        UserSettings.tempDir = tempDir;
    }

    public static boolean isSaveBinary() {
        return saveBinary;
    }

    public static void setSaveBinary(boolean saveBinary) {
        UserSettings.saveBinary = saveBinary;
    }

    public static String getOutputName() {
        return outputName;
    }

    public static void setOutputName(String outputName) {
        UserSettings.outputName = outputName;
    }

    public static int getNumberJobs() {
        return numberJobs;
    }

    public static void setNumberJobs(int numberJobs) {
        UserSettings.numberJobs = numberJobs;
    }
}
