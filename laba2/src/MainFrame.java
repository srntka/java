import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private final JTextField xField, yField, zField, resultField, memoryField; // поля для считывания значений + вывода рез-та (тк исп в разл методах)
    private final ButtonGroup radioButtons = new ButtonGroup(); //группа радио-кнопок
    private final Box hboxFormulaType = Box.createHorizontalBox(); //контейнер для отображения радио-кнопок
    private double memory = 0;

    private int formulaId = 1;

    private double calculate1(double x, double y, double z) {
        return Math.sin(Math.sin(y)+Math.exp(Math.cos(y))+z*z)*Math.pow( Math.sin(Math.PI*y*y)+Math.log(x*x),0.25);
    }

    private double calculate2(double x, double y, double z) {
        return Math.atan(Math.pow(z,x)) / (y*y+z*Math.sin(Math.log(x)));
    }

    // buttonName – текст рядом с кнопкой, formulaId – идентификатор формулы
    private void addRadioButton(String buttonName, final int formulaId) {
        // Создать экземпляр радио-кнопки с заданным текстом
        JRadioButton button = new JRadioButton(buttonName);
        // Определить и зарегистрировать обработчик
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                // Который будет устанавливать идентификатор выбранной
                // Который будет устанавливать идентификатор выбранной
                // формулы в классе Formula равным formulaId
                MainFrame.this.formulaId = formulaId;
            }
        });
        // Добавить радио-кнопку в группу
        radioButtons.add(button);
        // Добавить радио-кнопку в контейнер
        // Для этого ссылка на контейнер сделана полем данных класса
        hboxFormulaType.add(button);
    }

    public MainFrame() { //конструктор класса
        super("Вычисление формул"); //обращение к конструктуру предка, исп только заголовок окна
        Toolkit kit = Toolkit.getDefaultToolkit();
        setSize(450, 300);
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2); //центровка окна приложения по центру экрана

        //с помощью метода помозника создаются радио-кнопки и включ в компоновку окна
        // «клей» C1-H1
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        //первая кнопка устанавл выделенной
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        //  «клей» C1-H2
        hboxFormulaType.add(Box.createHorizontalGlue());
        //желтая рамка коробки
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        JLabel labelForX = new JLabel("X:");
        JLabel labelForY = new JLabel("Y:");
        JLabel labelForZ = new JLabel("Z:");
        xField=new JTextField("0", 10); //0 по умолч
        xField.setMaximumSize(xField.getPreferredSize()); //уст макс размер как желаемые для предотвр масштаб
        yField=new JTextField("0", 10);
        yField.setMaximumSize(yField.getPreferredSize());
        zField=new JTextField("0", 10);
        zField.setMaximumSize(zField.getPreferredSize());
        //контейнер "коробка с горизонт укалдкой"
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        //C2-H1 (с левого края до ввода Х)
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX); //подпись для Х
        hboxVariables.add(Box.createHorizontalStrut(10)); //"распорка" 10 пикселей между текстом и вводом
        hboxVariables.add(xField);
        hboxVariables.add(Box.createHorizontalStrut(50));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(yField);
        hboxVariables.add(Box.createHorizontalStrut(50));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(zField);
        hboxVariables.add(Box.createHorizontalGlue());

        JLabel labelForResult = new JLabel("Результат:");
        resultField = new JTextField("0", 13);
        resultField.setMaximumSize(resultField.getPreferredSize());
        JLabel labelForMemory= new JLabel("Память:");
        memoryField=new JTextField("0", 13);
        memoryField.setMaximumSize(memoryField.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(resultField);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(labelForMemory);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(memoryField);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JButton buttonCalc = new JButton("Вычислить"); //кнопка "Вычислить"
        //обработчик нажатия на кнопку
        buttonCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double x = Double.parseDouble(xField.getText());
                    double y = Double.parseDouble(yField.getText());
                    double z = Double.parseDouble(zField.getText());
                    //выч рез-та
                    double result;
                    if (formulaId==1) {
                        result = calculate1(x,y,z);
                        resultField.setText(String.valueOf(result));
                    } else {
                        result=calculate2(x,y,z);
                        resultField.setText(String.valueOf(result));
                    }
                }
                catch (NumberFormatException exception){
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей запятой",
                            "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        //кнопка "очистить"
        JButton buttonReset = new JButton("Очистить");
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xField.setText("0");
                yField.setText("0");
                zField.setText("0");
                resultField.setText("0");
            }
        });

        //кнопка M+

        JButton buttonMplus = new JButton("M+");
        buttonMplus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double result = Double.parseDouble(resultField.getText());
                    memory += result;
                    memoryField.setText(String.valueOf(memory));
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей запятой",
                            "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memory=0;
                memoryField.setText("0");
            }
        });

        //коробка для кнопок
        Box hboxButtons=Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonMplus);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonMC);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        //сборка горизонт коробок в единую вертикальную
        Box contentBox=Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());

        getContentPane().add(contentBox,BorderLayout.CENTER);
    }
}
