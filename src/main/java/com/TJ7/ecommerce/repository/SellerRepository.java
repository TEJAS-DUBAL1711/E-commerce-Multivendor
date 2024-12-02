package com.TJ7.ecommerce.repository;

import com.TJ7.ecommerce.model.Seller;
import com.TJ7.ecommerce.multivendor.domain.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    Seller findByEmail(String email);

    List<Seller> findByAccountStatus(AccountStatus status);
}
