
Para criar o projeto caso não existente (ou sem egit):
 cli: 
 Dentro da pasta com o nome do projeto (Banksystem) 
 git clone https://github.com/$(User)/Banksystem.git

  $(User) = nome do repositório de vocês()

 No eclipse, com workspace diretorio pai do Banksystem:
 (p.ex: ../Banksystem ) 
 new java project ->  Name: Banksystem 
 
 Ele deve configurar automático, se houver alguma dependencia faltando um quickfix deve ser o suficiente.

Nos próximos casos : 
 é só utilizar 
 git pull 
 ou 
 git fetch
 git merge 

Novos arquivos 
 git add "path da raiz do diretório que fez o clone até o arquivo"

Para salvar localmente as alterações
 git commit -a -m "Comentário sobre a alteração"

Para dar upload nos commits feitos
 git push 

(tem o merge e outras coisas interessantes)
