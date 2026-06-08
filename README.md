# 🚀 AstroColony Logistics API

Sistema desenvolvido para gerenciamento de missões espaciais, tripulantes, diretores de voo e controle de recursos alimentares. A aplicação foi construída utilizando Java 21, Spring Boot, Oracle Database, Docker e Azure Cloud, seguindo boas práticas de desenvolvimento Back-End e arquitetura em camadas.

---

# 📋 Objetivo do Projeto

A AstroColony Logistics API tem como objetivo fornecer uma solução para gerenciamento logístico de missões espaciais, permitindo:

- Cadastro e gerenciamento de missões
- Controle de tripulantes
- Controle de diretores de voo
- Gerenciamento de alimentos
- Controle de status de missões
- Persistência de dados em banco Oracle
- Documentação automática via Swagger

---

# 🏛 Arquitetura da Solução

```text
┌─────────────┐
│   Usuário   │
└──────┬──────┘
       │ HTTP/HTTPS
       ▼
┌───────────────────────────────┐
│ Azure Linux VM                │
│                               │
│ Docker                        │
│ ┌───────────────────────────┐ │
│ │ Spring Boot API           │ │
│ └───────────────────────────┘ │
└───────────────┬───────────────┘
                │
                ▼
      ┌───────────────────┐
      │ Oracle Database   │
      └───────────────────┘
```

![img.png](img.png)

---

# 🛠 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Maven
- Oracle Database
- Docker
- Docker Compose
- Swagger / OpenAPI
- Azure Virtual Machine
- Linux Ubuntu

---

# 📂 Estrutura do Projeto

