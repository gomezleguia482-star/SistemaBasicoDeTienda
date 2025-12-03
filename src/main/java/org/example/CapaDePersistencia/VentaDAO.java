package org.example.CapaDePersistencia;

import org.example.CapaDeModelos.CarpetaArticulo.Articulo;
import org.example.CapaDeModelos.CarpetaCliente.Cliente;
import org.example.CapaDeModelos.CarpetaProducto.Producto;
import org.example.CapaDeModelos.CarpetaProducto.ProductoAlimento;
import org.example.CapaDeModelos.CarpetaProducto.ProductoElectronico;
import org.example.CapaDeModelos.CarpetaProducto.ProductoRopa;
import org.example.CapaDeModelos.CarpetaVenta.Venta;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class VentaDAO {

    private static final String SQL_REPORTE_VENTAS =
            "SELECT v.id_venta, v.fecha_venta, v.total_general, " +
            "c.id_cliente, c.nombre_cliente AS nombre_cliente, c.email_cliente AS email_cliente, " +
            "p.tipo_producto, p.id_producto, p.nombre_producto, p.stock_producto, p.precio_producto, " +
            "p.fecha_vencimiento, p.meses_garantia, p.color, p.talla, " +
            "dv.cantidad, dv.precio_unitario " +
            "FROM Ventas v " +
            "JOIN Clientes c ON v.id_cliente = c.id_cliente " +
            "JOIN Detalle_Venta dv ON v.id_venta = dv.id_venta " +
            "JOIN productos p ON dv.id_producto = p.id_producto " +
            "ORDER BY v.id_venta, p.id_producto";

    public static void guardarVenta(Venta venta) {
        String sqlVenta = "INSERT INTO Ventas (id_cliente, fecha_venta, total_general) VALUES (?, ?, ?)";
        String sqlDetalle = "INSERT INTO Detalle_Venta (id_venta, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = ManejadorDB.leerDB();
            connection.setAutoCommit(false);

            try (PreparedStatement psVenta = connection.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
                psVenta.setInt(1, venta.getIdCliente());
                psVenta.setDate(2, Date.valueOf(venta.getFechaVenta()));
                psVenta.setDouble(3, venta.getTotalVenta());
                psVenta.executeUpdate();

                try (ResultSet generatedKeys = psVenta.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idVentaGenerado = generatedKeys.getInt(1);

                        try (PreparedStatement psDetalle = connection.prepareStatement(sqlDetalle)) {
                            for (Articulo articulo : venta.getListaArticulos()) {
                                Producto producto = ProductoDAO.buscarProductoId(articulo.getIdProducto());
                                if (producto != null) {
                                    psDetalle.setInt(1, idVentaGenerado);
                                    psDetalle.setInt(2, articulo.getIdProducto());
                                    psDetalle.setInt(3, articulo.getCantidadSell());
                                    psDetalle.setDouble(4, producto.getPrecio());
                                    psDetalle.addBatch();
                                }
                            }
                            psDetalle.executeBatch();
                        }
                    } else {
                        throw new SQLException("Creating sale failed, no ID obtained.");
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static ArrayList<Venta> leerVentasConDetalles() {
        ArrayList<Venta> reporte = new ArrayList<>();
        Venta ventaActual = null;
        int idVentaAnterior = -1;

        try (Connection connection = ManejadorDB.leerDB();
             PreparedStatement ps = connection.prepareStatement(SQL_REPORTE_VENTAS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idVentaActual = rs.getInt("id_venta");

                if (idVentaActual != idVentaAnterior) {
                    if (ventaActual != null) {
                        reporte.add(ventaActual);
                    }

                    int idCliente = rs.getInt("id_cliente");
                    String nombreCliente = rs.getString("nombre_cliente");
                    String emailCliente = rs.getString("email_cliente");
                    Cliente cliente = new Cliente(idCliente, nombreCliente, emailCliente);

                    Timestamp fechaSql = rs.getTimestamp("fecha_venta");
                    LocalDate fechaVenta = fechaSql.toLocalDateTime().toLocalDate();
                    double totalGeneral = rs.getDouble("total_general");

                    ventaActual = new Venta(idVentaActual, cliente.getId(), new ArrayList<>(), totalGeneral, fechaVenta);
                    idVentaAnterior = idVentaActual;
                }

                int idProducto = rs.getInt("id_producto");
                int cantidad = rs.getInt("cantidad");
                Articulo articulo = new Articulo(idProducto, cantidad);

                if (ventaActual != null) {
                    ventaActual.getListaArticulos().add(articulo);
                }
            }

            if (ventaActual != null) {
                reporte.add(ventaActual);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reporte;
    }

}
