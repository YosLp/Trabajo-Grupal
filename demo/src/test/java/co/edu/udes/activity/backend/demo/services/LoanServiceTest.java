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
    public void GetAllLoans() {
        Loan prestamo = new Loan();

        when(loanRepository.findAll()).thenReturn(List.of(prestamo));

        List<Loan> resultado = loanService.getAllLoans();

        assertEquals(1, resultado.size());
        verify(loanRepository).findAll();


    }

    @Test
    public void testGetLoanById() {
        Loan prestamo = new Loan();
        prestamo.setId(1L);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(prestamo));

        Optional<Loan> resultado = loanService.getLoanById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        verify(loanRepository).findById(1L);
    }

    @Test
    public void testSaveLoan() {
        Loan prestamo = new Loan();
        when(loanRepository.save(prestamo)).thenReturn(prestamo);

        Loan resultado = loanService.saveLoan(prestamo);

        assertNotNull(resultado);
        verify(loanRepository).save(prestamo);
    }


    @Test
    public void testDeleteLoan() {
        when(loanRepository.existsById(1L)).thenReturn(true);
        boolean resultado = loanService.deleteLoan(1L);

        assertTrue(resultado);
        verify(loanRepository).deleteById(1L);
    }

    @Test
    public void testDeleteLoanNotFound() {
        when(loanRepository.existsById(2L)).thenReturn(false);
        boolean resultado = loanService.deleteLoan(2L);

        assertFalse(resultado);
        verify(loanRepository, never()).deleteById(2L);
    }



}
