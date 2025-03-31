package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Loan;
import co.edu.udes.activity.backend.demo.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    
    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public Optional<Loan> getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        return loanService.saveLoan(loan);
    }

    @PutMapping("/{id}")
    public Loan updateLoan(@PathVariable Long id, @RequestBody Loan updatedLoan) {
        return loanService.updateLoan(id, updatedLoan);
    }

    @DeleteMapping("/{id}")
    public String deleteLoan(@PathVariable Long id) {
        boolean deleted = loanService.deleteLoan(id);
        return deleted ? "Préstamo eliminado correctamente" : "No se encontró el préstamo con ID: " + id;
    }
}
