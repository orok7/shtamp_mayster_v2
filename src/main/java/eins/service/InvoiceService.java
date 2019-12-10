package eins.service;

import eins.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    void save(Invoice o);

    Invoice findOne(Long id);

    List<Invoice> findAll();

    List<Invoice> findAllByBuyerId(Long id);

    List<Invoice> findAllWithProductsByBuyerId(Long id);

    List<Invoice> findAllWithBuyer();

    Invoice findOneWithProducts(Long id);

    List<Invoice> findAllWithProducts();

}
