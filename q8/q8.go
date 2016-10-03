/**
 *
 * @author Natarajan em 12/09/2016
 */

package main

import (
        "fmt"
        "time"
        "sync"
)

var x = 0
var y = 0
var wg sync.WaitGroup
var mutex = &sync.Mutex{}

func sum(){

        mutex.Lock()
        x = y + 1
        y = x + 1
        mutex.Unlock()

}

func threadSum() {
        sum()
}


func main() {

        wg.Add(2)

        for i := 0; i < 10; i++ {

                go threadSum()

                go threadSum()

                //fmt.Println(x)
        }

        wg.Done()

        time.Sleep(10 * time.Microsecond)
        fmt.Printf("Resultado de x:  %v", x)


}



///**
// *
// * @author Natarajan em 12/09/2016
// */
//
//package main
//
//import (
//	"fmt"
//)
//
//
//
//func sum(x, y int, cx, cy chan int){
//
//
//        x = y + 1
//        y = x + 1
//
//        cx <- x
//        cy <- y
//
//}
//
//func threadA(x, y int, cx, cy chan int) {
//        sum(x, y, cx, cy)
//}
//
//func threadB(x, y int, cx, cy chan int) {
//        sum(x, y, cx, cy)
//}
//
//func main() {
//
//        var x = 0
//        var y = 0
//
//        cx := make(chan int)
//        cy := make(chan int)
//
//        //cx <- x
//        //cy <- y
//
//
//        for i := 0; i < 10; i++ {
//
//                go threadA(x, y, cx, cy)
//
//                go threadB(x, y, cx, cy)
//
//                fmt.Println(<-cx)
//        }
//
//}
