package main

import (
        "net/http"
        //"io/ioutil"
)

func main() {
        println("Sending post do op2 on node2...")
        resp, err := http.Post("http://q12node2.herokuapp.com/op2", "", nil)

        if err != nil {
                // handle error
        }

        defer resp.Body.Close()
        //body, err := ioutil.ReadAll(resp.Body)

}