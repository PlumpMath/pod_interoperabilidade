#### Detalhes sobre a questão 15:

O Heroku disponibiliza uma cota limitada de projetos gratuitos, que no meu caso já conseguiu ser estourada por conta dos demais questões da lista (+ 1 projeto de outra disciplina) - ao que talvez nenhum de nós tenha se atentado. 

Todos os módulo (accountA, accountB, accountC, txcoord e accontManager) já estavam configurados para rodar no heroku, mas só vim perceber o estouro da cota quando estava inserindo a segunda conta.

Por este motivo, não foi possível realizar a implantação a contento, tal como a questão solicita.

##### Sobre as transações

Percebemos que a segunda transação (R$ 500 da conta C para a conta B) não pode ser realizada por falta de saldo na conta.

Neste caso, Uma exceção é lançada e transação realizar um rollback, conforme configurado no sistema.

As transações seguintes são completadas desconsiderando a citada acima.
