package co.edu.udes.activity.backend.demo.repositories;

import co.edu.udes.activity.backend.demo.models.Loan;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository <Loan,Long>{
   List<Loan> findByUserId(Long userId);
    
} 