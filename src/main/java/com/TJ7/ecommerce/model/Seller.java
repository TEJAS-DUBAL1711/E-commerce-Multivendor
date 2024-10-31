package com.TJ7.ecommerce.model;

import com.TJ7.ecommerce.multivendor.domain.USER_ROLE;
import com.TJ7.ecommerce.multivendor.domain.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seller {

    /**
     GenerationType.IDENTITY is used here because it is the most compatible with different database providers.
     The difference between GenerationType.IDENTITY and GenerationType.AUTO is that AUTO is a vendor independent
     way to generate a unique identifier, while IDENTITY relies on the database to generate the identifier.
     The difference between GenerationType.IDENTITY and GenerationType.SEQUENCE is that SEQUENCE is a database sequence
     which is shared by all the instances of the application, while IDENTITY is a database column which is auto-incremented
     for each record inserted. */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String sellerName;

    private String mobile;

    @Column(unique = true,nullable = false)
    private String email;

    private String password;

    /**
     * The @Embedded annotation is used to specify that a class will be embedded in another entity class.
     * In this context, it means that the fields of the BusinessDetails and BankDetails classes
     * will be mapped as if they were part of the Seller entity itself, rather than being mapped to a separate table.
     * This allows for better normalization and sharing of common fields across different entities.
     */
    @Embedded
    private BusinessDetails bussinessDetails=new BusinessDetails();

    @Embedded
    private BankDetails bankDetails=new BankDetails();

    @OneToOne(cascade = CascadeType.ALL)
    private  Address pickupAddress=new Address();


    /**
     * GSTIN or Goods and Services Tax Identification Number is a 15 digit unique number which is given to every
     * business that is registered under GST. It is used to identify businesses that are registered under the GST
     * regime. The format of GSTIN is as follows
     * <p>
     * The first 2 digits represent the state code of the business. The next 10 digits represent the PAN number of
     * the business. The 13th digit is allocated based on the number of registrations a business has within a state.
     * The 14th digit is set to "Z" by default. The 15th digit is a check code which is calculated based on the
     * previous 14 digits.
     * <p>
     * Here we are using String to store the GSTIN because it is a unique identifier and it is not a number in the
     * classical sense. It has to be validated and formatted according to the rules of GSTIN.
     */
    private String GSTIN;

    private USER_ROLE role;

    private boolean isEmailVerified=false;

    private AccountStatus accountStatus=AccountStatus.PENDING_VERIFICATION;


}
