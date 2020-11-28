package io.github.monthalcantara.agenciaviagem.aeroporto;

import io.github.monthalcantara.agenciaviagem.pais.Pais;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;

public class NovoAeroportoRequestTest {

    private Pais pais = new Pais("Brasil");
    private NovoAeroportoRequest aeroportoRequest = new NovoAeroportoRequest("SSA", 1L);
    private EntityManager manager = Mockito.mock(EntityManager.class);

    @Test
    @DisplayName("Deve converter um aeroportoRequest em aeroporto")
    void toModelTest() {

        Mockito.when(manager.find(Pais.class, 1L)).thenReturn(pais);
        Aeroporto aeroporto = aeroportoRequest.toModel(manager);
        Assertions.assertNotNull(aeroporto);
    }

    @Test
    @DisplayName("Não deveria criar um aeroporto se o Pais não existe")
    void toModelTest2(){
        Mockito.when(manager.find(Pais.class, 1L)).thenReturn(null);
        ResponseStatusException responseStatusException = Assertions.assertThrows(ResponseStatusException.class, () -> aeroportoRequest.toModel(manager));
        Assertions.assertTrue(responseStatusException.getReason().contains("Não foi encontrado País com o id informado"));
    }
}
