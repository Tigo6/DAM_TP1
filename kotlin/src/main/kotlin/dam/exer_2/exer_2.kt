package dam.exer_2

import com.sun.tools.javac.main.Option
import java.util.Scanner
import kotlin.reflect.typeOf


/*
    2.2 Exercise 2
    Create a console-based calculator that allows users to perform various operations. Your
    implementation should incorporate the following Kotlin features:
    1. The when expression for handling different operations.
    2. Exceptions to handle potential errors (e.g., division by zero, invalid input).
    3. String templates and string formatting for a clear and structured output.


    Your calculator should support the following:
    1. Basic arithmetic operations: Addition, subtraction, multiplication, and division.
    2. Boolean operators: AND (&&), OR (||), NOT (!).
    3. Bitwise shift operators: Left shift (shl), right shift (shr).
    4. Show the results in decimal, hexadecimal, and boolean.

*/
enum class Operation {
    ADD,
    SUB,
    MULT,
    DIV,
    AND,
    OR,
    NOT,
    SHIFT_LEFT,
    SHIFT_RIGHT,
    QUIT
}
val operationsMap = mapOf(
    0 to Operation.ADD,
    1 to Operation.SUB,
    2 to Operation.MULT,
    3 to Operation.DIV,
    4 to Operation.AND,
    5 to Operation.OR,
    6 to Operation.NOT,
    7 to Operation.SHIFT_LEFT,
    8 to Operation.SHIFT_RIGHT,
    9 to Operation.QUIT
)


class Calculator{

    var scanner: Scanner;

    constructor(){
        this.scanner = Scanner(System.`in`)
    }

    fun extractNumbers(): IntArray {
        println("Selecione o primeiro número:")
        val num1 = scanner.nextLine().toIntOrNull()
            ?: throw IllegalArgumentException("Primeiro número inválido")

        println("Selecione o segundo número:")
        val num2 = scanner.nextLine().toIntOrNull()
            ?: throw IllegalArgumentException("Segundo número inválido")

        return intArrayOf(num1, num2)
    }

    fun extractBooleans(): BooleanArray {
        println("Selecione o primeiro valor (true/false):")
        val a = scanner.nextLine().toBooleanStrictOrNull()
            ?: throw IllegalArgumentException("Valor boolean inválido")

        println("Selecione o segundo valor (true/false):")
        val b = scanner.nextLine().toBooleanStrictOrNull()
            ?: throw IllegalArgumentException("Valor boolean inválido")

        return booleanArrayOf(a, b)
    }


    fun showResult(value: Int) {
        println("""
            Resultado:
            Decimal: $value
            Hexadecimal: ${value.toString(16)}
            """.trimIndent())
        }


    fun runEngine() {
        while (true) {
            println()
            println("=========================================")
            println("Por favor escolha a operação a realizar:")
            for ((key, value) in operationsMap) {
                println("Key $key - $value")
            }

            val input = scanner.nextLine().toIntOrNull()

            if (input == null || !operationsMap.containsKey(input)) {
                println("Por favor selecione uma opção válida")
                continue
            }

            val operation = operationsMap[input]

            when (operation) {
                Operation.ADD,
                Operation.SUB,
                Operation.MULT,
                Operation.DIV -> {
                    try {
                        val numbers = extractNumbers()
                        val a = numbers[0]
                        val b = numbers[1]

                        val result = calculate(operation, a, b)
                        showResult(result)

                    } catch (e: IllegalArgumentException) {
                        println("Erro: ${e.message}")
                    } catch (e: ArithmeticException) {
                        println("Erro matemático: ${e.message}")
                    }
                }

                Operation.NOT ->{
                    try {
                        println("Selecione o valor (true/false):")
                        val a = scanner.nextLine().toBooleanStrictOrNull()
                            ?: throw IllegalArgumentException("Valor boolean inválido")

                        println("Resultado: ${!a}")

                    } catch (e: IllegalArgumentException) {
                        println("Erro: ${e.message}")
                    }
                }
                Operation.AND,
                Operation.OR -> {

                    try {
                        val values = extractBooleans()
                        val a = values[0]
                        val b = values[1]

                        val result = calculateBoolean(operation, a, b)
                        println("Resultado: $result")

                    } catch (e: IllegalArgumentException) {
                        println("Erro: ${e.message}")
                    }

                }

                Operation.SHIFT_LEFT,
                Operation.SHIFT_RIGHT ->{
                    val numbers = extractNumbers()
                    val a = numbers[0]
                    val b = numbers[1]

                    val result = calculate(operation, a, b)

                    showResult(result)
                }

                Operation.QUIT -> {
                    println("A sair...")
                    return
                }

                else -> println("Operação não suportada")
            }
        }
    }

    fun calculate(op: Operation, a: Int, b: Int): Int {
        return when (op) {
            Operation.ADD -> a + b
            Operation.SUB -> a - b
            Operation.MULT -> a * b
            Operation.DIV -> {
                if (b == 0) throw ArithmeticException("Divisão por zero!")
                a / b
            }
            Operation.SHIFT_LEFT -> a shl b
            Operation.SHIFT_RIGHT -> a shr b


            else -> throw IllegalArgumentException("Operação não suportada")
        }
    }

    fun calculateBoolean(op: Operation, a: Boolean, b: Boolean): Boolean {
        return when (op) {
            Operation.AND -> a && b
            Operation.OR -> a || b
            Operation.NOT -> !a
            else -> throw IllegalArgumentException("Operação inválida")
        }
    }



}

fun main(){
    var calc = Calculator()
    calc.runEngine()
}