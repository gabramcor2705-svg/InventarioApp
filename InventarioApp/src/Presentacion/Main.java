package Presentacion;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        configurarLookAndFeel();
        SwingUtilities.invokeLater(() -> new InventarioFrame().setVisible(true));
    }

    private static void configurarLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Si no se puede usar el look and feel del sistema, se usa el predeterminado.
        }
    }
}
