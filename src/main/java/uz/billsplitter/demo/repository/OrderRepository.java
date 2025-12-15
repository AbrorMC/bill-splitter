package uz.billsplitter.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.billsplitter.demo.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
