/**
 *
 * @author Natarajan em 12/09/2016
 */

package main

import (
	"fmt"
        "time"
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

        time.Sleep(10 * time.Microsecond)

        for i := 0; i < 10; i++ {

                fmt.Print(value)
        }


}


/*

O programa alternativo "q3-b.go" mostra que um pequeno timer colocado logo após a chamada da goroutine "setter()" foi
suficiente (levando em consideração máquina local) para obter apenas o valor "AAAAAAAAAA".

*/