package com.studyhub.sth.unitarios.application.services;
import com.studyhub.sth.application.dtos.representante.RepresentanteCreateDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteUpdateDto;
import com.studyhub.sth.application.services.RepresentanteService;
import com.studyhub.sth.domain.entities.Empresa;
import com.studyhub.sth.domain.entities.Representante;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.repositories.IEmpresaRepository;
import com.studyhub.sth.domain.repositories.IRepresentanteRepository;
import com.studyhub.sth.domain.repositories.IUsuarioRepository;
import com.studyhub.sth.libs.mapper.IMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RepresentanteServiceTest {

    @Mock
    private IRepresentanteRepository representanteRepository;

    @Mock
    private IUsuarioRepository usuarioRepositorio;

    @Mock
    private IEmpresaRepository empresaRepository;

    @Mock
    private IMapper mapper;

    @InjectMocks
    private RepresentanteService representanteService;

    private RepresentanteCreateDto representanteCreateDto;
    private RepresentanteUpdateDto representanteUpdateDto;
    private Representante representante;
    private Empresa empresa;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Preparação dos objetos utilizados nos testes
        usuario = new Usuario();
        usuario.setNome("Test User");
        usuario.setEmail("testuser@example.com");

        empresa = new Empresa();
        empresa.setEmpresaId(UUID.randomUUID());

        representanteCreateDto = new RepresentanteCreateDto();
        representanteCreateDto.setEmpresaId(empresa.getEmpresaId());

        representanteUpdateDto = new RepresentanteUpdateDto();

        representante = new Representante();
        representante.setUsuario(usuario);
        representante.setEmpresa(empresa);
    }

    @Test
    void testCriarRepresentante_Success() throws ElementoNaoEncontradoExcecao {
        // Mocking dos métodos
        when(empresaRepository.findById(empresa.getEmpresaId())).thenReturn(Optional.of(empresa));
        when(representanteRepository.save(any(Representante.class))).thenReturn(representante);
        when(mapper.map(any(Representante.class), eq(RepresentanteDto.class))).thenReturn(new RepresentanteDto());

        // Execução do método
        RepresentanteDto representanteDto = representanteService.criarRepresentante(representanteCreateDto);

        // Verificações
        assertNotNull(representanteDto, "Deve retornar um DTO de Representante");
        verify(empresaRepository).findById(empresa.getEmpresaId());
        verify(representanteRepository).save(any(Representante.class));
    }

    @Test
    void testCriarRepresentante_EmpresaNaoEncontrada() {
        // Mocking do retorno da empresa como não encontrada
        when(empresaRepository.findById(empresa.getEmpresaId())).thenReturn(Optional.empty());

        // Execução e verificação de exceção
        ElementoNaoEncontradoExcecao exception = assertThrows(ElementoNaoEncontradoExcecao.class, () -> {
            representanteService.criarRepresentante(representanteCreateDto);
        });

        // Verificação do tipo da exceção
        assertEquals("Empresa não encontrada", exception.getMessage());
    }

    @Test
    void testObterRepresentantePorId_Success() {
        // Mocking do retorno do representante
        when(representanteRepository.findById(representante.getRepresentanteId())).thenReturn(Optional.of(representante));
        when(mapper.map(any(Representante.class), eq(RepresentanteDto.class))).thenReturn(new RepresentanteDto());

        // Execução do método
        RepresentanteDto representanteDto = representanteService.obterRepresentantePorId(representante.getRepresentanteId());

        // Verificações
        assertNotNull(representanteDto, "Deve retornar um DTO de Representante");
        verify(representanteRepository).findById(representante.getRepresentanteId());
    }

    @Test
    void testObterRepresentantePorId_RepresentanteNaoEncontrado() {
        // Mocking do retorno do representante como não encontrado
        when(representanteRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // Execução e verificação de exceção
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            representanteService.obterRepresentantePorId(UUID.randomUUID());
        });

        // Verificação do tipo da exceção
        assertEquals("Representante não encontrado", exception.getMessage());
    }

    @Test
    void testAtualizarRepresentante_Success() {
        // Mocking dos métodos
        when(representanteRepository.findById(representante.getRepresentanteId())).thenReturn(Optional.of(representante));
        when(representanteRepository.save(any(Representante.class))).thenReturn(representante);
        when(mapper.map(any(Representante.class), eq(RepresentanteDto.class))).thenReturn(new RepresentanteDto());

        // Execução do método
        RepresentanteDto representanteDto = representanteService.atualizarRepresentante(representante.getRepresentanteId(), representanteUpdateDto);

        // Verificações
        assertNotNull(representanteDto, "Deve retornar um DTO de Representante");
        verify(representanteRepository).findById(representante.getRepresentanteId());
        verify(representanteRepository).save(any(Representante.class));
    }

    @Test
    void testDeletarRepresentante_Success() {
        // Mocking do retorno do representante
        when(representanteRepository.findById(representante.getRepresentanteId())).thenReturn(Optional.of(representante));

        // Execução do método
        representanteService.deletarRepresentante(representante.getRepresentanteId());

        // Verificações
        verify(representanteRepository).delete(any(Representante.class));
    }

    @Test
    void testDeletarRepresentante_RepresentanteNaoEncontrado() {
        // Mocking do retorno do representante como não encontrado
        when(representanteRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // Execução e verificação de exceção
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            representanteService.deletarRepresentante(UUID.randomUUID());
        });

        // Verificação do tipo da exceção
        assertEquals("Representante não encontrado", exception.getMessage());
    }
}