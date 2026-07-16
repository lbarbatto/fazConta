package com.pororoca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.pororoca.controller.FazContaController;

import java.util.Objects;

/**
 * Entry point of the FazConta JavaFX application.
 *
 * <p>This class is responsible for bootstrapping the JavaFX runtime,
 * loading the UI layer, and initializing the primary stage.</p>
 *
 * <p>It follows a clean separation of concerns where:
 * <ul>
 *     <li>FXML defines presentation structure</li>
 *     <li>CSS defines visual styling</li>
 *     <li>Controller manages interaction logic</li>
 *     <li>Model encapsulates business rules</li>
 * </ul>
 * </p>
 */
public class Main extends Application {

    private static final String FXML_PATH = "/fazconta.fxml";
    private static final String CSS_PATH = "/style.css";
    private static final String APP_TITLE = "FazConta";

    /**
     * Initializes the JavaFX application and displays the primary stage.
     *
     * <p>This method loads the FXML layout, applies stylesheets,
     * and configures the main application window.</p>
     *
     * @param stage primary stage provided by JavaFX runtime
     * @throws Exception if the UI cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = createLoader();

        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(loadCss());

        configureStage(stage, scene);

        registerKeyboardShortcuts(scene, loader);

        stage.show();
    }

    /**
     * Creates and configures the FXMLLoader instance.
     *
     * @return configured FXMLLoader pointing to main FXML layout
     * @throws NullPointerException if FXML resource is not found
     */
    private FXMLLoader createLoader() {
        return new FXMLLoader(
                Objects.requireNonNull(
                        getClass().getResource(FXML_PATH),
                        "FXML file not found: " + FXML_PATH
                )
        );
    }

    /**
     * Loads application stylesheet safely.
     *
     * @return resolved CSS external form URL
     * @throws NullPointerException if CSS resource is missing
     */
    private String loadCss() {
        return Objects.requireNonNull(
                getClass().getResource(CSS_PATH),
                "CSS file not found: " + CSS_PATH
        ).toExternalForm();
    }

    /**
     * Configures primary stage properties.
     *
     * @param stage application window
     * @param scene UI scene graph
     */
    private void configureStage(Stage stage, Scene scene) {
        stage.setTitle(APP_TITLE);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
    }

    /**
     * Registers keyboard shortcuts that map physical keys to
     * the same commands used by the on-screen buttons.
     *
     * @param scene UI scene graph
     * @param loader configured FXML loader
     */
    private void registerKeyboardShortcuts(
            Scene scene,
            FXMLLoader loader
    ) {

        FazContaController controller =
                loader.getController();

        scene.setOnKeyPressed(event -> {
            String value =
                    switch (event.getText()) {
                        case "0", "1", "2", "3", "4",
                             "5", "6", "7", "8", "9" ->
                                event.getText();
                        case "+", "-", "*", "/" ->
                                event.getText();
                        case "×" -> "×";
                        case "÷" -> "÷";
                        case ".", "," -> ".";
                        case "=" -> "=";
                        case "" ->
                                switch (event.getCode()) {
                                    case ENTER -> "=";
                                    case BACK_SPACE -> "⌫";
                                    case DELETE -> "CE";
                                    case ESCAPE -> "C";
                                    default -> null;
                                };
                        default -> null;
                    };

            if (value != null) {
                controller.handleButton(value);
            }
        });
    }

    /**
     * Application entry point.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        launch(args);
    }
}