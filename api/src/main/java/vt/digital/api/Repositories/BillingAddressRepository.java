package vt.digital.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vt.digital.api.Models.BillingAddress;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, Integer> {
}