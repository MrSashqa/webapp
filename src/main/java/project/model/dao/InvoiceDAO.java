package project.model.dao;

import project.model.entity.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceDAO {

    void insert(Invoice invoice);

    void updateStatus(Invoice invoice);

    List<Invoice> getAllByStatus(boolean paid);

    Optional<Invoice> getByClientIdAndStatus(int id, boolean paid);

}
