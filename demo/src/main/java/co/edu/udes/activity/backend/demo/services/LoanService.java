package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Loan;
import co.edu.udes.activity.backend.demo.models.Material;
import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.repositories.LoanRepository;
import co.edu.udes.activity.backend.demo.repositories.MaterialRepository;
import co.edu.udes.activity.backend.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan updateLoan(Long id, Loan updatedLoan) {
        return loanRepository.findById(id).map(loan -> {
            loan.setLoanDate(updatedLoan.getLoanDate());
            loan.setReturnDate(updatedLoan.getReturnDate());
            loan.setUser(updatedLoan.getUser());
            loan.setMaterials(updatedLoan.getMaterials());
            return loanRepository.save(loan);
        }).orElse(null);
    }

    public boolean deleteLoan(Long id) {
        if (loanRepository.existsById(id)) {
            loanRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean registerLoan(List<Long> materialIds, Long userId, LocalDateTime loanDate, LocalDateTime returnDate) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return false;
        }

        Set<Material> materials = new HashSet<>();

        for(Long id: materialIds) {
            materialRepository.findById(id).ifPresent(materials::add);
        }

        if(materials.isEmpty()){
            return false;
        }

        Loan loan = new Loan();
        loan.setUser(optionalUser.get());
        loan.setMaterials(materials);
        loan.setLoanDate(loanDate);
        loan.setReturnDate(returnDate);
        loan.setStatus("Activo");

        loanRepository.save(loan);
        return true;
    }

    public boolean returnMaterial(Long loanId) {
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        if (optionalLoan.isPresent()) {
            Loan loan = optionalLoan.get();
            loan.setStatus("Devuelto");
            loanRepository.save(loan);
            return true;
        }
        return false;
    }

    public List<Loan> getLoansByUser(Long userId) {
        return loanRepository.findByUserId(userId);
    }

}
