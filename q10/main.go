package main

import (
        "sync"
        //"fmt"
        "fmt"
)

type Passenger struct {
        nome string
        pmutex *sync.Mutex
}

func entra(Passenger p) {

}

func espera(Passenger p){

}

func sai(Passenger p){

}

func volta(Passenger p){

}

func main() {

        //var v = Passenger{"p1", &sync.Mutex{}}
        //v.pmutex.Lock();
        //fmt.Printf("Resultado de x:  %v", v.nome)
        //v.pmutex.Unlock();

        var passengers [2]Passenger;

        for i := 0; i < len(passengers); i++ {
                passengers[i] = Passenger{fmt.Sprintf("Pass nº %d", i), &sync.Mutex{}}
        }

        for i := 0; i < len(passengers); i++ {
                fmt.Printf("Nome: %v\n", passengers[i].nome)
        }

}
