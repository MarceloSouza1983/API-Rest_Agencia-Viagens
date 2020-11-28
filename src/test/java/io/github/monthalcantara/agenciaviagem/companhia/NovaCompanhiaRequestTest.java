package io.github.monthalcantara.agenciaviagem.companhia;

import io.github.monthalcantara.agenciaviagem.pais.Pais;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;

public class NovaCompanhiaRequestTest {

    private EntityManager manager = Mockito.mock(EntityManager.class);
    private Pais pais = new Pais("Brasil");
    private NovaCompanhiaRequest companhiaRequest = new NovaCompanhiaRequest("Azul", 1L);

    @Test
    @DisplayName("Deveria converter uma companhiaRequest em Companhia")
    void toModelTest() {

        Mockito.when(manager.find(Pais.class, 1L)).thenReturn(pais);

        Companhia companhia = companhiaRequest.toModel(manager);
        Assertions.assertNotNull(companhia);
    }

    @Test
    @DisplayName("Não deveria criar uma Compainha ao passar um Pais inexistente")
    void toModelTest2() {
        Mockito.when(manager.find(Pais.class, 1L)).thenReturn(null);
        IllegalStateException illegalStateException = Assertions.assertThrows(IllegalStateException.class, () -> companhiaRequest.toModel(manager));
        Assertions.assertTrue(illegalStateException.getMessage().contains("Validação externa falhou e chegou um id sem país aqui no toModel"));
    }
}
