package dam.exer_1

import java.util.Scanner

/*
* 2.1 Exercise 1
Create and initialize an integer array with the first 50 perfect squares (12,22,32,...,502)
dynamically, using the following:
• a) Using IntArray constructor;
• b) Using a range and map();
• c) Using Array with constructor;
*
*/

const val ARR_SIZE: Int = 50;

fun exercise1(option: Int?){
    var arr: IntArray? = null;
    if (option == 1 ){
        //Pode receber uma funcao como parametro de inicialização
        arr = IntArray(ARR_SIZE) { a -> (a + 1) * (a + 1) }
        println(arr.contentToString())
    }
    if (option == 2 ){
        //Para valores entre 1 e ARR SIZE fazer uma lista dos quadrados. No fim transformar em array
        arr = (1..ARR_SIZE).map{ num -> num * num}.toIntArray()
    }

    if (option == 3 ){
        val arr = Array<Int>(ARR_SIZE, {i -> (i+1) * (i+1)})
        print(arr.toIntArray().contentToString())
    }
    println(arr.contentToString())
}
fun main(){
    val scanner = Scanner(System.`in`)
    while (true){
        println("Exercício 1 || Para terminar selecione '0'")
        println("Escolha a uma das opções:")
        println("1) Com IntArray constructor")
        println("2) Com range e map():")
        println("3) Com Array with constructor:")
        val num: Int? = scanner.nextLine().toIntOrNull()
        if(num == null || num > 3){
            println("Input Inválido")
        }
        if (num == 0){
            println("A fechar ...")
            return;
        }
        exercise1(num)
    }
}

