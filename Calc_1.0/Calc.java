package Calc;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Calc extends Application{
    @Override
    public void start(Stage primaryStage) {
        final String[] calculation_method = {"+"};

        TextField input_1 = new TextField();
        input_1.setLayoutX(10);
        input_1.setLayoutY(10);
        input_1.setFocusTraversable(false);
        input_1.setPromptText("请输入数字");

        ComboBox<String> operator = new ComboBox<>();
        operator.setPrefWidth(150);
        operator.setLayoutX(200);
        operator.setLayoutY(10);
        operator.getItems().addAll("+", "-", "x", "/", "//", "%", "^", "!", "<<", ">>");
        operator.setValue("+");

        TextField input_2 = new TextField();
        input_2.setLayoutX(400);
        input_2.setLayoutY(10);
        input_2.setFocusTraversable(false);
        input_2.setPromptText("请输入数字");

        Text operator_tip = new Text();
        operator_tip.setLayoutX(600);
        operator_tip.setLayoutY(10);
        operator_tip.setFont(Font.font(24));

        Button solve = new Button("计算");
        solve.setPrefWidth(200);
        solve.setPrefHeight(40);
        solve.setLayoutX(1350);
        solve.setLayoutY(600);

        Tooltip output_tip = new Tooltip("输出答案");
        output_tip.setFont(Font.font(36));

        TextField output = new TextField();
        output.setFont(Font.font(72));
        output.setPrefWidth(1600);
        output.setPrefHeight(200);
        output.setLayoutY(650);
        output.setPromptText("计算结果");
        output.setTooltip(output_tip);
        output.setEditable(false);

        Group root = new Group();
        root.getChildren().addAll(input_1, operator, input_2, solve, output);

        Scene scene = new Scene(root);
        scene.setCursor(Cursor.CROSSHAIR);

        primaryStage.setScene(scene);

        operator.valueProperty().addListener((_, _, newValue) -> calculation_method[0] = newValue);

        solve.setOnAction(_ -> {
            try {
                double math;
                switch (calculation_method[0]) {
                    case "+" -> {
                        math = Double.parseDouble(input_1.getText()) + Double.parseDouble(input_2.getText());
                        output.setText(String.valueOf(math));
                    }
                    case "-" -> {
                        math = Double.parseDouble(input_1.getText()) - Double.parseDouble(input_2.getText());
                        output.setText(String.valueOf(math));
                    }
                    case "x" -> {
                        math = Double.parseDouble(input_1.getText()) * Double.parseDouble(input_2.getText());
                        output.setText(String.valueOf(math));
                    }
                    case "/" -> {
                        try {
                            math = Double.parseDouble(String.valueOf(Double.parseDouble(input_1.getText()) / Double.parseDouble(input_2.getText())));
                            output.setText(String.valueOf(math));
                        } catch (ArithmeticException e) {
                            output.setText("除零错误");
                        }
                    }
                    case "//" -> {
                        try {
                            math = Double.parseDouble(String.valueOf(Double.parseDouble(input_1.getText()) - Double.parseDouble(input_1.getText()) % Double.parseDouble(input_2.getText()))) / Double.parseDouble(input_2.getText());
                            output.setText(String.valueOf(math));
                        } catch (ArithmeticException e) {
                            output.setText("除零错误");
                        }
                    }
                    case "%" -> {
                        math = Double.parseDouble(input_1.getText()) * 100;
                        output.setText(math + "%");
                    }
                    case "^" -> {
                        math = 0;
                        double oldMath = Double.parseDouble(input_1.getText());
                        for (int i = 0; i < (Double.parseDouble(input_2.getText()) - 1); i++) {
                            if (Double.parseDouble(input_2.getText()) == 1) {
                                math = oldMath;
                                break;
                            }
                            if (i == 0) {
                                math = Double.parseDouble(input_1.getText());
                            }
                            math = math * oldMath;
                        }
                        output.setText(String.valueOf(math));
                    }
                    case "!" -> {
                        math = 0;
                        for (int i = 0; i < Double.parseDouble(input_1.getText()); i++) {
                            if (Double.parseDouble(input_1.getText()) == 1) {
                                math = 1;
                                break;
                            }
                            if (i == 0) {
                                math = 1;
                            }
                            if ((i + 2) <= Double.parseDouble(input_1.getText())) {
                                math = math * (i + 2);
                            }
                        }
                        output.setText(String.valueOf(math));
                    }
                    case "<<" -> {
                        math = getMath(input_2);
                        math = Double.parseDouble(input_1.getText()) * math;
                        output.setText(String.valueOf(math));
                    }
                    case ">>" -> {
                        math = getMath(input_2);
                        math = Double.parseDouble(input_1.getText()) / math;
                        output.setText(String.valueOf(math));
                    }
                }
            } catch (NumberFormatException e) {
                output.setText("应为数字");
            }
        });

        primaryStage.setTitle("Calc");
        primaryStage.getIcons().add(new Image("/Calc/pictures/Calc.png"));

        primaryStage.setWidth(1600);
        primaryStage.setHeight(900);

        primaryStage.setOpacity(0.7);

        primaryStage.setResizable(false);

        primaryStage.show();
    }

    private double getMath(TextField num) {
        double math;
        math = 1;
        for (int i = 0; i < Double.parseDouble(num.getText()); i++) {
            if (Double.parseDouble(num.getText()) == 1) {
                math = 10;
                break;
            }
            if (i == 0) {
                math = 1;
            }
            math = math * 10;
        }
        return math;
    }
}
