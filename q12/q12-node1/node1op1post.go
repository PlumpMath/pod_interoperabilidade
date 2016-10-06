package main

import (
        "net/http"
        //"io/ioutil"
)

func main() {

        println("Sending post do op1 on node3...")
        resp, err := http.Post("http://podq12node3.appspot.com/op1", "", nil)

        if err != nil {
                // handle error
        }

        println("Sent")

        defer resp.Body.Close()
        //body, err := ioutil.ReadAll(resp.Body)

}