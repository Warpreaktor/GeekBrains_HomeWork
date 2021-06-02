package HomeWork_07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private static final int WIN_HEIGHT = 400;
    private static final int WIN_WIDTH = 400;
    private static final int WIN_X = 400;
    private static final int WIN_Y = 300;

    private final Box mainPanel;
    private final JPanel calculatorPanel;
    private final JPanel operatorsPanel;
    private final JPanel operandsPanel;
    private final Box resultPanel;
    private final JLabel resultScreen;
    private final JLabel operandScreen;

    private StringBuilder operator1;
    private StringBuilder operator2;
    private char operand;
    private byte operation; //1 - пишем в первый оператор, ожидаем операнд; 2 - Пишем во второй оператор ожидаем операнд


    public Calculator() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WIN_X, WIN_Y, WIN_WIDTH, WIN_HEIGHT);
        setTitle("Calculator");
        setResizable(false);

        //Создаем все необходимые компоненты
        mainPanel = new Box(BoxLayout.Y_AXIS);
        resultPanel = new Box(BoxLayout.X_AXIS);

        operandScreen = new JLabel();
        resultScreen = new JLabel();

        calculatorPanel = new JPanel(new GridLayout(1, 2));
        operatorsPanel = new JPanel(new GridLayout(4, 3));
        operandsPanel = new JPanel(new GridLayout(4, 2));

        //Передаем менеджерам созданные панельки чтобы не морочить голову с их размерами
        resultScreenInit();

        add(mainPanel);

        mainPanel.add(resultPanel);
        mainPanel.add(calculatorPanel, BorderLayout.CENTER);
        calculatorPanel.add(operatorsPanel, BorderLayout.WEST);
        calculatorPanel.add(operandsPanel, BorderLayout.EAST);

        operatorsInit();
        operandsInit();
        operation = 1;
        setVisible(true);
    }

    /**
     * Инициализируем кнопки калькулятора
     */
    private void operatorsInit() {
        for (int i = 9; i >= 0; i--) {
            JButton button = new JButton(String.valueOf(i));
            button.setName(String.valueOf(i));
            operatorsPanel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (operation == 1) {
                        operator1.append(button.getName());
                        resultScreen.setText(operator1.toString());
                    }
                    if (operation == 2) {
                        operator2.append(button.getName());
                        resultScreen.setText(operator2.toString());
                    }
                }
            });
        }

        /* Точка для возможности производить операции с дробными числами */
        JButton dot = new JButton(".");
        operatorsPanel.add(dot);
        dot.setName(".");
        dot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (operation == 1){
                        setDot(operator1);
                        return;
                    }
                    if (operation == 2){
                        setDot(operator2);
                        return;
                    }
            }
        });
        operator1 = new StringBuilder();
        operator2 = new StringBuilder();
    }

    /**
     * Ставим точку только если она уже не стоит
     */
    public void setDot(StringBuilder operator){
            if (operator.indexOf(".") != -1) {
                //ничего не делать. Больше точек ставить не надо.
            } else {
                operator.append(".");
                resultScreen.setText(operator.toString());
            }
        }

    /**
     * Инициализируем операнды
     */
    private void operandsInit() {
        JButton division = createOperand("/");
        JButton mult = createOperand("*");
        JButton minus = createOperand("-");
        JButton plus = createOperand("+");
        JButton reset = createResetOperand("C");

        JButton equal = new JButton("=");
        equal.setName("=");
        equal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               plusEqual();
            }
        });


        operandsPanel.add(division);
        operandsPanel.add(minus);
        operandsPanel.add(mult);
        operandsPanel.add(plus);
        operandsPanel.add(reset);
        operandsPanel.add(equal);
    }

    /**
     * Создание стандартного операнда
     */
    private JButton createOperand(String operand){
        JButton object = new JButton(operand);
        object.setName(operand);
        object.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation(object);
            }
        });
        return object;
    }

    /**
     *Создание операнда "СБРОС РЕЗУЛЬТАТА"
     */
    private JButton createResetOperand(String operand){
        JButton object = new JButton(operand);
        object.setName(operand);
        object.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearResultScreen();
            }
        });
        return object;
    }

    /**
     *Создание операнда "равно"
     */
    private JButton createEqualOperand(String operand){
        JButton object = new JButton(operand);
        object.setName(operand);
        object.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plusEqual();
            }
        });
        return object;
    }
    public double equal(double operator1, double operator2, char operand){
        switch(operand){
            case '+':
                return operator1 + operator2;
            case '-':
                return operator1 - operator2;
            case '*':
                return operator1 * operator2;
            case '/':
                return operator1 / operator2;
        }
        return -1;
    }

    /**
     * Первичная настройка результирующего экрана
     */
    private void resultScreenInit() {
        resultScreen.setBackground(Color.BLUE);
        resultScreen.setText("0");
        operandScreen.setText("#    ");
        resultScreen.setFont(new Font(Font.SERIF, Font.PLAIN, 48));
        operandScreen.setFont(new Font(Font.MONOSPACED, Font.LAYOUT_LEFT_TO_RIGHT, 48));
        resultPanel.add(operandScreen);
        operandScreen.setSize(1000,20);
        resultPanel.add(resultScreen);
    }
    public void operation(JButton button){
        if (operation == 1 && !operator1.toString().equals("")) {
            operation = 2;
            operand = button.getName().charAt(0);
            operandScreen.setText(operand + "  ");
            return;
        }
        if (operation == 2){
            plusEqual();
            operand = button.getName().charAt(0);
            operandScreen.setText(operand + "    ");
        }
    }
    private void clearResultScreen(){
        resultScreen.setText("0");
        operation = 1;
        operator1 = new StringBuilder();
        operator2 = new StringBuilder();
    }

    public void plusEqual(){
        if (operation == 1) {
            //Нечего пока сравнивать
            return;
        }
        if (operation == 2 && !operator2.toString().equals("")){
            double a = Double.parseDouble(operator1.toString());
            double b = Double.parseDouble(operator2.toString());
            String result = String.valueOf(equal(a, b, operand));
            resultScreen.setText(result);
            operator1 = new StringBuilder(result);
            operator2 = new StringBuilder();
            operation = 2;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
