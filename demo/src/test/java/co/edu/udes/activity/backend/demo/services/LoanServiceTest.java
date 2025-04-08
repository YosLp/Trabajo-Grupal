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
    public void testUpdateLoan() {
        Loan original = new Loan();
        original.setId(1L);

        Loan actualizado = new Loan();
        actualizado.setLoanDate(LocalDateTime.of(2024, 5, 1, 10, 0));
        actualizado.setReturnDate(LocalDateTime.of(2024, 5, 2, 10, 0));

        Student estudiante = new Student();
        estudiante.setId(100L);
        actualizado.setStudent(estudiante);

        Material material = new Material();
        material.setId(200L);
        actualizado.setMaterials(Set.of(material));

        when(loanRepository.findById(1L)).thenReturn(Optional.of(original));
        when(loanRepository.save(original)).thenReturn(original);

        Loan resultado = loanService.updateLoan(1L, actualizado);

        assertNotNull(resultado);
        assertEquals(actualizado.getLoanDate(), resultado.getLoanDate());
        assertEquals(actualizado.getReturnDate(), resultado.getReturnDate());
        assertEquals(actualizado.getStudent(), resultado.getStudent());
        assertEquals(actualizado.getMaterials(), resultado.getMaterials());
        verify(loanRepository).save(original);
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
