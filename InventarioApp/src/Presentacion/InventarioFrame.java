package Presentacion;

import Entidades.Producto;
import LogicaNegocio.ProductoService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class InventarioFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color PRIMARY = new Color(28, 99, 214);
    private static final Color PRIMARY_DARK = new Color(18, 70, 156);
    private static final Color PANEL_BG = new Color(245, 247, 250);
    private static final Color ALERT = new Color(198, 40, 40);
    private static final Color OK = new Color(46, 125, 50);

    private final ProductoService service = new ProductoService();
    private final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JTextField txtUbicacion;
    private JTextField txtStockMinimo;
    private JTextField txtBuscarId;

    private JLabel lblTotalProductos;
    private JLabel lblValorInventario;
    private JLabel lblAlertas;
    private JLabel lblEstado;

    private JTable tblProductos;
    private DefaultTableModel tableModel;

    public InventarioFrame() {
        initFrame();
        initComponents();
        cargarProductos();
    }

    private void initFrame() {
        setTitle("InventarioApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 760);
        setMinimumSize(new Dimension(1180, 720));
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JPanel content = new JPanel(new BorderLayout(18, 18));
        content.setBorder(new EmptyBorder(18, 18, 18, 18));
        content.setBackground(new Color(236, 239, 244));

        content.add(buildHeader(), BorderLayout.NORTH);
        content.add(buildCenter(), BorderLayout.CENTER);
        content.add(buildFooter(), BorderLayout.SOUTH);

        setContentPane(content);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout(16, 10));
        header.setOpaque(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Gestion Profesional de Inventario");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(new Color(33, 37, 41));

        JLabel subtitle = new JLabel("Registra productos, controla stock y revisa alertas desde una sola vista.");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setForeground(new Color(95, 99, 104));

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(4));
        titlePanel.add(subtitle);

        JPanel metricsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        metricsPanel.setOpaque(false);
        lblTotalProductos = buildMetricCard(metricsPanel, "Productos", "0");
        lblValorInventario = buildMetricCard(metricsPanel, "Valor total", "$0.00");
        lblAlertas = buildMetricCard(metricsPanel, "Alertas", "0");

        header.add(titlePanel, BorderLayout.WEST);
        header.add(metricsPanel, BorderLayout.EAST);
        return header;
    }

    private JLabel buildMetricCard(JPanel parent, String title, String value) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(150, 82));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230)),
                new EmptyBorder(10, 12, 10, 12)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblTitle.setForeground(new Color(100, 106, 115));

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblValue.setForeground(PRIMARY_DARK);

        card.add(lblTitle);
        card.add(Box.createVerticalStrut(8));
        card.add(lblValue);
        parent.add(card);
        return lblValue;
    }

    private JPanel buildCenter() {
        JPanel center = new JPanel(new BorderLayout(18, 18));
        center.setOpaque(false);
        center.add(buildFormPanel(), BorderLayout.WEST);
        center.add(buildTablePanel(), BorderLayout.CENTER);
        return center;
    }

    private JPanel buildFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(PANEL_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230)),
                new EmptyBorder(18, 18, 18, 18)
        ));
        panel.setPreferredSize(new Dimension(340, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 0, 6, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel sectionTitle = new JLabel("Formulario de Producto");
        sectionTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        sectionTitle.setForeground(new Color(33, 37, 41));
        panel.add(sectionTitle, gbc);

        gbc.gridy++;
        panel.add(createFieldLabel("ID"), gbc);
        gbc.gridy++;
        txtId = createTextField();
        panel.add(txtId, gbc);

        gbc.gridy++;
        panel.add(createFieldLabel("Nombre"), gbc);
        gbc.gridy++;
        txtNombre = createTextField();
        panel.add(txtNombre, gbc);

        gbc.gridy++;
        panel.add(createFieldLabel("Cantidad"), gbc);
        gbc.gridy++;
        txtCantidad = createTextField();
        panel.add(txtCantidad, gbc);

        gbc.gridy++;
        panel.add(createFieldLabel("Precio"), gbc);
        gbc.gridy++;
        txtPrecio = createTextField();
        panel.add(txtPrecio, gbc);

        gbc.gridy++;
        panel.add(createFieldLabel("Ubicacion"), gbc);
        gbc.gridy++;
        txtUbicacion = createTextField();
        panel.add(txtUbicacion, gbc);

        gbc.gridy++;
        panel.add(createFieldLabel("Stock minimo"), gbc);
        gbc.gridy++;
        txtStockMinimo = createTextField();
        panel.add(txtStockMinimo, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(14, 0, 8, 0);
        JButton btnRegistrar = createPrimaryButton("Registrar producto");
        btnRegistrar.addActionListener(e -> registrarProducto());
        panel.add(btnRegistrar, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(6, 0, 6, 0);
        JButton btnActualizarVista = createSecondaryButton("Actualizar vista");
        btnActualizarVista.addActionListener(e -> cargarProductos());
        panel.add(btnActualizarVista, gbc);

        gbc.gridy++;
        JButton btnLimpiar = createSecondaryButton("Limpiar formulario");
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        panel.add(btnLimpiar, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(18, 0, 6, 0);
        panel.add(createFieldLabel("Buscar por ID"), gbc);

        gbc.gridy++;
        txtBuscarId = createTextField();
        panel.add(txtBuscarId, gbc);

        gbc.gridy++;
        JButton btnBuscar = createSecondaryButton("Buscar producto");
        btnBuscar.addActionListener(e -> buscarProducto());
        panel.add(btnBuscar, gbc);

        gbc.gridy++;
        gbc.weighty = 1.0;
        panel.add(new JPanel(), gbc);

        return panel;
    }

    private JPanel buildTablePanel() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230)),
                new EmptyBorder(18, 18, 18, 18)
        ));

        JPanel toolbar = new JPanel(new BorderLayout());
        toolbar.setOpaque(false);

        JLabel tableTitle = new JLabel("Inventario actual");
        tableTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        tableTitle.setForeground(new Color(33, 37, 41));
        toolbar.add(tableTitle, BorderLayout.WEST);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        actions.setOpaque(false);

        JButton btnEntrada = createSecondaryButton("Entrada stock");
        btnEntrada.addActionListener(e -> registrarMovimiento(true));
        JButton btnSalida = createSecondaryButton("Salida stock");
        btnSalida.addActionListener(e -> registrarMovimiento(false));
        JButton btnEliminar = createDangerButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarProductoSeleccionado());

        actions.add(btnEntrada);
        actions.add(btnSalida);
        actions.add(btnEliminar);
        toolbar.add(actions, BorderLayout.EAST);

        panel.add(toolbar, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Cantidad", "Precio", "Ubicacion", "Stock minimo", "Estado"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblProductos = new JTable(tableModel);
        tblProductos.setRowHeight(28);
        tblProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblProductos.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tblProductos.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        tblProductos.getTableHeader().setBackground(new Color(232, 236, 242));
        tblProductos.getTableHeader().setForeground(new Color(45, 52, 54));
        tblProductos.setShowGrid(false);
        tblProductos.setIntercellSpacing(new Dimension(0, 0));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblProductos.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblProductos.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblProductos.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        tblProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tblProductos.getSelectedRow() >= 0) {
                    cargarProductoSeleccionadoEnFormulario();
                }
            }
        });

        panel.add(new JScrollPane(tblProductos), BorderLayout.CENTER);

        JLabel help = new JLabel("Tip: selecciona una fila para cargar sus datos en el formulario.");
        help.setFont(new Font("SansSerif", Font.PLAIN, 12));
        help.setForeground(new Color(110, 116, 124));
        panel.add(help, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel buildFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);

        lblEstado = new JLabel("Sistema listo.");
        lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblEstado.setForeground(new Color(79, 85, 92));

        footer.add(lblEstado, BorderLayout.WEST);
        return footer;
    }

    private JLabel createFieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        label.setForeground(new Color(80, 86, 94));
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(0, 34));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 205, 214)),
                new EmptyBorder(7, 10, 7, 10)
        ));
        return field;
    }

    private JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        styleButton(button, PRIMARY, Color.WHITE);
        return button;
    }

    private JButton createSecondaryButton(String text) {
        JButton button = new JButton(text);
        styleButton(button, new Color(233, 238, 247), new Color(33, 37, 41));
        return button;
    }

    private JButton createDangerButton(String text) {
        JButton button = new JButton(text);
        styleButton(button, new Color(255, 235, 238), ALERT);
        return button;
    }

    private void styleButton(JButton button, Color bg, Color fg) {
        button.setFocusPainted(false);
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        button.setOpaque(true);
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button.putClientProperty("JButton.buttonType", "roundRect");
    }

    private void registrarProducto() {
        try {
            Producto producto = leerProductoDesdeFormulario();
            service.registrar(producto);
            cargarProductos();
            limpiarFormulario();
            mostrarEstado("Producto registrado correctamente.", false);
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    private Producto leerProductoDesdeFormulario() {
        int id = parseInteger(txtId.getText(), "El ID debe ser un numero entero");
        String nombre = requireText(txtNombre.getText(), "El nombre es obligatorio");
        int cantidad = parseInteger(txtCantidad.getText(), "La cantidad debe ser un numero entero");
        double precio = parseDouble(txtPrecio.getText(), "El precio debe ser un numero valido");
        String ubicacion = requireText(txtUbicacion.getText(), "La ubicacion es obligatoria");
        int stockMinimo = parseInteger(txtStockMinimo.getText(), "El stock minimo debe ser un numero entero");

        return new Producto(id, nombre, cantidad, precio, ubicacion, stockMinimo);
    }

    private void cargarProductos() {
        List<Producto> productos = service.listar();
        tableModel.setRowCount(0);

        int alertas = 0;
        double total = 0;
        for (Producto producto : productos) {
            boolean bajoStock = producto.getCantidad() <= producto.getStockMinimo();
            if (bajoStock) {
                alertas++;
            }
            total += producto.getCantidad() * producto.getPrecio();

            tableModel.addRow(new Object[]{
                producto.getId(),
                producto.getNombre(),
                producto.getCantidad(),
                moneyFormat.format(producto.getPrecio()),
                producto.getUbicacion(),
                producto.getStockMinimo(),
                bajoStock ? "Bajo stock" : "Disponible"
            });
        }

        lblTotalProductos.setText(String.valueOf(productos.size()));
        lblValorInventario.setText(moneyFormat.format(total));
        lblAlertas.setText(String.valueOf(alertas));
        lblAlertas.setForeground(alertas > 0 ? ALERT : OK);

        if (productos.isEmpty()) {
            mostrarEstado("No hay productos registrados todavia.", false);
        } else {
            mostrarEstado("Inventario actualizado correctamente.", false);
        }
    }

    private void buscarProducto() {
        try {
            int id = parseInteger(txtBuscarId.getText(), "Ingresa un ID valido para buscar");
            Producto producto = service.buscar(id);

            if (producto == null) {
                mostrarError("No se encontro un producto con el ID indicado.");
                return;
            }

            seleccionarProductoEnTabla(producto.getId());
            poblarFormulario(producto);
            mostrarEstado("Producto encontrado y cargado en el formulario.", false);
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    private void seleccionarProductoEnTabla(int idProducto) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int idTabla = Integer.parseInt(tableModel.getValueAt(i, 0).toString());
            if (idTabla == idProducto) {
                tblProductos.setRowSelectionInterval(i, i);
                tblProductos.scrollRectToVisible(tblProductos.getCellRect(i, 0, true));
                break;
            }
        }
    }

    private void eliminarProductoSeleccionado() {
        int row = tblProductos.getSelectedRow();
        if (row < 0) {
            mostrarError("Selecciona un producto en la tabla para eliminarlo.");
            return;
        }

        int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
        String nombre = tableModel.getValueAt(row, 1).toString();

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "Se eliminara el producto \"" + nombre + "\". Deseas continuar?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            service.eliminar(id);
            cargarProductos();
            limpiarFormulario();
            mostrarEstado("Producto eliminado correctamente.", false);
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    private void registrarMovimiento(boolean esEntrada) {
        int row = tblProductos.getSelectedRow();
        if (row < 0) {
            mostrarError("Selecciona un producto para registrar el movimiento.");
            return;
        }

        String accion = esEntrada ? "entrada" : "salida";
        String valor = JOptionPane.showInputDialog(this, "Cantidad para la " + accion + ":");
        if (valor == null) {
            return;
        }

        try {
            int cantidad = parseInteger(valor, "La cantidad debe ser un numero entero");
            int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());

            if (esEntrada) {
                service.entradaStock(id, cantidad);
            } else {
                service.salidaStock(id, cantidad);
            }

            cargarProductos();
            seleccionarProductoEnTabla(id);
            mostrarEstado("Movimiento registrado correctamente.", false);
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    private void cargarProductoSeleccionadoEnFormulario() {
        int row = tblProductos.getSelectedRow();
        if (row < 0) {
            return;
        }

        int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
        Producto producto = service.buscar(id);
        if (producto != null) {
            poblarFormulario(producto);
        }
    }

    private void poblarFormulario(Producto producto) {
        txtId.setText(String.valueOf(producto.getId()));
        txtNombre.setText(producto.getNombre());
        txtCantidad.setText(String.valueOf(producto.getCantidad()));
        txtPrecio.setText(String.valueOf(producto.getPrecio()));
        txtUbicacion.setText(producto.getUbicacion());
        txtStockMinimo.setText(String.valueOf(producto.getStockMinimo()));
        txtBuscarId.setText(String.valueOf(producto.getId()));
    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");
        txtUbicacion.setText("");
        txtStockMinimo.setText("");
        txtBuscarId.setText("");
        tblProductos.clearSelection();
        mostrarEstado("Formulario listo para un nuevo registro.", false);
    }

    private int parseInteger(String text, String message) {
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }

    private double parseDouble(String text, String message) {
        try {
            return Double.parseDouble(text.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }

    private String requireText(String text, String message) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return text.trim();
    }

    private void mostrarError(String mensaje) {
        mostrarEstado("Error: " + mensaje, true);
        JOptionPane.showMessageDialog(this, mensaje, "Validacion", JOptionPane.WARNING_MESSAGE);
    }

    private void mostrarEstado(String mensaje, boolean esError) {
        lblEstado.setText(mensaje);
        lblEstado.setForeground(esError ? ALERT : UIManager.getColor("Label.foreground"));
    }
}
