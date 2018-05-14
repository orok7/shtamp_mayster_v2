package eins.service;

import eins.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    void save(Invoice o);

    Invoice findOne(long id);

    List<Invoice> findAll();

    List<Invoice> findAllByBuyerId(long id);

    List<Invoice> findAllWithProductsByBuyerId(long id);

    List<Invoice> findAllWithBuyer();

    Invoice findOneWithProducts(long id);

    List<Invoice> findAllWithProducts();

}
