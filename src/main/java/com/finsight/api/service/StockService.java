package com.finsight.api.service;

import com.finsight.api.entity.Stock;
import com.finsight.api.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService{

    private final StockRepository stockRepository;

    //Tüm hisseleri getirsin
    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }

    //ID ile hisse bulsun
    public Optional<Stock> getStockById(Long id){
        return stockRepository.findById(id);
    }

    // Symbol ile hisse bul (örn: "THYAO")
    public Optional<Stock> getStockBySymbol(String symbol) {
        return stockRepository.findBySymbol(symbol.toUpperCase());
    }

    //hisse ekleyebilelim
    public Stock createStock(Stock stock){
        stock.setSymbol(stock.getSymbol().toUpperCase());

        //zaten var mı diye kontrol
        if(stockRepository.existsBySymbol(stock.getSymbol())){
            throw new RuntimeException("Bu sembol zaten mevcut: " + stock.getSymbol());
        }

        //oluşturma zamanını set edelim
        stock.setLastUpdated(LocalDateTime.now());
        return stockRepository.save(stock);
    }


    // Hisse güncelle
    public Stock updateStock(Long id, Stock updatedStock) {
        Stock existingStock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hisse bulunamadı: " + id));

        existingStock.setName(updatedStock.getName());
        existingStock.setSector(updatedStock.getSector());
        existingStock.setCurrentPrice(updatedStock.getCurrentPrice());
        existingStock.setLastUpdated(LocalDateTime.now());

        return stockRepository.save(existingStock);
    }

    //hisse sil
    public void deleteStock(Long id){
        if(!stockRepository.existsById(id)){
            throw new RuntimeException("Hisse Bulunamadı: " + id);
        }

        stockRepository.deleteById(id);
    }


    public Long getStockCount(){
        return stockRepository.count();
    }


}