```text
.
├── src
│   ├── main
│   └── test
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

# ⚙️ Pré-requisitos

Para executar o projeto localmente é necessário possuir:

- Java 21
- Maven 3.9+
- Docker
- Docker Compose
- Oracle Database (caso não utilize o container)

Verificar versões instaladas:

```bash
java -version
mvn -version
docker --version
docker compose version
```

---

# 🔧 Configuração das Variáveis de Ambiente

A aplicação utiliza as seguintes variáveis:

| Variável | Descrição |
|-----------|-----------|
| USER_DB_ORACLE | Usuário Oracle |
| PASSWORD_DB_ORACLE | Senha Oracle |

Linux:

```bash
export USER_DB_ORACLE=system
export PASSWORD_DB_ORACLE=senha
```

Windows PowerShell:

```powershell
$env:USER_DB_ORACLE="system"
$env:PASSWORD_DB_ORACLE="senha"
```

---

# ▶️ Executando Localmente

## Clonar o repositório

```bash
git clone <URL_DO_REPOSITORIO>
```

Entrar na pasta:

```bash
cd spring
```

---

## Compilar o projeto

```bash
mvn clean package
```

---

## Executar a aplicação

Linux:

```bash
java -jar target/*.jar
```

Windows:

```powershell
java -jar target\*.jar
```

---

# 🐳 Executando com Docker

## Build da imagem

```bash
docker build -t astrocolony-api .
```

---

## Executar container

```bash
docker run -d \
-p 8080:8080 \
--name astrocolony-api \
astrocolony-api
```

---

## Verificar execução

```bash
docker ps
```

---

## Ver logs

```bash
docker logs -f astrocolony-api
```

---

# 🐳 Executando com Docker Compose

Subir containers:

```bash
docker compose up -d --build
```

---

Verificar containers:

```bash
docker ps
```

---

Parar containers:

```bash
docker compose down
```

---

Remover containers e volumes:

```bash
docker compose down -v
```

---

# 📖 Swagger

Após a aplicação estar em execução:

```text
http://localhost:8080/swagger-ui/index.html
```

Ou em ambiente Azure:

```text
http://IP_DA_VM:8080/swagger-ui/index.html
```

---

# 📡 Principais Endpoints

## Missões

| Método | Endpoint |
|----------|----------|
| GET | /missoes |
| GET | /missoes/{id} |
| POST | /missoes |
| PUT | /missoes/iniciar-missao/{id} |
| DELETE | /missoes/{id} |

---

## Tripulantes

| Método | Endpoint |
|----------|----------|
| POST | /tripulantes |
| POST | /tripulantes/login |
| GET | /tripulantes/buscar-por-pin/{pin} |
| GET | /tripulantes/buscar-por-status/{status} |
| PUT | /tripulantes/mudar-status |
| DELETE | /tripulantes/deletar-por-pin/{pin} |

---

## Diretor de Voo

| Método | Endpoint |
|----------|----------|
| GET | /diretor-voo |
| POST | /diretor-voo |
| POST | /diretor-voo/login |
| PUT | /diretor-voo/alterar-status/{id}-{status} |

---

## Alimentos

| Método | Endpoint |
|----------|----------|
| GET | /alimentos |
| POST | /alimentos |
| PUT | /alimentos |
| DELETE | /alimentos |

---

# ☁️ Deploy na Azure

## 1. Login Azure

```powershell
az login
```

---

## 2. Criar Resource Group

```powershell
az group create `
--name rg-astrocolony `
--location brazilsouth
```

---

## 3. Criar Máquina Virtual Linux

```powershell
az vm create `
--resource-group rg-astrocolony `
--name vm-astrocolony `
--image Ubuntu2204 `
--admin-username azureuser `
--generate-ssh-keys
```

---

## 4. Liberar Porta SSH

```powershell
az vm open-port `
--resource-group rg-astrocolony `
--name vm-astrocolony `
--port 22
```

---

## 5. Liberar Porta da API

```powershell
az vm open-port `
--resource-group rg-astrocolony `
--name vm-astrocolony `
--port 8080
```
---

## Utilizar ps1

```powershell
.\azure-vm-linux.ps1
```

---

## 6. Conectar na VM

```bash
ssh azureuser@IP_DA_VM
```

---

# 🐧 Configuração da VM Linux

Atualizar pacotes:

```bash
sudo apt update
sudo apt upgrade -y
```

---

# 🐳 Instalação do Docker

Instalar Docker:

```bash
sudo apt install docker.io docker-compose-v2 -y
```

---

Adicionar usuário ao grupo Docker:

```bash
sudo usermod -aG docker $USER
```

---

Aplicar alterações:

```bash
newgrp docker
```

---

Validar instalação:

```bash
docker --version
```

---

Validar Docker Compose:

```bash
docker compose version
```

---

# 📥 Publicando o Projeto na VM

## Opção 1 - Git Clone

Instalar Git:

```bash
sudo apt install git -y
```

---

Clonar repositório:

```bash
git clone https://github.com/2TDSPO-JPG/GlobalSolution-Cloud.git
```

Entrar no projeto:

```bash
cd spring
```

---

## Opção 2 - SCP

No computador local:

```powershell
scp -r .\spring azureuser@IP_DA_VM:/home/azureuser/
```

---

# 🚀 Executando a Aplicação na VM

Entrar na pasta do projeto:

```bash
cd spring
```

---

Subir aplicação:

```bash
docker compose up -d --build
```

---

Verificar containers:

```bash
docker ps
```

---

Ver logs:

```bash
docker logs -f app-rm565824
```

---

# 🔍 Testando a Aplicação

Testar API:

```bash
curl http://localhost:8080/swagger-ui/index.html
```

---

Acessar pelo navegador:

```text
http://IP_DA_VM:8080/swagger-ui/index.html
```

---

# 🛑 Parando a Aplicação

```bash
docker compose down
```

---

# 🗑️ Removendo Ambiente Completo

```bash
docker compose down -v
docker system prune -a -f
```

---

# ☁️ Removendo Recursos da Azure

Remover apenas a VM:

```powershell
az vm delete `
--resource-group rg-astrocolony `
--name vm-astrocolony
```

---

Remover todo o ambiente (VM, discos, rede, IP público e demais recursos):

```powershell
az group delete `
--name rg-astrocolony `
--yes `
--no-wait
```

---

# 👨‍💻 Desenvolvedor

Pedro Ferreira | RM565824 | 2TDSPO

---

# 📄 Licença

Projeto desenvolvido para fins acadêmicos e demonstração de conhecimento técnico.