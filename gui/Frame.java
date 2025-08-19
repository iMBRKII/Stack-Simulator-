
package gui;

import stack.Stack;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author MK
 */
public class Frame extends Application{
    
    private final Label inputLabel = new Label("input: ");
    private final Label outputLabel = new Label("output: ");
    private final Label introLabel = new Label("Stack Simulator");
    private final Label introByWhoLabel = new Label("by/ Mubarak");
    
    private final Label instruction = new Label();
    private final TextField inputTextField = new TextField();
    private final TextField outputTextField = new TextField();
    private final TextField sizeField = new TextField();
    private final ComboBox<String> comboBox = new ComboBox<>();

    private final VBox VboxPane = new VBox(12);
    private final VBox VboxPane2 = new VBox();
    private final VBox VboxPaneMainScreen = new VBox();
    private final FlowPane flowPane = new FlowPane();
    private final FlowPane flowPane2 = new FlowPane();
    private final HBox HboxPane = new HBox();
    private final StackPane stackPane = new StackPane();
    private Scene scene;
    
    private final Stack stack = new Stack();

    private Alert messageAlert;
    
    private final Canvas canvas = new Canvas(400, 600);
    private final double rectWidth = 250;
    private final double rectHeight = 450;
    private final double x = 50;
    private final double y = 50;
    
    @Override
    public void start(Stage stage) {
        
        eventHandler();
        initComboBox();
        fonts();
        layout();
        
        HboxPane.setVisible(false);
        VboxPaneMainScreen.setVisible(true);

        stackPane.getChildren().addAll(VboxPaneMainScreen, HboxPane);
        scene = new Scene(stackPane, 1920, 1050);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Stack simulator");
        stage.getIcons().add(new Image("icons/stack.png"));
        stage.show();
    }
    
    private void eventHandler(){
        inputTextField.setOnAction(e -> {
            
            outputTextField.clear();
            inputTextField.requestFocus();
            
            if (comboBox.getValue().equals("Push")) {
                String value = inputTextField.getText();
                if (!value.isEmpty()) {
                    if (stack.getSize() < stack.getMaxDiv()) {
                        stack.push(value);
                        drawDividedRectangle(stack.getMaxDiv());
                        instruction.setText("Added value: " + value + ". Top: " + stack.getLastElement());
                    } else {
                        instruction.setText("No more space! You've reached the maximum of " + stack.getMaxDiv() + " values.");
                    }
                } else {
                    instruction.setText("Please enter a valid value.");
                }
            }
            
            if (comboBox.getValue().equals("Pop")){
                if (!stack.isEmpty()){
                    outputTextField.setText(stack.pop());
                    drawDividedRectangle(stack.getMaxDiv());
                    instruction.setText("Deleted last value. Top: " + (stack.getLastElement() != null ? stack.getLastElement() : "null"));
                }
                else{
                    instruction.setText("No values to delete");
                }
            }
            
            if (comboBox.getValue().equals("isEmpty")){
                if (stack.isEmpty()){
                    outputTextField.setText("true");
                    instruction.setText("The stack is empty !!");
                }
                else {
                    outputTextField.setText("false");
                    instruction.setText("There are element in the stack");
                }
            }
            
            if (comboBox.getValue().equals("isFull")){
                if (stack.isFull()){
                    outputTextField.setText("true");
                    instruction.setText("The stack is full !!");
                }
                else {
                    outputTextField.setText("false");
                    instruction.setText("There are few spaces u can fill");
                }
            }
            
            if (comboBox.getValue().equals("Peek")){
                outputTextField.setText(stack.peek());
                instruction.setText("");
            }
            
            if (comboBox.getValue().equals("contain")){
                if (stack.contain(inputTextField.getText())){
                    outputTextField.setText("true");
                    instruction.setText("The stack contain " + inputTextField.getText() + ".");
                }
                else {
                    outputTextField.setText("false");
                    instruction.setText("The item you are looking for does not exist");
                }
            }
            
            if (comboBox.getValue().equals("display")){
                if(stack.isEmpty()){
                    instruction.setText("There are nothing to display");
                }
                else{
                    outputTextField.setText(stack.display());
                }
            }
        });

        sizeField.setOnAction((var e) -> {
            if (!sizeField.getText().isEmpty()) {
                try {
                    if (Integer.parseInt(sizeField.getText()) > 0 && Integer.parseInt(sizeField.getText()) <= 15) {
                        stack.setMaxDiv(Integer.parseInt(sizeField.getText()));
                        stack.clear();
                        stack.setLastElement(null);
                        drawDividedRectangle(stack.getMaxDiv());
                        
                        VboxPaneMainScreen.setVisible(false);
                        HboxPane.setVisible(true);
                    } 
                    else {
                        messageAlert = new Alert(Alert.AlertType.ERROR);

                        messageAlert.setHeaderText("Enter a positve number from 1 to 15");
                        messageAlert.show();
                    }
                }
                catch (NumberFormatException exception) {
                    messageAlert = new Alert(Alert.AlertType.ERROR);

                    messageAlert.setHeaderText("Enter a valid number");
                    messageAlert.show();
                }
            }
        });
        
        comboBox.setOnAction(e ->{
            inputTextField.clear();
            inputTextField.requestFocus();
        });
    }
    
