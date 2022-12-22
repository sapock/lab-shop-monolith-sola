package labshopmonolithsola.domain;

import labshopmonolithsola.domain.OrderPlaced;
import labshopmonolithsola.MonolithApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Order_table")
@Data

public class Order  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String productId;
    
    private Integer qty;
    
    private String customerId;

    private Double amount;

    @PostPersist
    public void onPostPersist(){
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        labshopmonolithsola.external.DecreaseStockCommand decreaseStockCommand = new labshopmonolithsola.external.DecreaseStockCommand();
        decreaseStockCommand.setQty(getQty()); // 전달받은 qty 를 command 에 set 한다. 
        // mappings goes here
        MonolithApplication.applicationContext.getBean(labshopmonolithsola.external.InventoryService.class)
            .decreaseStock(Long.valueOf(getProductId()), decreaseStockCommand); // decreaseStock 에 productId 를 파라미터로 추가 

        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();

    }
    @PrePersist
    public void onPrePersist(){
    }

    public static OrderRepository repository(){
        OrderRepository orderRepository = MonolithApplication.applicationContext.getBean(OrderRepository.class);
        return orderRepository;
    }






}
