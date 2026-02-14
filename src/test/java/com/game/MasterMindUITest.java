package com.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

import static java.awt.SystemColor.text;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MasterMindUITest {

    @Mock
    MasterMindLogic mockLogic;

    @Spy
    MasterMindUI spyMasterMindUI;

    //
    Color[] palette = {
            new Color(240, 17, 17),
            new Color(104, 227, 70),
            new Color(45, 64, 189),
            new Color(191, 83, 40),
            };
    String[] labels = {"R", "V", "A", "M"};


    @BeforeEach
    void setUp() {
        //Instanciamos la ui y le pasamos el mock por parámetros
        MasterMindUI masterMindUI = new MasterMindUI(palette,labels,10,mockLogic);
        //Convertimos la ui "real" en el SPY
        spyMasterMindUI = spy(masterMindUI);
        //Evitamos que salten ventanas emergentes durante el test
        lenient().doNothing().when(spyMasterMindUI).showMessage(anyString());
    }

    /**TEST varios DE LA FUNCIÓN tryToCheck()**/
    @Test
    void testTryToCheck_IncompleteGuessArray(){
        //Given
            //Si no le pasamos nada
        //When
            //e intentamos comprobar un input vacío
        spyMasterMindUI.tryToCheck();
        //Then
            //verify es un metodo de mockito que comprueba si se ha ejecutado una función
            // se llamó al metodo showMess y mostró el siguiente mensaje?
        verify(spyMasterMindUI).showMessage(contains("Fill all slots"));
            //si el anterior verify pasa el test, verificamos lo siguiente:
            //no se llama nunca al metodo real checkGuess sin importar los parámetros
        verify(mockLogic, never()).checkGuess(any()); //el test se detiene antes de dejar pasar bugs
            //comprobamos que la variable que controla la fila no ha aumentado
            //no se puede pasar turno si no rellenas el guessArray
        assertEquals(0,spyMasterMindUI.currentRow, "Rellena los colores");
    }

    @Test
    void testTryToCheck_CompleteGuessArray_AdvanceRow(){
        //Given
            //guardamos en un array de circulos la referencia de la fila actual
        MasterMindUI.Circle[] fila = spyMasterMindUI.guessRows.get(0);
            //por cada circulo de guessRows lo pintamos de rojo
        for (MasterMindUI.Circle c : fila){
            c.setCircleColor(Color.RED);
        }
            //cuando se llame a la funcion CheckGuess sin importar sus parametros
            //devuelveme una simulación de un resultado de logica
            // -> en este caso ningun acierto, pero respuesta valida porque esta completa
        when(mockLogic.checkGuess(any())).thenReturn(new MasterMindLogic.Result(0,0));

        //When
            //llamamos al metodo tryToCheck que contiene la logica que pinta el array de negras y blancas
        spyMasterMindUI.tryToCheck();
        //Then
            //verificamos que se ha llamado a la funcion checkGuess
        verify(mockLogic).checkGuess(any());
            //comprobamos que la variable que guarda la fila HA AUMENTADO
            //ya que nuestro guessRow era valido al estar completo
        assertEquals(1,spyMasterMindUI.currentRow);
    }

    @Test
    void testTryToCheck_CorrectPaintingValidation(){
        //Given
            //Accedemos a la fila actual y simulamos input
        MasterMindUI.Circle[] fila = spyMasterMindUI.guessRows.get(0);
        for (MasterMindUI.Circle c : fila){
            c.setCircleColor(Color.BLUE);
        }
            //Cuando se llame a la logica checkGuess, debe devolver este resultado controlado(1negro,1blanco)
            //Con esto comprobamos si la ui rellena correctamente el array de blancas y negras
        when(mockLogic.checkGuess(any())).thenReturn(new MasterMindLogic.Result(1,1));
        //When
            //lo mismo, llamamos a la funcion que controla la lógica de pintado
        spyMasterMindUI.tryToCheck();
        //Then
            //accedemos al array de blancas/negras de la fila actual
        MasterMindUI.Circle[] colors = spyMasterMindUI.pinRows.get(0);
            //verificamos que la posicion 0 del array anterior es color negro
        assertEquals(Color.BLACK, colors[0].getColor());
            //verificamos que la posicion 1 del array anterior es color blanco
        assertEquals(Color.WHITE, colors[1].getColor());

    }

    /**TEST DE LA FUNCION showFrame()**/
    @Test
    void testShowFrame_Visible(){
        //When
        spyMasterMindUI.showFrame();
        //Then
        assertTrue(spyMasterMindUI.frame.isVisible());
        spyMasterMindUI.frame.dispose();
    }


}