    private void initComboBox(){
        comboBox.getItems().addAll(
                "Push",
                "Pop",
                "isEmpty",
                "isFull",
                "contain",
                "Peek",
                "display"
        );
        
        comboBox.setValue("Push");
    }
    
    private void fonts(){
        introByWhoLabel.setId("intro-by");
        introLabel.setId("intro-title");
        
        inputLabel.setFont(new Font(16));
        outputLabel.setFont(new Font(16));
        
        instruction.setFont(new Font(14));
        instruction.setStyle("-fx-text-fill: #750909");
    }
    
    private void layout(){
        inputTextField.setMinWidth(254);
        outputTextField.setMinWidth(250);
        outputTextField.setEditable(false);
        outputTextField.setPromptText("none..");
        
        
        
        flowPane.setHgap(3);
        flowPane.getChildren().addAll(inputLabel, inputTextField, comboBox);


        flowPane2.getChildren().addAll(outputLabel, outputTextField);

        VboxPane.setAlignment(Pos.CENTER);
        VboxPane.getChildren().addAll(flowPane, flowPane2, instruction);

        VboxPane2.setAlignment(Pos.CENTER);
        VboxPane2.getChildren().add(canvas);

        introLabel.setFont(new Font(24));
        sizeField.setMaxWidth(255);
        sizeField.setPromptText("Enter size for the stack");
        VboxPaneMainScreen.setAlignment(Pos.CENTER);
        VboxPaneMainScreen.setSpacing(70);
        
        VBox introPane = new VBox();
        introPane.setAlignment(Pos.CENTER);
        introPane.getChildren().addAll(introLabel, introByWhoLabel);
        
        VboxPaneMainScreen.getChildren().addAll(introPane, sizeField);

        HboxPane.getChildren().addAll(VboxPane2, VboxPane);
        HboxPane.setAlignment(Pos.CENTER);
        
        HboxPane.setStyle("-fx-background-color: #C0C0C0");
        VboxPaneMainScreen.setStyle("-fx-background-color: #C0C0C0");
    }

    private void drawDividedRectangle(int divisions) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(x, y, rectWidth, rectHeight);

        double divisionHeight = rectHeight / divisions;
        for (int i = 1; i < divisions; i++) {
            gc.strokeLine(x, y + i * divisionHeight, x + rectWidth, y + i * divisionHeight);
        }

        gc.setFill(Color.BEIGE);

        for (int i = 0; i < stack.getSize(); i++) {
            double currentY = y + rectHeight - (i + 1) * divisionHeight;
            gc.fillRect(x, currentY, rectWidth, divisionHeight);
            gc.setStroke(Color.BLACK);
            gc.strokeText(stack.getElementAt(i), x + 10, currentY + divisionHeight / 2);
            gc.strokeRect(x, currentY, rectWidth, divisionHeight);
        }
    }
}
