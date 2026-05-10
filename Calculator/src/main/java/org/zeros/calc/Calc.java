package org.zeros.calc;

import javafx.application.Application;
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

import java.util.Objects;

public class Calc extends Application {
    @Override
    public void start(Stage primaryStage) {
        final String[] mode = {"Math"};
        final String[] calculation_method = {"+", "&"};

        ComboBox<String> subject = new ComboBox<>();
        subject.setPrefWidth(150);
        subject.setLayoutX(10);
        subject.setLayoutY(10);
        subject.getItems().addAll("Math", "Physics");
        subject.setValue("Math");

        TextField input_1 = new TextField();
        input_1.setLayoutX(200);
        input_1.setLayoutY(10);
        input_1.setFocusTraversable(false);
        input_1.setPromptText("请输入数字");

        ComboBox<String> math_operator = new ComboBox<>();
        math_operator.setPrefWidth(150);
        math_operator.setLayoutX(400);
        math_operator.setLayoutY(10);
        math_operator.getItems().addAll("+", "-", "x", "/", "//", "%", "^", "!", "<<", ">>");
        math_operator.setValue("+");

        ComboBox<String> physics_operator = new ComboBox<>();
        physics_operator.setPrefWidth(150);
        physics_operator.setLayoutX(400);
        physics_operator.setLayoutY(10);
        physics_operator.getItems().addAll("&", "|", "^", "~");
        physics_operator.setValue("&");

        TextField input_2 = new TextField();
        input_2.setLayoutX(600);
        input_2.setLayoutY(10);
        input_2.setFocusTraversable(false);
        input_2.setPromptText("请输入数字");

        Text math_operator_tip = new Text("这是加法");
        math_operator_tip.setLayoutX(800);
        math_operator_tip.setLayoutY(30);
        math_operator_tip.setFont(Font.font(24));

        Text physics_operator_tip = new Text("这是与运算");
        physics_operator_tip.setLayoutX(800);
        physics_operator_tip.setLayoutY(30);
        physics_operator_tip.setFont(Font.font(24));

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
        root.getChildren().addAll(subject, input_1, math_operator, input_2, math_operator_tip, solve, output);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        subject.valueProperty().addListener((_, _, newValue) -> {
            switch (newValue) {
                case "Math" -> {
                    root.getChildren().addAll(math_operator, math_operator_tip);
                    root.getChildren().removeAll(physics_operator, physics_operator_tip);
                    mode[0] = "Math";
                }
                case "Physics" -> {
                    root.getChildren().addAll(physics_operator, physics_operator_tip);
                    root.getChildren().removeAll(math_operator, math_operator_tip);
                    mode[0] = "Physics";
                }
            }
        });

        math_operator.valueProperty().addListener((_, _, newValue) -> {
            calculation_method[0] = newValue;
            switch (calculation_method[0]) {
                case "+" -> math_operator_tip.setText("这是加法");
                case "-" -> math_operator_tip.setText("这是减法");
                case "x" -> math_operator_tip.setText("这是乘法");
                case "/" -> math_operator_tip.setText("这是除法");
                case "//" -> math_operator_tip.setText("这是整除");
                case "%" -> math_operator_tip.setText("这是百分号");
                case "^" -> math_operator_tip.setText("这是次方");
                case "!" -> math_operator_tip.setText("这是阶乘");
                case "<<" -> math_operator_tip.setText("这是左移");
                case ">>" -> math_operator_tip.setText("这是右移");
            }
        });

        physics_operator.valueProperty().addListener((_, _, newValue) -> {
            calculation_method[1] = newValue;
            switch (calculation_method[1]) {
                case "&" -> physics_operator_tip.setText("这是与运算");
                case "|" -> physics_operator_tip.setText("这是或运算");
                case "^" -> physics_operator_tip.setText("这是异或运算");
                case "~" -> physics_operator_tip.setText("这是非运算");
            }
        });

        solve.setOnAction(_ -> {
            try {
                String text1 = input_1.getText().trim();
                String text2 = input_2.getText().trim();

                if (text1.isEmpty()) {
                    output.setText("请输入第一个数字");
                    return;
                }

                double num1 = Double.parseDouble(text1);
                double num2 = 0;

                String mathOperatorValue = calculation_method[0];
                String physicsOperatorValue = calculation_method[1];
                if (mode[0].equals("Math")) {
                    if (! mathOperatorValue.equals("%") && ! mathOperatorValue.equals("!")) {
                        if (text2.isEmpty()) {
                            output.setText("请输入第二个数字");
                            return;
                        }
                        num2 = Double.parseDouble(text2);
                    }
                } else if (mode[0].equals("Physics")) {
                    if (! physicsOperatorValue.equals("~")) {
                        if (text2.isEmpty()) {
                            output.setText("请输入第二个数字");
                            return;
                        }
                        num2 = Double.parseDouble(text2);
                    }
                }

                double math;

                if (mode[0].equals("Math")) {
                    switch (mathOperatorValue) {
                        case "+" -> {
                            math = num1 + num2;
                            output.setText(String.valueOf(math));
                        }
                        case "-" -> {
                            math = num1 - num2;
                            output.setText(String.valueOf(math));
                        }
                        case "x" -> {
                            math = num1 * num2;
                            output.setText(String.valueOf(math));
                        }
                        case "/" -> {
                            if (num2 == 0) {
                                output.setText("除零错误");
                            } else {
                                math = num1 / num2;
                                output.setText(String.valueOf(math));
                            }
                        }
                        case "//" -> {
                            if (num2 == 0) {
                                output.setText("除零错误");
                            } else {
                                math = Math.floor(num1 / num2);
                                output.setText(String.valueOf((long) math));
                            }
                        }
                        case "%" -> {
                            math = num1 * 100;
                            output.setText(math + "%");
                        }
                        case "^" -> {
                            math = Math.pow(num1, num2);
                            output.setText(String.valueOf(math));
                        }
                        case "!" -> {
                            math = factorial(num1);
                            output.setText(String.valueOf((long) math));
                        }
                        case "<<" -> {
                            int value = (int) num1;
                            int shift = (int) num2;
                            math = value << shift;
                            output.setText(String.valueOf((long) math));
                        }
                        case ">>" -> {
                            int value = (int) num1;
                            int shift = (int) num2;
                            math = value >> shift;
                            output.setText(String.valueOf((long) math));
                        }
                        default -> output.setText("未知操作符");
                    }
                }
                if (mode[0].equals("Physics")) {
                    switch (physicsOperatorValue) {
                        case "&" -> output.setText(String.valueOf((int) num1 & (int) num2));
                        case "|" -> output.setText(String.valueOf((int) num1 | (int) num2));
                        case "^" -> output.setText(String.valueOf((int) num1 ^ (int) num2));
                        case "~" -> output.setText(String.valueOf(~(int) num1));
                        default -> output.setText("未知操作符");
                    }
                }

            } catch (NumberFormatException e) {
                output.setText("请输入有效的数字");
            } catch (Exception e) {
                output.setText("计算错误: " + e.getMessage());
            }
        });

        primaryStage.setTitle("Calc");
        try {
            primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("pictures/Calc.png"))));
        } catch (Exception e) {
            System.out.println("图标加载失败: " + e.getMessage());
        }

        primaryStage.setWidth(1600);
        primaryStage.setHeight(900);
        primaryStage.setOpacity(0.9);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private double factorial(double n) {
        int num = (int) n;
        if (num < 0) {
            return Double.NaN;
        }
        long result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}