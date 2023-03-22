package com.testePratico.simplesDental.service;

import com.testePratico.simplesDental.exception.NotFoundException;
import com.testePratico.simplesDental.model.Contato;
import com.testePratico.simplesDental.model.Profissional;
import com.testePratico.simplesDental.repository.ProfissionalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProfissionalServiceTest {

    @InjectMocks
    private ProfissionalService profissionalService;

    @Mock
    private ProfissionalRepository profissionalRepository;

    private Profissional profissional;
    private Optional<Profissional> profissionalOptional;
    private Optional<List<Profissional>> optionalList;
    private Contato contato;

    public static final Long ID = 1L;
    public static final String NOME = "Helder";
    public static final String CONTATO_FONE = "64982345678";
    public static final String CARGO = "Desenvolvedor";
    public static final LocalDate NASCIMENTO = LocalDate.parse("2023-03-05");
    public static final LocalDate CREATE_DATA = LocalDate.now();
    public static final Contato CONTATO = new Contato(ID, NOME, CONTATO_FONE);
    public static final List<Contato> CONTATOS = List.of(CONTATO);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciarObjetos();
    }

    @Test
    public void salvarProfissionalTest(){
        Mockito.when(profissionalRepository.save(Mockito.any())).thenReturn(profissional);
        Profissional profissionalSalvo = profissionalService.salvarProfissional(profissional);

        Assertions.assertNotNull(profissionalSalvo);
        Assertions.assertEquals(Profissional.class, profissionalSalvo.getClass());
        Assertions.assertNotEquals(profissionalSalvo.getNome(), "Maria");
        Assertions.assertNotEquals(profissionalSalvo.getNome(), CONTATO_FONE);
        Assertions.assertEquals(profissionalSalvo.getId(), ID);
        Assertions.assertEquals(profissionalSalvo.getNome(), NOME);
        Assertions.assertEquals(profissionalSalvo.getCargo(), CARGO);
        Assertions.assertEquals(profissionalSalvo.getCreate_data(), LocalDate.now());
    }

    @Test
    public void buscarProfissionalIDTest(){
        Mockito.when(profissionalRepository.findById(Mockito.anyLong())).thenReturn(profissionalOptional)
            .thenThrow(new NotFoundException("Profissional " + ID + " não encontrado"));

        try {
            Profissional response = profissionalService.buscarProfissionalID(ID);
            Assertions.assertNotNull(response);
            Assertions.assertEquals(Profissional.class, response.getClass());
            Assertions.assertNotEquals(response.getNome(), "Maria");
            Assertions.assertNotEquals(response.getNome(), CONTATO_FONE);
            Assertions.assertEquals(response.getId(), ID);
            Assertions.assertEquals(response.getNome(), NOME);
            Assertions.assertEquals(response.getCargo(), CARGO);
            Assertions.assertEquals(response.getCreate_data(), CREATE_DATA);

            profissionalService.buscarProfissionalID(10L);
        }catch (Exception e){
            Assertions.assertEquals(NotFoundException.class, e.getClass());
            Assertions.assertEquals("Profissional " + ID + " não encontrado", e.getMessage());
        }
    }

    @Test
    public void buscarProfissionaisTest(){
        Mockito.when(profissionalRepository.findByNomeLike(Mockito.anyString())).thenReturn(optionalList)
            .thenThrow(new NotFoundException("Profissional " + "er" + " não encontrado"));

        try{
            List<Profissional> profissionalList = profissionalService.buscarProfissionais("er");
            Assertions.assertNotNull(profissionalList);
            Assertions.assertEquals(Profissional.class, profissionalList.get(0).getClass());
            Assertions.assertNotEquals(profissionalList.get(0).getNome(), "Maria");
            Assertions.assertNotEquals(profissionalList.get(0).getNome(), CONTATO_FONE);
            Assertions.assertEquals(profissionalList.get(0).getId(), ID);
            Assertions.assertEquals(profissionalList.get(0).getNome(), NOME);
            Assertions.assertEquals(profissionalList.get(0).getCargo(), CARGO);
            Assertions.assertEquals(profissionalList.get(0).getCreate_data(), CREATE_DATA);

            profissionalService.buscarProfissionalID(10L);
        }catch (Exception e){
            Assertions.assertEquals(NotFoundException.class, e.getClass());
            Assertions.assertNotEquals("Profissional " + "er" + " não encontrado", e.getMessage());
            Assertions.assertEquals("Cargo " + "er" + " não encontrado", e.getMessage());
        }
    }

    @Test
    public void alterarProfissional(){
        profissional.setNome("testeAlteracao");
        Mockito.when(profissionalRepository.findById(Mockito.anyLong())).thenReturn(profissionalOptional);
        Mockito.when(profissionalRepository.save(Mockito.any())).thenReturn(profissional);

        Profissional profissionalAlterado = profissionalService.alterarProfissional(profissional);

        Assertions.assertNotNull(profissionalAlterado);
        Assertions.assertEquals(Profissional.class, profissionalAlterado.getClass());
        Assertions.assertNotEquals(profissionalAlterado.getNome(), "Maria");
        Assertions.assertNotEquals(profissionalAlterado.getNome(), CONTATO_FONE);
        Assertions.assertEquals(profissionalAlterado.getId(), ID);
        Assertions.assertEquals(profissionalAlterado.getNome(), "testeAlteracao");
        Assertions.assertEquals(profissionalAlterado.getCargo(), CARGO);
        Assertions.assertEquals(profissionalAlterado.getCreate_data(), LocalDate.now());
    }

    @Test
    public void excluirProfissional(){
        Mockito.when(profissionalRepository.findById(Mockito.anyLong())).thenReturn(profissionalOptional);
        Mockito.doNothing().when(profissionalRepository).deleteById(Mockito.anyLong());
        profissionalService.excluirProfissional(ID);
        Mockito.verify(profissionalRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    private void iniciarObjetos(){
        profissional = new Profissional(ID, NOME, CARGO, NASCIMENTO, CREATE_DATA, CONTATOS);
        profissionalOptional = Optional.of(new Profissional(ID, NOME, CARGO, NASCIMENTO, CREATE_DATA, CONTATOS));
        contato = new Contato(ID, NOME, CONTATO_FONE);
        optionalList = Optional.of(List.of(profissional));
    }
}