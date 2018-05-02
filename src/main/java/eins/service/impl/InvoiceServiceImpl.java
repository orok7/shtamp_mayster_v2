package eins.service.impl;

import eins.dao.InvoiceDao;
import eins.entity.Invoice;
import eins.entity.ProductToBuy;
import eins.service.InvoiceService;
import eins.service.ProductService;
import eins.service.ProductToBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceDao dbDAO;

    @Autowired
    ProductToBuyService pbService;

    @Autowired
    ProductService pService;

    @Override
    public void save(Invoice o) {
        dbDAO.save(o);
    }

    @Override
    public Invoice findOne(int id) {
        return dbDAO.findOne(id);
    }

    @Override
    public List<Invoice> findAll() {
        return dbDAO.findAll();
    }

    @Override
    public List<Invoice> findAllByBuyerId(int id) {
        return dbDAO.findAllByBuyerId(id);
    }

    @Override
    public List<Invoice> findAllWithProductsByBuyerId(int id) {
        return findAllWithProducts(dbDAO.findAllByBuyerId(id));
    }

    @Override
    public List<Invoice> findAllWithBuyer() {
        return dbDAO.findAllWithBuyer();
    }

    @Override
    public Invoice findOneWithProducts(int id) {
        Invoice invoice = dbDAO.findOne(id);
        List<ProductToBuy> listPB = pbService.findAll();

        List<ProductToBuy> listProd = listPB.stream()
                .filter(pb -> invoice.getId() == pb.getInvoice().getId())
                .collect(Collectors.toList());

        listProd.forEach(pb -> {
            pb.setProduct(pService.findOne(pb.getProduct().getId()));
        });

        invoice.setProducts(listProd);
        return invoice;
    }

    @Override
    public List<Invoice> findAllWithProducts() {
        return findAllWithProducts(dbDAO.findAll());
    }

    private List<Invoice> findAllWithProducts(List<Invoice> listInvoice){
        List<ProductToBuy> listPB = pbService.findAll();

        listInvoice.forEach(o -> {

            List<ProductToBuy> listProd = listPB.stream()
                    .filter(pb -> o.getId() == pb.getInvoice().getId())
                    .collect(Collectors.toList());

            listProd.forEach(pb -> {
                pb.setProduct(pService.findOne(pb.getProduct().getId()));
            });

            o.setProducts(listProd);
        });
        return listInvoice;
    }
}