import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel{

    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    @Override
    public int getRowCount() {
        return new Double(Math.ceil((to-from)/step)).intValue()+1;
    }

    @Override
    public int getColumnCount() {
        return 3; // Третий столбец для булевского значения
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step*rowIndex;
        Double result=0.0;
        if (columnIndex==0) {
            return x;
        } else if (columnIndex == 1){
            // Проходим по всем коэффициентам, начиная с последнего (a0)
            // последовательно в результат добавляется значение многочлена, добавляя коэффициенты один за другим
            for (int i = coefficients.length - 1; i >= 0; i--) {
                result = coefficients[i] + x * result;
            }
            return result;
        } else if (columnIndex == 2) {
            return ((Double) getValueAt(rowIndex, 1)) > 0;
        } else
            return null;
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Значение X";
            case 1 -> "Значение многочлена";
            case 2 -> "Значение больше нуля?";
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 2){
            return Boolean.class; // Третий столбец — это булевое значение, отображаемое как флажок
        }
        return Double.class;
    }}

