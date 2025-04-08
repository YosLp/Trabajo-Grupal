package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Loan;
import co.edu.udes.activity.backend.demo.models.Material;
import co.edu.udes.activity.backend.demo.models.Student;
import co.edu.udes.activity.backend.demo.repositories.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoanServiceTest {

    private LoanRepository loanRepository;
    private LoanService loanService;

    @BeforeEach
    public void setUp() {

        loanRepository = mock(LoanRepository.class);
        loanService = new LoanService();
        loanService = Mockito.spy(loanService);
        loanService.loanRepository = loanRepository;

    }
}

    @Test
    public void testGetAllLoans(){




    }
