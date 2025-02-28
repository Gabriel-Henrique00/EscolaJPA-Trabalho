# Sistema de Gerenciamento de Alunos com JPA

## 📌 Descrição
Este projeto implementa um sistema de gerenciamento de alunos utilizando **Java Persistence API (JPA)** para persistência de dados. O sistema permite realizar operações como cadastro, consulta e atualização de informações dos alunos.

## ✨ Funcionalidades
- 📌 **Cadastro** de alunos com nome, RA, e-mail e notas.
- 🔍 **Consulta** de alunos pelo ID ou nome.
- ✏️ **Atualização** de dados de um aluno.
- 📋 **Listagem** de todos os alunos cadastrados.
- 🛠️ **Testes** unitários para validar funcionalidades principais.

## 📂 Estrutura do Projeto
```
projeto-jpa/
│-- src/main/java/
│   ├── model/            # Contém a classe Aluno
│   ├── dao/              # Contém a classe AlunoDao
│   ├── util/             # Contém JPAUtil para gestão do EntityManager
│   ├── testes/             # Contém o arquivo principal com menu interativo
│-- persistence.xml       # Configuração do banco de dados e Hibernate
│-- README.md             # Documentação do projeto
```



## 📝 Tecnologias Utilizadas
- **Java 11+**
- **JPA/Hibernate**
- **JUnit 5** (para testes)
- **Maven**


