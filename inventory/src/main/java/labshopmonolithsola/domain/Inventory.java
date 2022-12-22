package labshopmonolithsola.domain;

import labshopmonolithsola.InventoryApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Inventory_table")
@Data

public class Inventory  {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)    
    private Long id;
    
    private Long stock;

    @PostPersist
    public void onPostPersist(){
    }

    public static InventoryRepository repository(){
        InventoryRepository inventoryRepository = InventoryApplication.applicationContext.getBean(InventoryRepository.class);
        return inventoryRepository;
    }



    public void decreaseStock(DecreaseStockCommand decreaseStockCommand){
        if( getStock() < decreaseStockCommand.getQty() ){
            System.out.println(" No enough Stock!!! ~~~ ");
        }
        setStock( getStock() - decreaseStockCommand.getQty()); // 주문한 qty 만큼 stock 감소
    }



}
