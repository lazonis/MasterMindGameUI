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
        doNothing().when(spyMasterMindUI).showMessage(anyString());
    }

    /**TEST varios DE LA FUNCIÓN tryToCheck()**/
    @Test
    void testTryToCheck_IncompleteGuessArray(){
        //Given

        //When
        spyMasterMindUI.tryToCheck();

        //Then
        verify(spyMasterMindUI).showMessage(contains("Fill all slots"));

        verify(mockLogic, never()).checkGuess(any());

        assertEquals(0,spyMasterMindUI.currentRow, "Rellena los colores");
    }

    @Test
    void testTryToCheck_CompleteGuessArray_AdvanceRow(){
        //Given
        MasterMindUI.Circle[] fila = spyMasterMindUI.guessRows.get(0);

        for (MasterMindUI.Circle c : fila){
            c.setCircleColor(Color.RED);
        }

        when(mockLogic.checkGuess(any())).thenReturn(new MasterMindLogic.Result(0,0));

        //When
        spyMasterMindUI.tryToCheck();
        //Then
        verify(mockLogic).checkGuess(any());
        assertEquals(1,spyMasterMindUI.currentRow);
    }

    @Test
    void testTryToCheck_CorrectPaintingValidation(){
        //Given
        MasterMindUI.Circle[] fila = spyMasterMindUI.guessRows.get(0);
        for (MasterMindUI.Circle c : fila){
            c.setCircleColor(Color.BLUE);
        }
        when(mockLogic.checkGuess(any())).thenReturn(new MasterMindLogic.Result(1,1));
        //When
        spyMasterMindUI.tryToCheck();
        //Then
        MasterMindUI.Circle[] colors = spyMasterMindUI.pinRows.get(0);

        assertEquals(Color.BLACK, colors[0].getColor());
        assertEquals(Color.WHITE, colors[1].getColor());

    }


}
