package com.game;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class MasterMindLogicTest {

    //Instanciamos la clase test
    // con las variables de la clase a testear,
    // compartidas para cada test
    Color[] palette = {new Color(240, 17, 17), new Color(104, 227, 70), new Color(45, 64, 189), new Color(191, 83, 40), new Color(242, 135, 34), new Color(204, 94, 235)};
    String[] labels = {"R", "V", "A", "M", "N", "L"};
    int secretLength = 4;

    Color[] fixedSecret = {new Color(240, 17, 17),new Color(240, 17, 17), new Color(104, 227, 70),new Color(104, 227, 70)};

    /*TEST para la función generateSecret*/
    @Test
    public void testGenerateSecret(){
        //Given
        MasterMindLogic game = new MasterMindLogic(palette,secretLength,labels);

        //When (llamamos al método original)
        Color[] secret = game.generateSecret(secretLength);

        //Then
        //Comprueba que no es nulo
        assertNotNull(secret, "Resultado aleatorio de Colores[] no puede ser nulo");
        //Comprueba que no tiene más longitud de la permitida
        assertEquals(secretLength, secret.length,
                "Longitud del Colores[] debe ser " + secretLength);
        //Comprueba que contiene colores de la paleta
        List<Color> listaPaleta = Arrays.asList(palette);
        for (Color color : secret){
            assertTrue(listaPaleta.contains(color),
                    "El secreto Colores[] generado tiene colores que no están en la paleta");
        }
    }

    /*TEST para la función checkGuess*/
    /*TODO ACIERTOS POSICIÓN + VALOR*/
    @Test
    public void checkBlacks(){
        //Given -> inicializamos el juego con la paleta
            // y un secret fijo para comprobar la lógica matemática del checkGuess
            MasterMindLogic game = new MasterMindLogic(palette,fixedSecret,labels);
        //When
            //el mismo que el definido arriba
            Color [] userInput = {new Color(240, 17, 17),new Color(240, 17, 17), new Color(104, 227, 70),new Color(104, 227, 70)};
            //Verifica la primera condición del if en checkGuess
            MasterMindLogic.Result resultTest = game.checkGuess(userInput);
        //Then
            //este test comprueba que cuando todas coincidan, devuelva el resultado correcto
            //así demuestra que la lógica de comprobación de negras funciona perfectamente
            assertEquals(4, resultTest.blacks, "Resultado no puede ser diferente a 4 (todo aciertos)");
            assertEquals(0, resultTest.whites, "No puede haber valores blancos");
    }
    /*TODOS LOS VALORES EXISTEN PERO NO COINCIDEN EN POSICIÓN*/
    @Test
    public void checkWhites(){
        //Given
            MasterMindLogic game = new MasterMindLogic(palette,fixedSecret,labels);
        //When
            //Mismos colores que fixedScreen pero orden invertido -> todas blancas
            Color [] userInput = {new Color(104, 227, 70),new Color(104, 227, 70),new Color(240, 17, 17),new Color(240, 17, 17)};
            //Verifica el funcionamiento del else en checkGuess
            MasterMindLogic.Result resultTest = game.checkGuess(userInput);
        //Then
            assertEquals(0,resultTest.blacks, "No puede haber valores negros");
            assertEquals(4,resultTest.whites, "Resultado no puede ser diferente a 4,todas son blancas");
    }
    /*NINGÚN ACIERTO NI EN VALOR NI EN POSICIÓN*/
    @Test
    public void noMatch(){
        //Given
        MasterMindLogic game = new MasterMindLogic(palette,fixedSecret,labels);
        //When
        Color [] userInput = {new Color(45, 64, 189), new Color(191, 83, 40),new Color(45, 64, 189), new Color(191, 83, 40)};
        MasterMindLogic.Result resultTest = game.checkGuess(userInput);
        //Then
        assertEquals(0,resultTest.blacks, "No puede haber valores negros");
        assertEquals(0,resultTest.whites, "No puede haber valores blancos");
    }

    /*TEST para la función checkGuess*/
}
