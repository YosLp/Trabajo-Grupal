package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.dto.LoanDTO;
import co.edu.udes.activity.backend.demo.dto.LoanRequestDTO;
import co.edu.udes.activity.backend.demo.models.Loan;
import co.edu.udes.activity.backend.demo.models.Material;
import co.edu.udes.activity.backend.demo.models.User;
import co.edu.udes.activity.backend.demo.repositories.LoanRepository;
import co.edu.udes.activity.backend.demo.repositories.MaterialRepository;
import co.edu.udes.activity.backend.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<LoanDTO> getAllLoans() {
        return loanRepository.findAll()
                .stream()
                .map(loan -> modelMapper.map(loan, LoanDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<LoanDTO> getLoanById(Long id) {
        return loanRepository.findById(id)
                .map(loan -> modelMapper.map(loan, LoanDTO.class));
    }

    public LoanDTO saveLoan(LoanRequestDTO dto) {
        Loan loan = new Loan();
        Optional<User> userOpt = userRepository.findById(dto.getUserId());

        if (userOpt.isEmpty()) return null;

        Set<Material> materiales = dto.getMaterialsId().stream()
                .map(materialRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        if (materiales.isEmpty()) return null;

        loan.setLoanDate(dto.getLoanDate());
        loan.setReturnDate(dto.getReturnDate());
        loan.setUser(userOpt.get());
        loan.setMaterials(materiales);
        loan.setStatus(dto.getStatus());

        return modelMapper.map(loanRepository.save(loan), LoanDTO.class);
    }

    public LoanDTO updateLoan(Long id, LoanRequestDTO dto) {
        return loanRepository.findById(id).map(loan -> {
            loan.setLoanDate(dto.getLoanDate());
            loan.setReturnDate(dto.getReturnDate());
            loan.setStatus(dto.getStatus());

            userRepository.findById(dto.getUserId()).ifPresent(loan::setUser);

            Set<Material> materiales = dto.getMaterialsId().stream()
                    .map(materialRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());

            if (!materiales.isEmpty()) {
                loan.setMaterials(materiales);
            }

            return modelMapper.map(loanRepository.save(loan), LoanDTO.class);
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
        if (optionalUser.isEmpty()) return false;

        Set<Material> materials = materialIds.stream()
                .map(materialRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        if (materials.isEmpty()) return false;

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
        return loanRepository.findById(loanId).map(loan -> {
            loan.setStatus("Devuelto");
            loanRepository.save(loan);
            return true;
        }).orElse(false);
    }

    public List<LoanDTO> getLoansByUser(Long userId) {
        return loanRepository.findByUserId(userId).stream()
                .map(loan -> modelMapper.map(loan, LoanDTO.class))
                .collect(Collectors.toList());
    }
}

