package co.edu.udes.activity.backend.demo.controllers;

import co.edu.udes.activity.backend.demo.dto.LoanDTO;
import co.edu.udes.activity.backend.demo.dto.LoanRequestDTO;
import co.edu.udes.activity.backend.demo.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<LoanDTO> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanRequestDTO dto) {
        LoanDTO created = loanService.saveLoan(dto);
        return created != null ? ResponseEntity.ok(created) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanDTO> updateLoan(@PathVariable Long id, @RequestBody LoanRequestDTO dto) {
        LoanDTO updated = loanService.updateLoan(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long id) {
        return loanService.deleteLoan(id)
                ? ResponseEntity.ok("Préstamo eliminado correctamente")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el préstamo con ID: " + id);
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
    public ResponseEntity<List<LoanDTO>> getLoansByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(loanService.getLoansByUser(userId));
    }
}

