/**
 *
 * @author Natarajan em 12/09/2016
 */

package main

import (
	"fmt"
	"net"
	"os"
        "log"
        "database/sql"
        _ "github.com/lib/pq"

        "encoding/json"
)

type Pessoa struct {
        Id    int32  `json:"id"`
        Nome  string `json:"nome"`
        Idade int32  `json:"idade"`
}

type GrupoPessoas struct {
        Pessoas []Pessoa `json:"pessoas"`
}

func search(query string) string {
        var conninfo string = "host=localhost port=5432 dbname=pod_q5 sslmode=disable"

        db, err := sql.Open("postgres", conninfo)
        if err != nil {
                panic(err)
        }

        //rows, err := db.Query("SELECT * FROM pessoa")
        rows, err := db.Query(query)

        if err != nil {
                log.Fatal(err)
        }

        pessoas := make([]Pessoa, 3, 3)

        i := 0

        for rows.Next() {
                var nome string
                var id int32
                var idade int32
                if err := rows.Scan(&id, &nome, &idade); err != nil {
                        log.Fatal(err)
                }
                //fmt.Printf("%s tem %d anos\n", nome, idade)

                p := Pessoa{
                        Id:    id,
                        Nome:  nome,
                        Idade: idade,
                }

                pessoas[i] = p

                i++

                //pbytes, _ := json.Marshal(p)
                //s := string(pbytes)
                //fmt.Println(s)

        }

        if err := rows.Err(); err != nil {
                log.Fatal(err)
        }

        ps := GrupoPessoas{
                Pessoas: pessoas,
        }

        psbytes, _ := json.Marshal(ps)
        ss := string(psbytes)
        //fmt.Println(ss)
        return ss

}

func main() {
	service := ":12345"
	tcpAddr, err := net.ResolveTCPAddr("tcp", service)
	checkError(err)

	listener, err := net.ListenTCP("tcp", tcpAddr)

	checkError(err)

	fmt.Println("Start listening...")

	for {
		conn, err := listener.Accept()
		if err != nil {
			continue
		}

		fmt.Println("Connected!!")
		go handleClient(conn)
	}
}

func handleClient(conn net.Conn) {

	for {

		messageBuf := make([]byte, 1024)
		messageLen, err := conn.Read(messageBuf)

		checkError(err)

		message := string(messageBuf[:messageLen])
		fmt.Println("Received query: " + message)

		//message = message + " [from go server]"

		result := search(message)

		conn.Write([]byte(result))
	}
}

func checkError(err error) {
	if err != nil {
		fmt.Fprintf(os.Stderr, "Error: %s\n", err.Error())
		os.Exit(1)
	}
}
