package com.finsight.api.repository;


import com.finsight.api.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

    //symbole göre hisse bulsun
    Optional<Stock> findBySymbol(String symbol);

    //symbol var mı kontrol etsin
    boolean existsBySymbol(String symbol);

}
