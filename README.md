# lab-shop-monolith-sola

'''
   mvn clean spring-boot:run
   http :8082/inventories id=1 stock=10   # 재고 추가
   http :8081/orders productId=1 qty=10   # 주문 
   http :8082/inventories/1  # 1번 항목 재고 조회  - inventories .. 라는 이름은 springboot 에서 자동 생성 (aggregate 의 복수형으로)
   http :8081/orders    #주문 목록 조회 
'''