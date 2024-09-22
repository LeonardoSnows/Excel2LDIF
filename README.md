# Descrição
O Excel2LDIF é uma aplicação Java que integra dados de diferentes fontes. 
Ele lê informações de um arquivo Excel, realiza requisições a uma API de CEP para obter detalhes do endereço, consulta um diretório LDAP para verificar ou buscar informações adicionais e, finalmente, gera um arquivo LDIF com os dados consolidados.

# Funcionalidades
- Leitura de informações de um arquivo Excel.
- Consulta de endereço a partir de CEP utilizando uma API de CEP.
- Pesquisa de dados em um diretório LDAP.
- Geração de um arquivo LDIF com as informações obtidas.

# Tecnologias Utilizadas
- Java 11
- Apache POI (manipulação de arquivos Excel)
- JNDI (integração com LDAP)
- Jackson ou Gson (manipulação de JSON)
- SLF4J + Logback (logging)

> [!NOTE]
> Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests para melhorias.

# Configuracoes
1.Clone o repositório:
``` sh 
  git clone https://github.com/LeonardoSnows/Excel2LDIF.git
```

2.Configure o arquivo application.properties: No diretório src/main/resources, configure o arquivo application.properties com as seguintes informações:
``` sh   
  # Configuração do LDAP
  ldapURL=ldap://localhost:1389
  ldapUsernameCN=cn=admin,dc=example,dc=org
  ldapPassword=adminpassword
```
