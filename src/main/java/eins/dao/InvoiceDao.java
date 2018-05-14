package eins.dao;

import eins.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceDao extends JpaRepository<Invoice, Long> {

    @Query("select i from Invoice i left outer join fetch i.buyer")
    List<Invoice> findAllWithBuyer();

    List<Invoice> findAllByBuyerId(long id);

}
