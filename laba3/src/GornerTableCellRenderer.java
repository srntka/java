import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer {

    private String needle = null;
    private final JPanel panel; // Панель для ячейки
    private final JLabel label; // Метка для отображения значения
    private final DecimalFormat formatter; // Форматировщик для чисел

    public GornerTableCellRenderer() {
        // Инициализация панели и метки
        panel = new JPanel();
        label = new JLabel();
        panel.add(label); // Добавляем метку на панель
        panel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Устанавливаем выравнивание

        // Инициализация форматировщика
        formatter = (DecimalFormat) NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(5); // Показывать только 5 знаков после запятой
        formatter.setGroupingUsed(false); // Не использовать группировку (например, 1000 вместо 1,000)

        // Настройка разделителя для десятичных чисел
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setDecimalSeparator('.'); // Устанавливаем точку в качестве разделителя
        formatter.setDecimalFormatSymbols(symbols);
    }

    public void setNeedle(String needle) {
        this.needle = needle;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        // Преобразовать значение в строку с использованием форматировщика
        String formattedValue = formatter.format(value);
        label.setText(formattedValue);

        // Проверяем, нужно ли выделить ячейку
        if (column == 1 && needle != null && needle.equals(formattedValue)) {
            panel.setBackground(Color.RED); // Задний фон - красный
        } else {
            panel.setBackground(Color.WHITE); // Задний фон - белый
        }

        return panel; // Возвращаем настроенный компонент
    }

}
