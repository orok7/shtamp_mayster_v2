package eins.service;

import eins.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    void save(Invoice o);

    Invoice findOne(int id);

    List<Invoice> findAll();

    List<Invoice> findAllByBuyerId(int id);

    List<Invoice> findAllWithProductsByBuyerId(int id);

    List<Invoice> findAllWithBuyer();

    Invoice findOneWithProducts(int id);

    List<Invoice> findAllWithProducts();

}
