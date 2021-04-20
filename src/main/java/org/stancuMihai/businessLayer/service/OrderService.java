package org.stancuMihai.businessLayer.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.stancuMihai.businessLayer.util.PdfInitializer;
import org.stancuMihai.dataAccessLayer.AbstractDao;
import org.stancuMihai.model.Client;
import org.stancuMihai.model.Product;
import org.stancuMihai.model.ProductOrder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/***
 *
 * * The business logic for the Order Class
 */
public class OrderService {

    public static AbstractDao<ProductOrder> orderDataAccessService;
    public static OrderService orderService = null;
    public static ProductService productService = ProductService.getInstance();
    public static ClientService clientService = ClientService.getInstance();
    private static int receiptCount = 0;

    private OrderService() {
        OrderService.orderDataAccessService = new AbstractDao<>(ProductOrder.class);
    }

    /***
     *
     * @return it returns singleton instance of OrderService
     */
    public static OrderService getInstance() {
        if (orderService == null) {
            orderService = new OrderService();
        }
        return orderService;
    }

    public ProductOrder findById(Integer id) throws SQLException {
        return orderDataAccessService.findById(id);
    }

    public ProductOrder update(Integer id, ProductOrder model) throws SQLException {
        return orderDataAccessService.update(id, model);
    }

    /***
     * Method which creates PDF receipt
     * @param id client id
     */
    public void createReceipt(int id) throws SQLException, DocumentException, FileNotFoundException {
        List<ProductOrder> clientProductOrder = selectAll().stream().filter(s -> s.getClientId().equals(id)).collect(Collectors.toList());
        List<Product> products = new ArrayList<>();
        for (ProductOrder productOrder : clientProductOrder) {
            products.add(productService.findById(productOrder.getProductId()));
        }
        Client client = clientService.findById(id);
        Document document = new Document();
        receiptCount++;
        PdfWriter.getInstance(document, new FileOutputStream("Receipt" + receiptCount));
        document.open();
        PdfPTable table = new PdfPTable(3);
        PdfInitializer.addTableHeader(table);
        PdfInitializer.addRows(table, client.getId() + " " + client.getName());
        StringBuilder orderString = new StringBuilder();
        int productOrderIndex = 0;
        for (Product product : products) {
            orderString.append(product.getName()).append(" ").append(product.getPrice()).append(" ")
                    .append(clientProductOrder.get(productOrderIndex).getQuantity()).append("\n");
            productOrderIndex++;
        }
        PdfInitializer.addRows(table, orderString.toString());
        PdfInitializer.addRows(table, String.valueOf(getTotalSumId(id)));
        document.add(table);
        document.close();
    }

    /***
     *
     * @param id client id to be processed
     * @return returns the total of of the client
     */
    public Double getTotalSumId(Integer id) throws SQLException {
        List<ProductOrder> orders = selectAll();
        List<ProductOrder> clientRelated = orders.stream().filter(s -> s.getClientId().equals(id)).collect(Collectors.toList());
        double orderPrice = 0;
        int i = 0;
        for (ProductOrder productOrder : clientRelated) {
            orderPrice = orderPrice + productService.findById(productOrder.getProductId()).getPrice() * clientRelated.get(i).getQuantity();
            i++;
        }
        return orderPrice;
    }

    public ProductOrder create(ProductOrder model) throws SQLException {
        Product product = productService.findById(model.getProductId());
        int remainder = product.getQuantity() - model.getQuantity();
        product.setQuantity(remainder);
        if (remainder > 0) {
            productService.update(product.getId(), product);
            return orderDataAccessService.create(model);
        } else return null;
    }

    public ProductOrder delete(Integer id) throws SQLException {
        return orderDataAccessService.delete(id);
    }

    public List<ProductOrder> selectAll() throws SQLException {
        return orderDataAccessService.selectAll();
    }

}


