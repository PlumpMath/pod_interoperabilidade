/**
 *
 * @author Natarajan em 12/09/2016
 */

package main

import (
	"fmt"
        //"time"
)

var value = "B"

func setValue(){
        value = "A"
}

func setter(){
        setValue()
}


func main() {

        go setter()

        for i := 0; i < 10; i++ {
                fmt.Print(value)
        }

}

/*

Devido ao alto desempenho da concorrência em Go lang - e também pelo fato da execução utilizar uma variável utilizada
ao mesmo tempo pela thread/goroutine "go setter()", o valor impresso na maioria das vezes é "BAAAAAAAAA".

Isso ocorre porque o bloco for inicia sua execução em curto espaço de tempo diferente da execução da goroutine que altera
a variável 'value', fazendo com o que o valor de B apareça na primeira iteração do for.

No entanto, em testes locais, foi possível inclusive obter a impressão "BBBBBBBBBB" - uma prova de que a execução do for
se deu num espaço de tempo tão curto que não foi possível auferir a mudança da variável.

O programa alternativo "q3-b.go" mostra que um pequeno timer colocado logo após a chamada da goroutine "setter()" foi
suficiente (levando em consideração máquina local) para obter apenas o valor "AAAAAAAAAA".

*/