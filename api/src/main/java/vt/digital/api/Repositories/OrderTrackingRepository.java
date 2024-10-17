package vt.digital.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vt.digital.api.Models.OrderTracking;

@Repository
public interface OrderTrackingRepository extends JpaRepository<OrderTracking, Integer> {
    OrderTracking findByOrderOrderId(Integer orderId);
}
