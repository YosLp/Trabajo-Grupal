package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.models.Loan;
import co.edu.udes.activity.backend.demo.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

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

    @PostMapping("/register")
    public ResponseEntity<String> registerLoan(
            @RequestParam Long userId,
            @RequestParam List<Long> materialIds,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime loanDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDate) {

        boolean success = loanService.registerLoan(materialIds, userId, loanDate, returnDate);
        return success ? ResponseEntity.ok("Préstamo registrado") : ResponseEntity.badRequest().body("Error al registrar el préstamo");
    }

    @PostMapping("/return/{loanId}")
    public ResponseEntity<String> returnMaterial(@PathVariable Long loanId) {
        boolean success = loanService.returnMaterial(loanId);
        return success ? ResponseEntity.ok("Material devuelto") : ResponseEntity.badRequest().body("No se encontró el préstamo");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Loan>> getLoansByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(loanService.getLoansByUser(userId));
    }

}
