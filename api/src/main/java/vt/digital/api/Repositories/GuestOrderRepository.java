package vt.digital.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vt.digital.api.Models.GuestOrder;

@Repository
public interface GuestOrderRepository extends JpaRepository<GuestOrder, Integer> {
    GuestOrder findByOrderOrderId(Integer orderId);
}
