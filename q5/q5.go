/**
 *
 * @author Natarajan em 12/09/2016
 */

package main

import (
	"fmt"
        "time"

)

var x = 0
var y = 0

func sum(){

        x = y + 1
        y = x + 1
}

func threadA() {
        sum()
}

func threadB() {
        sum()
}

func main() {



        for i := 0; i < 10; i++ {

                go threadA()

                go threadB()

                fmt.Println(x)
        }

        time.Sleep(10 * time.Microsecond)
        fmt.Printf("Resultado de x:  %v", x)


}
