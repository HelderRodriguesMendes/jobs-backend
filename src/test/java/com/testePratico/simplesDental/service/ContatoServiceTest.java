package com.testePratico.simplesDental.service;

import com.testePratico.simplesDental.exception.NotFoundException;
import com.testePratico.simplesDental.model.Contato;
import com.testePratico.simplesDental.repository.ContatoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ContatoServiceTest {

    @InjectMocks
    private ContatoService contatoService;

    @Mock
    private ContatoRepository contatoRepository;

    public static final Long ID = 1L;
    public static final String NOME = "Helder";
    public static final String CONTATO = "64934564832";

    private Contato contato;
    private Optional<Contato> contatoOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciarObjetos();
    }

    @Test
    public void salvarContatoTest(){
        Mockito.when(contatoRepository.save(Mockito.any())).thenReturn(contato);
        Contato contatoSalvo = contatoService.salvarContato(contato);

        Assertions.assertNotNull(contatoSalvo);
        Assertions.assertEquals(Contato.class, contatoSalvo.getClass());
        Assertions.assertNotEquals(contatoSalvo.getNome(), "Maria");
        Assertions.assertNotEquals(contatoSalvo.getNome(), CONTATO);
        Assertions.assertEquals(contatoSalvo.getId(), ID);
        Assertions.assertEquals(contatoSalvo.getNome(), NOME);
        Assertions.assertEquals(contatoSalvo.getContato(), CONTATO);
    }

    @Test
    public void buscarContatoIDTest(){
        Mockito.when(contatoRepository.findById(Mockito.anyLong()))
            .thenReturn(contatoOptional).thenThrow(new NotFoundException(
                "Contato " + ID + " não encontrado"));
       try{
           Contato response = contatoService.buscarContatoID(ID);

           Assertions.assertNotNull(response);
           Assertions.assertEquals(Contato.class, response.getClass());
           Assertions.assertEquals(response.getId(), ID);
           Assertions.assertNotEquals(response.getNome(), "Maria");
           Assertions.assertNotEquals(response.getNome(), CONTATO);
           Assertions.assertEquals(response.getNome(), NOME);
           Assertions.assertEquals(response.getContato(), CONTATO);

           contatoService.buscarContatoID(10L);
       }catch (Exception e){
           Assertions.assertEquals(NotFoundException.class, e.getClass());
           Assertions.assertEquals("Contato " + ID + " não encontrado", e.getMessage());
       }
    }

    @Test
    public void buscarContatosTest(){
        Mockito.when(contatoRepository.buscarContatos_nome(Mockito.anyString()))
            .thenReturn(List.of(contato)).thenThrow(new NotFoundException("Contato "
            + ID + " não encontrado"));

        try{
            List<Contato> contatoList = contatoService.buscarContatos("er");

            Assertions.assertNotNull(contatoList);
            Assertions.assertEquals(Contato.class, contatoList.get(0).getClass());
            Assertions.assertEquals(contatoList.get(0).getId(), ID);
            Assertions.assertNotEquals(contatoList.get(0).getNome(), "Maria");
            Assertions.assertNotEquals(contatoList.get(0).getNome(), CONTATO);
            Assertions.assertEquals(contatoList.get(0).getNome(), NOME);
            Assertions.assertEquals(contatoList.get(0).getContato(), CONTATO);

            contatoService.buscarContatos("1");
        }catch (Exception e){
            Assertions.assertEquals(NotFoundException.class, e.getClass());
            Assertions.assertEquals("Contato " + "1" + " não encontrado", e.getMessage());
        }
    }

    @Test
    public void editarContatoTest(){
        contato.setNome("testeAlteracao");
        Mockito.when(contatoRepository.findById(Mockito.anyLong())).thenReturn(contatoOptional);
        Mockito.when(contatoRepository.save(Mockito.any())).thenReturn(contato);

        Contato contatoSalvo = contatoService.editarContato(contato);

        Assertions.assertNotNull(contatoSalvo);
        Assertions.assertEquals(Contato.class, contatoSalvo.getClass());
        Assertions.assertNotEquals(contatoSalvo.getNome(), "Maria");
        Assertions.assertNotEquals(contatoSalvo.getNome(), CONTATO);
        Assertions.assertEquals(contatoSalvo.getId(), ID);
        Assertions.assertEquals(contatoSalvo.getNome(), "testeAlteracao");
        Assertions.assertEquals(contatoSalvo.getContato(), CONTATO);
    }

    @Test
    public void excluirContatoTest(){
        Mockito.when(contatoRepository.findById(Mockito.anyLong())).thenReturn(contatoOptional);
        Mockito.doNothing().when(contatoRepository).deleteById(Mockito.anyLong());

        contatoService.excluirContato(ID);
        Mockito.verify(contatoRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    private void iniciarObjetos(){
        contato = new Contato(ID, NOME, CONTATO);
        contatoOptional = Optional.of(new Contato(ID, NOME, CONTATO));
    }
}